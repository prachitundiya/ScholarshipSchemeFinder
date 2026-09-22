package com.prachi.scholarshipfinder.service;

import com.prachi.scholarshipfinder.entity.Admin;

public interface AdminService {

    Admin login(String username, String password);

}