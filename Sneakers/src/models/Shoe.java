package models;

/**
 * This abstract class represents a Shoe object.
 * The class defines attributes and methods related to shoes.
 * Principal author: Jasroop Singh ID: 991715844
 */
public abstract class Shoe {

    private String _brand; // Stores the brand of the shoe
    private String _model; // Stores the model of the shoe
    private int _year; // Stores the year the shoe was made
    private double _price; // Stores the price of the shoe (in dollars)
    private int _quantity; // Stores the quantity of this shoe available
    private int _id; // Stores a unique identifier for the shoe

    /**
     * Constructs a Shoe object with specified attributes.
     * 
     * @param brand    The brand of the shoe.
     * @param model    The model of the shoe.
     * @param year     The year the shoe was made.
     * @param price    The price of the shoe (in dollars).
     * @param quantity The quantity of this shoe available.
     * @param id       The unique identifier for the shoe.
     */
    public Shoe(String brand, String model, int year, double price, int quantity, int id) {
        _brand = brand;
        _model = model;
        _year = year;
        _price = price;
        _quantity = quantity;
        _id = id;
    }

    // Getters and Setters for various attributes

    /**
     * Sets the brand of the shoe.
     * 
     * @param brand The brand to set for the shoe.
     */
    public void setBrand(String brand) {
        _brand = brand;
    }

    /**
     * Gets the brand of the shoe.
     * 
     * @return The brand of the shoe.
     */
    public String getBrand() {
        return _brand;
    }

    /**
     * Sets the model of the shoe.
     * 
     * @param model The model to set for the shoe.
     */
    public void setModel(String model) {
        _model = model;
    }

    /**
     * Gets the model of the shoe.
     * 
     * @return The model of the shoe.
     */
    public String getModel() {
        return _model;
    }

    /**
     * Sets the price of the shoe.
     * 
     * @param price The price to set for the shoe (in dollars).
     */
    public void setPrice(double price) {
        _price = price;
    }

    /**
     * Gets the price of the shoe.
     * 
     * @return The price of the shoe (in dollars).
     */
    public double getPrice() {
        return _price;
    }

    /**
     * Sets the unique identifier for the shoe.
     * 
     * @param id The unique identifier to set for the shoe.
     */
    public void setId(int id) {
        _id = id;
    }

    /**
     * Gets the unique identifier of the shoe.
     * 
     * @return The unique identifier of the shoe.
     */
    public int getId() {
        return _id;
    }

    /**
     * Sets the quantity of the shoe available.
     * 
     * @param quantity The quantity to set for the shoe.
     */
    public void setQuantity(int quantity) {
        _quantity = quantity;
    }

    /**
     * Gets the quantity of the shoe available.
     * 
     * @return The quantity of the shoe available.
     */
    public int getQuantity() {
        return _quantity;
    }

    /**
     * Sets the year the shoe was made.
     * 
     * @param year The year to set for the shoe.
     */
    public void setYear(int year) {
        _year = year;
    }

    /**
     * Gets the year the shoe was made.
     * 
     * @return The year the shoe was made.
     */
    public int getYear() {
        return _year;
    }

}
