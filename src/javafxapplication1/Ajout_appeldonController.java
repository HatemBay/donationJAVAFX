/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Appel_don;
import com.donation.Entite.CurrentUser;
import com.donation.Service.ServiceAssociation;
import com.donation.Service.ServiceFos;
import com.donation.Service.service_appeldon;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class Ajout_appeldonController implements Initializable {

    ServiceAssociation SA = new ServiceAssociation();

    @FXML
    private TextField nom;

    @FXML
    private TextArea description;

    @FXML
    private ComboBox<String> categorie;

    @FXML
    private ImageView image;
    private ObservableList<String> list = FXCollections.observableArrayList();
    ;
    File file;
    String path;
    service_appeldon sa = new service_appeldon();
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

    @FXML
    void creerappeldon(ActionEvent event) {
        Appel_don a = new Appel_don();
        a.setCategory(categorie.getValue());
        System.out.println(categorie.getValue());
        
        a.setDescription(description.getText());
        System.out.println(description.getText());
        
        a.setImage(path);
        System.out.println(path);
        
        a.setNom(nom.getText());
        System.out.println(nom.getText());
        
        a.setUser_id(SA.currentAssociation.getId_Association());
        System.out.println(SA.currentAssociation.getId_Association());
        
        
        sa.ajoutappeldon(a);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Resultat:");
        alert.setContentText("appelDon est ajouté avec succés");

        alert.showAndWait();

    }

    @FXML
    void importerimage(ActionEvent event) {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {

            try {
                String imageFile = selectedFile.toURI().toURL().toString();
                this.file = selectedFile;
                System.out.println(imageFile);
                System.out.println(selectedFile.getAbsoluteFile());

                Image i = new Image(imageFile) {
                };

                try {
//                      path=PostFile.upload(selectedFile.getAbsolutePath());
                    image.setImage(i);

                } catch (Exception ex) {
                    //Logger.getLogger(AjoutcauseController.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println(path);

            } catch (MalformedURLException ex) {
                //Logger.getLogger(AjoutcauseController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        list.add(" Livres / Magazines'");
        list.add("Vêtements");
        list.add("Matériel spécialisé");
        categorie.setItems(list);
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
