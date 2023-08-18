package com.example.demo.dto;

public class StudentCreateDTO {
    private String name;
    private String email;

    public StudentCreateDTO(String name, String email) {
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
