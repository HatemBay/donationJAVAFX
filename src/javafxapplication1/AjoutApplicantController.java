/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Applicant;
import com.donation.Service.ServiceApplicant;
import com.donation.Service.ServiceOffer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amin
 */
public class AjoutApplicantController implements Initializable {
    @FXML
    private AnchorPane main;
    @FXML
    private TextField location;
    private TextField date;
    @FXML
    private Button add;
    @FXML
    private TextField description;
    @FXML
    private Label error;
    @FXML
    private DatePicker datedispo;
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

    @FXML
    private void onsubmit(ActionEvent event) throws IOException {
         com.donation.Service.ServiceApplicant SA = new ServiceApplicant();
        
        java.sql.Date d = new  java.sql.Date(new Date(datedispo.getEditor().getText()).getTime());
         
        Applicant a1 = new Applicant( d, location.getText(), description.getText());
        try {
            
                SA.addApplicant(a1);             
                AnchorPane redirect;
                redirect = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
                main.getChildren().setAll(redirect);                             
           
            
        } catch (SQLException ex) {
            Logger.getLogger(AjoutApplicantController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void toHome(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toProducts(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DisplayProductUser.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        
    }

    @FXML
    private void toEvents(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EventsUserList.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toDonations(ActionEvent event) {
    }

    @FXML
    private void toAssociations(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void toVolunteering(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjoutApplicant.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toActions(ActionEvent event) throws IOException {

    }

    @FXML
    private void logout(ActionEvent event) {
    }

        
    }
    

