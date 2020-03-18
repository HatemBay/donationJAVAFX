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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siss_Ima
 */
public class ActivityAdminActivityCardController  {

    public Label name1;
    public Button update1;
    public Button remove1;
    private Activity activity;
      private Connection connection;
    private Statement statement;
   
    public ActivityAdminActivityCardController(){
        connection = DataBase.getInstance().getConnection();
    }
    

     public void init(Activity activity) throws SQLException {
        this.activity = activity;
        this.name1.setText(this.activity.getName_ac());
        ResultSet rs = connection.createStatement().executeQuery("select Id_ac from `donation`.`activity` where `Name_ev` ='"+ activity.getName_ev()+"'and `Name_ac` = '"+activity.getName_ac()+"'and `Duration` ='"+ activity.getDuration()+"'and `Description_ac` = '"+activity.getDescription_ac()+"'and `Type_ac` = '"+activity.getType_ac()+"';");
        while (rs.next()){
            activity.setId_ac(rs.getInt("Id_ac"));
            System.out.println("namebbv event: "+ activity.getId_ac());
        }
    }
    

    @FXML
    public void onClickUpdate1(ActionEvent event) throws IOException, SQLException {
        FXMLLoader ActivityAdminUpdateLoader = new FXMLLoader(getClass().getResource("ModifyActivity.fxml"));
        Parent ActivityAdminUpdateView = ActivityAdminUpdateLoader.load();
        ModifyActivityController ActivityAdminUpdateController = ActivityAdminUpdateLoader.getController();
        ActivityAdminUpdateController.init(activity);
        Stage ActivityAdminUpdateStage = new Stage();
        ActivityAdminUpdateStage.setScene(new Scene(ActivityAdminUpdateView));
        ActivityAdminUpdateStage.initModality(Modality.APPLICATION_MODAL);
        ActivityAdminUpdateStage.showAndWait();
    }

    @FXML
    public void onClickRemove1(ActionEvent event) throws  IOException, SQLException {
        ServiceActivity.getInstance().delete(activity.getId_ac());
       FXMLLoader ActivityAdminListLoader = new FXMLLoader(getClass().getResource("EventsAdminList.fxml"));
        Parent ActivityAdminUpdateView = ActivityAdminListLoader.load();
        Stage ActivityAdminUpdateStage = new Stage();
        ActivityAdminUpdateStage.setScene(new Scene(ActivityAdminUpdateView));
        ActivityAdminUpdateStage.initModality(Modality.APPLICATION_MODAL);
        ActivityAdminUpdateStage.showAndWait();
        
       
    }
    
}
