/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Association;
import com.donation.Service.ServiceAssociation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class DisplayAssociationAdminController implements Initializable {

    @FXML
    private AnchorPane main;
    @FXML
    private VBox associationcontainer;
    @FXML
    private ImageView menu;

    com.donation.Service.ServiceAssociation SA = new ServiceAssociation();
    @FXML
    private TextField name;
    @FXML
    private TextField objectif;
    @FXML
    private PasswordField password;
    @FXML
    private Button browse;
    @FXML
    private TextArea about;
    @FXML
    private TextField type;
    @FXML
    private TextArea address;
    @FXML
    private TextField email;
    @FXML
    private Button update;
    @FXML
    private Pane modifform;
    @FXML
    private Button fermeture;
    @FXML
    private TextField image;
    @FXML
    private TextField search;
    @FXML
    private RadioButton sortdate;
    @FXML
    private RadioButton sortname;
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
        fermeture.setBackground(Background.EMPTY);
        try {

            modifform.setVisible(false);
            List<Association> associations = SA.getAssociations();
            Association connected = SA.getById(5);

            afficher(associations);
            Image men = new Image("pics/admin.jpg");
            menu.setPreserveRatio(false);
            menu.setImage(men);

        } catch (SQLException ex) {
            Logger.getLogger(DisplayAssociationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void afficher(List<Association> associations) throws SQLException {
        Association connected = SA.getById(5);
        associationcontainer.getChildren().clear();

        for (int i = 0; i < associations.size(); i++) {
            Association actuel = associations.get(i);
            Pane single = new Pane();
            single.setPrefHeight(150);
            single.setPrefWidth(547);
            single.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

            ImageView logo = new ImageView();
            logo.setLayoutX(3);
            logo.setLayoutY(3);
            logo.setFitWidth(136);
            logo.setFitHeight(147);
            logo.setPreserveRatio(false);
            Image log = new Image(actuel.getLogo_Association());
            logo.setImage(log);

            Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(166);
            name.setLayoutY(20);
            name.setStyle("-fx-font-size :18");
            name.setText(actuel.getNom_Association());

            Label type = new Label();
            type.setPrefHeight(27);
            type.setPrefWidth(USE_COMPUTED_SIZE);
            type.setLayoutX(166);
            type.setLayoutY(47);
            type.setStyle("-fx-font-size :15");
            type.setText(actuel.getType_Association());

            Label location = new Label();
            location.setPrefHeight(27);
            location.setPrefWidth(USE_COMPUTED_SIZE);
            location.setLayoutX(180);
            location.setLayoutY(72);
            location.setStyle("-fx-font-size :12");
            location.setText(actuel.getAddress_Association());

            ImageView marker = new ImageView();
            marker.setLayoutX(163);
            marker.setLayoutY(78);
            marker.setFitWidth(15);
            marker.setFitHeight(15);
            marker.setPreserveRatio(false);
            Image mark = new Image("pics/pin.png");
            marker.setImage(mark);

            Label description = new Label();
            description.setPrefHeight(27);
            description.setPrefWidth(USE_COMPUTED_SIZE);
            description.setLayoutX(164);
            description.setLayoutY(100);
            description.setStyle("-fx-font-size :12");
            description.setText(actuel.getDescription_Association());

            Button supprimer = new Button("X");
            supprimer.setPrefHeight(15);
            supprimer.setPrefWidth(15);
            supprimer.setLayoutX(519);
            supprimer.setLayoutY(3);
            supprimer.setVisible(true);

                supprimer.setOnMouseClicked((MouseEvent e) -> {
                    try {
                        SA.deleteAssociation(actuel);
                        AnchorPane redirect;
                        redirect = FXMLLoader.load(getClass().getResource("DisplayAssociationAdmin.fxml"));
                        main.getChildren().setAll(redirect);

                    } catch (SQLException ex) {
                        Logger.getLogger(DisplayAssociationController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(DisplayAssociationController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });

                
            

            single.getChildren().add(logo);
            single.getChildren().add(name);
            single.getChildren().add(type);
            single.getChildren().add(location);
            single.getChildren().add(marker);
            single.getChildren().add(description);
            single.getChildren().add(supprimer);
            

            associationcontainer.getChildren().add(single);
        }

    }

    @FXML
    private void searchAction(KeyEvent event) throws SQLException {
        String m = search.getText();
        ArrayList<Association> a = (ArrayList<Association>) SA.SearchAssociations(m);
        ObservableList<Association> obs = FXCollections.observableArrayList(a);
        afficher(obs);
    }

    @FXML
    private void sortdate(ActionEvent event) throws SQLException {
        associationcontainer.getChildren().clear();
        sortname.setSelected(false);
        afficher(SA.TrierAssociations(1));
    }

    @FXML
    private void sortname(ActionEvent event) throws SQLException {
        sortdate.setSelected(false);
        associationcontainer.getChildren().clear();
        afficher(SA.TrierAssociations(2));
    }

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
    private void toProducts(ActionEvent event) {
    }

    @FXML
    private void toEvents(ActionEvent event) {
    }

    @FXML
    private void toDonations(ActionEvent event) {
    }

    @FXML
    private void toAssociations(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DisplayAssociationAdmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toVolunteering(ActionEvent event) {
    }

    @FXML
    private void toActions(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DisplayActionAdmin.fxml"));
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
