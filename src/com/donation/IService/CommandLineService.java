/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.IService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.donation.Entite.Product;
import com.donation.Entite.Command;
import com.donation.Entite.CommandLine;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.donation.Utils.DataBase;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
public class CommandLineService implements IserviceCommandLine {

Connection cnx;

    public CommandLineService() {
        cnx=DataBase.getInstance().getConnection();
    }   

    @Override
    public void addCommand(CommandLine c) throws SQLException {
    Statement stm=cnx.createStatement();        
    String requete= "INSERT INTO `commandline`(`Id_CommandLine`, `Id_Command`, `Id_Product`, `Date_liv`, `State_Command`, `Type_Command`)"
                +  "VALUES (NULL, '"+c.getId_Command()+"', "
                +" '"+c.getId_Product()+"', "
                 +" '"+c.getDate_liv()+"' ,  "
                +"'"+c.getState_Command()+"', "
                +"'"+c.getType_Command()+"')";
   stm.executeUpdate(requete);
   System.out.println("😃😈 element inserted 😍 succeeds 😈😃");    }

    @Override
    public void deleteCommand(int id) throws SQLException {
             PreparedStatement pst;
             String requete = " DELETE FROM `commandline` WHERE Id_Command='"+id+"'" ;
             pst = cnx.prepareStatement(requete);
             Statement ste=cnx.createStatement();
             ste.executeUpdate(requete);
             System.out.println("😃😈 element deleted 😍 succeeds 😈😃");      }

    @Override
    public void updateCommand(CommandLine c) throws SQLException {
          PreparedStatement pst;
String requete = " UPDATE `commandline` SET `Id_CommandLine`=?,`Id_Command`=?,`Id_Product`=?,`Date_liv`=?,`State_Command`=?,`Type_Command`=? WHERE `Id_Command`='"+c.getId_Command()+"'"  ;
            pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId_CommandLine());
            pst.setInt(2,c.getId_Command());
            pst.setInt(3,c.getId_Product());
            pst.setDate(4,c.getDate_liv());
            pst.setString(5,c.getState_Command());
            pst.setString(6,c.getType_Command());
            pst.executeUpdate();
      System.out.println("😃😈 element updated 😍 succeeds 😈😃");    }

    @Override
    public List<CommandLine> getCommand() throws SQLException {
    Statement stm = cnx.createStatement();
    String requete = "SELECT * FROM `commandline` ";
    ResultSet rst = stm.executeQuery(requete);
    List<CommandLine> CommandLines = new ArrayList<>();
        while (rst.next()) {
            CommandLine c2 = new CommandLine();
            c2.setId_CommandLine(rst.getInt("Id_CommandLine"));
            c2.setId_Command(rst.getInt("Id_Command"));
            c2.setId_Product(rst.getInt("Id_Product"));
            c2.setDate_liv(rst.getDate("Date_liv"));
            c2.setState_Command(rst.getString("State_Command"));
            c2.setType_Command(rst.getString("Type_Command"));          
            CommandLines.add(c2);
        }
       System.out.println("😃😈 display 😍 succeeds 😈😃");

     return CommandLines;     }

    @Override
    public CommandLine getById(int id) throws SQLException {
    CommandLine c= null;
    Statement stm = cnx.createStatement();
    String requete = "SELECT * FROM `commandline` WHERE `Id_CommandLine`= '"+id+"'" ;
       ResultSet rst = stm.executeQuery(requete);

            if (rst.next())
            {c=new CommandLine (rst.getInt("Id_CommandLine"),rst.getInt("Id_Command"),rst.getInt("Id_Product"),rst.getDate("Date_liv"),rst.getString("State_Command"),rst.getString("Type_Command"));
            }
                  System.out.println("😃😈 display by id 😍 succeeds 😈😃");

       
        return c ;    }
    
    public List<CommandLine>  getByType(String type) throws SQLException {
    CommandLine c = null;
    Statement stm = cnx.createStatement();
    String requete = " SELECT * FROM `commandline` WHERE(Type_Command like '"+type+"')" ;
    stm = cnx.createStatement();
    ResultSet rst = stm.executeQuery(requete);
  List<CommandLine> CommandLines = new ArrayList<>();
        while (rst.next()) {
            CommandLine c2 = new CommandLine();
            c2.setId_CommandLine(rst.getInt("Id_CommandLine"));
            c2.setId_Command(rst.getInt("Id_Command"));
            c2.setId_Product(rst.getInt("Id_Product"));
            c2.setDate_liv(rst.getDate("Date_liv"));
            c2.setState_Command(rst.getString("State_Command"));
            c2.setType_Command(rst.getString("Type_Command"));          
            CommandLines.add(c2);
        }
     System.out.println("😃😈 display by type 😍 succeeds 😈😃");
        return CommandLines ;
    
}
    public List<CommandLine> getTrier() throws SQLException {
    List<CommandLine> arrcommand=new ArrayList<>();
    Statement stm = cnx.createStatement();
    String requete = "select * from CommandLine ORDER BY Date_liv ASC";
    ResultSet rst = stm.executeQuery(requete);

       
     while (rst.next()) {
         int Id_CommandLine=rst.getInt("Id_CommandLine");
         int Id_Command=rst.getInt("Id_Command");
         int Id_Product=rst.getInt("Id_Product");
         Date Date_liv=rst.getDate("Date_liv");
         String State_Command=rst.getString("State_Command");
         String Type_Command=rst.getString("Type_Command");
         CommandLine c = new CommandLine(Id_CommandLine,Id_Command,Id_Product, Date_liv,State_Command,Type_Command);
         arrcommand.add(c);
        }
     System.out.println("😃😈 sorted display 😍 succeeds 😈😃");

    return arrcommand;
    }  
      
} 

