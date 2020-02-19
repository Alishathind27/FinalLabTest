package com.example.alisha_finalassignment;

public class Person {

    int id;
    String fName, lName, address;
    int phone_number;

    public Person(int id, String fName, String lName, String address, int phone_number) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getId(){
        return id;
    }
    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone_number() {
        return phone_number;
    }
}
