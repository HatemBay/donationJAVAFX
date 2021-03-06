/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;


import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import com.donation.Entite.Event;
import com.donation.Entite.GeoCoordinates;
import com.donation.Service.ServiceEvent;
import com.donation.IService.StorageService;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.frame.Frame;
import com.teamdev.jxbrowser.navigation.Navigation;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Siss_Ima
 */
public class EventsAdminUpdate implements Initializable {

    public TextField t1;
    public TextArea t4;
    public ComboBox category;
    public DatePicker dateTime;
    public TextField t3;
    public Button submit;
    public TextField poster;
    public Button posterPicker;
    public Button close;
    public BorderPane locationPane;
    private FileChooser fileChooser;
    private File posterFileInput;
    private Engine engine;
    private Browser browser;
    private BrowserView view;
    private Navigation navigation;
    private Frame frame;
    private Event event;
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
    

     public void init(Event event) {
        this.event = event;
        this.category.getSelectionModel().select(event.getType_ev().toString());
        this.t1.setText(event.getName_ev());
        frame.executeJavaScript("initUpdate(" + event.getLocation_ev().getLatitude() + "," + event.getLocation_ev().getLongitude() + ")");
        this.t4.setText(event.getDescription_ev());
        this.dateTime.setValue(event.getDate_ev().toLocalDateTime().toLocalDate());
        this.poster.setText(event.getPoster());
        this.t3.setText(event.getEquipement_ev());
        String catVal = (String) category.getValue();
        event.setType_ev(catVal);  
    }
    @FXML
    public void onClickPosterPicker(ActionEvent actionEvent) {
        posterFileInput = fileChooser.showOpenDialog(t1.getScene().getWindow());
        poster.setText(posterFileInput.getName());
    }
   @FXML
    public void onSubmit(ActionEvent actionEvent) throws IOException {
      if (!isFormValid()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Form Entries");
            alert.initOwner(t3.getScene().getWindow());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.showAndWait();
        } else {
        event.setName_ev(t1.getText());
        event.setLocation_ev(new GeoCoordinates((Double) frame.executeJavaScript("getMarkerPosLat()"), (Double) frame.executeJavaScript("getMarkerPosLon()")));
        event.setDate_ev(Timestamp.valueOf(dateTime.getValue().atStartOfDay()));
        event.setDescription_ev(t4.getText());
        event.setEquipement_ev(t3.getText());
        if (!poster.getText().equals(event.getPoster())) {
            event.setPoster(StorageService.getInstance().upload(posterFileInput));
        } 
        String catVal = (String) category.getValue();
        event.setType_ev(catVal);
        ServiceEvent.getInstance().update(event);
        FXMLLoader AddActivityLoader = new FXMLLoader(getClass().getResource("EventsAdminList.fxml"));
        Parent AddActivityView = AddActivityLoader.load();
        Stage AddActivityStage = new Stage();
        AddActivityStage.setScene(new Scene(AddActivityView));
        AddActivityStage.initModality(Modality.APPLICATION_MODAL);
        AddActivityStage.showAndWait();
         browser.close();
        close.getScene().getWindow().hide();
      }
    }
    private boolean isFormValid() {
        return !t1.getText().isEmpty() && !t4.getText().isEmpty()  && dateTime.getValue() != null && dateTime.getValue().isAfter(LocalDate.now()) && !t3.getText().isEmpty() && !poster.getText().isEmpty() && frame.executeJavaScript("getMarkerPosLat()") != null;
    }

    @FXML
    public void onClose(ActionEvent actionEvent) {
        browser.close();
        // engine.close();
        close.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        category.getItems().addAll("MUSIC","PAINT","POETRY","THEATER","DANCE");
        fileChooser = new FileChooser();
        fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png"));
        engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED).licenseKey("6P830J66YAHR0SZU0NCR697SI131EC20F6ZG5KJ02OTYTXMYOZTKSBIZ7R7EY3WPWIPD").build());
        browser = engine.newBrowser();
        view = BrowserView.newInstance(browser);
        locationPane.setCenter(view);
        navigation = browser.navigation();
        navigation.loadUrlAndWait("http://localhost/l/", com.teamdev.jxbrowser.time.Timestamp.fromSeconds(45)); // Insert Maps URL
        frame = browser.mainFrame().get();
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