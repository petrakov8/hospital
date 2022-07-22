/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jekichoid
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("animation.fxml"));
        primaryStage.setTitle("Poznyaki Clinic");
        primaryStage.setScene(new Scene(root, 1280,800));
        primaryStage.show();
        
    }

    
    public static void main(String[] args) {
        launch(args);
    }
    
}
