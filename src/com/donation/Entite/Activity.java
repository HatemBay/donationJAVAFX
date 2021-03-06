/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.Entite;

/**
 *
 * @author Siss_Ima
 */
public class Activity {
    private int Id_ac;
    private String Name_ev;
    private String Name_ac;
    private double Duration;
    private String Description_ac;
    private String Type_ac ;

public Activity() {
    }

    public Activity(int Id_ac) {
        this.Id_ac = Id_ac;
    }


    public Activity(String Name_ac) {
        this.Name_ac = Name_ac;
    }

    public Activity(String Name_ev, String Name_ac, double Duration, String Description_ac, String Type_ac) {
        this.Name_ev = Name_ev;
        this.Name_ac = Name_ac;
        this.Duration = Duration;
        this.Description_ac = Description_ac;
        this.Type_ac = Type_ac;
    }


public Activity(int Id_ac, String Name_ev, String Name_ac, double Duration, String Description_ac, String Type_ac) {
        this.Id_ac = Id_ac;
        this.Name_ev = Name_ev;
        this.Name_ac = Name_ac;
        this.Duration = Duration;
        this.Description_ac = Description_ac;
        this.Type_ac = Type_ac;
    }

    public Activity(String Name_ac, double Duration, String Description_ac, String Type_ac) {
        this.Name_ac = Name_ac;
        this.Duration = Duration;
        this.Description_ac = Description_ac;
        this.Type_ac = Type_ac;
    }

    public int getId_ac() {
        return Id_ac;
    }

    public String getName_ev() {
        return Name_ev;
    }

    public String getName_ac() {
        return Name_ac;
    }

    public double getDuration() {
        return Duration;
    }

    public String getDescription_ac() {
        return Description_ac;
    }

    public String getType_ac() {
        return Type_ac;
    }

    public void setId_ac(int Id_ac) {
        this.Id_ac = Id_ac;
    }

    public void setName_ev(String Name_ev) {
        this.Name_ev = Name_ev;
    }

    public void setName_ac(String Name_ac) {
        this.Name_ac = Name_ac;
    }

    public void setDuration(double Duration) {
        this.Duration = Duration;
    }

    public void setDescription_ac(String Description_ac) {
        this.Description_ac = Description_ac;
    }

    public void setType_ac(String Type_ac) {
        this.Type_ac = Type_ac;
    }

    @Override
    public String toString() {
        return "Activity{" + "Id_ac=" + Id_ac + ", Name_ev=" + Name_ev + ", Name_ac=" + Name_ac + ", Duration=" + Duration + ", Description_ac=" + Description_ac + ", Type_ac=" + Type_ac + '}';
    }

   
 
    
    
}