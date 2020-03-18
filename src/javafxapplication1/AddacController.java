/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Activity;
import com.donation.Service.ServiceActivity;
import com.teamdev.jxbrowser.browser.Browser;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Siss_Ima
 */
public class AddacController implements Initializable {

    
    public TextField t1;
    public  TextField t;
    public  Button b;
    public  ComboBox<String> category;
    public  TextArea yo;
    public  Button b3;
    private Browser browser;
    public TextField ppp;
 

    /**
     * Initializes the controller class.
     */  

    @FXML
    private void OnSubmit(ActionEvent event) throws IOException {
        if (!isFormValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Form Entries");
            alert.initOwner(ppp.getScene().getWindow());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        } else {

        Activity activity = new Activity();
        activity.setName_ev(ppp.getText());
        activity.setName_ac(t1.getText());
        activity.setDuration(t.getWidth());
        activity.setDescription_ac(yo.getText());
        String catVal = (String) category.getValue();
        activity.setType_ac(catVal);
        ServiceActivity.getInstance().insert(activity);
         FXMLLoader ReturnActivityLoader = new FXMLLoader(getClass().getResource("EventsAdminList.fxml"));
        Parent ReturnActivityView = ReturnActivityLoader.load();
        EventsAdminList c = ReturnActivityLoader.getController();
        ((AnchorPane) t1.getParent()).getChildren().setAll(c.getList());
        
       
    }

    
    }
    private boolean isFormValid() {
        return !t1.getText().isEmpty() && !t.getText().isEmpty() && !yo.getText().isEmpty()  && !ppp.getText().isEmpty() ;
    }
   @Override
    public void initialize(URL url, ResourceBundle rb) {
    category.getItems().addAll("Board Games","Competitions","hiking","cycling","reading books");
        // TODO
    }  

  
    
}
