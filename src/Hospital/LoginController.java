/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital;

import com.jfoenix.controls.JFXSnackbar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


public class LoginController implements Initializable {

    @FXML
    private Button Enter;

    @FXML
    private ImageView Exit;
    
    @FXML
    private TextField login_text;

    @FXML
    private PasswordField password_field;
    
    @FXML
    private AnchorPane pane;
    
    @Override   
    public void initialize(URL url, ResourceBundle rb) {
        Exit.setOnMouseClicked(event -> {
            //thread.start();
            System.exit(0);
        });
        Enter.setOnAction(event -> {
            //переход на форму
                /*  Enter.getScene().getWindow().hide();//прячем окно
                
                FXMLLoader loader = new FXMLLoader();//создание переменной
                loader.setLocation(getClass().getResource("/Hospital/FXML_dermatologist.fxml"));
                
                try{
                    loader.load();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();*/ 
                
            String loginText = login_text.getText().trim();
            String loginPassword = password_field.getText().trim();
            
            if(!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);
            else 
                System.out.println("Login and password is empty");                 
        });
    }    

    private void loginUser(String loginText, String loginPassword) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        DatabaseHandler dbHandler = new DatabaseHandler();
        Doctors doctor = new Doctors();
        doctor.setTelephoneD(Integer.parseInt(loginText));
        doctor.setPassword(loginPassword);
        ResultSet result = dbHandler.getDoctor(doctor);
        
        int counter = 0;
        int idsp = 0; 
                //doctor.getIdspeciality();
        try{
            while(result.next()){
                counter++;
                idsp = result.getInt("idspeciality");
                System.out.println(idsp);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        if(counter >= 1){
            //System.out.println("Success");
            
            //System.out.println(idsp);
            if(idsp == 1) openNewScene("/Hospital/FXML_dermatologist.fxml");
            if(idsp == 2) openNewScene("/Hospital/admin.fxml");
        }
        else{error("Login or password is wrong");}
        
    }
    
    private void error(String s){
        JFXSnackbar snackbar = new JFXSnackbar(pane); 
        final JFXSnackbar.SnackbarEvent snackbarEvent = new JFXSnackbar.SnackbarEvent(new Label(s), Duration.seconds(3.33), null);
        snackbar.setPrefWidth(1280);
        snackbar.enqueue(snackbarEvent);
    }
    public void openNewScene(String window){
        Enter.getScene().getWindow().hide();//прячем окно
                
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
                stage.showAndWait();
    }
        
}
