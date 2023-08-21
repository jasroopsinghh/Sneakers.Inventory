package controllers;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Sneakers;

/**
 * This class controls the editing view for sneakers in a graphical user
 * interface.
 * It handles user interactions, updates the display, and provides functionality
 * for editing and removing sneakers.
 * The EditController is responsible for connecting the user interface with the
 * sneakers data and actions.
 * Principal Author: Jasroop Singh ID: 991715844
 */
public class EditController {

    // Reference to the shared sneakers list from the MainController
    static Sneakers _sneakersList = MainController._sneakersList;

    // TableView to display sneakers information
    @FXML
    private TableView<Sneakers> tableView;

    // TableColumn to display brand information
    @FXML
    private TableColumn<Sneakers, String> brand;

    // TableColumn to display model information
    @FXML
    private TableColumn<Sneakers, String> model;

    // TableColumn to display year information
    @FXML
    private TableColumn<Sneakers, Integer> year;

    // TableColumn to display price information
    @FXML
    private TableColumn<Sneakers, Double> price;

    // TableColumn to display quantity information
    @FXML
    private TableColumn<Sneakers, Integer> quantity;

    // TableColumn to display ID information
    @FXML
    private TableColumn<Sneakers, Integer> id;

    // Button to close the edit window
    @FXML
    private Button btnClose;

    // Button to initiate the editing of sneakers
    @FXML
    private Button btnEdit;

    // Button to remove selected sneakers
    @FXML
    private Button btnRemove;

    // TextField for user input search
    @FXML
    private TextField _search;

    // Observable list of sneakers for display
    ObservableList<Sneakers> observableSneakersList = SneakersData.observableSneakersList;
    static int selection;

    /**
     * Initializes the controller class and sets up event handlers and table view.
     * This method is called automatically when the FXML file is loaded.
     */
    @FXML
    private void initialize() {
        btnEdit.setOnAction(e -> onEditClicked());
        btnRemove.setOnAction(e -> onRemoveClicked());
        btnClose.setOnAction(e -> onCloseClicked());

        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Populates the table view with sneakers data and sets up filtering and
        // sorting.
        setupTableView();
    }

    /**
     * Sets up the TableView with data and filtering/sorting capabilities.
     * The TableView displays sneakers data and allows filtering and sorting.
     */
    private void setupTableView() {
        observableSneakersList.clear();
        observableSneakersList.addAll(_sneakersList.getSneakersList());
        tableView.setItems(observableSneakersList);

        // Filtering logic
        FilteredList<Sneakers> filter = new FilteredList<>(observableSneakersList, b -> true);

        _search.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(Sneakers -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (Sneakers.getBrand().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (Sneakers.getModel().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(Sneakers.getPrice()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(Sneakers.getYear()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(Sneakers.getQuantity()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(Sneakers.getId()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
        });

        // Sorting logic
        SortedList<Sneakers> sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    /**
     * Opens the edit window when the "Edit" button is clicked.
     * Updates the sneakers list after the edit and updates the table view.
     */
    private void onEditClicked() {
        update();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/views/EditWindow.fxml"));
            Scene scene = new Scene(root);
            Stage secondStage = new Stage();
            secondStage.setScene(scene);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        _sneakersList = EditWindowController.getSneakersList();
        updateTableView();
    }

    /**
     * Removes the selected sneaker when the "Remove" button is clicked.
     */
    private void onRemoveClicked() {

        int selection = tableView.getSelectionModel().getSelectedIndex();

        if (selection >= 0) { // Check if an item is selected
            Sneakers selectedSneakers = tableView.getItems().get(selection);

            // Create an alert
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Sneaker Removal");
            alert.setHeaderText("Are you sure you want to remove the " + selectedSneakers.getBrand() + " Sneaker?");
            alert.setContentText(selectedSneakers.getBrand().toString());

            // Show the alert and wait for user response
            ButtonType result = alert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) { // User clicked OK
                observableSneakersList.remove(selectedSneakers);
                _sneakersList.remove(selectedSneakers);
                _sneakersList.save();
            }
        }

    }

    /**
     * Closes the current window when the "Close" button is clicked.
     */
    private void onCloseClicked() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    /**
     * Updates the TableView with the latest sneaker data.
     */
    private void updateTableView() {
        tableView.setItems(observableSneakersList);
    }

    /**
     * Updates the currently selected index from the TableView.
     */
    private void update() {
        selection = tableView.getSelectionModel().getSelectedIndex();
    }

    /**
     * Data class for holding the observable list of sneakers.
     * This class contains the observable list used to display sneakers in the
     * TableView.
     */
    public class SneakersData {
        public static ObservableList<Sneakers> observableSneakersList = FXCollections.observableArrayList();
    }

    /**
     * Gets the current selection index from the table.
     * 
     * @return The selected index
     */
    static int getSelection() {
        return selection;
    }

    /**
     * Gets the shared sneakers list.
     * 
     * @return The sneakers list
     */
    static Sneakers getSneakersList() {
        return _sneakersList;
    }
}
