package com.example.demo.dto;

public class UsuarioDTO {
    private Integer userId;
    private String typeIdentity;
    private long numberIdentity;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String user;
    private String pass;
    private long phone;
    private RolDTO role;
    private AreaDTO area;

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "userId=" + userId +
                ", typeIdentity='" + typeIdentity + '\'' +
                ", numberIdentity=" + numberIdentity +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", firstLastName='" + firstLastName + '\'' +
                ", secondLastName='" + secondLastName + '\'' +
                ", email='" + email + '\'' +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", phone=" + phone +
                ", role=" + role +
                ", area=" + area +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTypeIdentity() {
        return typeIdentity;
    }

    public void setTypeIdentity(String typeIdentity) {
        this.typeIdentity = typeIdentity;
    }

    public long getNumberIdentity() {
        return numberIdentity;
    }

    public void setNumberIdentity(long numberIdentity) {
        this.numberIdentity = numberIdentity;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public RolDTO getRole() {
        return role;
    }

    public void setRole(RolDTO role) {
        this.role = role;
    }

    public AreaDTO getArea() {
        return area;
    }

    public void setArea(AreaDTO area) {
        this.area = area;
    }

}
