/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;


/**
 *
 * @author Access
 */
public class Manager extends Employee {

    private static Manager instance;

    public Manager(String name, String phoneNumber, String gender, double salary, int age, String email, String password) {
        super(name, phoneNumber, gender, salary, age, email, password);
        instance = this;
    }

    public void hirePharmacist(Pharmacist p) {
        getSystem().addPharmacist(p);
    }

    public void firePharmacist(int id) {
        getSystem().removePharmacist(id);
    }

    public static Manager getManager(){
        if(instance != null){
            return instance;
        }
        else {
            System.out.println("No Manager currently");
        }
        return null;

    }

    public static void setManager (String name, String phoneNumber, String gender,
                                   double salary, int age, String email, String password) {
        if (instance == null) {
            instance = new Manager(name, phoneNumber, gender, salary, age, email, password);
        }
        else {
            System.out.println("Manager already exists");
        }
    }

}
