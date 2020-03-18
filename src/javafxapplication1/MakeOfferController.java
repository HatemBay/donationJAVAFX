/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;
import com.donation.Entite.Offre;
import com.donation.Service.ServiceAssociation;
import com.donation.Service.ServiceOffre;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import static com.donation.Utils.Whatsapp.ACCOUNT_SID;
import static com.donation.Utils.Whatsapp.AUTH_TOKEN;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author OusSama
 */
public class MakeOfferController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField contact;
    @FXML
    private TextField state;
    @FXML
    private Button submit;
    private JFXTextField msg;
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
    private void submit(ActionEvent event) {
         ServiceOffre o = new ServiceOffre();
     if (type.getText().equals("")) {
            msg.setText("Type  invalid ");
            } else if (contact.getText().equals("")) {
            msg.setText("contact invalid   ");
             } else if (state.getText().equals("")) {
            msg.setText("state invalid   ");
            } 

//if (!isFormValid()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error");
//            alert.setHeaderText(null);
//            alert.setContentText("Invalid Form Entries");
//            alert.initOwner(t3.getScene().getWindow());
//            alert.initModality(Modality.APPLICATION_MODAL);
//            alert.showAndWait();
//        }
            else 
            {
            Offre p = new Offre(type.getText(), contact.getText(),state.getText(),ServiceAssociation.currentAssociation.getId_Association()); 
           ServiceOffre of = new ServiceOffre();
            of.addoffre(p);} 
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("offer");
            alert.setHeaderText("Thank You ");
            alert.setContentText("Your offer has been added !");
            alert.showAndWait();
             Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+21692562931"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "5démmmm 💖!")
                .create();

        System.out.println(message.getSid());
    
 
   
    
    
    
    }

    private boolean isFormValid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    private void toAssociations(ActionEvent event) {
    }

    @FXML
    private void toVolunteering(ActionEvent event) {
    }

    @FXML
    private void toActions(ActionEvent event) {
    }

    @FXML
    private void logout(ActionEvent event) {
    }
    
    
    }

