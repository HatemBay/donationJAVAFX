/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donationfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Hatem
 */
public class DisplayActionController implements Initializable {

    @FXML
    private AnchorPane main;
    @FXML
    private ImageView background;
    @FXML
    private VBox actioncontainer;
    @FXML
    private TextField search;
    @FXML
    private RadioButton sortname;
    @FXML
    private RadioButton sortdate;
    @FXML
    private Button association;
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
    private void searchaction(KeyEvent event) {
    }

    @FXML
    private void sortnameaction(ActionEvent event) {
    }

    @FXML
    private void sortdateaction(ActionEvent event) {
    }

    @FXML
    private void goAssociation(ActionEvent event) {
    }

    @FXML
    private void toHome(ActionEvent event) {
    }

    @FXML
    private void toProducts(ActionEvent event) {
    }

    @FXML
    private void toEvents(ActionEvent event) {
    }

    @FXML
    private void toDonations(ActionEvent event) {
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
