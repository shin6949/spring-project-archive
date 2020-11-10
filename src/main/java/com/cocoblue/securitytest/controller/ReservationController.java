package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Customer;
import com.cocoblue.securitytest.dto.Reservation;
import com.cocoblue.securitytest.exception.TypeError;
import com.cocoblue.securitytest.service.CustomerService;
import com.cocoblue.securitytest.service.DepartmentService;
import com.cocoblue.securitytest.service.DoctorService;
import com.cocoblue.securitytest.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final DepartmentService departmentService;
    private final DoctorService doctorService;
    private final CustomerService customerService;

    public ReservationController(ReservationService reservationService, DepartmentService departmentService, DoctorService doctorService, CustomerService customerService) {
        this.reservationService = reservationService;
        this.departmentService = departmentService;
        this.doctorService = doctorService;
        this.customerService = customerService;
    }

    @GetMapping(path = "/getall")
    @ResponseBody
    public Map<String, Object> getAllConfirmedReservation() {
        List<Reservation> reservations = reservationService.getAllConfirmedReservation();

        return reservationsProcessing(reservations);
    }

    @GetMapping(path = "/getallbydoctor/{doctorNo}")
    @ResponseBody
    public Map<String, Object> getAllConfirmedReservationByDoctorNo(@PathVariable String doctorNo) {
        long doctorNoLong;

        // 주소에 잘못 된 값을 준 경우 400 Error을 줌.
        try {
            doctorNoLong = Long.parseLong(doctorNo);
        } catch(Exception e) {
            throw new TypeError();
        }

        List<Reservation> reservations = reservationService.getAllConfirmedReservationByDoctorNo(doctorNoLong);

        return reservationsProcessing(reservations);
    }

    private Map<String, Object> reservationsProcessing(List<Reservation> reservations) {
        Map<String, Object> result = new HashMap<String, Object>();

        if(reservations.size() == 0) {
            result.put("count", 0);
            result.put("reservations", null);

        } else {
            result.put("count", reservations.size());
            result.put("reservations", reservations);
        }

        return result;
    }

    @RequestMapping(path = "")
    public String makeReservation(Model model) {
        model.addAttribute("departments", departmentService.getAllDepartment());
        addLoginImf(model);

        return "reservation/make";
    }

    private void addLoginImf(Model model) {
        Customer customer = customerService.getLoginUser();

        if(customer == null) {
            return;
        }

        model.addAttribute("loginCno", customer.getCno());
        model.addAttribute("loginName", customer.getName());
    }

    @PostMapping(path = "/availabledate")
    @ResponseBody
    public Map<String, Object> getAvailableDate() {
        Map<String, Object> result = new HashMap<String, Object>();

        return result;
    }
}
