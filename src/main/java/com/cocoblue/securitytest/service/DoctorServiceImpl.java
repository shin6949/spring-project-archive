package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.DoctorDao;
import com.cocoblue.securitytest.dto.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorDao doctorDao;

    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDao.getAllDoctors();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartCode(long departmentCode) {
        return doctorDao.getAllDoctorsByDepartCode(departmentCode);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartName(String departmentName) {
        return doctorDao.getAllDoctorsByDepartName(departmentName);
    }

    @Override
    public Doctor getDoctorByNo(long doctorNo) {
        return doctorDao.getDoctorByNo(doctorNo);
    }

    @Override
    public Doctor getDoctorByName(String doctorName) {
        return doctorDao.getDoctorByName(doctorName);
    }
}
