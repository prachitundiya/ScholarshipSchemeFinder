package com.prachi.scholarshipfinder.serviceimpl;

import com.prachi.scholarshipfinder.dao.AdminDAO;
import com.prachi.scholarshipfinder.entity.Admin;
import com.prachi.scholarshipfinder.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Admin login(String username, String password) {

        return adminDAO.login(username, password);

    }
}