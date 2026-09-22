package com.prachi.scholarshipfinder.dao;

import com.prachi.scholarshipfinder.entity.Scholarship;

import java.util.List;

public interface ScholarshipDAO {

    // Add Scholarship
    void addScholarship(Scholarship scholarship);

    // Get Scholarship By ID
    Scholarship getScholarshipById(int id);

    // View All Scholarships
    List<Scholarship> getAllScholarships();

    // Update Scholarship
    void updateScholarship(Scholarship scholarship);

    // Delete Scholarship
    void deleteScholarship(int id);

    // Search By State
    List<Scholarship> findByState(String state);

    // Search By Category
    List<Scholarship> findByCategory(String category);

    // Search By Income Limit
    List<Scholarship> findByIncomeLimit(double incomeLimit);

    // Search By Scholarship Name
    List<Scholarship> findByScholarshipName(String scholarshipName);

    // Check Duplicate Scholarship
    boolean existsByScholarshipName(String scholarshipName);

    // Find Existing Scholarship By Exact Name
    Scholarship findExistingScholarship(String scholarshipName);
}