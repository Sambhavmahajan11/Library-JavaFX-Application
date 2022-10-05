package com.example.lab;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginController {

    @FXML
    private TextField ID;


    @FXML
    private PasswordField Password;

    @FXML
    private Button submitButton;

    @FXML
    public void register(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();

        System.out.println(ID.getText());
        System.out.println(Password.getText());
        if (ID.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Please enter your name");
            return;
        }

        if (Password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Form Error!",
                    "Please enter a password");
            return;
        }

        String Id = ID.getText();
        String password = Password.getText();

        if(Id.equals("admin")&&password.equals("admin")){

            Stage stage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab/home.fxml"));
            stage.setTitle("Book details");
            stage.setScene(new Scene(root, 800, 650));
            stage.show();
            showAlert(Alert.AlertType.CONFIRMATION, "Login Successful!",
                    "Welcome " + ID.getText());
            ID.setText("");
            Password.setText("");
        }
        else if (Id.equals("user")&&password.equals("user")){

            Stage stage= new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/lab/search.fxml"));
            stage.setTitle("Book details");
            stage.setScene(new Scene(root, 800, 650));
            stage.show();
            showAlert(Alert.AlertType.CONFIRMATION, "Login Successful!",
                    "Welcome " + ID.getText());
            ID.setText("");
            Password.setText("");
        }
        else{
            showAlert(Alert.AlertType.ERROR, "Login Failure!",
                    "Please enter a correct ID/Password");
            return;
        }
    }

    private static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}