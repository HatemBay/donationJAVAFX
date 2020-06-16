/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.Service;

import com.donation.Entite.Fos_user;
import com.donation.IService.IService;
import com.donation.Utils.DataBase;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ASUS
 */
public class ServiceFos implements IService<Fos_user>{
      private Connection con;
    private Statement ste;
    public static Fos_user currentUser = new Fos_user();

    public ServiceFos(){
        con = DataBase.getInstance().getConnection();
    }

    @Override
    public void add(Fos_user t) throws SQLException {
        ste = con.createStatement();
        String addUser = "INSERT INTO `donation`.`fos_user` (`id`, `username`, `username_canonical` , `email` , `email_canonical` , `enabled` , `salt` , `password`, `last_login` , `confirmation_token` , `password_requested_at` , `roles` , `First_name` , `Last_name` , `Tel` , `Address` , `Blood_type` , `redeem`) "
                + "VALUES (NULL, '" + t.getUsername()+ "', '" + t.getUsername() + "', '" + t.getEmail()+ "', '" + t.getEmail()  + "', '1' , NULL , '" + t.getPassword()+ "', NULL , NULL , NULL , 'a:0:{}' , 'slm' , 'slm' , '" + t.getTel() + "', '" + t.getAddress()+ "', '" + t.getBlood_type() + "' , NULL);";
        ste.executeUpdate(addUser);
    }


    @Override
    public boolean delete(Fos_user t) throws SQLException {
        ste = con.createStatement();
        String deleteAdmin = "DELETE FROM `donation`.`fos_user` WHERE `id` = " + t.getId();
        ste.executeUpdate(deleteAdmin);
        return ste.execute(deleteAdmin); //check*************************************** 
    }

    @Override
    public boolean update(Fos_user t) throws SQLException {
        ste = con.createStatement();
        String updateUser = "UPDATE `donation`.`fos_user` SET `email` = '" + t.getEmail() + "', `password` = '" + t.getPassword() +"', `Tel` = '" + t.getTel() + "', `Address` = '" + t.getAddress() + "', `Blood_type` = '" + t.getBlood_type() + "'"
                + "WHERE `id` = " + t.getId();
        ste.executeUpdate(updateUser);
        return ste.execute(updateUser); //check*************************************** 
    }

    @Override
    public List<Fos_user> readAll() throws SQLException {
        List<Fos_user> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from fos_user");
        while (rs.next()) {
            int Id_user = rs.getInt("id");
            String Username = rs.getString("username");
            String Email = rs.getString("email");
            String Password = rs.getString("Password");
            int Tel = rs.getInt("Tel");
            String Address = rs.getString("Address");
            String Blood_type = rs.getString("Blood_type");
            //int roles = rs.getInt("roles");
//            Fos_user u = new Fos_user(Id_user, Username , Email, Password, Tel, Address, Blood_type, roles);
            Fos_user u = new Fos_user(Id_user, Username , Email, Password, Tel, Address, Blood_type);
            arr.add(u);
        }
        return arr;
    }
    
    
    public List<String> chercher(String str) throws SQLException {
        List<Fos_user> s = readAll().stream().filter(e-> e.getUsername().contains(str)).collect(Collectors.toList());
        List<String> res = (List<String>) s.stream().map(e -> e.getUsername()).collect(Collectors.toList());
        
        return res;
    }
    
    public List<String> searchByName(String str) throws SQLException {
        List<String> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select `username` from fos_user where `username` like '%" +str+ "%';");
        while (rs.next()) {
            String username = "" + rs.getString("username");
            arr.add(username);
        }
        return arr;
    }
    
    
    
    public List<String> readAllLogins() throws SQLException {
        List<String> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from fos_user ;");
        while (rs.next()) {
            String logins = "" + rs.getString("email");
            arr.add(logins);
        }
        return arr;
    }
    
    public List<String> readAllUsername() throws SQLException {
        List<String> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from fos_user ;");
        while (rs.next()) {
            String logins = "" + rs.getString("username");
            arr.add(logins);
        }
        return arr;
    }
    
    
    public List<String> readAllTels() throws SQLException {
        List<String> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from fos_user ;");
        while (rs.next()) {
            String tels = rs.getString("Tel");
            arr.add(tels);
        }
        return arr;
    }
    
    public List<String> searchById(int Id) throws SQLException {
        List<String> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select `username` from fos_user where `id` like '" +Id+ "';");
        while (rs.next()) {
            String username = "" + rs.getString("username");
            arr.add(username);
        }
        return arr;
    }
    
