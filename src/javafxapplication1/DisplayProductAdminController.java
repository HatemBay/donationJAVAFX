/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication1;

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
public class DisplayProductAdminController implements Initializable {
 com.donation.IService.ProductService SP= new ProductService();
    private Statement ste;
    private Connection cnx;
    @FXML
    private BarChart<String, Number> Barchart;
    @FXML
    private TextField search;
    @FXML
    private Button addProduct;
    @FXML
    private Button deleteProduct;
    @FXML
    private Button export;
    @FXML
    private Button sendmail;
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
    @FXML
    private TableColumn<Product, Integer> tcId_admin;
    @FXML
    private TableColumn<Product, Integer> tcId_Association;
    @FXML
    private TableView<Product> tableview;
     ObservableList<Product> listproduct  = FXCollections.observableArrayList();
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
     
       public void Aff(){
                        try {
        cnx=DataBase.getInstance().getConnection();
            ste = cnx.createStatement();
                        listproduct.clear();
        for(Product p: SP.getProduct())
             listproduct.add(p);
      } catch (SQLException ex) {
     }
       tcName_Product.setCellValueFactory(new PropertyValueFactory<>("Name_Product"));
         tcQuantity_Total.setCellValueFactory(new PropertyValueFactory<>("Quantity_Total"));
         tcPrice_Product.setCellValueFactory(new PropertyValueFactory<>("Price_Product"));
         tcDescription_Product.setCellValueFactory(new PropertyValueFactory<>("Description_Product"));
         tcQuantity_Remaining.setCellValueFactory(new PropertyValueFactory<>("Quantity_Remaining"));
         tcId_admin.setCellValueFactory(new PropertyValueFactory<>("Id_admin"));
         tcId_Association.setCellValueFactory(new PropertyValueFactory<>("Id_Association"));
        tableview.setItems(listproduct);
     tableview.setEditable(true);
      tcName_Product.setCellFactory(TextFieldTableCell.forTableColumn());
      tcQuantity_Total.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      tcPrice_Product.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
      tcDescription_Product.setCellFactory(TextFieldTableCell.forTableColumn());
      tcQuantity_Remaining.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      tcId_admin.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      tcId_Association.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                     Aff();
       export.setOnAction(event ->{pdf();});
            Product productselected = tableview.getSelectionModel().getSelectedItem();

            sendmail.setOnAction(event->{SendMail.sendMail("khaoulasoury@gmail.com", "sayi", productselected.getName_Product()+" "+productselected.getDescription_Product()+"youp");
});

        RechercheAV();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("courbe de vente");
       try {
           series.getData().add(new XYChart.Data<>("taux de vente", SP.SalesRate(916)));
           series.getData().add(new XYChart.Data<>("Total prix",SP.TotalPrise(916)));
        Barchart.getData().addAll(series);
     
       } catch (SQLException ex) {
           Logger.getLogger(DisplayProductAdminController.class.getName()).log(Level.SEVERE, null, ex);
       }
    }    

    @FXML
    private void addProduct(ActionEvent event) throws IOException,SQLException {
       Parent tableview = FXMLLoader.load(getClass().getResource("AddProductAdmin.fxml"));
        Scene sceneview = new Scene(tableview);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); }

    @FXML
    private void deleteProduct() throws SQLException,AWTException {
   tableview.setItems(listproduct);

             ObservableList<Product> allProducts,SingleDemandes ;
             allProducts=tableview.getItems();
             SingleDemandes=tableview.getSelectionModel().getSelectedItems();
             Product p = SingleDemandes.get(0);
             ProductService SP = new ProductService(); 
             Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation delete");
             alert.setHeaderText("this confirmation about delet");
             alert.setContentText("are you sure to delete??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
               SP.deleteProduct(p.getId_Product());
               SingleDemandes.forEach(allProducts::remove);
             }else{System.out.println("Cancel");}
           
             RechercheAV(); }
     public void RechercheAV(){
       FilteredList<Product> filteredData = new FilteredList<>(listproduct, b -> true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(product -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				if (product.getName_Product().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (String.valueOf(product.getPrice_Product()).indexOf(lowerCaseFilter)!=-1) {
					return true; 
				}
				else
				
				if (String.valueOf(product.getId_Product()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<Product> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		
		
		tableview.setItems(sortedData);
    }
   void pdf() {
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.tableview ;
Node root1=this.Barchart;    
           job.printPage(root);
           job.endJob();
            
       

  }}
   void stripe(){
   Stripe.apiKey = "sk_test_4eC39HqLyjWDarjtT1zdp7dc";

Map<String, Object> card = new HashMap<>();
card.put("number", "4242424242424242");
card.put("exp_month", 2);
card.put("exp_year", 2021);
card.put("cvc", "314");
Map<String, Object> params = new HashMap<>();
params.put("type", "card");
params.put("card", card);

   }
    @FXML
    private void update_Name(TableColumn.CellEditEvent bb) throws SQLException {
     Product productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setName_Product(bb.getNewValue().toString());
  Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}
    }

    @FXML
    private void update_Quantity_Total(TableColumn.CellEditEvent b) throws SQLException {
     Product productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setQuantity_Total(Integer.parseInt(b.getNewValue().toString()));
       Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}
    }

    @FXML
    private void update_Price_Product(TableColumn.CellEditEvent b) throws SQLException {
     Product productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setPrice_Product(Float.parseFloat(b.getNewValue().toString()));
       Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}}

    @FXML
    private void update_Description_Product(TableColumn.CellEditEvent b) throws SQLException {
    Product productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setDescription_Product(b.getNewValue().toString());
       Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}}

    @FXML
    private void update_Quantity_Remaining(TableColumn.CellEditEvent b) throws SQLException {
         Product productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setQuantity_Remaining(Integer.parseInt(b.getNewValue().toString()));
       Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}}

    @FXML
    private void update_Id_admin(TableColumn.CellEditEvent b) throws SQLException {
     Product productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setId_admin(Integer.parseInt(b.getNewValue().toString()));
     Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");}}

    @FXML
    private void update_Id_Association(TableColumn.CellEditEvent bb) throws SQLException {
     Product productselected = tableview.getSelectionModel().getSelectedItem();
     productselected.setId_Association(Integer.parseInt(bb.getNewValue().toString()));
        Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SP.updateProduct(productselected);
             }else{System.out.println("Cancel");} }

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
    private void toProducts(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("DisplayProductAdmin.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        
    }

    @FXML
    private void toEvents(ActionEvent event) throws IOException {
        
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
