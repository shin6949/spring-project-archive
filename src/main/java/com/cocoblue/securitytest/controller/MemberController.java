package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Customer;
import com.cocoblue.securitytest.service.CustomerService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/members")
public class MemberController {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    public MemberController(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/loginerror")
    public String loginerror(@RequestParam("login_error") String loginError) {
        return "members/loginerror";
    }

    @GetMapping("/register")
    public String register() {
        return "members/register";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerService.addMember(customer, false);
        return "redirect:/main";
    }

    @RequestMapping(value = "/emailCheck", method = RequestMethod.POST)
    @ResponseBody
    public String emailCheck(Customer customer) throws Exception {
        if(customerService.checkEmail(customer.getId())) {
            // 중복된 이메일이 존재하지 않으면 true
           return "true";
        } else {
            return "false";
        }
    }
}