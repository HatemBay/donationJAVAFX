/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Service.ServiceAssociation;
import com.donation.Service.ServiceAssociation2;
import com.donation.Service.ServiceUsers;
import com.donation.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Hatem
 */
public class AssociationSignInController implements Initializable {
    ServiceAssociation serAssociation = new ServiceAssociation();
    ServiceUsers serUsers = new ServiceUsers();
    private final Connection con;
    private Statement ste;
    
    com.donation.Service.ServiceAssociation SA = new ServiceAssociation();

    private Label emailSent;
    @FXML
    private Button button1;
    @FXML
    private Button button11;
    @FXML
    private PasswordField aPass;
    @FXML
    private TextField aLogin;
    
    public String generate(int id) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = id;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);

        return generatedString;
    }

    public AssociationSignInController() {
        con = DataBase.getInstance().getConnection();

        // initialize the Pattern object
        //pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    private void associationSignUp(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AjoutAssociation.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    @FXML
    private void associationLogin(ActionEvent event) throws IOException, SQLException{
        String login = aLogin.getText();
        String pass = aPass.getText();
        String strpass = "";
        String strcode = "";

        ResultSet rs = con.createStatement().executeQuery("select * from `association` where `Email_Association` = '" + login + "';");
        while (rs.next()) {
            strpass = (String) rs.getString("Password_Association");
            strcode = (String) rs.getString("redeem");
            System.out.println(strpass);
            System.out.println(pass);
        }

        /*
        *
        *test on email and pass existence
        *
         */
        if ((!strpass.equals(pass)&& !strcode.equals(pass)) || !serAssociation.readAllEmails().contains(login)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Wrong email or password!");
            alert.showAndWait();
            aLogin.clear();
            aPass.clear();
            return;
        }else if(strpass.equals(pass)){//case where user entered the generated code
            SA.SignIn(login, pass);
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        }else{
            SA.SignInWithCode(login, strcode);
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("NewPassAssoc.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            serUsers.SignInWithCode(login, pass);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
            
        
        }
    }
    
    private void backRoot(ActionEvent event) throws IOException{
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("RootPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void passForgotten(ActionEvent event) throws IOException, SQLException, InterruptedException {
        String login = "";
        login = aLogin.getText();
        String res = "";
        TextInputDialog dialog = new TextInputDialog(login);
        dialog.setTitle("Changing password");
        dialog.setHeaderText("Your password will be reset, confirm");
        dialog.setContentText("Please enter your email");
        Optional<String> result = dialog.showAndWait();
        System.out.println("slm");
        if (result.isPresent()) {
            res = result.get();
            if (serAssociation.readAllEmails().contains(res)) {
                String rand = generate(7);
                SendingMail sm = new SendingMail("This is your account redemption code : (Note: the code will expire in 2 hours)" + rand, res, "Redeem your account");
                SendingMail.sendMail();
                con.createStatement().execute("update `association2` set `redeem` = '" + rand + "' where `Email_Association` = '" + res + "';");
                AssociationSignInController.Ticker t = new AssociationSignInController.Ticker(7200, res);
                emailSent.setTextFill(Color.web("#34ff2d"));
                emailSent.setText("Check your email.");
            } else {
                emailSent.setTextFill(Color.web("#ed0e0e"));
                emailSent.setText("Entered email does not exist.");
            }
            
        }

        aLogin.clear();
        aPass.clear();
    }

    @FXML
    private void userSignIn(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("UserSignIn.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public class Ticker {

    Timer timer;
    String login = aLogin.getText();


    public Ticker(int seconds, String s) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
        login = s;
	}

    class RemindTask extends TimerTask {
        @Override
        public void run() {
            try {
                timer.cancel(); //Terminate the timer thread
                con.createStatement().execute("update `association2` set `redeem` = Null where `Email_Association` = '" + login + "';");

                System.out.println("operation succeeded");
            } catch (SQLException ex) {
                Logger.getLogger(UserSignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
}
