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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DisplayCommandController implements Initializable {

    @FXML
    private TableColumn<Command, Integer> tcId_Command;
    @FXML
    private TableColumn<Command, Integer> tcId_Product;
    @FXML
    private TableColumn<Command, Integer> tcQuantity_Product;
    @FXML
    private TableColumn<Command, Integer> tcPaid;
    @FXML
    private TableColumn<Command, java.sql.Date> tcDate_Command;
    @FXML
    private Button sendmail;
    @FXML
    private Button export;
    @FXML
    private Button deleteCommand;
    @FXML
    private Button addProduct;

    /**
     * Initializes the controller class.
     */
     com.donation.IService.ProductService SP= new ProductService();
     com.donation.IService.CommandService SC=new CommandService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private TableView<Command> tableview;
    ObservableList<Command> listCommand  = FXCollections.observableArrayList();
    @FXML
    private TextField search;
    @FXML
    private Button Commandline;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                       Aff();
       export.setOnAction(event ->{pdf();});
       
    }    

       public void Aff(){
                        try {
        cnx=DataBase.getInstance().getConnection();
            ste = cnx.createStatement();
                        listCommand.clear();
        for(Command c: SC.getCommand())
             listCommand.add(c);
      } catch (SQLException ex) {
     }
       tcId_Command.setCellValueFactory(new PropertyValueFactory<>("Id_Command"));
         tcId_Product.setCellValueFactory(new PropertyValueFactory<>("Id_Product"));
         tcQuantity_Product.setCellValueFactory(new PropertyValueFactory<>("Quantity_Product"));
         tcPaid.setCellValueFactory(new PropertyValueFactory<>("Paid"));
         tcDate_Command.setCellValueFactory(new PropertyValueFactory<>("Date_Command"));
        tableview.setItems(listCommand);
     tableview.setEditable(true);
      tcId_Command.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      tcId_Product.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      tcQuantity_Product.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      tcPaid.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
     // tcDate_Command.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
      
    }
    @FXML
    private void deleteCommand() throws SQLException {
       tableview.setItems(listCommand);

             ObservableList<Command> allCommands,SingleDemandes ;
             allCommands=tableview.getItems();
             SingleDemandes=tableview.getSelectionModel().getSelectedItems();
             Command c = SingleDemandes.get(0);
             CommandService SC=new CommandService();
             Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation delete");
             alert.setHeaderText("this confirmation about delet");
             alert.setContentText("are you sure to delete??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
                 SC.deleteCommand(c.getId_Command());
             
               SingleDemandes.forEach(allCommands::remove);
             }else{System.out.println("Cancel");}
           
           }

   void pdf() {
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.tableview ;
           job.printPage(root);
           job.endJob();
            
       

  }}
    @FXML
    private void addProduct(ActionEvent event)   throws IOException,SQLException {

       Parent tableview = FXMLLoader.load(getClass().getResource("AddCommandUser.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); }  

    @FXML
    private void update_Product(TableColumn.CellEditEvent bb) throws SQLException {
        Command commandselected = tableview.getSelectionModel().getSelectedItem();
     commandselected.setId_Product(Integer.parseInt(bb.getNewValue().toString()));
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
                 SC.updateCommand(commandselected);
             
             }else{System.out.println("Cancel");} }

    @FXML
    private void update_Quantity(TableColumn.CellEditEvent bb) throws SQLException {
       Command commandselected = tableview.getSelectionModel().getSelectedItem();
     commandselected.setQuantity_Product(Integer.parseInt(bb.getNewValue().toString()));
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
                 SC.updateCommand(commandselected);
             
             }else{System.out.println("Cancel");} }

    @FXML
    private void Commandline(ActionEvent event) throws IOException {
   Parent tableview = FXMLLoader.load(getClass().getResource("DisplayCommandline.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();  }

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
