package com.prachi.scholarshipfinder.service;

import com.prachi.scholarshipfinder.entity.Scholarship;

import java.util.List;

public interface ScholarshipService {

    void addScholarship(Scholarship scholarship);

    Scholarship getScholarshipById(int id);

    List<Scholarship> getAllScholarships();

    void updateScholarship(Scholarship scholarship);

    void deleteScholarship(int id);
}