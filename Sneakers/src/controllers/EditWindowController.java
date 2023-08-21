package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Sneakers;
import javafx.scene.control.Button;

/**
 * Controller class for the main edit window of sneakers.
 * Allows users to edit sneaker details.
 * Author: Jasroop Singh ID: 991715844
 */
public class EditWindowController {

    // Reference to the sneakers list
    static Sneakers _sneakersList = MainController._sneakersList;
    ObservableList<Sneakers> observableSneakersList = FXCollections.observableArrayList();

    @FXML
    private TextField _brand;
    @FXML
    private TextField _model;
    @FXML
    private TextField _price;
    @FXML
    private TextField _id;
    @FXML
    private TextField _quantity;
    @FXML
    private TextField _year;

    @FXML
    private Button _btnEdit;
    @FXML
    private Button _btnClose;

    @FXML
    private TextField _checking;

    // Index of the selected sneaker
    @FXML
    public int _selected = EditController.getSelection();
    Sneakers _Sneaker;

    // Initialize the edit window
    @FXML
    private void initialize() {
        observableSneakersList = EditController.SneakersData.observableSneakersList;
        setSelected(_selected);
        showSelected();

        // Handle edit button click
        _btnEdit.setOnAction(e -> onEditClicked());

        // Handle close button click
        _btnClose.setOnAction(e -> onCloseClicked());
    }

    // Set the selected sneaker based on the index
    private void setSelected(int selected) {
        _Sneaker = _sneakersList.getSneakers(selected);
    }

    // Display the details of the selected sneaker
    private void showSelected() {
        _brand.setText(_Sneaker.getBrand());
        _model.setText(_Sneaker.getModel());
        _price.setText(String.valueOf(_Sneaker.getPrice()));
        _id.setText(String.valueOf(_Sneaker.getId()));
        _quantity.setText(String.valueOf(_Sneaker.getQuantity()));
        _year.setText(String.valueOf(_Sneaker.getYear()));
    }

    // Handle edit button click
    public void onEditClicked() {
        // Retrieve values from input fields
        String brand = _brand.getText();
        String model = _model.getText();
        double price = Double.parseDouble(_price.getText());
        int id = Integer.parseInt(_id.getText());
        int quantity = Integer.parseInt(_quantity.getText());
        int year = Integer.parseInt(_year.getText());

        // Create a new sneaker with edited details
        _Sneaker = new Sneakers(brand, model, year, price, quantity, id);

        // Update the sneaker list and observable list
        _sneakersList.replaceSneaker(_Sneaker, _selected);
        observableSneakersList.set(_selected, _Sneaker);
        _sneakersList.save();

        // Set status text
        _checking.setText("Edited");
    }

    // Handle close button click
    private void onCloseClicked() {
        Stage stage = (Stage) _btnClose.getScene().getWindow();
        stage.close();
    }

    // Get the reference to the sneakers list
    static Sneakers getSneakersList() {
        return _sneakersList;
    }
}
