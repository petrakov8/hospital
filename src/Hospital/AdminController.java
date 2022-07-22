package Hospital;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
//import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AdminController implements Initializable {
    
    //final ObservableList options = FXCollections.observableArrayList();
    final ObservableList options = FXCollections.observableArrayList();
    final ObservableList options2 = FXCollections.observableArrayList();
    final ObservableList options3 = FXCollections.observableArrayList();
    final ObservableList options4 = FXCollections.observableArrayList();
    final ObservableList options5 = FXCollections.observableArrayList();
    String combo_s_value;
    String combo_d_value;
    String idser;
    String iddoc;
    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane Slider;
    
    @FXML
    private Button add_app;
    
    @FXML
    private Button add_cl;
    
    @FXML
    private Button check_cl;
    
    @FXML
    private Button log_out;
    
    @FXML
    private Button reset;
    
    @FXML
    private Button delete;

    @FXML
    private DatePicker date_picker;

    @FXML
    private ComboBox<String> doctor_combo;

    @FXML
    private TextField duration;
    
    @FXML
    private ComboBox<String> service_combo;
    
    @FXML
    private TextField start_time;

    @FXML
    private TextField telephone_field;
    
    @FXML
    private TextField telephone_c;
    
    @FXML
    private TextField thirdname_c;
    
    @FXML
    private TextField filterField;
    
     @FXML
    private TextField lastname_c;
     
    @FXML
    private TextField firstname_c;
    
    @FXML
    private Label check;
    
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
    
    @FXML
    private Label time;
    
    @FXML
    private Button timetable;

    @FXML
    void choose_combo_s(ActionEvent event) {
        combo_s_value = service_combo.getValue();
        //System.out.println(combo_s_value);
        fillComboBox_D1();  
    }
    
     @FXML
    void choose_combo_d(ActionEvent event) {
        combo_d_value = doctor_combo.getValue();
    }
    
    @FXML
    public void getDate(ActionEvent event) {
        //LocalDate myDate = date_picker.getValue();
        //String myFormattedDate = myDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        //.setText(myDate.toString())
        //createApp();
    }
    
    ObservableList<ModelTable> oblist = FXCollections.observableArrayList();
    private volatile boolean stop; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        timetable.setOnMouseClicked(event -> {
            //thread.start();
            openNewScene("/Hospital/shedule.fxml");
        });
        //lastname_c.cl
        Exit.setOnMouseClicked(event -> {
            //thread.start();
            System.exit(0);
        });
        
        reset.setOnMouseClicked(event -> {
            Reset();
        });
        log_out.setOnMouseClicked(event -> {
            openNewScene("/Hospital/login.fxml");
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
        // TODO
        Timenow();
        check.setVisible(false);
        fillComboBox_S();
        //service_combo.setItems(options); 
        
        add_app.setOnAction(event ->{
            System.out.println("Action");
            createApp();
        }
        );
        
        add_cl.setOnAction(event ->{
            System.out.println("Action2");
            createCl();
        });
        
        check_cl.setOnAction(event ->{
            chechCl();
        });
        
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
    private void chechCl(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        
        String telephone = telephone_field.getText();
        ResultSet result =  dbHandler.getClient(telephone);
        int counter = 0;
        try {
            while (result.next()){
                counter++ ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (counter>=1){check.setVisible(true);check.setText("Клієнт зареєстрований");check.setTextFill(Color.web("Green"));}
        else if(counter<1) {check.setVisible(true);check.setText("Клієнт не зареєстрований");check.setTextFill(Color.web("Red"));}
        System.out.println(counter+telephone);
    }
    
    private void createApp() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        
        String date = date_picker.getValue().toString();//??
        String start = start_time.getText()+":00";//??
        //String end = 
        String end = duration.getText()+":00";//??
        int telephone = Integer.parseInt(telephone_field.getText());
        
        int service = Integer.parseInt(idser);//??
        
        ResultSet result = dbHandler.find_doc(iddoc);
        try {
            while(result.next()){options5.add(result.getString("telephoneD"));
            //System.out.println(options);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }

        int doctor = Integer.parseInt(options5.get(0).toString());//??
        System.out.println(doctor);
        Appointment app = new Appointment(date, start, end, telephone, doctor, service);
        
        dbHandler.createApp(app);
        
    }
    
    private void createCl(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        
        int telephone = Integer.parseInt(telephone_c.getText());
        String name = firstname_c.getText();
        String last = lastname_c.getText();
        String third = thirdname_c.getText();
        
        //ResultSet result = dbHandler.;
        Clients cl = new Clients(telephone, name, last , third);
        
        dbHandler.CreateClients(cl);
    }
    
    public void fillComboBox_S(){
        //String query = "SELECT idservice from appointment ";
        //pst
        DatabaseHandler dbHandler = new DatabaseHandler();
        Services service = new Services();
        ResultSet result = dbHandler.fillCombo_ser(service);
        try {
            while(result.next()){options.add(result.getString("nameservice"));
            //System.out.println(options);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        service_combo.setItems(options); 
    }
    
    public void fillComboBox_D1(){
        //String query = "SELECT idservice from appointment ";
        //pst
        DatabaseHandler dbHandler = new DatabaseHandler();
        //Services service = new Services();
        ResultSet result = dbHandler.fillCombo_doc1(combo_s_value);
        try {
            while(result.next()){options2.add(result.getString("idservice"));
            //System.out.println(options);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sc;
        sc = options2.get(0).toString();
        idser = sc;
        //doctor_combo.setItems(options2); 
        //System.out.println(sc);
        
        DatabaseHandler dbHandler2 = new DatabaseHandler();
        //Services service = new Services();
        ResultSet result2 = dbHandler2.fillCombo_doc2(sc);
        try {
            while(result2.next()){options3.add(result2.getString("idspeciality"));
            //System.out.println(options);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //service_combo2.setItems(options3); 
        String sc1;
        sc1 = options3.get(0).toString();
        iddoc = sc1;
        //System.out.println("Тута"+sc1);
         
         DatabaseHandler dbHandler3 = new DatabaseHandler();
        //Services service = new Services();
        ResultSet result3 = dbHandler3.fillCombo_doc3(sc1);
        try {
            while(result3.next()){//options4.add(result3.getString("firstname"));
                                  options4.add(result3.getString("lastnameD"));
            //System.out.println(options);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        doctor_combo.setItems(options4); 
    }
    
    private void Timenow(){
        Thread thread = new Thread(() ->{
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            while(!stop){
                try{
                    Thread.sleep(1000);//1 second
                }catch(Exception ex){
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(()->{
                time.setText(timenow);
            });         
            }
            
        });
        thread.start();
    }
    
    private void Reset(){
        start_time.clear();
        duration.clear();
        telephone_field.clear();
        telephone_c.clear();
        firstname_c.clear();
        lastname_c.clear();
        thirdname_c.clear();
    }
    
    
    
    
}
