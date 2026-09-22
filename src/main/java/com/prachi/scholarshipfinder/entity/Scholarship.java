package com.prachi.scholarshipfinder.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "scholarship")
public class Scholarship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String scholarshipName;
    private String category;
    private String state;
    private double incomeLimit;
    private String eligibility;
    private double amount;
    private String lastDate;
    private String website;

    // Default Constructor
    public Scholarship() {
    }

    // Parameterized Constructor
    public Scholarship(int id, String scholarshipName, String category, String state,
                       double incomeLimit, String eligibility, double amount,
                       String lastDate, String website) {
        this.id = id;
        this.scholarshipName = scholarshipName;
        this.category = category;
        this.state = state;
        this.incomeLimit = incomeLimit;
        this.eligibility = eligibility;
        this.amount = amount;
        this.lastDate = lastDate;
        this.website = website;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScholarshipName() {
        return scholarshipName;
    }

    public void setScholarshipName(String scholarshipName) {
        this.scholarshipName = scholarshipName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getIncomeLimit() {
        return incomeLimit;
    }

    public void setIncomeLimit(double incomeLimit) {
        this.incomeLimit = incomeLimit;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "Scholarship{" +
                "id=" + id +
                ", scholarshipName='" + scholarshipName + '\'' +
                ", category='" + category + '\'' +
                ", state='" + state + '\'' +
                ", incomeLimit=" + incomeLimit +
                ", eligibility='" + eligibility + '\'' +
                ", amount=" + amount +
                ", lastDate='" + lastDate + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}