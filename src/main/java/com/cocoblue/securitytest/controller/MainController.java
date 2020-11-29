package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Customer;
import com.cocoblue.securitytest.dto.ReservationView;
import com.cocoblue.securitytest.service.CustomerService;
import com.cocoblue.securitytest.service.ReservationService;
import com.cocoblue.securitytest.service.ReservationViewService;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MainController {
    private final CustomerService customerService;
    private final ReservationViewService reservationViewService;
    private final ReservationService reservationService;

    public MainController(CustomerService customerService, ReservationViewService reservationViewService, ReservationService reservationService) {
        this.customerService = customerService;
        this.reservationViewService = reservationViewService;
        this.reservationService = reservationService;
    }

    @RequestMapping("/main")
    public String main(Model model) {
        // 로그인 정보 model에 추가
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "main";
        }

        Customer currentUser = customerService.getLoginUser();

        model.addAttribute("loginedId", currentUser.getId());
        model.addAttribute("loginedName", currentUser.getName());

        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "members/login";
    }

    @RequestMapping("/mypage")
    public String mypage(Model model, @RequestParam(name = "page", required = false) String page) {
        // 로그인 정보 model에 추가
        if(page == null){
            page = "1";
        }

        Customer currentUser = customerService.getLoginUser();
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ReservationView> reservation = reservationViewService.getAllReservationByCno(customUserDetails.getCno(),Integer.parseInt(page));

        model.addAttribute("pagesCount",getTotalPage(reservationService.getReservationCount(customUserDetails.getCno())));
        model.addAttribute("nowPage",Integer.parseInt(page));
        model.addAttribute("loginedId", currentUser.getId());
        model.addAttribute("loginedName", currentUser.getName());
        model.addAttribute("reservation",reservation);

        return "mypage";
    }

    @PostMapping("/cancelreservation")
    @ResponseBody
    public boolean cancelReservation(HttpServletRequest request){
        long rno= Long.parseLong(request.getParameter("rno"));

        return reservationService.cancelReservation(rno);
    }

    private long getTotalPage(long reservationCount){
        if(reservationCount % 4 == 0){
            return reservationCount / 4;
        } else {
            return reservationCount / 4 + 1;
        }
    }
}