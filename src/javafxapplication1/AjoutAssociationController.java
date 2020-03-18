/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Association;
import com.donation.Entite.Association2;
import com.donation.Service.ServiceAssociation;
import com.donation.Utils.DataBase;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tarek
 */
public class AjoutAssociationController implements Initializable {

    @FXML
    private TextField objectif;
    @FXML
    private TextField name;
    @FXML
    private Button add;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirm;
    @FXML
    private TextField email;
    @FXML
    private TextArea address;
    @FXML
    private TextField type;
    @FXML
    private TextArea about;
    @FXML
    private TextField logo;
    @FXML
    private Button browse;
    @FXML
    private ImageView background;
    @FXML
    private AnchorPane main;
    @FXML
    private Button add1;
    
    public AjoutAssociationController() {

        // initialize the Pattern object
        pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }
    
    // Email Regex java
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    // static Pattern object, since pattern is fixed
    private static Pattern pattern;

    // non-static Matcher object because it's created from the input String
    private Matcher matcher;
    
    com.donation.Service.ServiceAssociation SA = new ServiceAssociation();

    /**
     * This method validates the input email address with EMAIL_REGEX pattern
     *
     * @param email
     * @return boolean
     */
    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Image back = new Image("file:/C:/Users/Tarek/Desktop/ESPRIT/3A/Semestre2/MOBILE/Séance2/donationfx/src/uploads/background.jpg");
//        background.setImage(back);
        
        browse.setOnMouseClicked((MouseEvent e)->{
            
        final FileChooser fileChooser = new FileChooser();
        final Stage stage = null;

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            logo.setText(file.toURI().toString());
        }
        
        });  
    }    

    @FXML
    private void addAction(ActionEvent event) throws IOException, SQLException {
        

        
        String namee = name.getText();
        String login = email.getText();
        String pass = password.getText();
        String passc = confirm.getText();
        String adresse = address.getText();
        String typee = type.getText();
        String obj = objectif.getText();
        String desc = about.getText();

        //ste = con.createStatement();
        if ((!"".equals(namee)) && (!"".equals(login)) && (!"".equals(pass)) && (!"".equals(passc)) && (!"".equals(adresse)) && (!"".equals(typee)) && (!"".equals(obj))) {

            /*
        *
        *this is a test on email existance which is the unique key
        *
             */
            
            if (SA.readAllEmails().contains(email) == true) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Email already exists");
                alert.showAndWait();
                email.clear();
                password.clear();
                confirm.clear();
                return;
            }
            

            /*
            *
            *test on the email textfield with regex
             */
            if (!validateEmail(email.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Invalid email pattern");
                alert.showAndWait();
                email.clear();
                password.clear();
                confirm.clear();
                return;
            }
            
            /*
            *
            *test on password length
            *
             */
            if (pass.length() < 5) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Password too short, minimum length 5 characters");
                alert.showAndWait();
                password.clear();
                confirm.clear();
                return;
            }
            /*
        *
        *testing the match between the two passwords
        *
             */
            if (!passc.equals(pass)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Failure");
                alert.setHeaderText(null);
                alert.setContentText("Passwords don't match");
                alert.showAndWait();
                password.clear();
                confirm.clear();
                return;
            }
            
            /* ------> all conditions are set: adding association to database*/
            Association a1 = new Association(1, name.getText(), objectif.getText(), email.getText(),password.getText(), address.getText(), type.getText(), about.getText(), logo.getText());
            try {
                    SA.addAssociation(a1);  
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Your account has been created");
                    alert.showAndWait();
                    AnchorPane redirect;
                    redirect = FXMLLoader.load(getClass().getResource("Home.fxml"));
                    main.getChildren().setAll(redirect); 
                    

            } catch (SQLException ex) {
                Logger.getLogger(AjoutAssociationController.class.getName()).log(Level.SEVERE, null, ex);
            }


            
        } else {
            /*
            *
            *if one of the fields is empty
            *
             */
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all the required fields");
            alert.showAndWait();

            name.clear();
            email.clear();
            password.clear();
            confirm.clear();
            address.clear();
            objectif.clear();
            type.clear();
            about.clear();

        }

        
        
        
        

    }

    @FXML
    private void signInAssoc(ActionEvent event) {
    }
    
}


