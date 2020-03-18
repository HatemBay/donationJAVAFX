/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.Entite;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Tarek
 */
public class Applicant {
    private int Id_Applicant;
    private Date Date_dispo;
    private String Location_Applicant;
    private String description;

    public Applicant() {
    }

    public Applicant(Date Date_dispo, String Location_Applicant, String description) {
        
        this.Date_dispo = Date_dispo;
        this.Location_Applicant = Location_Applicant;
        this.description = description;
        
    }

    public Applicant(Date Date_dispo, String description) {
        this.Date_dispo = Date_dispo;
        this.description = description;
    }

    public int getId_Applicant() {
        return Id_Applicant;
    }

    public Date getDate_dispo() {
        return Date_dispo;
    }

    public String getLocation_Applicant() {
        return Location_Applicant;
    }

    public String getDescription() {
        return description;
    }

    public void setId_Applicant(int Id_Applicant) {
        this.Id_Applicant = Id_Applicant;
    }

    public void setDate_dispo(Date Date_dispo) {
        this.Date_dispo = Date_dispo;
    }

    public void setLocation_Applicant(String Location_Applicant) {
        this.Location_Applicant = Location_Applicant;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Applicant{" + "Id_Applicant=" + Id_Applicant + ", Date_dispo=" + Date_dispo + ", Location_Applicant=" + Location_Applicant + ", description=" + description + '}';
    }


    
    
}