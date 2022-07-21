package me.cocoblue.twitchoauth2sample.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequestMapping("/login/oauth2/code")
public class OAuth2Controller {
    @RequestMapping("/twtich")
    public String getTwitchAuth(@RequestBody String body) {
        return body;
    }

    @RequestMapping("/google")
    public String getGoogleAuth(@RequestBody String body) {
        return body;
    }
}