    public Fos_user searchByIdU(int Id) throws SQLException {
        Fos_user arr = new Fos_user();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from fos_user where `id` = '" +Id+ "';");
        while (rs.next()) {
            int Id_user = rs.getInt("id");
            String Username = rs.getString("username");
            String Email = rs.getString("email");
            String Password = rs.getString("Password");
            int Tel = rs.getInt("Tel");
            String Address = rs.getString("Address");
            String Blood_type = rs.getString("Blood_type");
            //int roles = rs.getInt("roles");
//            Fos_user u = new Fos_user(Id_user, Username , Email, Password, Tel, Address, Blood_type, roles);
            arr = new Fos_user(Id_user, Username , Email, Password, Tel, Address, Blood_type);
        }
        
        return arr;
    }
    
    public List<String> searchByTel(int Tel) throws SQLException {
        List<String> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select `username` from fos_user where `tel` like '%" +Tel+ "%';");
        while (rs.next()) {
            String username = "" + rs.getString("username");
            arr.add(username);
        }
        return arr;
    }
    
    public List<String> searchByBloodType(String Bt) throws SQLException {
        List<String> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select `username` from fos_user where `Blood_type` like '%" +Bt+ "%';");
        while (rs.next()) {
            String username = "" + rs.getString("username");
            arr.add(username);
        }
        return arr;
    }
    
    public List<Fos_user> trier(int i) throws SQLException {
        
        Statement stm = con.createStatement();
        String query = null;
        if (i==1) {
            query = "select * from `fos_user` ORDER BY username ASC";
        }else if (i==2) {
            query = "select * from `fos_user` ORDER BY Tel ASC";
        }
        ResultSet rs = stm.executeQuery(query);
        List<Fos_user> arr = new ArrayList<>();
        
        while (rs.next()) {
            Fos_user a2 = new Fos_user();
            int Id_user = rs.getInt("id");
            String Username = rs.getString("username");
            String Email = rs.getString("email");
            String Password = rs.getString("Password");
            int Tel = rs.getInt("Tel");
            String Address = rs.getString("Address");
            String Blood_type = rs.getString("Blood_type");
            a2 = new Fos_user(Id_user, Username , Email, Password, Tel, Address, Blood_type);
            arr.add(a2);
        }
     return arr;
    }
    
    public boolean EmailExists(String Email) throws SQLException {
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select `email` from fos_user where `email` like '" + Email + "';");
        if (rs == null)
            return false;
        return true;
    }
       
   public List<Fos_user> SearchFos_user(String character) throws SQLException  {
        Statement stm = con.createStatement();
        String req="select * from fos_user where username  LIKE '%"+character+"%'" ;
        ResultSet rs = stm.executeQuery(req);
        List<Fos_user> usr = new ArrayList<>();
        while (rs.next()) {
            
            Fos_user a2 = new Fos_user();
            a2.setId(rs.getInt("id"));
            a2.setUsername(rs.getString("username"));
            a2.setPassword(rs.getString("Password"));
            a2.setTel(rs.getInt("Tel"));
            a2.setAddress(rs.getString("Address"));
            a2.setBlood_type(rs.getString("Blood_type"));
            
            usr.add(a2);
        }
     return usr;
    }
    
   public void SignIn(String mail, String password) throws SQLException {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM fos_user WHERE  email = '" + mail + "' and Password = '" + password + "'" );
            
            if (rs != null) {
                while (rs.next()) {       
                    currentUser.setId(rs.getInt("id"));
                    currentUser.setUsername(rs.getString("username"));
                    currentUser.setPassword(rs.getString("Password"));
                    currentUser.setTel(rs.getInt("Tel"));
                    currentUser.setAddress(rs.getString("Address"));
                    currentUser.setBlood_type(rs.getString("Blood_type"));
                }
         }   
    }

   
   public void SignInWithCode(String mail, String code) throws SQLException {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery("SELECT * FROM fos_user WHERE  email = '" + mail + "' and redeem = '" + code + "'" );
            
            if (rs != null) {
                while (rs.next()) {       
                    currentUser.setId(rs.getInt("id"));
                    currentUser.setUsername(rs.getString("username"));
                    currentUser.setPassword(rs.getString("Password"));
                    currentUser.setTel(rs.getInt("Tel"));
                    currentUser.setAddress(rs.getString("Address"));
                    currentUser.setBlood_type(rs.getString("Blood_type"));
                }
         }   
    }
   
   public void SignOut() throws SQLException {
        currentUser.setId(0);
        currentUser.setUsername("");
        currentUser.setPassword("");
        currentUser.setTel(0);
        currentUser.setAddress("");
        currentUser.setBlood_type("");
    }
}
