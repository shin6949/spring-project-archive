package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Customer;
import com.cocoblue.securitytest.dto.Department;
import com.cocoblue.securitytest.dto.Doctor;
import com.cocoblue.securitytest.dto.Reservation;
import com.cocoblue.securitytest.exception.TypeError;
import com.cocoblue.securitytest.service.CustomerService;
import com.cocoblue.securitytest.service.DepartmentService;
import com.cocoblue.securitytest.service.DoctorService;
import com.cocoblue.securitytest.service.ReservationService;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public ReservationController(ReservationService reservationService, DepartmentService departmentService,
                                 DoctorService doctorService, CustomerService customerService) {
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

        List<Reservation> reservations = reservationService.getAllConfirmedReservationByDoctorNo(doctorNoLong, LocalDateTime.now());

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
        model.addAttribute("dates", reservationService.getAvailableDate());
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
        result.put("availableDateList", reservationService.getAvailableDate());

        return result;
    }

    @RequestMapping(path = "/getdoctorbydepartment")
    @ResponseBody
    public List<Doctor> getAvailableDoctor(@RequestParam("departmentNo") String departmentNo,
                                          @RequestParam("departmentText") String departmentText) {

        Department department = departmentService.getDepartmentByName(departmentText);

        // select value, text를 강제로 수정한 경우 에러 표출
        if(Long.parseLong(departmentNo) != department.getDno() || !departmentText.equals(department.getName())) {
            throw new TypeError();
        }

        return doctorService.getAllDoctorsByDepartCode(Long.parseLong(departmentNo));
    }

    @RequestMapping(path = "gettimebydoctorno")
    @ResponseBody
    public ArrayList<String> getAvailableTime(@RequestParam("doctorNo") String doctorNo,
                                            @RequestParam("doctorName") String doctorName,
                                            @RequestParam("selectDate") String selectDate) {
        Doctor doctor;

        // 주소에 잘못 된 값을 준 경우 400 Error을 줌.
        try {
            doctor = doctorService.getDoctorByNo(Long.parseLong(doctorNo));
        } catch(Exception e) {
            throw new TypeError();
        }

        // select value, text를 강제로 수정한 경우 에러 표출
        if(Long.parseLong(doctorNo) != doctor.getDoctorNo() || !doctor.getName().equals(doctorName)) {
            throw new TypeError();
        }

        return (ArrayList<String>) reservationService.configureAvailableTime(selectDate, doctor);
    }

    @PostMapping(path = "makereservation", produces = "application/x-www-form-urlencoded; charset=utf8")
    public void makeReservation(@RequestBody MultiValueMap<String, String> data, HttpServletResponse response) throws IOException {
        // Form에서 받은 시간 데이터를 LocalDateTime으로 캐스팅함.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime reservationTime = LocalDateTime.parse(data.get("date").get(0) + " " + data.get("time").get(0), formatter);

        // 현재 로그인 중인 계정의 정보를 받음.
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 예약 구성
        Reservation reservation = new Reservation(reservationTime, data.get("doctor").get(0), data.get("symptom").get(0), customUserDetails.getCno(), true);

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            if(reservationService.makeReservation(reservation)) {
                out.println("<script>alert('예약 성공'); location.href='/main';</script>");
            } else {
                out.println("<script>alert('예약에 실패했습니다.'); location.href='/reservation/';</script>");
            }
        // 같은 시간에 동시에 예약한 경우
        } catch (DuplicateKeyException duplicateKeyException) {
            out.println("<script>alert('이미 중복된 예약이 있습니다.'); location.href='/reservation/';</script>");
        } catch (Exception exception) {
            out.println("<script>alert('서버 오류로 예약이 진행되지 않았습니다.'); location.href='/reservation/';</script>");
        }

        out.flush();
    }
}
