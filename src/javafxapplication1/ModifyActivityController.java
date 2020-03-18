/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Activity;

import com.donation.Service.ServiceActivity;
import com.donation.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siss_Ima
 */
public class ModifyActivityController implements Initializable {

    
    public TextField t1;
    public TextField t;
    public Button b;
    public ComboBox<String> category;
    public TextArea yo;
    public TextField ppp;
    public Button b3;
    private Activity activity;


    private Connection connection;
    private Statement statement;
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
   
    public ModifyActivityController(){
        connection = DataBase.getInstance().getConnection();
    }
    
    
    

    
      public void init(Activity activity) throws SQLException {
        this.activity = activity;
        this.ppp.setText(activity.getName_ev());
        this.t1.setText(activity.getName_ac());
        this.t.setText(String.valueOf(activity.getDuration()));
        this.category.getSelectionModel().select(activity.getType_ac().toString());
        this.yo.setText(activity.getDescription_ac());
        String catVal = (String) category.getValue();
        activity.setType_ac(catVal); 
        
        ResultSet rs = connection.createStatement().executeQuery("select Id_ac from `donation`.`activity` where `Name_ev` ='"+ activity.getName_ev()+"'and `Name_ac` = '"+activity.getName_ac()+"'and `Duration` ='"+ activity.getDuration()+"'and `Description_ac` = '"+activity.getDescription_ac()+"'and `Type_ac` = '"+activity.getType_ac()+"';");
        while (rs.next()){
            activity.setId_ac(rs.getInt("Id_ac"));
            System.out.println("namebbv event: "+ activity.getId_ac());
        }
    }

    @FXML
    public void Ons (ActionEvent event) throws IOException, SQLException {
        if (!isFormValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Form Entries");
            alert.initOwner(ppp.getScene().getWindow());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        } else {
        activity.setName_ev(ppp.getText());
        System.out.println("ppp"+ ppp.getText());
        System.out.println("name event: "+ activity.getId_ac());
        activity.setName_ac(t1.getText());
        activity.setDuration(Double.valueOf(t.getText()));
        activity.setDescription_ac(yo.getText());
        String catVal = (String) category.getValue();
        activity.setType_ac(catVal);
        
        
                System.out.println("name event: "+ activity.getId_ac());

        //connection.createStatement().executeUpdate("UPDATE `donation`.`activity` SET `Name_ev` ='"+ activity.getName_ev()+"', `Name_ac` = '"+activity.getName_ac()+"', `Duration` ='"+ activity.getDuration()+"', `Description_ac` = '"+activity.getDescription_ac()+"', `Type_ac` = '"+activity.getType_ac()+"' WHERE `Id_ac` = '" +activity.getId_ac()+ "';");
        //connection.createStatement().executeUpdate("UPDATE `donation`.`activity` SET `Name_ev` = '"+activity.getName_ev()+"', `Name_ac` = '"+activity.getName_ac()+"' ,`Duration` = "+activity.getDuration()+", `Description_ac` = '"+activity.getDescription_ac()+"', `Type_ac` = '"+activity.getType_ac()+"' WHERE `Id_ac` = '"+activity.getId_ac()+"';");
        
        ServiceActivity.getInstance().update(activity);
        FXMLLoader AddActivityLoader = new FXMLLoader(getClass().getResource("EventsAdminList.fxml"));
        Parent AddActivityView = AddActivityLoader.load();
        Stage AddActivityStage = new Stage();
        AddActivityStage.setScene(new Scene(AddActivityView));
        AddActivityStage.initModality(Modality.APPLICATION_MODAL);
        AddActivityStage.showAndWait();
       
        }
    }

    @FXML
    public void Onreturn(ActionEvent event) throws IOException {
         FXMLLoader AddActivityLoader = new FXMLLoader(getClass().getResource("EventsAdminList.fxml"));
        Parent AddActivityView = AddActivityLoader.load();
        Stage AddActivityStage = new Stage();
        AddActivityStage.setScene(new Scene(AddActivityView));
        AddActivityStage.initModality(Modality.APPLICATION_MODAL);
        AddActivityStage.showAndWait();
    }
    private boolean isFormValid() {
        return !t1.getText().isEmpty() && !t.getText().isEmpty() && !yo.getText().isEmpty()  && !ppp.getText().isEmpty() ;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    category.getItems().addAll("Board Games","Competitions","hiking","cycling","reading books");
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
    private void toEvents(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EventsAdminList.fxml"));
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
