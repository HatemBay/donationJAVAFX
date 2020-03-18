package javafxapplication1;

import com.donation.Entite.Activity;
import com.donation.Entite.Event;
import com.donation.Service.ServiceActivity;
import com.donation.Service.ServiceEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class EventsAdminList implements Initializable {

    public Button add;
    public VBox list;
    @FXML
    public ScrollPane sp;
    @FXML
    public TextField query;
    @FXML
    private Button search;
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

    public VBox getList() {
        return list;
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Can Do It Functionally
        ServiceEvent.getInstance().displayAll().forEach(event -> {
            try {
                list.getChildren().add(generateAdminEventCard(event));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
          ServiceActivity.getInstance().displayAll().forEach(activity -> {
            try {
                try {
                    list.getChildren().add(generateAdminEventCardd(activity));
                } catch (SQLException ex) {
                    Logger.getLogger(EventsAdminList.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    
    
    @FXML
    public void onClickAdd(ActionEvent actionEvent) throws IOException {
        Parent EventsAdminAddView = FXMLLoader.load(getClass().getResource("AddEvent.fxml"));
        sp.setContent(EventsAdminAddView);
        list.getChildren().clear();
        initialize(null, null);
    }
    private Parent generateAdminEventCard(Event event) throws IOException {
        FXMLLoader eventCardLoader = new FXMLLoader(getClass().getResource("EventsAdminEventCard.fxml"));
        Parent eventCardView = eventCardLoader.load();
        EventsAdminEventCard eventCardController = eventCardLoader.getController();
        eventCardController.init(event);
        return eventCardView;
    }
    private Parent generateAdminEventCardd(Activity activity) throws IOException, SQLException {
        FXMLLoader activityCardLoader = new FXMLLoader(getClass().getResource("ActivityAdminActivityCard.fxml"));
        Parent activityCardView = activityCardLoader.load();
        ActivityAdminActivityCardController activityCardController = activityCardLoader.getController();
        activityCardController.init(activity);
        return activityCardView;
}
     @FXML
    public void onClickSearch(ActionEvent actionEvent) throws SQLException {
                list.getChildren().clear();

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