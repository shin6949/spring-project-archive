package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.dao.CustomerDao;
import com.cocoblue.securitytest.dao.CustomerRoleDao;
import com.cocoblue.securitytest.dto.Customer;
import com.cocoblue.securitytest.dto.CustomerRole;
import com.cocoblue.securitytest.service.security.UserEntity;
import com.cocoblue.securitytest.service.security.UserRoleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;
    private final CustomerRoleDao customerRoleDao;

    public CustomerServiceImpl(CustomerDao customerDao, CustomerRoleDao customerRoleDao) {
        this.customerDao = customerDao;
        this.customerRoleDao = customerRoleDao;
    }

    @Override
    @Transactional
    public UserEntity getUser(String loginUserId) {
        Customer customer = customerDao.getCustomerById(loginUserId);
        return new UserEntity(customer.getCno(), customer.getId(), customer.getId(), customer.getPassword());
    }

    @Override
    @Transactional
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
        List<CustomerRole> customerRoles = customerRoleDao.getRolesByEmail(loginUserId);
        List<UserRoleEntity> list = new ArrayList<>();

        for (CustomerRole customerRole : customerRoles) {
            list.add(new UserRoleEntity(loginUserId, customerRole.getRoleName()));
        }
        return list;
    }

    @Override
    @Transactional
    public void addMember(Customer customer, boolean admin) {
        if (admin) {
            customerDao.insertAdmin(customer);
        } else {
            customerDao.insertUser(customer);
        }
    }

    @Override
    public Boolean checkEmail(String email) {
        return customerDao.checkEmail(email);
    }

    @Override
    public Customer getCustomerByCno(long cno) {
        return customerDao.getCustomerByCno(cno);
    }

    @Override
    public Customer getLoginUser() {
        return customerDao.getLoginUser();
    }
}
