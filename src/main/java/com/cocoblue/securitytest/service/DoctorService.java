package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();
    List<Doctor> getAllDoctorsByDepartCode(String departmentCode);
    List<Doctor> getAllDoctorsByDepartName(String departmentName);
    Doctor getDoctorByNo(long doctorNo);
    Doctor getDoctorByName(String doctorName);
}
