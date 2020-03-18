/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Product;
import com.donation.Utils.DataBase;
import com.donation.IService.CommandLineService;
import com.donation.IService.CommandService;
import com.donation.IService.ProductService;
import doryan.windowsnotificationapi.fr.*;
import donationfx.SendMail;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.awt.TrayIcon;
import static javafx.scene.control.Alert.AlertType.ERROR;
import com.donation.IService.ProductService;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddProductAdminController implements Initializable {

    @FXML
    private TextField TName_Product;
    @FXML
    private TextField TQuantity_Total;
    @FXML
    private TextField TPrice_Product;
    @FXML
    private TextField TDescription_Product;
    @FXML
    private TextField TQuantity_Remaining;
    @FXML
    private TextField TId_admin;
    @FXML
    private TextField TId_Association;
com.donation.IService.ProductService SP= new ProductService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private Button addproduct;
    @FXML
    private Button Buy;
    @FXML
    private Button home;
    @FXML
    private Button events;
    @FXML
    private Button donations;
    @FXML
    private Button associations;
    @FXML
    private Button volunteering;
    @FXML
    private Button actions;
    @FXML
    private Button logout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
public boolean verifierchamps ()
    {
        String vName_Product = TName_Product.getText ();
        String vQuantity_Total = TQuantity_Total.getText ();
        String vPrice_Product = TPrice_Product.getText ();
        String vDescription_Product  = TDescription_Product.getText ();
        String vQuantity_Remaining  = TQuantity_Remaining.getText ();
        String vId_admin = TId_admin.getText ();
        String vId_Association = TId_Association.getText ();
        
        

                // vérifie les champs vides
                if (vName_Product.trim (). equals ("") || vQuantity_Total.trim (). equals ("") || vPrice_Product.trim ().equals ("")
                   || vDescription_Product.trim (). equals ("") || vQuantity_Remaining.trim (). equals ("") || vId_admin.trim (). equals ("") || vId_Association.trim (). equals (""))
                {
                    JOptionPane.showMessageDialog (null, "One or more fields are empty", "Empty fields", 2);
                    return false;
                }else if (Integer.parseInt(vQuantity_Remaining)>Integer.parseInt(vQuantity_Total)){
                 JOptionPane.showMessageDialog (null, "check for remaining quantity", "quantity", 2);
                    return false;
                }else if(Integer.parseInt(vQuantity_Total)<0){
                 JOptionPane.showMessageDialog (null, "check for quantity", "quantity", 2);
                                    return false;
}
                else if (Integer.parseInt(vQuantity_Remaining)<0){
                 JOptionPane.showMessageDialog (null, "check for remaining quantity", "quantity", 2);
                                    return false;

                }else if(Float.parseFloat(vPrice_Product)<=0){
                 JOptionPane.showMessageDialog (null, "check for price", "price", 2);
                                    return false;
}
                
                 return true;}
    @FXML
    private void addProduct(ActionEvent event)throws SQLException, IOException {
    cnx=DataBase.getInstance().getConnection();
                ste = cnx.createStatement();   
    com.donation.IService.ProductService SP= new ProductService();
         Product p = new Product();
     
         String vName_Product = TName_Product.getText ();
        String vQuantity_Total = TQuantity_Total.getText ();
        String vPrice_Product = TPrice_Product.getText ();
        String vDescription_Product  = TDescription_Product.getText ();
        String vQuantity_Remaining  = TQuantity_Remaining.getText ();
        String vId_admin = TId_admin.getText ();
        String vId_Association = TId_Association.getText ();
           if (verifierchamps ()==true)
         {p.setName_Product(vName_Product);
         p.setQuantity_Total(Integer.parseInt(vQuantity_Total));
         p.setPrice_Product(Float.parseFloat(vPrice_Product));
         p.setDescription_Product(vDescription_Product);
         p.setQuantity_Remaining(Integer.parseInt(vQuantity_Remaining));
         p.setId_admin(Integer.parseInt(vId_admin));
         p.setId_Association(Integer.parseInt(vId_Association));
         System.out.println(p.toString());
                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation add");
             alert.setHeaderText("this confirmation about add");
             alert.setContentText("are you sure to add??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
           SP.addProduct(p);
          alert.setContentText("succeed");
           System.out.println("I'm a new product thank you ");
             }else{System.out.println("Cancel");}
           
                       //SendMail.sendMail("khaoula.soury@esprit.tn", "product", "new product");

        try {
            Notification.sendNotification("add succeed", "youp", TrayIcon.MessageType.INFO);
        } catch (AWTException ex) {
            Logger.getLogger(AddProductAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(AddProductAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }   
        
       
             
        //Parent tableview = FXMLLoader.load(getClass().getResource("Display.fxml"));
    
    
   
        
        //Scene sceneview = new Scene(tableview);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       // window.setScene(sceneview);
        window.show(); }

    @FXML
    private void toHome(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toProducts(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DisplayProductAdmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        
    }

    @FXML
    private void toEvents(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void toDonations(ActionEvent event) {
    }

    @FXML
    private void toAssociations(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void toVolunteering(ActionEvent event) {
    }

    @FXML
    private void toActions(ActionEvent event) throws IOException {

    }

    @FXML
    private void logout(ActionEvent event) {
    }

    
}
