package com.example.lab;

import java.sql.SQLException;
import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Window;


public class CreateController {

    ObservableList<String> BookLanguage = FXCollections.observableArrayList("English","Hindi","Tamil");


    @FXML
    private TextField idfield;

    @FXML
    private TextField namefield;


    @FXML
    private TextField authorfield;

    @FXML
    private ComboBox language;

    @FXML
    private CheckBox checkBox1;
    @FXML
    private CheckBox checkBox2;
    @FXML
    private CheckBox checkBox3;
    @FXML
    private CheckBox checkBox4;

    @FXML
    private RadioButton rb1;
    @FXML
    private RadioButton rb2;

    @FXML
    public void initialize() throws SQLException {
        language.setItems(BookLanguage);


    }

    @FXML
    private Button submitbutton;

    @FXML
    public void ClearAll(ActionEvent event) throws SQLException {
        idfield.setText("");
        namefield.setText("");
        authorfield.setText("");
        language.setValue("language");
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
        checkBox4.setSelected(false);
        rb1.setSelected(false);
        rb2.setSelected(false);

    }

    @FXML
    public void Add(ActionEvent event) throws SQLException {

        Window owner = submitbutton.getScene().getWindow();

        System.out.println(idfield.getText());
        System.out.println(namefield.getText());
        System.out.println(authorfield.getText());
        System.out.println(language.getValue());
        if (checkBox1.isSelected()){
            System.out.println(checkBox1.getText());
        }
        if (checkBox2.isSelected()){
            System.out.println(checkBox1.getText());
        }
        if (checkBox3.isSelected()){
            System.out.println(checkBox1.getText());
        }
        if (checkBox4.isSelected()){
            System.out.println(checkBox1.getText());
        }
        if(rb1.isSelected()){
            System.out.println(rb1.getText());
        }

        if(rb2.isSelected()){
            System.out.println(rb2.getText());
        }

        if (idfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a book id");
            return;
        }
        if (namefield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a book name");
            return;
        }

        if (authorfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a author name");
            return;
        }

        if(rb1.isSelected() && rb2.isSelected() ){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please select one Cover");
            return;
        }


        int ID = Integer.parseInt(idfield.getText());
        String Bookname = namefield.getText();
        String author = authorfield.getText();
        String lan = String.valueOf(language.getValue());
        String gen="";
        if (checkBox1.isSelected()){
            gen+=checkBox1.getText()+",";
        }
        if (checkBox2.isSelected()){
            gen+=checkBox2.getText()+",";
        }
        if (checkBox3.isSelected()){
            gen+=checkBox3.getText()+",";
        }
        if (checkBox4.isSelected()){
            gen+=checkBox4.getText();
        }

        String cover="";
        if(rb1.isSelected()){
            cover=rb1.getText();
        }

        if(rb2.isSelected()){
            cover=rb2.getText();
        }
        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.InsertRecord(ID, Bookname,author,lan,gen,cover);
        infoBox("Book added!", null, "Success");
        idfield.setText("");
        namefield.setText("");
        authorfield.setText("");
        language.setValue("language");
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
        checkBox4.setSelected(false);
        rb1.setSelected(false);
        rb2.setSelected(false);
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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