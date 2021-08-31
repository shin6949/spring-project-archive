package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.config.ApplicationConfig;
import com.cocoblue.securitytest.config.SecurityConfig;
import com.cocoblue.securitytest.dto.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class DoctorServiceTest {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @Test
    public void getAllDoctorsTest() {
        doctorService.getAllDoctors();
    }

    @Test
    public void getAllDoctorsByDepartCodeTest() throws Exception {
        System.out.println(doctorService.getAllDoctorsByDepartCode(1));
        for(Doctor doctor : doctorService.getAllDoctorsByDepartCode(1)) {
            assertEquals(1, doctor.getDno());
        }
    }

    @Test
    public void getAllDoctorByDepartNameTest() throws Exception {
        String departmentName = "신경외과";
        long dno = departmentService.getDepartmentByName(departmentName).getDno();

        System.out.println(departmentService.getDepartmentByName(departmentName));
        for(Doctor doctor : doctorService.getAllDoctorsByDepartName(departmentName)) {
            assertEquals(dno, doctor.getDno());
        }
    }

    @Test
    public void getDoctorByNoTest() throws Exception {
        long doctorNo = 1;
        assertEquals(doctorNo, doctorService.getDoctorByNo(doctorNo).getDno());
    }

    @Test
    public void getDoctorByName() throws Exception {
        String doctorName = "김민준";
        assertEquals(doctorName, doctorService.getDoctorByName(doctorName).getName());
    }
}
