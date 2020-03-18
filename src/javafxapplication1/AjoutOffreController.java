/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Offer;
import com.donation.Service.ServiceOffer;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amin
 */
public class AjoutOffreController implements Initializable {
    @FXML
    private TextField discription;
    @FXML
    private TextField Type;
    @FXML
    private TextField condition;
    @FXML
    private ChoiceBox post;
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
    @FXML
    private Button home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        post.setItems(FXCollections.observableArrayList(
                "Medecin", "Enseignat", "Volentaire")
        );
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException {
        
        Offer e = new Offer();
        
        e.setCondition(Type.getText());
        e.setDescription(discription.getText());
        e.setPost((String) post.getValue().toString());
        e.setDescription(discription.getText());
      LocalDate dd = LocalDate.now();
      Date date = java.sql.Date.valueOf(dd);
        e.setDate_ajout(date);
        ServiceOffer so = new ServiceOffer();
        so.addOffer(e);
        
        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("Home.fxml"));
                            Stage myWindow = (Stage) post.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            System.out.println(ex);
                        }
    }

    @FXML
    private void toHome(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toProducts(ActionEvent event) {
        
    }

    @FXML
    private void toEvents(ActionEvent event) {
    }

    @FXML
    private void toDonations(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Front.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toAssociations(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DisplayAssociation.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toVolunteering(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjoutOffre.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toActions(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DisplayAction.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void logout(ActionEvent event) {
    }

    
    
}
