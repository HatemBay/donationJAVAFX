/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.Command;
import com.donation.Entite.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import com.donation.Entite.Pdf;
import com.donation.Entite.Product;
import com.donation.Utils.DataBase;
import com.donation.IService.CommandLineService;
import com.donation.IService.CommandService;
import com.donation.IService.ProductService;
import com.itextpdf.text.DocumentException;
import com.stripe.Stripe;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javax.swing.text.Document;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
import doryan.windowsnotificationapi.fr.Notification;
import donationfx.SendMail;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import javafx.print.PrinterJob;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DisplayProductAssociationController implements Initializable {

    @FXML
    private TableView<Product> tableview;
    @FXML
    private TableColumn<Product, String> tcName_Product;
    @FXML
    private TableColumn<Product, Integer> tcQuantity_Total;
    @FXML
    private TableColumn<Product, Float> tcPrice_Product;
    @FXML
    private TableColumn<Product, String> tcDescription_Product;
    @FXML
    private TableColumn<Product, Integer> tcQuantity_Remaining;
    ObservableList<Product> listproduct = FXCollections.observableArrayList();
    com.donation.IService.ProductService SP = new ProductService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private Button Buy;
    @FXML
    private TableColumn<Product, Integer> tcId_Product;
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

    /**
     * Initializes the controller class.
     */
    public void Aff() {
        try {
            cnx = DataBase.getInstance().getConnection();
            ste = cnx.createStatement();
            listproduct.clear();
            for (Product p : SP.getProducts()) {
                listproduct.add(p);
            }
        } catch (SQLException ex) {
        }
        tcId_Product.setCellValueFactory(new PropertyValueFactory<>("Id_Product"));
        tcName_Product.setCellValueFactory(new PropertyValueFactory<>("Name_Product"));
        tcQuantity_Total.setCellValueFactory(new PropertyValueFactory<>("Quantity_Total"));
        tcPrice_Product.setCellValueFactory(new PropertyValueFactory<>("Price_Product"));
        tcDescription_Product.setCellValueFactory(new PropertyValueFactory<>("Description_Product"));
        tcQuantity_Remaining.setCellValueFactory(new PropertyValueFactory<>("Quantity_Remaining"));
        tableview.setItems(listproduct);
        tableview.setEditable(true);
        tcId_Product.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tcName_Product.setCellFactory(TextFieldTableCell.forTableColumn());
        tcQuantity_Total.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tcPrice_Product.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        tcDescription_Product.setCellFactory(TextFieldTableCell.forTableColumn());
        tcQuantity_Remaining.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
    }

    @FXML
    private void addCommand(ActionEvent event) throws IOException, SQLException {

        Parent tableview = FXMLLoader.load(getClass().getResource("AddCommandAssociation.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
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
