package com.example.lab;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;

public class HomeController {

    @FXML
    private AnchorPane scene;

    @FXML
    private BorderPane borderpane;

    @FXML
    public void initialize() throws IOException {
        Image image = new Image("https://media.istockphoto.com/photos/stack-of-colorful-books-isolated-on-white-background-collection-of-picture-id1216658448?k=20&m=1216658448&s=612x612&w=0&h=hppCe-4-mf_YT5rM2VGUc3-FYT4CdIfcszUD5DWaRTo=");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setX(30);
        imageView.setY(10);
        scene.getChildren().setAll(imageView);
    }

    @FXML
    public void create(ActionEvent event) throws Exception {

        Node node = (Node)FXMLLoader.load(getClass().getResource("/com/example/lab/create.fxml"));
        scene.getChildren().setAll(node);


    }

    @FXML
    public void read(ActionEvent event) throws Exception {
        Node node = (Node)FXMLLoader.load(getClass().getResource("/com/example/lab/search.fxml"));
        scene.getChildren().setAll(node);

    }

    @FXML
    public void update(ActionEvent event) throws Exception {
        Node node = (Node)FXMLLoader.load(getClass().getResource("/com/example/lab/update.fxml"));
        scene.getChildren().setAll(node);

    }

    @FXML
    public void delete(ActionEvent event) throws Exception {
        Node node = (Node)FXMLLoader.load(getClass().getResource("/com/example/lab/Delete.fxml"));
        scene.getChildren().setAll(node);

    }
}
