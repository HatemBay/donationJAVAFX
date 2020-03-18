/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

import com.donation.Entite.CommandLine;
import com.donation.Entite.Product;
import com.donation.IService.CommandLineService;
import com.donation.IService.ProductService;
import com.donation.Utils.DataBase;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DisplayCommandlineController implements Initializable {

    @FXML
    private TableColumn<CommandLine, Integer> tcId_CommandLine;
    @FXML
    private TableColumn<CommandLine, Integer> tcId_Command;
    @FXML
    private TableColumn<CommandLine, Integer> tcId_Product;
    @FXML
    private TableColumn<CommandLine, java.sql.Date> tcDate_liv;
    @FXML
    private TableColumn<CommandLine, String> tcState_Command;
    @FXML
    private TableColumn<CommandLine, String> Type_Command;
    @FXML
    private Button export;
    @FXML
    private TableView<CommandLine> tableview;
    ObservableList<CommandLine> list = FXCollections.observableArrayList();
    com.donation.IService.CommandLineService SCL = new CommandLineService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private Button Add;
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
            list.clear();
            for (CommandLine cl : SCL.getCommand()) {
                list.add(cl);
            }
        } catch (SQLException ex) {
        }
        tcId_CommandLine.setCellValueFactory(new PropertyValueFactory<>("Id_CommandLine"));
        tcId_Command.setCellValueFactory(new PropertyValueFactory<>("Id_Command"));
        tcId_Product.setCellValueFactory(new PropertyValueFactory<>("Id_Product"));
        tcDate_liv.setCellValueFactory(new PropertyValueFactory<>("Date_liv"));

        tcState_Command.setCellValueFactory(new PropertyValueFactory<>("State_Command"));
        Type_Command.setCellValueFactory(new PropertyValueFactory<>("Type_Command"));
        tableview.setItems(list);
        tableview.setEditable(true);
        Type_Command.setCellFactory(TextFieldTableCell.forTableColumn());
        // tcDate_liv.setCellFactory(TextFieldTableCell.forTableColumn());
        tcId_CommandLine.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tcState_Command.setCellFactory(TextFieldTableCell.forTableColumn());
        tcId_Command.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tcId_Product.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
        export.setOnAction(event -> {
            pdf();
        });

    }

    void pdf() {
        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(primaryStage);

            Node root = this.tableview;
            job.printPage(root);
            job.endJob();

        }
    }

    @FXML
    private void Add(ActionEvent event) throws IOException {
        Parent tableview = FXMLLoader.load(getClass().getResource("AddCommandLine.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
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
