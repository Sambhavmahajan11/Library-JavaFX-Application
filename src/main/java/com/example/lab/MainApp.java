package com.example.lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass());
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab/Login.fxml"));
        stage.setTitle("Login to Book database");
        stage.setScene(new Scene(root, 800, 650));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}