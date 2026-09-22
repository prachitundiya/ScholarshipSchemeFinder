package com.prachi.scholarshipfinder.dao;

import com.prachi.scholarshipfinder.entity.Admin;

public interface AdminDAO {

    Admin login(String username, String password);

}