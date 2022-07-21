package me.cocoblue.twitchoauth2sample.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class MainController {
    @GetMapping({"/", "/main"})
    public String getMain(@AuthenticationPrincipal OAuth2User oAuth2User) {
        // 로그인이 완료되었다면, null 값이 아닌 값이 나옴.
        log.info("로그인 된 유저:  " + oAuth2User);

        return "main";
    }

    // 제대로 로그인이 되었는지 확인하는 요청
    @GetMapping("/check")
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseBody
    public String getCheck() {
        return "Authorized!";
    }
}
