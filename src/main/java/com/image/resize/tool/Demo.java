package com.image.resize.tool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demo extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        String fxmlFile = "/layout/dialog.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        Scene scene = new Scene(rootNode, 100, 100);
        scene.getStylesheets().add("/styles/materialDark.css");
        scene.getStylesheets().add("/styles/resizeTool.css");

        stage.setTitle("Resize Tool");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
}
