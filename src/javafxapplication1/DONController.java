/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Appel_don;
import com.donation.Service.ServiceAssociation;
//import Entities.Cause;
import com.donation.Service.service_appeldon;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Oussama
 */
public class DONController implements Initializable {

    @FXML
    private TableColumn<Appel_don, String> nom;

    @FXML
    private TableColumn<Appel_don, String> image;

    @FXML
    private TableColumn<Appel_don, String> description;

    @FXML
    private TableColumn<Appel_don, String> categorie;
    private int id;
    @FXML
    private TableView<Appel_don> listedon;
    private ObservableList<Appel_don> list;
    service_appeldon s = new service_appeldon();
    @FXML
    private TableColumn<?, ?> categorie1;
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
    void modifierdon(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifieDon.fxml"));

            Parent root1 = loader.load();
            ModifieDonController modif = loader.getController();

            Appel_don c = listedon.getSelectionModel().getSelectedItem();

            modif.settitre(c.getNom());
            modif.setcateg(c.getCategory());

            modif.setdes(c.getDescription());

            id = s.getid(c.getNom());
            modif.setid(s.getid(c.getNom()));
            modif.setIdimage(new Image("http://localhost/pi/" + s.getImage(id)));

            Stage stage = new Stage();

            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void supprimerdon(ActionEvent event) {
        Appel_don a = listedon.getSelectionModel().getSelectedItem();
        ObservableList<Appel_don> don;

        don = listedon.getSelectionModel().getSelectedItems();
        don.forEach(list::remove);
        s.supprimerappeldon(s.getid(a.getNom()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Resultat:");
        alert.setContentText("don est supprimé avec succés");

        alert.showAndWait();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("session: " + ServiceAssociation.currentAssociation.getId_Association());
        list = s.afficheparid(ServiceAssociation.currentAssociation.getId_Association());
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        image.setCellValueFactory(new PropertyValueFactory<>("image"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        categorie.setCellValueFactory(new PropertyValueFactory<>("category"));
        listedon.setItems(list);

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
