package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartment();
    Department getDepartmentByName(String departmentName);
}
