/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Command;
import com.donation.Entite.Product;
import com.donation.IService.CommandService;
import com.donation.IService.ProductService;
import com.donation.Utils.DataBase;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafxapplication1.DisplayProductUserController;
import static java.lang.Integer.parseInt;
import java.sql.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddCommandUserController implements Initializable {

    @FXML
    private TextField Id_Product;
    @FXML
    private TextField Quantity_Product;
    @FXML
    private TextField Paid;
    @FXML
    private DatePicker Date_Command;
    @FXML
    private Button AddCommand;
    com.donation.IService.ProductService SP = new ProductService();
    com.donation.IService.CommandService SC = new CommandService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private Button ListCommand;
    @FXML
    private Button Buy;
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

    public void setId_Product(TextField Id_Product) {
        this.Id_Product = Id_Product;
    }

    public void setQuantity_Product(TextField Quantity_Product) {
        this.Quantity_Product = Quantity_Product;
    }

    public void setPaid(TextField Paid) {
        this.Paid = Paid;
    }

    public void setDate_Command(DatePicker Date_Command) {
        this.Date_Command = Date_Command;
    }

    public void setAddCommand(Button AddCommand) {
        this.AddCommand = AddCommand;
    }

    public void setSP(ProductService SP) {
        this.SP = SP;
    }

    public void setSC(CommandService SC) {
        this.SC = SC;
    }

    public void setSte(Statement ste) {
        this.ste = ste;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }

    public TextField getId_Product() {
        return Id_Product;
    }

    public TextField getQuantity_Product() {
        return Quantity_Product;
    }

    public TextField getPaid() {
        return Paid;
    }

    public DatePicker getDate_Command() {
        return Date_Command;
    }

    public Button getAddCommand() {
        return AddCommand;
    }

    public ProductService getSP() {
        return SP;
    }

    public CommandService getSC() {
        return SC;
    }

    public Statement getSte() {
        return ste;
    }

    public Connection getCnx() {
        return cnx;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public boolean verifierchamps() {
        String vId_Product = Id_Product.getText();
        String vQuantity_Product = Quantity_Product.getText();
        String vPaid = Paid.getText();
        //String vDate_Command  = Date_Command.getText ();

        // vérifie les champs vides
        //vDate_Command.trim (). equals ("")
        if (vId_Product.trim().equals("") || vQuantity_Product.trim().equals("") || vPaid.trim().equals("")) {
            JOptionPane.showMessageDialog(null, "One or more fields are empty", "Empty fields", 2);
            return false;

        } else if (Integer.parseInt(vQuantity_Product) <= 0) {
            JOptionPane.showMessageDialog(null, "check for quantity", "quantity", 2);
            return false;
        } else if (Integer.parseInt(vPaid) < 0) {
            JOptionPane.showMessageDialog(null, "you must paid command", "paid", 2);
            return false;

        } else if (Float.parseFloat(vId_Product) <= 0) {
            JOptionPane.showMessageDialog(null, "check for product", "product", 2);
            return false;
        }

        return true;
    }

    @FXML
    private void AddCommand(ActionEvent event) throws SQLException, IOException {

        cnx = DataBase.getInstance().getConnection();
        ste = cnx.createStatement();
        com.donation.IService.ProductService SP = new ProductService();
        Product p = new Product();
        Command c = new Command();
        String vId_Product = Id_Product.getText();
        String vQuantity_Product = Quantity_Product.getText();
        String vPaid = Paid.getText();
        DatePicker Date_Commandd = (DatePicker) Date_Command;
        String date = (String) Date_Commandd.getValue().toString();
        date = date.substring(0, 4) + '/' + date.substring(5, 7) + '/' + date.substring(8);
        java.util.Date myDate = new java.util.Date(date);
        java.sql.Date sqldate = new java.sql.Date(myDate.getTime());
        //String vDate_Command  = Date_Command.getText ();

        if (verifierchamps() == true) {
            c.setId_Product(Integer.parseInt(vId_Product));
            c.setQuantity_Product(Integer.parseInt(vQuantity_Product));
            c.setPaid(Integer.parseInt(vPaid));
            c.setDate_Command(sqldate);
            System.out.println(c.toString());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("confirmation add");
            alert.setHeaderText("this confirmation about add");
            alert.setContentText("are you sure to add??");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                SC.addCommand(new Command(Integer.parseInt(Id_Product.getText()), Integer.parseInt(Quantity_Product.getText()),
                        Integer.parseInt(Paid.getText()), Date.valueOf(Date_Command.getValue().toString())));
                Alert a1 = new Alert(Alert.AlertType.INFORMATION);
                a1.setTitle("youp");
                a1.setHeaderText("msg for add");
                a1.setContentText("your addition was successful");
                a1.showAndWait();
                System.out.println("I'm a new command thank you ");
            } else {
                System.out.println("Cancel");
            }

            //SendMail.sendMail("khaoula.soury@esprit.tn", "product", "new product");
//            try {
//                Notification.sendNotification("add succeed", "youp", TrayIcon.MessageType.INFO);
//            } catch (AWTException ex) {
//                Logger.getLogger(AddProductAdminController.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (MalformedURLException ex) {
//                Logger.getLogger(AddProductAdminController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        //Parent tableview = FXMLLoader.load(getClass().getResource("Display.fxml"));
        //Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void ListCommand(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("DisplayCommand.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void addCommand(ActionEvent event) {
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
    private void toProducts(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DisplayProductUser.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        
    }

    @FXML
    private void toEvents(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("EventsUserList.fxml"));
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
