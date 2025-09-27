/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;



public abstract class Person implements Comparable<Person>{
    private static PharmacyManagementSystem system;
    private String name;
    private String phoneNumber;
    private String gender;

    public Person(String name, String phoneNumber, String gender) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setGender(gender);

    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;

    }

    public void setName(String name) {
        if (name.matches("^[a-zA-Z\\s]+$")) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Invalid name, numbers and special characters aren't allowed.");
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if ((phoneNumber != null) && (phoneNumber.matches("01\\d{9}"))) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new IllegalArgumentException("Invalid phone number, Must be 11 digits.");
        }
    }

    public void setGender(String gender) {

        if ((gender != null)
                && (gender.equalsIgnoreCase("Male")
                || gender.equalsIgnoreCase("Female"))) {
            this.gender = gender.substring(0, 1).toUpperCase() + gender.substring(1).toLowerCase();
        } else {
            throw new IllegalArgumentException("Invalid gender. Must be 'Male', 'Female'.");
        }
    }

    public void updateInfo(String name, String phoneNumber, String gender) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setGender(gender);
    }

    public void displayInfo() {
        System.out.println("Name:" + name);
        System.out.println("Phone Number:" + phoneNumber);
        System.out.println("Gender:" + gender);

    }

    @Override
    public int compareTo(Person p){
        return this.getName().compareToIgnoreCase(p.getName());
    }

    public static void setSystem(PharmacyManagementSystem system){
        Person.system = system;
    }
    public static PharmacyManagementSystem getSystem() {
        return system;
    }


}

