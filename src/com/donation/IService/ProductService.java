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
public class ProductService implements IserviceProduct{
 Connection cnx;
  public ProductService() {
        cnx=DataBase.getInstance().getConnection();
    }

    @Override
    public void addProduct(Product p) throws SQLException {
        try{    Statement stm=cnx.createStatement();
             String requete= "INSERT INTO `product`"
                +"(`Name_Product`, `Quantity_Total`, `Price_Product`, `Description_Product`, `Quantity_Remaining`, `Id_admin`, `Id_Association`)"
                +  "VALUES ('"+p.getName_Product()+"', "
                +"'"+p.getQuantity_Total()+"',"
                +"'"+p.getPrice_Product()+"',"
                +"'"+p.getDescription_Product()+"',"
                +"'"+p.getQuantity_Remaining()+"',"
                +"'"+p.getId_admin()+"',"
                +"'"+p.getId_Association()+"')";
      stm.executeUpdate(requete);
      
      System.out.println("😃😈 element inserted 😍 succeeds 😈😃");
        }catch(SQLException ex){}
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        PreparedStatement pst;
        String requete = " DELETE FROM `product` WHERE Id_Product='"+id+"'" ;
        pst = cnx.prepareStatement(requete);
        Statement ste=cnx.createStatement();
        ste.executeUpdate(requete);
        System.out.println("😃😈 element deleted 😍 succeeds 😈😃");    }

    @Override
      public void updateProduct(Product p) throws SQLException {
            PreparedStatement pst;
            String requete = " UPDATE `product` SET `Name_Product`=?,`Quantity_Total`=?,`Price_Product`=?,`Description_Product`=?,`Quantity_Remaining`=?,`Id_admin`=?,`Id_Association`=? WHERE `Id_Product`=?" ;
            pst = cnx.prepareStatement(requete);
            pst.setString(1,p.getName_Product());
            pst.setInt(2, p.getQuantity_Total());
            pst.setFloat(3, p.getPrice_Product());
            pst.setString(4, p.getDescription_Product());
            pst.setInt(5,p.getQuantity_Remaining());
            pst.setInt(6, p.getId_admin());
            pst.setInt(7, p.getId_Association());
            pst.setInt(8,p.getId_Product());

            pst.executeUpdate();
      System.out.println("😃😈 element updated 😍 succeeds 😈😃");    
      }

    @Override
    public List<Product> getProduct() throws SQLException {
        Statement stm = cnx.createStatement();
        String requete = "select * from `product`";
        ResultSet rst = stm.executeQuery(requete);
        List<Product> products = new ArrayList<>();
        while (rst.next()) {
            Product p2 = new Product();
            p2.setId_Product(rst.getInt("Id_Product"));
            p2.setName_Product(rst.getString("Name_Product"));
            p2.setQuantity_Total(rst.getInt("Quantity_Total"));
            p2.setPrice_Product(rst.getFloat("Price_Product"));
            p2.setDescription_Product(rst.getString("Description_Product"));
            p2.setQuantity_Remaining(rst.getInt("Quantity_Remaining"));
            p2.setId_admin(rst.getInt("Id_admin"));
            p2.setId_Association(rst.getInt("Id_Association"));
            products.add(p2);
        }
       System.out.println("😃😈 display 😍 succeeds 😈😃");

     return products; 
    }
 public List<Product> getProducts() throws SQLException {
        Statement stm = cnx.createStatement();
        String requete = "SELECT `Id_Product`, `Name_Product`, `Quantity_Total`, `Price_Product`, `Description_Product`, `Quantity_Remaining` from `product`";
        ResultSet rst = stm.executeQuery(requete);
        List<Product> products = new ArrayList<>();
        while (rst.next()) {
            Product p2 = new Product();
            p2.setId_Product(rst.getInt("Id_Product"));
            p2.setName_Product(rst.getString("Name_Product"));
            p2.setQuantity_Total(rst.getInt("Quantity_Total"));
            p2.setPrice_Product(rst.getFloat("Price_Product"));
            p2.setDescription_Product(rst.getString("Description_Product"));
            p2.setQuantity_Remaining(rst.getInt("Quantity_Remaining"));

            products.add(p2);
        }
       System.out.println("😃😈 display user 😍 succeeds 😈😃");

     return products; 
    }
    @Override
    public Product getById(int id) throws SQLException {
          Product p = null;
          Statement stm = cnx.createStatement();
          String requete = " SELECT * FROM `product` WHERE `Id_Product`= '"+id+"'" ;
          ResultSet rst = stm.executeQuery(requete);

            if (rst.next())
            {p=new Product(rst.getInt("Id_Product"),rst.getString("Name_Product"),rst.getInt("Quantity_Total"),rst.getFloat("Price_Product"),
            rst.getString("Description_Product"),rst.getInt("Quantity_Remaining"),rst.getInt("Id_admin"),rst.getInt("Id_Association")
            );}
                  System.out.println("😃😈 display by id 😍 succeeds 😈😃");

       
        return p ;
    
    }
    
