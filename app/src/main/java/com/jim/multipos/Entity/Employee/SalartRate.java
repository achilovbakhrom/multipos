package com.jim.multipos.Entity.Employee;


public class SalartRate {
    private String fromDate;
    private double salaryAmount;
    private String salaryType;

    public SalartRate(String fromDate, double salaryAmount, String salaryType) {
        this.fromDate = fromDate;
        this.salaryAmount = salaryAmount;
        this.salaryType = salaryType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public double getSalaryAmount() {
        return salaryAmount;
    }

    public void setSalaryAmount(double salaryAmount) {
        this.salaryAmount = salaryAmount;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }
}
