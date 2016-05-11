package com.image.resize.tool.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.image.resize.tool.exception.GeneralException;
import com.image.resize.tool.service.FileService;
import com.image.resize.tool.service.ImageService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    @FXML
    private ToggleGroup imageSize;
    @FXML
    private ImageView loader;
    @FXML
    private Image loaderImage;

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
        if (directory != null) {
            inputDirectory.setText(directory.getAbsolutePath());
        }
    }

    public void chooseOutputDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(mainPanel.getScene().getWindow());
        if (directory != null) {
            outputDirectory.setText(directory.getAbsolutePath());
        }
    }

    public void process() {
        messageLabel.setText("");
        if (areValidDirectories()) {
            disableLayout();

            Platform.runLater(() -> {
                imageProcess();
            });

            Platform.runLater(() -> {
                enableLayout();
            });
        } else {
            messageLabel.setText("Invalid input");
        }
    }

    private void disableLayout() {
        loader.setImage(new Image("/images/35.gif"));
        mainPanel.setDisable(true);
    }

    private void enableLayout() {
        loader.setImage(null);
        mainPanel.setDisable(false);
    }

    private void imageProcess() {
        int size = Integer.parseInt(imageSize.getSelectedToggle().getUserData().toString());
        List<String> filePaths = FileService.filterFilePaths(inputDirectory.getText().trim());
        for (String filePath : filePaths) {
            try {
                ImageService.createThumbnail(filePath, outputDirectory.getText().trim(), size);
            } catch (IOException e) {
                throw new GeneralException("cannot resize", e);
            }
        }
        messageLabel.setText("Done !!!!");
    }

    private boolean areValidDirectories() {
        if (inputDirectory == null || inputDirectory.getText().trim().isEmpty() && outputDirectory == null
                || outputDirectory.getText().trim().isEmpty()) {
            return false;
        } else if (new File(outputDirectory.getText().trim()).isDirectory()
                && new File(inputDirectory.getText().trim()).isDirectory()) {
            return true;
        }
        return false;
    }

}
