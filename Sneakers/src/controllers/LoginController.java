package controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The LoginController class manages the login user interface and
 * authentication.
 * Author: Jasroop Singh ID: 991715844
 */
public class LoginController {

    @FXML
    private TextField _usr; // Text field for entering the username
    @FXML
    private TextField _password; // Text field for entering the password

    @FXML
    private Button _btnLogin; // Button used to initiate login
    @FXML
    private Button _btnClose; // Button used to close the login window
    @FXML
    private TextField _output; // Text field for displaying output messages

    // Initializes the controller class with the events
    @FXML
    private void initialize() {
        _btnLogin.setOnAction(e -> onLoginClicked());
        _btnClose.setOnAction(e -> onCloseClicked());
    }

    /**
     * Handles the action when the "Login" button is clicked.
     */
    private void onLoginClicked() {
        String username = _usr.getText();
        String password = _password.getText();

        // Check if the entered username and password are correct
        if (username.equals("Jasroop") && password.equals("Singh")) {
            try {
                // Load the Main.fxml UI upon successful login
                Parent root = FXMLLoader.load(getClass().getResource("/views/Main.fxml"));
                Scene scene = new Scene(root);
                Stage secondStage = new Stage();
                secondStage.setScene(scene);
                secondStage.initModality(Modality.APPLICATION_MODAL);
                secondStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            // Close the login window after successful login
            Stage stage = (Stage) _btnLogin.getScene().getWindow();
            stage.close();
        } else {
            // Display an error message for incorrect login credentials
            _output.setText("Wrong Username or Password");
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
            Stage stage = (Stage) _btnClose.getScene().getWindow();
            stage.close();
        }
    }
}
