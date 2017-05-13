package com.jim.multipos.model.entity;


public class Currency {
    private String name;
    private String code;
    private boolean isMain;

    public Currency(String name, String code, boolean isMain) {
        this.name = name;
        this.code = code;
        this.isMain = isMain;

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public boolean isMain() {
        return isMain;
    }
    public void setMain(boolean main) {
        isMain = main;
    }
}
