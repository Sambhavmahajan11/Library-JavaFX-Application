package com.example.lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class DeleteController {


    @FXML
    private TextField idfield;

    @FXML
    private Button deletebutton;

    @FXML
    private Button deleteallbutton;

    @FXML
    public void DeleteAll(ActionEvent event) throws SQLException {

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.DeleteAll();


    }

    @FXML
    public void delete(ActionEvent event) throws SQLException {

        Window owner = deletebutton.getScene().getWindow();

        System.out.println(idfield.getText());
        if (idfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Error!",
                    "Please enter book id/name");
            return;
        }


        String Name = idfield.getText();

        JdbcDao jdbcDao = new JdbcDao();

        String ans=jdbcDao.getRecord(idfield.getText(),idfield.getText());
        if(ans=="No records found"){
            infoBox("Book not found", null, "Failure");
        }
        else {
            String[] arr = ans.split(" ");
            String message = arr[2] + " has been deleted!!";
            jdbcDao.DeleteRecord(Name);
            infoBox(message, null, "Success");
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
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
