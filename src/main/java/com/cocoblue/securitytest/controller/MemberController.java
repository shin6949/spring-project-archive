package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Member;
import com.cocoblue.securitytest.service.MemberService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/members")
public class MemberController {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    public MemberController(MemberService memberService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/loginform")
    public String loginform() {
        return "members/loginform";
    }

    @GetMapping("/loginerror")
    public String loginerror(@RequestParam("login_error") String loginError) {
        return "members/loginerror";
    }

    @GetMapping("/joinform")
    public String joinform() {
        return "members/joinform";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberService.addMember(member, false);
        return "redirect:/members/welcome";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "members/welcome";
    }

}