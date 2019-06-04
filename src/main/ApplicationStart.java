package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.UTF8Control;

import java.util.Locale;
import java.util.ResourceBundle;

public class ApplicationStart extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("resources/uiresources", new Locale("mr"), new UTF8Control());
        Parent root = FXMLLoader.load(getClass().getResource("/login/login.fxml"), bundle);
        primaryStage.setTitle("Marathi Demo");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
