package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.config.ApplicationConfig;
import com.cocoblue.securitytest.config.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class HolidayServiceTest {
    @Autowired
    HolidayService holidayService;

//    @Test
//    public void apiTest() throws Exception {
//        List<LocalDate> value = new ArrayList<LocalDate>();
//        String object = holidayService.getItemsFromOpenApi(2020, 10, value);
//        System.out.println(value);
//    }
}
