package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.security.CustomUserDetailsService;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CustomUserDetailsServiceTests {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Test
    public void loadByUsernameTest() {
        UserDetails customUser = customUserDetailsService.loadUserByUsername("adsf123@test.com");
        System.out.println(customUser);
        assertNotNull(customUser);
    }
}
