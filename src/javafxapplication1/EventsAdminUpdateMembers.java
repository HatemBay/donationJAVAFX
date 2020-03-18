package javafxapplication1;

import com.donation.Entite.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.io.IOException;
import javafx.fxml.FXML;

public class EventsAdminUpdateMembers {
    public Button close;
    public VBox list;
    private Event event;
    @FXML
    private VBox list2;
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
        //to do
    }
    @FXML
    public void onClose(ActionEvent actionEvent) {
        close.getScene().getWindow().hide();
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
