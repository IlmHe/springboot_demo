package com.example.demo.dto;

public class StudentUpdateDTO {
    private String name;
    private String email;

    public StudentUpdateDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public StudentUpdateDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
