package com.android.foodapp.Models;

public class Account {
    private int IdAccount;
    private String FullName;
    private String UserName;
    private String PasswordAccount;
    private String Phone;
    private String Email;
    private String Address;
    private int IdRole;

    public Account(){}

    public Account(int idAccount, String fullName, String userName, String passwordAccount, String phone, String email, String address, int idRole) {
        IdAccount = idAccount;
        FullName = fullName;
        UserName = userName;
        PasswordAccount = passwordAccount;
        Phone = phone;
        Email = email;
        Address = address;
        IdRole = idRole;
    }

    public int getIdAccount() {
        return IdAccount;
    }

    public void setIdAccount(int idAccount) {
        IdAccount = idAccount;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPasswordAccount() {
        return PasswordAccount;
    }

    public void setPasswordAccount(String passwordAccount) {
        PasswordAccount = passwordAccount;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getIdRole() {
        return IdRole;
    }

    public void setIdRole(int idRole) {
        IdRole = idRole;
    }
}
