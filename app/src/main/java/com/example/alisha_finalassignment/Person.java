package com.example.alisha_finalassignment;

public class Person {

    int id;
    String fName, lName, address;
    int phone_number;

    public Person(int id, String fName, String lName, int phone_number,String address) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.phone_number = phone_number;
        this.address = address;
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

    public int getPhone_number() {
        return phone_number;
    }

    public String getAddress() {
        return address;
    }
}
