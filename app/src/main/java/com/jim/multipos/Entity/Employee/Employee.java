package com.jim.multipos.Entity.Employee;


public class Employee {
    private String name;
    private String phone;
    private String startDate;
    private long password;

    public Employee(String name, String phone, String startDate,  long password) {
        this.name = name;
        this.phone = phone;
        this.startDate = startDate;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }
}
