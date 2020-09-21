package com.cocoblue.securitytest.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @RequestMapping("/main")
    @ResponseBody
    public String main() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal().toString().equals("anonymousUser")) {
            System.out.println(auth.getPrincipal().toString());
        } else {
            System.out.println(auth.toString());
            System.out.println(auth.getName());
            System.out.println(auth.getDetails());
        }

        return "main page";
    }

    @GetMapping("/login")
    public String login() {
        return "members/loginform";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/test")
    @ResponseBody
    public String testPage() {
        return "TEST PAGE";
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping("/securepage")
    @ResponseBody
    public String securitypage() {
        return "secure page";
    }
}