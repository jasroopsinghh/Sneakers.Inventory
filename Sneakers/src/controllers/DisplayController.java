package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Sneakers;

public class DisplayController {

    // Reference to the sneakers list from MainController
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
    private Button _btnClose;

    // TextField for user input search
    @FXML
    private TextField _search;

    // ObservableList to store and display sneakers data
    ObservableList<Sneakers> observableSneakersList = FXCollections.observableArrayList();

    /**
     * Initializes the display and sets up event listeners.
     */
    @FXML
    private void initialize() {
        _btnClose.setOnAction(e -> onCloseClicked());

        // Set up cell value factories for table columns
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model.setCellValueFactory(new PropertyValueFactory<>("model"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Populate the observableSneakersList with data from _sneakersList
        observableSneakersList.addAll(_sneakersList.getSneakersList());

        tableView.setItems(observableSneakersList);

        // Filter the data based on search input
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

        // Apply sorting to the filtered data
        SortedList<Sneakers> sortedData = new SortedList<>(filter);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    /**
     * Closes the display window.
     */
    private void onCloseClicked() {
        Stage stage = (Stage) _btnClose.getScene().getWindow();
        stage.close();
    }
}
