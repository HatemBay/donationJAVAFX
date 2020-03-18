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
import com.donation.Entite.Command;
import com.donation.Entite.Product;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.donation.Utils.DataBase;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DELL
 */
public class CommandService implements IserviceCommand{
    Connection cnx;
    public CommandService() {
        cnx=DataBase.getInstance().getConnection();
    }
    @Override
    public void addCommand(Command c) throws SQLException {
       Statement stm=cnx.createStatement();
              String requete= "INSERT INTO `command`"+
              "(`Id_Command`, `Id_Product`, `Quantity_Product`, `Paid`, `Date_Command`)"
                +  "VALUES (NULL, '"+c.getId_Product()+"', "
                +"'"+c.getQuantity_Product()+"', "
                +"'"+c.getPaid()+"', "
                +"'"+c.getDate_Command()+"')";
      stm.executeUpdate(requete);
      System.out.println("😃😈 element inserted 😍 succeeds 😈😃");
    
    }

    @Override
    public void deleteCommand(int id) throws SQLException {
        PreparedStatement pst;
        String requete = " DELETE FROM `command` WHERE Id_Command='"+id+"'" ;
        pst = cnx.prepareStatement(requete);
        Statement ste=cnx.createStatement();
        ste.executeUpdate(requete);
        System.out.println("😃😈 element deleted 😍 succeeds 😈😃"); 

    }

    @Override
    public void updateCommand(Command c) throws SQLException {
           PreparedStatement pst;
            String requete = " UPDATE `command` SET `Id_Command`=?,`Id_Product`=?,`Quantity_Product`=?,`Paid`=?,`Date_Command`=? WHERE `Id_Command`='"+c.getId_Command()+"'"  ;
            pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId_Command());
            pst.setInt(2,c.getId_Product());
            pst.setInt(3, c.getQuantity_Product());
            pst.setInt(4,c.getPaid());
            pst.setDate(5,c.getDate_Command());
            pst.executeUpdate();
      System.out.println("😃😈 element updated 😍 succeeds 😈😃"); 
    }

    @Override
    public List<Command> getCommand() throws SQLException {
        Statement stm = cnx.createStatement();
        String requete = "select * from `command`";
        ResultSet rst = stm.executeQuery(requete);
        List<Command> commands = new ArrayList<>();
        while (rst.next()) {
            Command c2 = new Command ();
            c2.setId_Command(rst.getInt("Id_Command"));
            c2.setId_Product(rst.getInt("Id_Product"));
            c2.setQuantity_Product(rst.getInt("Quantity_Product"));
            c2.setPaid(rst.getInt("Paid"));
            c2.setDate_Command(rst.getDate("Date_Command"));
            commands.add(c2);
        }
       System.out.println("😃😈 display 😍 succeeds 😈😃");

     return commands; 
    }
  public List<Command> getCommands() throws SQLException {
        Statement stm = cnx.createStatement();
        String requete = "SELECT `Id_Command`, `Id_Product`, `Quantity_Product`, `Paid` FROM `command`";
        ResultSet rst = stm.executeQuery(requete);
        List<Command> commands = new ArrayList<>();
        while (rst.next()) {
            Command c2 = new Command ();
            c2.setId_Command(rst.getInt("Id_Command"));
            c2.setId_Product(rst.getInt("Id_Product"));
            c2.setQuantity_Product(rst.getInt("Quantity_Product"));
            c2.setPaid(rst.getInt("Paid"));
            commands.add(c2);
        }
       System.out.println("😃😈 display 😍 succeeds 😈😃");

     return commands; 
    }
    @Override
    public Command getById(int id) throws SQLException {
        Command c = null;
          Statement stm = cnx.createStatement();
          String requete = " SELECT * FROM `command` WHERE `Id_Command`= '"+id+"'" ;
          ResultSet rst = stm.executeQuery(requete);
            if (rst.next())
            {c=new Command(rst.getInt("Id_Command"),rst.getInt("Id_Product"),rst.getInt("Quantity_Product"),rst.getInt("Paid"),rst.getDate("Date_Command"));
            }
                  System.out.println("😃😈 display by id 😍 succeeds 😈😃");

       
        return c ;

    }
    
}
