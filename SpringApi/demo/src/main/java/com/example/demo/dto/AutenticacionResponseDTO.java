package com.example.demo.dto;

public class AutenticacionResponseDTO {
    private String jwt;

    public AutenticacionResponseDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
