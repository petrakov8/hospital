package Hospital;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Shedule_dermController implements Initializable {

    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane Slider;

    @FXML
    private Button appointment;

    @FXML
    private Button change;

    @FXML
    private Button delete;

    @FXML
    private TextField filterField;

    @FXML
    private Button log_out;
    
    @FXML
    private Button back;

    @FXML
    private TableView<ModelTable> table;

    @FXML
    private TableColumn<ModelTable, String> table_day;

    @FXML
    private TableColumn<ModelTable, String> table_end;

    @FXML
    private TableColumn<ModelTable, Integer> table_id;

    @FXML
    private TableColumn<ModelTable, String> table_lastname_c;

    @FXML
    private TableColumn<ModelTable, String> table_lastname_d;

    @FXML
    private TableColumn<ModelTable, String> table_name_c;

    @FXML
    private TableColumn<ModelTable, String> table_start;

    @FXML
    private TableColumn<ModelTable, String> table_sv;

    @FXML
    private TableColumn<ModelTable, Integer> table_tel_c;

    @FXML
    private TableColumn<ModelTable, Integer> table_tel_d;
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    private volatile boolean stop; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointment.setOnMouseClicked(event -> {
            openNewScene2("/Hospital/FXML_dermatologist.fxml");
        });
        delete.setOnMouseClicked(event -> {
            //thread.start();
            deleteApp();
        });
        back.setOnMouseClicked(event -> {
            openNewScene3("/Hospital/FXML_dermatologist.fxml");
        });
        
        
        change.setOnMouseClicked(event -> {
            DatabaseHandler dbHandler = new DatabaseHandler();
            oblist = dbHandler.getDatainfo();
            table.setItems(oblist);
        });
        Exit.setOnMouseClicked(event -> {
            System.exit(0);
        });
        Slider.setTranslateX(-200);
        Menu.setOnMouseClicked(event ->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(Slider);
            
            slide.setToX(-200);
            slide.play();
            
            Slider.setTranslateX(0);//
            
            slide.setOnFinished((ActionEvent e)->{
            Menu.setVisible(false);
            MenuBack.setVisible(true);
        });
            
        });
        MenuBack.setOnMouseClicked(event ->{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(Slider);
            
            slide.setToX(0);
            slide.play();
            
            Slider.setTranslateX(-200);//
            
            slide.setOnFinished((ActionEvent e)->{
            Menu.setVisible(true);
            MenuBack.setVisible(false);
        });
            
        });
        log_out.setOnMouseClicked(event -> {
            openNewScene("/Hospital/login.fxml");
        });
        // TODO
        table_id.setCellValueFactory(new PropertyValueFactory<>("idappointment"));
        table_day.setCellValueFactory(new PropertyValueFactory<>("dateA"));
        table_start.setCellValueFactory(new PropertyValueFactory<>("start_at"));
        table_end.setCellValueFactory(new PropertyValueFactory<>("end_at"));
        table_tel_c.setCellValueFactory(new PropertyValueFactory<>("telephoneC"));
        //table_name_c.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        table_name_c.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFirstnameC()));
        //table_lastname_c.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        table_lastname_c.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastnameC()));
        table_tel_d.setCellValueFactory(new PropertyValueFactory<>("telephoneD"));
        //table_lastname_d.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        table_lastname_d.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLastnameD()));
        table_sv.setCellValueFactory(new PropertyValueFactory<>("nameservice"));
        DatabaseHandler dbHandler = new DatabaseHandler();
        oblist = dbHandler.getDatainfo();
        table.setItems(oblist);
        
        FilteredList<ModelTable> filteredData = new FilteredList<>(oblist, p -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				String telC = String.valueOf(person.getTelephoneC());
                                String telD = String.valueOf(person.getTelephoneD());
				if (telC.toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				} else if (person.getDateA().contains(lowerCaseFilter)) {
					return true; // Filter matches last name.
				}
				return false; // Does not match.
			});
		});
        SortedList<ModelTable> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		table.setItems(sortedData);
    }    
    
    private void deleteApp(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        //table.setEditable(true);
        int id = table.getSelectionModel().getSelectedItem().getIdappointment();
        dbHandler.deleteApp(id);
    }
    
    public void openNewScene(String window){
        log_out.getScene().getWindow().hide();//прячем окно
                
                FXMLLoader loader = new FXMLLoader();//создание переменной
                loader.setLocation(getClass().getResource(window));
                
                try{
                    loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
    }
    
    public void openNewScene2(String window){
        appointment.getScene().getWindow().hide();//прячем окно
                
                FXMLLoader loader = new FXMLLoader();//создание переменной
                loader.setLocation(getClass().getResource(window));
                
                try{
                    loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
    }
    public void openNewScene3(String window){
        back.getScene().getWindow().hide();//прячем окно
                
                FXMLLoader loader = new FXMLLoader();//создание переменной
                loader.setLocation(getClass().getResource(window));
                
                try{
                    loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
    }
}
