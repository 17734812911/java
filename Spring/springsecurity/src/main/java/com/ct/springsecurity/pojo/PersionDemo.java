package com.ct.springsecurity.pojo;

public class PersionDemo {

    private String name;

    public PersionDemo() {
    }

    public PersionDemo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PersionDemo{" +
                "name='" + name + '\'' +
                '}';
    }
}
