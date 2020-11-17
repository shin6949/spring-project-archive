package com.cocoblue.securitytest.dto;

import com.cocoblue.securitytest.config.ApplicationConfig;
import com.cocoblue.securitytest.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class BoardTest {
    LocalTime localTime = LocalTime.now();

    @Test
    public void test() {
        ArrayList<String> timeString = new ArrayList<>(Arrays.asList("09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00",
                "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "17:00:00", "17:30:00"));
        System.out.println(timeString);
        timeString.remove("15:30:00");
        System.out.println(timeString);
    }
}
