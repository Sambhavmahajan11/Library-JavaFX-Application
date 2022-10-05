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


public class UpdateController  {

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
    private Button autofillbutton;


    @FXML
    private Button updatebutton;


    @FXML
    public void ClearAll(ActionEvent event) throws SQLException {
        idfield.setText("");
        namefield.setText("");
        authorfield.setText("");
        language.setValue("Language");
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
        checkBox4.setSelected(false);
        rb1.setSelected(false);
        rb2.setSelected(false);


    }


    @FXML
    public void Autofill(ActionEvent event) throws SQLException {
        Window owner = autofillbutton.getScene().getWindow();
        if (idfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a book id");
            return;
        }
        JdbcDao jdbcDao = new JdbcDao();
        String ans =jdbcDao.getRecord(idfield.getText(),idfield.getText());
        if(ans=="No records found"){
            infoBox("Book not found", null, "Failure");
        }
        String[] arr = ans.split(" ");
        System.out.println(Arrays.toString(arr));
        namefield.setText(arr[2]);
        authorfield.setText(arr[8]);
        language.setValue(arr[4]);

        String[] s=arr[6].split(",");
        for(int i=0;i<arr.length;i++) {
            if (arr[i].equals("Fiction"))
                checkBox1.setSelected(true);
            if (arr[i].equals("Non-Fiction"))
                checkBox2.setSelected(true);
            if (arr[i].equals("Crime"))
                checkBox3.setSelected(true);
            if (arr[i].equals("Thriller"))
                checkBox4.setSelected(true);
        }

        if(arr[9].equals("Hard"))
            rb1.setSelected(true);
        if(arr[9].equals("Soft"))
            rb2.setSelected(true);


    }

    public void initialize() {
        language.setItems(BookLanguage);

    }

    @FXML
    public void Update(ActionEvent event) throws SQLException {

        Window owner = updatebutton.getScene().getWindow();

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
                    "Please enter your Book id");
            return;
        }
        if (namefield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a book name");
            return;
        }

        if (authorfield.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a author");
            return;
        }

        if(rb1.isSelected() && rb2.isSelected() ){
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please select one ");
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
            gen+=checkBox4.getText()+",";
        }

        String cover="";
        if(rb1.isSelected()){
            cover=rb1.getText();
        }

        if(rb2.isSelected()){
            cover=rb2.getText();
        }

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.UpdateRecord(ID, Bookname,author,lan,gen,cover);
        infoBox("Book Updated!", null, "Success");
        idfield.setText("");
        namefield.setText("");
        authorfield.setText("");
        language.setValue("Language");
        checkBox1.setSelected(false);
        checkBox2.setSelected(false);
        checkBox3.setSelected(false);
        checkBox4.setSelected(false);
        rb1.setSelected(false);
        rb2.setSelected(false);
        idfield.requestFocus();
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