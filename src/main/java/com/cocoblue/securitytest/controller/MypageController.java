package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Reservation;
import com.cocoblue.securitytest.dto.ReservationView;
import com.cocoblue.securitytest.service.CustomerService;
import com.cocoblue.securitytest.service.ReservationService;
import com.cocoblue.securitytest.service.ReservationViewService;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MypageController {
    CustomerService customerService;
    ReservationViewService reservationViewService;
    public MypageController(CustomerService customerService,ReservationViewService reservationViewService) {
        this.customerService = customerService;
        this.reservationViewService = reservationViewService;
    }
    @RequestMapping("/mypage")
    public String mypage(Model model) {
        // 로그인 정보 model에 추가
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "mypage";
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ReservationView> reservation = reservationViewService.getAllConfiremdReservationByCno(customUserDetails.getCno());

        model.addAttribute("loginedId", customUserDetails.getId());
        model.addAttribute("loginedName", customerService.getCustomerByCno(customUserDetails.getCno()).getName());
        model.addAttribute("reservation",reservation);
        return "mypage";
    }





}
