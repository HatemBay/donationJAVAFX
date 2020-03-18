/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.Service;
import com.donation.Entite.Applicant;
import com.donation.IService.IServiceApplicant;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.donation.Utils.DataBase;

/**
 *
 * @author Tarek
 */
public class ServiceApplicant implements IServiceApplicant{
     Connection cnx;

    public ServiceApplicant() {
        cnx = DataBase.getInstance().getConnection();
    }
 @Override
    public void addApplicant(Applicant a) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "INSERT INTO `applicant` (`Date_dispo`, `Location_Applicant`,`description`)"
                + "     VALUES ('"+a.getDate_dispo()+"', '"+a.getLocation_Applicant()+"', '"+a.getDescription()+"')";
         stm.executeUpdate(query);      
    }

   
     @Override
    public List<Applicant> getApplicants() throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `applicant`";
        ResultSet rst = stm.executeQuery(query);
        List<Applicant> Applicants = new ArrayList<>();
        while (rst.next()) {
            
            Applicant a2 = new Applicant();
            a2.setId_Applicant(rst.getInt("Id_Applicant"));
              a2.setDate_dispo(rst.getDate("Date_dispo"));
            a2.setLocation_Applicant(rst.getString("Location_Applicant"));
            a2.setDescription(rst.getString("description"));
            
            Applicants.add(a2);
        }
     return Applicants;
    }

    @Override
    public Applicant getById(int id) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `applicant` where Id_Applicant= '"+id+"'";
        ResultSet rst = stm.executeQuery(query);
        
        Applicant a2 = new Applicant();
        
        while (rst.next()) {

            a2.setId_Applicant(rst.getInt("Id_Applicant"));
          
           a2.setDate_dispo(rst.getDate("Date_dispo"));
            a2.setLocation_Applicant(rst.getString("Location_Applicant"));
            a2.setDescription(rst.getString("description"));
        
            
        }
     return a2;
    }
   
    public Applicant getByName(String location) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "select * from `applicant` where Location_Applicant= '"+location+"'";
        ResultSet rst = stm.executeQuery(query);
        
        Applicant a2 = new Applicant();
        
        while (rst.next()) {

            a2.setId_Applicant(rst.getInt("Id_Applicant"));
         
            a2.setDate_dispo(rst.getDate("Date_dispo"));
            a2.setLocation_Applicant(rst.getString("Location_Applicant"));
            a2.setDescription(rst.getString("description"));
            
            
            
        }
     return a2;
    }
    @Override
    public void deleteApplicant(Applicant a) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "DELETE FROM applicant where Id_Applicant= '"+a.getId_Applicant()+"'";
        stm.executeUpdate(query);    
    }
@Override
    public void deleteApplicant(int id) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "DELETE FROM applicant where Id_Applicant= "+id;
        stm.executeUpdate(query);          
    }
@Override
    public void updateApplicant(Applicant a) throws SQLException {
         Statement stm = cnx.createStatement();
        String query = "UPDATE applicant SET Location_Applicant = '"+a.getLocation_Applicant()+"',Date_dispo= '"+a.getDate_dispo()+"',description= '"+a.getDescription()+"'WHERE Id_Applicant = '"+a.getId_Applicant()+"'";
        stm.executeUpdate(query); 
    }
    @Override
    public List<Applicant> TrierApplicants(int i) throws SQLException {
        Statement stm = cnx.createStatement();
        String query = "";
        if (i==1) {
          query = "select * from `applicant` ORDER BY Location_Applicant ASC";  
        }else if (i==2) {
           query = "select * from `applicant` ORDER BY Date_dispo ASC"; 
        }

        ResultSet rst = stm.executeQuery(query);
        List<Applicant> Applicants = new ArrayList<>();
        while (rst.next()) {
            
            Applicant a2 = new Applicant();
            a2.setId_Applicant(rst.getInt("Id_Applicant"));
        
            a2.setDate_dispo(rst.getDate("Date_dispo"));
            a2.setLocation_Applicant(rst.getString("Location_Applicant"));
            a2.setDescription(rst.getString("description"));
            
            Applicants.add(a2);
        }
     return Applicants;
    }

    @Override
    public List<Applicant> SearchApplicants(String character) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
  

}
