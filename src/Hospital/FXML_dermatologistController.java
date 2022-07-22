package Hospital;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class FXML_dermatologistController implements Initializable {
    
    @FXML
    private ImageView Exit;

    @FXML
    private Label Menu;

    @FXML
    private Label MenuBack;

    @FXML
    private AnchorPane Slider;

    @FXML
    private TextArea add_area;

    @FXML
    private TextField bules_field;

    @FXML
    private TextField change_field;

    @FXML
    private ComboBox<String> color2_combo;

    @FXML
    private ComboBox<String> color_combo;

    @FXML
    private TextArea complaints_area;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<String> dermographism_combo;

    @FXML
    private ComboBox<String> elasticity_combo;

    @FXML
    private ComboBox<String> glands_combo;

    @FXML
    private TextField hair_field;

    @FXML
    private ComboBox<String> humidity_combo;

    @FXML
    private TextField local_field;

    @FXML
    private ComboBox<String> muscles_combo;

    @FXML
    private TextField name_filed;

    @FXML
    private ComboBox<String> nodes_combo;

    @FXML
    private Button perscription;

    @FXML
    private Button print;

    @FXML
    private TextField rash_field;

    @FXML
    private ComboBox<String> service_combo;

    @FXML
    private TextField stan_field;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField telephone_field;

    @FXML
    private TextArea text;

    @FXML
    private TextField thirdname_field;

    @FXML
    private TextArea type_area;
    
    @FXML
    private Button log_out;
    
    @FXML
    private Button timetable;
    //
    ObservableList<String> list1 = FXCollections.observableArrayList("попередній профогляд","диспансеризація","звернення при захворюванні","профогляд декретованої групи");
    ObservableList<String> list2 = FXCollections.observableArrayList("звичайний","блідий","синюшний");
    ObservableList<String> list3 = FXCollections.observableArrayList("звичайний","блідий","синюшний","жовтяничний","сірий");
    ObservableList<String> list4 = FXCollections.observableArrayList("звичайна","суха","підвищена");
    ObservableList<String> list5 = FXCollections.observableArrayList("нормальна","знижена");
    ObservableList<String> list6 = FXCollections.observableArrayList("нормальний","знижений");
    ObservableList<String> list7 = FXCollections.observableArrayList("не змінений","змінений");
    ObservableList<String> list8 = FXCollections.observableArrayList("нормальний","червоний","червоний розлитий","стійкий білий",
                                                                     "змішаний","уртикарний","білий","нерозлитий","нестійкий","розлитий","стійкий", "рожевий");
    ObservableList<String> list9 = FXCollections.observableArrayList("не збільшено","збільшено","значно збільшено");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         timetable.setOnMouseClicked(event -> {
            openNewScene("/Hospital/shedule_derm.fxml");
        });
         log_out.setOnMouseClicked(event -> {
            openNewScene2("/Hospital/login.fxml");
        });
        service_combo.setItems(list1);
        color_combo.setItems(list2);
        color2_combo.setItems(list3);
        humidity_combo.setItems(list4);
        elasticity_combo.setItems(list5);
        muscles_combo.setItems(list6);
        glands_combo.setItems(list7);
        dermographism_combo.setItems(list8);
        nodes_combo.setItems(list9);
            
        perscription.setOnAction(e->{
            //print(text);
            String comment1 = service_combo.getValue();
            String comment2 = color_combo.getValue();
            String comment3 = color2_combo.getValue();
            String comment4 = humidity_combo.getValue();
            String comment5 = elasticity_combo.getValue();
            String comment6 = muscles_combo.getValue();
            String comment7 = glands_combo.getValue();
            String comment8 = dermographism_combo.getValue();
            String comment9 = nodes_combo.getValue();
            
            String text1 = surname_field.getText();
            String text2 = name_filed.getText();
            String text3 = thirdname_field.getText();
            String text4 = change_field.getText();
            String text5 = hair_field.getText();
            String text6 = bules_field.getText();
            String text7 = rash_field.getText();
            String text8 = local_field.getText();
            String text9 = stan_field.getText();
            String text10 = telephone_field.getText();
            
            String date1 = date.getValue().toString();
            
            String area1 = complaints_area.getText();
            String area2 = type_area.getText();
            String area3 = add_area.getText();
            
            
            text.appendText("                                           POZNYAKI CLINIC\n" +
                            "ОГЛЯД ДЕРМАТОВЕНЕРОЛОГА\n" +
                            "Вид обстеження: " + comment1 + "\n" +
                            text1 + " " + text2 + " " + text3 + "\n" +
                            "Номер телефону: " + text10 + "\n" + 
                            "Дата " +  date1 + "\n" +
                            "Скарги: " + area1 + "\n" + 
                            "ДЕРМАТОЛОГІЧНИЙ СТАТУС" + "\n" +
                            "Видимі слизові оболонки" + "\n" +
                            "Колір: " + comment2 + "\n" +
                            "Патологічні зміни" + text4 + "\n" +
                            "ШКІРНІ ПОКРИВИ" + "\n" + 
                            "Колір: " + comment3 + "\n" +
                            "Вологість: " + comment4 + "\n" +
                            "Еластичність: " + comment5 + "\n" +
                            "Тугор м'язів: " + comment6 + "\n" + 
                            "Стан сальний та потових залоз: " + comment7 + "\n" +
                            "ПРИДАТКИ ШКІРИ" + "\n" + 
                            "Волосся: " + text5 + "\n" +
                            "Нігтьові платівки: " + text6 + "\n" +
                            "Дермографзім " + comment8 + "\n" + 
                            "Висипання: " + text7 + "\n" +
                            "Характер висипів: " + area2 + "\n" +
                            "Лімфатичні вузли: " + comment9 + "\n" + 
                            "Локалізація: " + text8+ "\n" +
                            "Стан: " + text9+ "\n" +
                            "Додаткові данні: " + area3 
            );
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
        
        print.setOnAction(e->{
            print(text);
        });
        
        
    }

    private void print(Node node) {
    System.out.println("Creating a printer job...");

    PrinterJob job = PrinterJob.createPrinterJob();
    if (job != null) {
      System.out.println(job.jobStatusProperty().asString());

      boolean printed = job.printPage(node);
      if (printed) {
        job.endJob();
      } else {
        System.out.println("Printing failed.");
      }
    } else {
      System.out.println("Could not create a printer job.");
    }
  }
    
    private void perscription() {
        
        //text.appendText(string);
    }
    
    public void openNewScene(String window){
        timetable.getScene().getWindow().hide();//прячем окно
                
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
    
}
