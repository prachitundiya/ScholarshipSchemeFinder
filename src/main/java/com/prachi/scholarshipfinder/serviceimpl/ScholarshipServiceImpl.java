package com.prachi.scholarshipfinder.serviceimpl;

import com.prachi.scholarshipfinder.dao.ScholarshipDAO;
import com.prachi.scholarshipfinder.entity.Scholarship;
import com.prachi.scholarshipfinder.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipServiceImpl implements ScholarshipService {

    @Autowired
    private ScholarshipDAO scholarshipDAO;

    @Override
    public void addScholarship(
            Scholarship scholarship) {

        scholarshipDAO.addScholarship(
                scholarship
        );
    }

    @Override
    public Scholarship getScholarshipById(
            int id) {

        return scholarshipDAO.getScholarshipById(
                id
        );
    }

    @Override
    public List<Scholarship> getAllScholarships() {

        return scholarshipDAO.getAllScholarships();
    }

    @Override
    public void updateScholarship(
            Scholarship scholarship) {

        scholarshipDAO.updateScholarship(
                scholarship
        );
    }

    @Override
    public void deleteScholarship(
            int id) {

        scholarshipDAO.deleteScholarship(
                id
        );
    }

    @Override
    public List<Scholarship> findByState(
            String state) {

        return scholarshipDAO.findByState(
                state
        );
    }

    @Override
    public List<Scholarship> findByCategory(
            String category) {

        return scholarshipDAO.findByCategory(
                category
        );
    }

    @Override
    public List<Scholarship> findByIncomeLimit(
            double incomeLimit) {

        return scholarshipDAO.findByIncomeLimit(
                incomeLimit
        );
    }

    @Override
    public List<Scholarship> findByScholarshipName(
            String scholarshipName) {

        return scholarshipDAO.findByScholarshipName(
                scholarshipName
        );
    }

    @Override
    public boolean existsByScholarshipName(
            String scholarshipName) {

        return scholarshipDAO.existsByScholarshipName(
                scholarshipName
        );
    }

    @Override
    public Scholarship findExistingScholarship(
            String scholarshipName) {

        return scholarshipDAO.findExistingScholarship(
                scholarshipName
        );
    }
}