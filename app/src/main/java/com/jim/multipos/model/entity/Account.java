package com.jim.multipos.model.entity;


import java.util.List;
import java.util.UUID;

public class Account {
    private String name, id;
    private List<String> currencies;
    public Account(String name) {
        this.name = name;
        id = UUID.randomUUID().toString();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {return this.id; }
    public void setId(String id) { this.id = id; }

}
