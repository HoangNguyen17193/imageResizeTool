package com.image.resize.tool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/layout/main.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        Scene scene = new Scene(rootNode, 600, 500);
        scene.getStylesheets().add("/styles/materialDark.css");

        stage.setTitle("Hello JavaFX and Maven");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
}
