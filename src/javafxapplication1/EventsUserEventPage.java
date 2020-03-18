package javafxapplication1;

import com.donation.Entite.Activity;
import com.donation.Entite.Event;
import com.donation.IService.StorageService;
import com.donation.Utils.DataBase;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.frame.Frame;
import com.teamdev.jxbrowser.navigation.Navigation;
import com.teamdev.jxbrowser.view.javafx.BrowserView;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EventsUserEventPage implements Initializable {
    public ImageView poster;
    public Label name;
    public Label equipement;
    public Label description;
    public Label category;
    public Label dateTime;
    public Button close;
    public BorderPane locationPane;
    private Engine engine;
    private Browser browser;
    private BrowserView view;
    private Navigation navigation;
    private Frame frame;
    private Event event;
    private Activity activity;
   
    public Label name_ac;
    public Label duration;
    public Label Desc;
    public Label type_ac;
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
   
    public EventsUserEventPage(){
        connection = DataBase.getInstance().getConnection();
    }
    

    public void init(Event event) throws IOException, SQLException {
        this.event = event;
        this.name.setText(this.event.getName_ev());
        this.dateTime.setText(this.event.getDate_ev().toLocalDateTime().toLocalDate().toString());
        this.equipement.setText(this.event.getEquipement_ev());
        this.category.setText(this.event.getType_ev());
        this.description.setText(this.event.getDescription_ev());
        this.poster.setImage(new Image(StorageService.getInstance().download(this.event.getPoster())));
        frame.executeJavaScript("initDisplay(" + this.event.getLocation_ev().getLatitude() + "," + this.event.getLocation_ev().getLongitude() + ")");
        System.out.println("slmo3alaykom  " + this.event.getName_ev());
        ResultSet rs = connection.createStatement().executeQuery("select * from `donation`.`activity` where `Name_ev` ='"+ this.event.getName_ev()+"';");
        while (rs.next()){
            this.name_ac.setText(rs.getString("Name_ac"));
                    System.out.println("slmo3alaykom  " + this.event.getName_ev());

            this.duration.setText(rs.getString("Duration"));
            this.Desc.setText(rs.getString("Description_ac"));
            this.type_ac.setText(rs.getString("Type_ac"));
        }
    }
     

    @FXML
    public void onClose(ActionEvent actionEvent) {
        browser.close();
        // engine.close();
        close.getScene().getWindow().hide();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = Engine.newInstance(EngineOptions.newBuilder(RenderingMode.HARDWARE_ACCELERATED).licenseKey("6P830J66YAHR0SZU0NCR697SI131EC20F6ZG5KJ02OTYTXMYOZTKSBIZ7R7EY3WPWIPD").build());
        browser = engine.newBrowser();
        view = BrowserView.newInstance(browser);
        locationPane.setCenter(view);
        navigation = browser.navigation();
        navigation.loadUrlAndWait("http://localhost/l/", com.teamdev.jxbrowser.time.Timestamp.fromSeconds(45)); // Insert Maps URL
        frame = browser.mainFrame().get();
    }

    @FXML
    private void toHome(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
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
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EventUserList.fxml"));
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
        
    }

    @FXML
    private void toVolunteering(ActionEvent event) {
    }

    @FXML
    private void toActions(ActionEvent event) throws IOException {

    }

    @FXML
    private void logout(ActionEvent event) {
    }
}
