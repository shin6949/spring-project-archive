package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/main")
    public String main(Model model) {
        // 로그인 정보 model에 추가
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return "main";
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("loginedId", customUserDetails.getId());
        model.addAttribute("loginedName", customUserDetails.getName());

        return "main";
    }

    @GetMapping("/login")
    public String login() {
        return "members/login";
    }
}