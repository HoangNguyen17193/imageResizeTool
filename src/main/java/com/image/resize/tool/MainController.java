package com.image.resize.tool;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private TextField inputDirectory;
    @FXML
    private TextField outputDirectory;
    @FXML
    private Label messageLabel;
    @FXML
    private AnchorPane mainPanel;

    public void chooseFile() {
        Stage stage = (Stage) mainPanel.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file);
    }

    public void chooseInputDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(mainPanel.getScene().getWindow());
        if (directory == null) {
            inputDirectory.setText("No Directory selected");
        } else {
            inputDirectory.setText(directory.getAbsolutePath());
        }
    }

    public void chooseOutputDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(mainPanel.getScene().getWindow());
        if (directory == null) {
            outputDirectory.setText("No Directory selected");
        } else {
            outputDirectory.setText(directory.getAbsolutePath());
        }
    }

}
