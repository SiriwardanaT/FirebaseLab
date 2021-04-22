package com.example.firebaselab.modal;

public class User {
    private String username;
    private String ID;
    private String address;
    private String contactNo;


    public User() {
    }

    public User(String username, String ID, String address, String contactNo) {
        this.username = username;
        this.ID = ID;
        this.address = address;
        this.contactNo = contactNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
