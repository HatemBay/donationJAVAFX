/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Applicant;
import com.donation.Service.ServiceApplicant;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class ActionController implements Initializable {

    @FXML
    private AnchorPane affiche;
    @FXML
    private TableView<Applicant> table;


    private ComboBox<String> sortcombo;
    @FXML
    private TableColumn<Applicant, String> name;
    @FXML
    private TableColumn<Applicant, Date> date;
    @FXML
    private TableColumn<Applicant, String> location;
  
    private ObservableList<Applicant> applivantdata = FXCollections.observableArrayList();
    @FXML
    private TextField rech;
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
        com.donation.Service.ServiceApplicant SL = new ServiceApplicant();
        ObservableList<String> options = FXCollections.observableArrayList( "Date","Name" );
        
        name.setCellValueFactory(new PropertyValueFactory<Applicant, String>("description"));
        date.setCellValueFactory(new PropertyValueFactory<Applicant, Date>("Date_dispo"));
        location.setCellValueFactory(new PropertyValueFactory<Applicant, String>("Location_Applicant"));
        
        
        ObservableList<Applicant> res;
        try {
            res = FXCollections.observableArrayList(SL.getApplicants());
            table.setItems(res);
        } catch (SQLException ex) {
            Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
    }    

  
    @FXML
    private void delete(ActionEvent event) throws SQLException {
        Applicant a = table.getSelectionModel().getSelectedItem();
        ServiceApplicant as = new ServiceApplicant();
        as.deleteApplicant(a);
        resetTableData();
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
                      
        ModifierApplicantController.idmod = table.getSelectionModel().getSelectedItem().getId_Applicant();
        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("ModifierApplicant.fxml"));
                            Stage myWindow = (Stage) table.getScene().getWindow();
                            Scene sc = new Scene(root);
                            myWindow.setScene(sc);
                            myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
                            myWindow.show();
                        } catch (IOException ex) {
                            Logger.getLogger(ActionController.class.getName()).log(Level.SEVERE, null, ex);
                        }


    }
    
    
       
 public void resetTableData() throws SQLException
    {
        List<Applicant> listAPP = new ArrayList<>();
        ServiceApplicant sa =  new ServiceApplicant ();
        listAPP = sa.getApplicants();
        ObservableList<Applicant> data = FXCollections.observableArrayList(listAPP);
        table.setItems(data);
    }

  
    @FXML
    private void rech(ActionEvent event) throws SQLException {
        /*
        name.setCellValueFactory(new PropertyValueFactory<>("description"));
        date.setCellValueFactory(new PropertyValueFactory<>("Date_dispo"));
        location.setCellValueFactory(new PropertyValueFactory<>("Location_Applicant"));
            
            ServiceApplicant sr=new ServiceApplicant();
            List<Applicant> list = sr.getApplicants();
            
//            table.setItems((ObservableList<Applicant>) list);
            
            FilteredList<Applicant> filtredData= new FilteredList<>(applivantdata, b ->true);
            rech.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super Applicant> Applicant;
                filtredData.setPredicate((Applicant applicant) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    
                    String lowerCaseFilter = newValue.toLowerCase();
                    if(applicant.getLocation_Applicant().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (applicant.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Applicant> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
       
    }
*/
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
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EventsAdminList.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void toDonations(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FrontAdmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
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
    private void toVolunteering(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Action.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
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

    @FXML
    private void listeOffres(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AfficherOffre.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}  

