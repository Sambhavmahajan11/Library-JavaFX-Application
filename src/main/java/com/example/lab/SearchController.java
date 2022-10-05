package com.example.lab;

import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class SearchController {

    @FXML
    private TextField idfield;

    @FXML
    private Button searchbutton;

    @FXML
    private Button searchallbutton;

    @FXML
    public void search(ActionEvent event) throws SQLException {

        Window owner = searchbutton.getScene().getWindow();

        System.out.println(idfield.getText());
        if (idfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Please enter book id/name");
            return;
        }


        String Name = idfield.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.searchRecord(Name);


    }

    @FXML
    public void searchAll(ActionEvent event) throws SQLException {

        Window owner = searchallbutton.getScene().getWindow();


        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.SearchAll();


    }


    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

}