package Hospital;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimationController implements Initializable {

    @FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle cirlc3;

    @FXML
    private Button start;

    @FXML
    void start(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setRotate(circle1,true,360,10);
        setRotate(circle2,true,180,18);
        setRotate(cirlc3,true,145,24);
        start.setOnMouseClicked(event -> {
            openNewScene("/Hospital/login.fxml");
        });
        
    } 
    private void setRotate(Circle c, boolean reverse, int angle, int duration){
        RotateTransition rt = new RotateTransition(Duration.seconds(duration),c);
        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(3);
        rt.setCycleCount(18);
        rt.play();
    }
     public void openNewScene(String window){
        start.getScene().getWindow().hide();//прячем окно
                
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
