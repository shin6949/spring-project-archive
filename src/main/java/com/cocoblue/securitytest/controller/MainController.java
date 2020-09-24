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

        return "main page";
    }

    @GetMapping("/login")
    public String login() {
        return "members/login";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/test")
    @ResponseBody
    public String testPage() {
        return "TEST PAGE";
    }

    @RequestMapping("/securepage")
    @ResponseBody
    public String securitypage() {
        return "secure page";
    }
}