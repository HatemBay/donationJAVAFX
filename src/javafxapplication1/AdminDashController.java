/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Users;
import com.donation.Service.ServiceUsers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
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
 * @author Hatem
 */
public class AdminDashController implements Initializable {

    
    ServiceUsers serUsers = new ServiceUsers();

    /**
     * Initializes the controller class.
     */
    
         @FXML
    private AnchorPane main;
    @FXML
    private VBox associationcontainer;
    @FXML
    private ImageView menu;

    ServiceUsers SU = new ServiceUsers();
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
        try {
            
            
            modifform.setVisible(false);
            List<Users> associations = SU.readAll();
            Users connected = SU.searchByIdU(SU.currentUser.getId_user());
            
            afficher(associations);
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }    


    private void afficher(List<Users> associations) throws SQLException{
        Users connected = SU.searchByIdU(SU.currentUser.getId_user());
        associationcontainer.getChildren().clear();
        
        for (int i = 0; i < associations.size(); i++) {
            Users actuel = associations.get(i);
            Pane single = new Pane();
            single.setPrefHeight(150);
            single.setPrefWidth(547);
            single.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
            
            
            Label name = new Label();
            name.setPrefHeight(27);
            name.setPrefWidth(USE_COMPUTED_SIZE);
            name.setLayoutX(166);
            name.setLayoutY(20);
            name.setStyle("-fx-font-size :18");
            name.setText(actuel.getFirst_name() + " " +actuel.getLast_name());
            
            Label tel = new Label();
            tel.setPrefHeight(27);
            tel.setPrefWidth(USE_COMPUTED_SIZE);
            tel.setLayoutX(166);
            tel.setLayoutY(47);
            tel.setStyle("-fx-font-size :15");
            tel.setText("" + actuel.getTel());
                        
            Label address = new Label();
            address.setPrefHeight(27);
            address.setPrefWidth(USE_COMPUTED_SIZE);
            address.setLayoutX(180);
            address.setLayoutY(72);
            address.setStyle("-fx-font-size :12");
            address.setText(actuel.getAddress());
            
            ImageView marker = new ImageView();
            marker.setLayoutX(163);
            marker.setLayoutY(78);
            marker.setFitWidth(15);
            marker.setFitHeight(15);
            marker.setPreserveRatio(false);
            Image mark = new Image("pics/pin.png");
            marker.setImage(mark);            
            
            Label bt = new Label();
            bt.setPrefHeight(27);
            bt.setPrefWidth(USE_COMPUTED_SIZE);
            bt.setLayoutX(164);
            bt.setLayoutY(100);
            bt.setStyle("-fx-font-size :12");
            bt.setText(actuel.getBlood_type());

            Button supprimer = new Button("X");
            supprimer.setPrefHeight(15);
            supprimer.setPrefWidth(15);
            supprimer.setLayoutX(519);
            supprimer.setLayoutY(3);
            supprimer.setVisible(true); 
              
         supprimer.setOnMouseClicked((MouseEvent e) -> {
                    try {
                        SU.delete(actuel);
                        AnchorPane redirect;
                        redirect = FXMLLoader.load(getClass().getResource("AdminDash.fxml"));
                        main.getChildren().setAll(redirect);

                    } catch (SQLException ex) {
                        System.out.println(ex);                   
                    } catch (IOException ex) {
                        System.out.println(ex);  
                    }
                });

            
            
            single.getChildren().add(name);
            single.getChildren().add(tel);
            single.getChildren().add(address);
            single.getChildren().add(marker);
            single.getChildren().add(bt);
            single.getChildren().add(supprimer);
            
            
            associationcontainer.getChildren().add(single);
        }
    
    
    }

    @FXML
    private void searchAction(KeyEvent event) throws SQLException {
        String m = search.getText();
        ArrayList<Users> a = (ArrayList<Users>) SU.SearchUsers(m);
        ObservableList<Users> obs = FXCollections.observableArrayList(a);
        afficher(obs);
    }

    @FXML
    private void sortFN(ActionEvent event) throws SQLException {
        associationcontainer.getChildren().clear();
        sortname.setSelected(false);
        afficher(SU.trier(1));
    }

    @FXML
    private void sortname(ActionEvent event) throws SQLException {
        sortdate.setSelected(false);
        associationcontainer.getChildren().clear();
        afficher(SU.trier(2));
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
