/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.Entite;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author tarek
 */
public class Offer {
    private int Id_Offer;
    private String post;
    private String description;
    private String type;
    private String condition;
   private Date Date_ajout;

    public Offer() {
    }

    public Offer(int Id_Offer) {
        this.Id_Offer = Id_Offer;
    }

    public Offer(int Id_Offer, String post, String description, String type, String condition, Date Date_ajout) {
        this.Id_Offer = Id_Offer;
        this.post = post;
        this.description = description;
        this.type = type;
        this.condition = condition;
        this.Date_ajout = Date_ajout;
    }

    public Offer(String post, String description, String type, String condition, Date Date_ajout) {
        this.post = post;
        this.description = description;
        this.type = type;
        this.condition = condition;
        this.Date_ajout = Date_ajout;
    }

    public int getId_Offer() {
        return Id_Offer;
    }

    public String getPost() {
        return post;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getCondition() {
        return condition;
    }

    public Date getDate_ajout() {
        return Date_ajout;
    }

    public void setId_Offer(int Id_Offer) {
        this.Id_Offer = Id_Offer;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setDate_ajout(Date Date_ajout) {
        this.Date_ajout = Date_ajout;
    }

    @Override
    public String toString() {
        return "Offer{" + "Id_Offer=" + Id_Offer + ", post=" + post + ", description=" + description + ", type=" + type + ", condition=" + condition + ", Date_ajout=" + Date_ajout + '}';
    }

}