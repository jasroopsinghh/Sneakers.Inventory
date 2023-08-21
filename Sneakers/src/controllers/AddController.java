package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Sneakers;
import javafx.scene.control.Button;

/**
 * This class is responsible for controlling the logic behind the UI where new
 * sneakers are added to the list.
 * It handles user input and interaction with the buttons and text fields.
 * Principal author: Jasroop Singh ID: 991715844
 */
public class AddController {

    // Reference to the sneakers list from the MainController
    static Sneakers _sneakersList = MainController._sneakersList;

    @FXML
    private TextField _brand; // Text field for entering the brand of the sneakers
    @FXML
    private TextField _model; // Text field for entering the model of the sneakers
    @FXML
    private TextField _price; // Text field for entering the price of the sneakers (in dollars)
    @FXML
    private TextField _id; // Text field for entering the unique ID of the sneakers
    @FXML
    private TextField _quantity; // Text field for entering the quantity of sneakers
    @FXML
    private TextField _year; // Text field for entering the manufacturing year of the sneakers

    @FXML
    private Button _btnAdd; // Button for adding the sneakers to the list
    @FXML
    private Button _btnClose; // Button for closing the UI

    @FXML
    private TextField _checking; // Text field for displaying status messages

    /**
     * Initializes the controller and sets up event handlers for buttons.
     */
    @FXML
    private void initialize() {
        _btnAdd.setOnAction(e -> onAddClicked());
        _btnClose.setOnAction(e -> onCloseClicked());
    }

    /**
     * Called when the "Add" button is clicked.
     * Reads user input from text fields, creates a new Sneakers object, adds it to
     * the list,
     * and updates the status message.
     */
    private void onAddClicked() {
        String brand = _brand.getText();
        String model = _model.getText();

        try {
            double price = Double.parseDouble(_price.getText());
            int id = Integer.parseInt(_id.getText());
            int quantity = Integer.parseInt(_quantity.getText());
            int year = Integer.parseInt(_year.getText());

            Sneakers sneakers = new Sneakers(brand, model, year, price, quantity, id);

            if (_sneakersList.addSneakers(sneakers)) {
                _checking.setText("Added");
                _sneakersList.save();
            } else {
                _checking.setText("Failed to add");
            }
        } catch (NumberFormatException e) {
            _checking.setText("Invalid input values");
        }
    }

    /**
     * Called when the "Close" button is clicked.
     * Closes the current UI window.
     */
    private void onCloseClicked() {
        Stage stage = (Stage) _btnClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Returns the sneakers list.
     * 
     * @return The sneakers list.
     */
    static Sneakers getSneakersList() {
        return _sneakersList;
    }
}
