/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.donation.Service;

import com.donation.IService.IServiceOffer;
import com.donation.Entite.Offer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.donation.Utils.DataBase;

/**
 *
 * @author tarek
 */
public class ServiceOffer implements IServiceOffer {
    Connection cnx;

    public ServiceOffer() {
        cnx = DataBase.getInstance().getConnection();
    }
 
  

    @Override
    public Offer getById(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `offer` where Id_Offer= '"+id+"'";
        ResultSet rst = stm.executeQuery(query);
        
        Offer a2 = new Offer();
        
        while (rst.next()) {
            
            a2.setId_Offer(rst.getInt("Id_Offer"));
            a2.setPost(rst.getString("post"));
            a2.setDescription(rst.getString("description"));
            a2.setCondition(rst.getString("condition"));
              a2.setType(rst.getString("type"));
            a2.setDate_ajout(rst.getDate("Date_ajout"));
             
        }
     return a2;
    }

    public Offer getByName(String name) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `offer` where POst= '"+name+"'";
        ResultSet rst = stm.executeQuery(query);
        
        Offer a2 = new Offer();
        
        while (rst.next()) {
            
            a2.setId_Offer(rst.getInt("Id_Offer"));
            a2.setPost(rst.getString("post"));
            a2.setDescription(rst.getString("description"));
             a2.setType(rst.getString("type"));
            a2.setCondition(rst.getString("condition"));
           
            a2.setDate_ajout(rst.getDate("Date_ajout"));
             
        }
     return a2;
    }
    @Override
    public void deleteOffer(Offer a) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "DELETE FROM offer where Id_Offer= '"+a.getId_Offer()+"'";
        stm.executeUpdate(query);    
    }
@Override
    public void deleteOffer(int id) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "DELETE FROM offer where Id_Offer= '"+id+"'";
        stm.executeUpdate(query);          
    }

   @Override
    public void updateOffer(Offer a) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "UPDATE offer SET post= '"+a.getPost()+"', description= '"+a.getDescription()+"', type= '"+a.getType()+"', condition= '"+a.getCondition()+"'' WHERE Id_Offer='"+a.getId_Offer()+"'";
        stm.executeUpdate(query); 
    }

    
    @Override
    public List<Offer> TrierOffers(int i) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "";
        if (i==1) {
            query = "select * from `offer` ORDER BY date_ajout ASC";
        }else if (i==2) {
            query = "select * from `offer` ORDER BY post ASC";
        }
        
        ResultSet rst = stm.executeQuery(query);
        List<Offer> offers = new ArrayList<>();
        while (rst.next()) {
            
            Offer a2 = new Offer();
            a2.setId_Offer(rst.getInt("Id_offer"));
            a2.setPost(rst.getString("post"));
            a2.setCondition(rst.getString("condition"));
            a2.setDescription(rst.getString("description"));
              a2.setType(rst.getString("type"));
            a2.setDate_ajout(rst.getDate("Date_ajout"));
            
            offers.add(a2);
        }
     return offers;
    }
    @Override
    public List<Offer> SearchOffers(String character) throws SQLException  {
        Statement stm = cnx.createStatement();
        String req="select * from offer where post  LIKE '%"+character+"%'" ;
        ResultSet rst = stm.executeQuery(req);
        List<Offer> offers = new ArrayList<>();
        while (rst.next()) {
            Offer a2 = new Offer();
            a2.setId_Offer(rst.getInt("Id_offer"));
            a2.setPost(rst.getString("post"));
            a2.setCondition(rst.getString("condition"));
              a2.setType(rst.getString("type"));
            a2.setDescription(rst.getString("description"));
            a2.setDate_ajout(rst.getDate("Date_ajout"));
            offers.add(a2);
        }
     return offers;
    }

    @Override
    public List<String> SearchOffersNames(String character) throws SQLException {
       Statement stm = cnx.createStatement();
        String req="select * from offer where post  LIKE '%"+character+"%'" ;
        ResultSet rst = stm.executeQuery(req);
        List<String> offers = new ArrayList<>();
        while (rst.next()) {
            String a2 = "";
            a2=rst.getString("post");
            offers.add(a2);
        }
     return offers;
    }

    @Override
    public void addOffer(Offer a) throws SQLException {
Statement stm = cnx.createStatement();
        String query = "INSERT INTO `offer` ( `post`,`description`,`type`, `condition`,`Date_ajout`)"
                + "     VALUES ( '"+a.getPost()+"', '"+a.getDescription()+"', '"+a.getType()+"', '"+a.getCondition()+"','"+a.getDate_ajout()+"')";
         stm.executeUpdate(query);     
         System.out.println("Ajoutee");
    }

    @Override
    public List<Offer> getOffers() throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `offer`";
        ResultSet rst = stm.executeQuery(query);
        List<Offer> offers = new ArrayList<>();
        while (rst.next()) {
            
            Offer a2 = new Offer();
            a2.setId_Offer(rst.getInt("Id_Offer"));
            a2.setPost(rst.getString("post"));
            a2.setDescription(rst.getString("description"));
              a2.setType(rst.getString("type"));
                a2.setCondition(rst.getString("condition"));
            a2.setDate_ajout(rst.getDate("Date_ajout"));
            
            offers.add(a2);
        }
     return offers;//To change body of generated methods, choose Tools | Templates.
    }

   
    
}
