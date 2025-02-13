package com.ems;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private int age;
    private String address;
    
    public Employee(){}
    public Employee(int id, String firstName, String lastName, int age, String gender, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }
    //TODO: Getter
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }
    
    public String getAddress() {
        return address;
    }

    // TODO: Setter
    public void setId(int id) {
        this.id = id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    
    
}
