/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.Entite;

/**
 *
 * @author Hatem
 */
public class Fos_user {

    private int id;
    private String username, email, password;
    private int tel;
    private String address, blood_type;


    public Fos_user(int id, String username, String email, String password, int tel, String address, String blood_type) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.address = address;
        this.blood_type = blood_type;
    }

    public Fos_user(String username, String email, String password, int tel, String address, String blood_type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.address = address;
        this.blood_type = blood_type;
    }

    public Fos_user(String username, String email, String password, int tel, String address) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.tel = tel;
        this.address = address;
    }

    public Fos_user() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    @Override
    public String toString() {
        return "Fos_user{" + "id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", tel=" + tel + ", address=" + address + ", blood_type=" + blood_type + '}';
    }

    

    

}
