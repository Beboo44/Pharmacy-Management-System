/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;

abstract public class Employee extends Person {
    private double salary;
    private int age;
    private String email;
    private String password;

    Employee(String name, String phoneNumber, String gender, double salary, int age, String email, String password) {
        super(name, phoneNumber, gender);
        setSalary(salary);

        setAge(age);

        setEmail(email);
        if(password.length()>3 && password.length()<15)
            this.password = password;
        else
            throw new IllegalArgumentException("Invalid password. password must be between 3 and 15 characters.");
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public void setSalary(double salary) {
        if (salary > 0) {
            this.salary = salary;
        } else {
            throw new IllegalArgumentException("Invalid salary. Must be positive.");
        }

    }

    public void setAge(int age) {
        if (age >= 18 && age <= 100) {

            this.age = age;
        } else {
            throw new IllegalArgumentException("Invalid age. Must be between 18 and 100.");
        }
    }

    public void setEmail(String email) {
        if (email.matches("^[^\\s@]+@gmail\\.com$")) {
            this.email = email;

        } else {
            throw new IllegalArgumentException("Invalid email. Must be a valid Gmail address with no spaces.");

        }
    }

    public void changePassword(String oldPass, String newPass) {
        if (oldPass.equals(password)) {
            password = newPass;
        } else {
            throw new IllegalArgumentException("Incorrect Password, Please enter old password");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Salary:" + salary);
        System.out.println("Age:" + age);
        System.out.println("Email:" + email);

    }

    public void updateInfo(String name, String phoneNumber, String gender, double salary, int age, String email,String oldPass, String newPass) {
        super.updateInfo(name, phoneNumber, gender);
        setSalary(salary);
        setAge(age);
        setEmail(email);
        changePassword(oldPass,newPass);
    }

    public String getPassword() {
        return password;
    }




}