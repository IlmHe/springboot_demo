package com.example.demo.model;

public class StudentCreate {
    private String name;
    private String email;

    public StudentCreate(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

}
