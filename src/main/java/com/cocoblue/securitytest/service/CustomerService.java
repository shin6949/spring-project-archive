package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dto.Customer;
import com.cocoblue.securitytest.service.security.UserDbService;

public interface CustomerService extends UserDbService {
    void addMember(Customer member, boolean b);
    Boolean checkEmail(String email);
    Customer getCustomerByCno(long cno);
    Customer getLoginUser();
}