      public Product getByName(String nom) throws SQLException {
      Product p = null;
      Statement stm = cnx.createStatement();
         String requete = " SELECT * FROM `product` WHERE (Name_Product like '"+nom+"%')" ;
        try {
           
            stm = cnx.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            if (rst.next())
            p=new Product(rst.getInt("Id_Product"),rst.getString("Name_Product"),rst.getInt("Quantity_Total"),rst.getFloat("Price_Product"),
            rst.getString("Description_Product"),rst.getInt("Quantity_Remaining"),rst.getInt("Id_admin"),rst.getInt("Id_Association"));
            
                } catch (SQLException ex) {
        }
                          System.out.println("😃😈 display by name 😍 succeeds 😈😃");

        return p ;
    
}
      
 public List<Product> getTrier() throws SQLException {
 List<Product> arrproduct=new ArrayList<>();
 Statement stm = cnx.createStatement();
 String requete = "select * from Product ORDER BY Name_Product ASC";
 ResultSet rst = stm.executeQuery(requete);

       
     while (rst.next()) {
         
         int Id_Product=rst.getInt("Id_Product");
         String Name_Product=rst.getString("Name_Product");
         int Quantity_Total=rst.getInt("Quantity_Total");
         float Price_Product=rst.getFloat("Price_Product");
         String Description_Product=rst.getString("Description_Product");
         int Quantity_Remaining=rst.getInt("Quantity_Remaining");
         int Id_admin=rst.getInt("Id_admin");
         int Id_Association=rst.getInt("Id_Association");            
         Product a = new Product(Id_Product, Name_Product,Quantity_Total,Price_Product,Description_Product,Quantity_Remaining,Id_admin,Id_Association);
         arrproduct.add(a);
        
        }
     System.out.println("😃😈 sorted display 😍 succeeds 😈😃");

    return arrproduct;
    }     
     public boolean ProductHasNote(int id) {
        try {
            String requete = "SELECT * FROM Product WHERE Quantity_Total=0 AND Id_Product = '" + id+ "'";
            PreparedStatement pst;
            pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery(requete);//ça est
            while (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Echec de recherche de produit" + e);
        }
        return false;
    } 
     public float SalesRate(int id) throws SQLException{
                 float SalesRate = 0.0f;
                 PreparedStatement pst;
                 String requete = "SELECT (((Quantity_Total-Quantity_Remaining) /Quantity_Total)*100) FROM Product WHERE Id_Product = '" + id + "'";
                 pst = cnx.prepareStatement(requete);
                 ResultSet rs = pst.executeQuery(requete);
                 while (rs.next()) {
                    SalesRate = rs.getFloat(1);}
                 System.out.println("😃😈 calculated SalesRate 😍 succeeds 😈😃");
                 return SalesRate;
                    
//ça est y
   
}
     public float TotalProduct() throws SQLException{
                float totalproduct=0.0f;
                PreparedStatement pst;
                String requete = "SELECT COUNT(Quantity_Total) FROM Product ";
                pst = cnx.prepareStatement(requete);
                ResultSet rs = pst.executeQuery(requete);
                while (rs.next()) {
                   totalproduct = rs.getFloat(1);}
                System.out.println("😃😈 total product😍 succeeds 😈😃");
                return totalproduct;}
      public float TotalQuantiteRemaining() throws SQLException{
               float TotalQuantiteRemaining=0.0f;
               PreparedStatement pst;
               String requete = "SELECT SUM(Quantity_Remaining) FROM Product ";
               pst = cnx.prepareStatement(requete);
               ResultSet rs = pst.executeQuery(requete);
               while (rs.next()) {
                   TotalQuantiteRemaining = rs.getFloat(1);}
               System.out.println("😃😈 Total Quantity😍 succeeds 😈😃");
                   return TotalQuantiteRemaining;
                   //ça est y
     }
     public float TotalPrise(int id) throws SQLException{
               float Total = 0.0f;
               PreparedStatement pst;
               String requete = "SELECT (Price_Product * (Quantity_Total-Quantity_Remaining)) FROM Product WHERE Id_Product = '" + id + "'";
               pst = cnx.prepareStatement(requete);
               ResultSet rs = pst.executeQuery(requete);
               while (rs.next()) {
                 Total = rs.getFloat(1);}
               System.out.println("😃😈 calculated total 😍 succeeds 😈😃");
                    return Total;
//ça est y
}}