package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Sneakers;

/**
 * The MainController class is responsible for controlling the main user
 * interface (Main.fxml) and handling user interactions.
 * Author: Jasroop Singh ID: 991715844
 */
public class MainController {

    // Static instance of Sneakers list
    static Sneakers _sneakersList = new Sneakers(null, null, 0, 0, 0, 0);

    @FXML
    private Button btnAdd; // Button used to add a new Sneakers
    @FXML
    private Button btnClose; // Button used to close the application
    @FXML
    private Button btnDisplay; // Button used to display Sneakers
    @FXML
    private Button btnEdit; // Button used to edit Sneakers

    // Initializes the controller class with the events
    @FXML
    private void initialize() {
        _sneakersList.loadSneakers();

        btnAdd.setOnAction(e -> onAddClicked());
        btnEdit.setOnAction(e -> onEditClicked());
        btnDisplay.setOnAction(e -> onDisplayClicked());
        btnClose.setOnAction(e -> onCloseClicked());
    }

    /**
     * Handles the action when the "Add" button is clicked.
     */
    private void onAddClicked() {
        try {
            // Load the Add.fxml UI
            Parent root = FXMLLoader.load(getClass().getResource("/views/Add.fxml"));
            Scene scene = new Scene(root);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        _sneakersList = AddController.getSneakersList();
    }

    /**
     * Handles the action when the "Edit" button is clicked.
     */
    private void onEditClicked() {
        try {
            // Load the Edit.fxml UI
            Parent root = FXMLLoader.load(getClass().getResource("/views/Edit.fxml"));
            Scene scene = new Scene(root);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        _sneakersList = EditController.getSneakersList();
    }

    /**
     * Handles the action when the "Display" button is clicked.
     */
    private void onDisplayClicked() {
        try {
            // Load the Display.fxml UI
            Parent root = FXMLLoader.load(getClass().getResource("/views/Display.fxml"));
            Scene scene = new Scene(root);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    /**
     * Handles the action when the "Close" button is clicked.
     */
    private void onCloseClicked() {

        // Create an alert
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to Exit?");
        alert.setContentText("Exit");

        // Show the alert and wait for user response
        ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

        // Close the application window
        if (result == ButtonType.OK) { // User clicked OK
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        }
    }

    /**
     * Returns the static Sneakers list.
     *
     * @return The Sneakers list.
     */
    public static Sneakers getSneakersList() {
        return _sneakersList;

    }

}
