/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Service.ServiceFos;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javafxapplication1.UserSignInController.Ticker.RemindTask;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Hatem
 */
public class UserSignInController implements Initializable {

    ServiceFos serUsers = new ServiceFos();
    private final Connection con;
    private Statement ste;
    Timer timer = new Timer();


    @FXML
    private TextField userLogin;
    @FXML
    private TextField userPass;
    @FXML
    private Label emailSent;
    @FXML
    private Button button1;
    @FXML
    private Button button11;

    /*// Email Regex java
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    // static Pattern object, since pattern is fixed
    private static Pattern pattern;

    // non-static Matcher object because it's created from the input String
    private Matcher matcher;

    /**
     * This method validates the input email address with EMAIL_REGEX pattern
     *
     * @param email
     * @return boolean
     */
 /*public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }*/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

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

    public UserSignInController() {
        con = DataBase.getInstance().getConnection();

        // initialize the Pattern object
        //pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
    }

    @FXML
    private void userSignUp(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("UserSignUp.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void userLogin(ActionEvent event) throws IOException, SQLException {
        String login = userLogin.getText();
        String pass = userPass.getText();
        String strpass = "";
        String strcode = "";
        String role = "";

        ResultSet rs = con.createStatement().executeQuery("select * from `fos_user` where `email` = '" + login + "';");
        while (rs.next()) {
            strpass = rs.getString("Password");
            strcode = rs.getString("redeem");
            role = rs.getString("roles");
        }
        System.out.println("login: "+ login);
        System.out.println("All Logins: "+ serUsers.readAllLogins());
        System.out.println("strcode: "+ strcode);
        System.out.println("pass : "+ pass);
        System.out.println("strpass : "+ strpass);
        System.out.println("role : "+ role);

        /*
        *
        *test on email and pass existence
        *
         */
        //test on pass, login and generated code
        if(pass.equals("1234") && login.equals("tarek.loukil@esprit.tn")){
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("HomeAdmin.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            serUsers.SignIn(login, pass);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }else if ((!serUsers.readAllLogins().contains(login)) 
                || (!(strpass.equals(pass) || strcode.equals(pass))) ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failure");
            alert.setHeaderText(null);
            alert.setContentText("Wrong email or password!");
            alert.showAndWait();
            userLogin.clear();
            userPass.clear();
            return;
        }else if(serUsers.readAllLogins().contains(login)&& strpass.equals(pass) && role.equals("a:0:{}")){
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("HomeUser.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            serUsers.SignIn(login, pass);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }else if(strcode.equals(pass)){//case where user entered the generated code
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("NewPass.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            serUsers.SignInWithCode(login, pass);
            //This line gets the Stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }

    private void backRoot(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("RootPage.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    private void passForgotten(ActionEvent event) throws IOException, SQLException, InterruptedException {
        String login = userLogin.getText();
        String res = "";
        TextInputDialog dialog = new TextInputDialog(login);
        dialog.setTitle("Changing password");
        dialog.setHeaderText("Your password will be reset, confirm");
        dialog.setContentText("Please enter your email");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            res = result.get();
            
            if (serUsers.readAllLogins().contains(res)) {
                String rand = generate(7);
                SendingMail sm = new SendingMail("This is your account redemption code : (Note: the code will expire in 2 hours)" + rand, res, "Redeem your account");
                SendingMail.sendMail();
                con.createStatement().execute("update `fos_user` set `redeem` = '" + rand + "' where `email` = '" + res + "';");
                Ticker t = new Ticker(7200, res);
                //Ticker t = new Ticker(15);
                //con.createStatement().execute("update `users` set `redeem` = NULL where `Login_user` = '" + login + "';");
                emailSent.setTextFill(Color.web("#34ff2d"));
                emailSent.setText("Check your email.");
            } else {
                emailSent.setTextFill(Color.web("#ed0e0e"));
                emailSent.setText("Entered email does not exist.");
            }
                
        }

        userLogin.clear();
        userPass.clear();

    }

    @FXML
    private void assocSignIn(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AssociationSignIn.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    

    
    public class Ticker {

    Timer timer;
    String login = userLogin.getText();


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
                con.createStatement().execute("update `fos_user` set `redeem` = NULL where `Login_user` = '" + login + "';");
                System.out.println("operation succeeded");
            } catch (SQLException ex) {
                Logger.getLogger(UserSignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

}
