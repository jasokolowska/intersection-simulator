package com.example.intersectionsimulator;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

public class IntersectionApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane anchorPane = setAnchorPane();
        Scene scene = new Scene(anchorPane, 800, 800);
        IntersectionController controller = new IntersectionController(new Intersection(), anchorPane);

        controller.run();

        anchorPane.setOnMouseClicked(event ->
                System.out.println("mouse pressed on (x - y): " + event.getSceneX() + " - "+ event.getSceneY())
                );

        stage.setTitle("Intersection Simulator");
        stage.setScene(scene);
        stage.show();
    }

    private AnchorPane setAnchorPane() {
        AnchorPane anchorPane = new AnchorPane();
        BackgroundSize size = new BackgroundSize(800,800, false, false, false, true);
        Image image = new Image("./com/example/intersectionsimulator/intersection.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                size);
        Background background = new Background(backgroundImage);
        anchorPane.setBackground(background);
        return anchorPane;
    }

    public static void main(String[] args) {
        launch();
    }
}