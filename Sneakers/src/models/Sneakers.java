package models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a class for Sneakers, a subclass of Shoe.
 *
 * This class defines methods to manage a list of sneakers, including adding,
 * removing,
 * and replacing sneakers in the list.
 *
 * @author Jasroop Singh ID: 991715844
 */
public class Sneakers extends Shoe {

    private ArrayList<Sneakers> _sneakersList = new ArrayList<Sneakers>();

    /**
     * Constructs a new Sneakers object with specified attributes.
     *
     * @param brand    The brand of the sneakers.
     * @param model    The model of the sneakers.
     * @param year     The year the sneakers were made.
     * @param price    The price of the sneakers in dollars.
     * @param quantity The quantity of sneakers available.
     * @param id       The unique identifier for the sneakers.
     */
    public Sneakers(String brand, String model, int year, double price, int quantity, int id) {
        super(brand, model, year, price, quantity, id);
    }

    /**
     * Returns the number of sneakers in the sneakers list.
     *
     * @return The number of sneakers in the list.
     */
    public int getSize() {
        return _sneakersList.size();
    }

    /**
     * Retrieves a Sneakers object from the sneakers list at the specified index.
     *
     * @param index The index of the sneakers to retrieve.
     * @return The Sneakers object at the specified index.
     */
    public Sneakers getSneakers(int index) {
        return _sneakersList.get(index);
    }

    /**
     * Adds a Sneakers object to the sneakers list.
     *
     * @param sneaker The Sneakers object to add to the list.
     * @return True if the addition was successful, false otherwise.
     */
    public boolean addSneakers(Sneakers sneaker) {
        return _sneakersList.add(sneaker);
    }

    /**
     * Retrieves the list of sneakers.
     *
     * @return The list of sneakers.
     */
    public ArrayList<Sneakers> getSneakersList() {
        return _sneakersList;
    }

    /**
     * Loads initial Sneakers data.
     */
    public void loadSneakers() {
        Scanner reader = null;
        try {
            String relativePath = "item_list.txt";
            File fileDescriptor = new File(relativePath);

            reader = new Scanner(fileDescriptor);

            while (reader.hasNext()) {
                String record = reader.nextLine();
                // Now split the string and parse each field
                String[] fields = record.split(",");
                String brand = fields[0];
                String model = fields[1];
                int year = Integer.parseInt(fields[2]);

                double price = Double.parseDouble(fields[3]);
                int quantity = Integer.parseInt(fields[4]);

                int id = Integer.parseInt(fields[5]);

                // Create a new object for each record
                Sneakers obj = new Sneakers(brand, model, year, price, quantity, id);
                _sneakersList.add(obj); // Add object to list
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // You should add proper error reporting & recovery code here!
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

    }

    /**
     * Saves the Sneakers data to a file.
     */

    public void save() {
        PrintWriter writer = null;
        try {
            File fileDescriptor = new File("item_list.txt");
            writer = new PrintWriter(fileDescriptor);
            for (int index = 0; index < _sneakersList.size(); index++) {
                Sneakers sn = _sneakersList.get(index);
                writer.println(sn.getBrand() + "," + sn.getModel() + "," + sn.getYear() + "," + sn.getPrice() + ","
                        + sn.getQuantity() + "," + sn.getId());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace(); // Add error recovery here if needed
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Removes a Sneakers object from the sneakers list.
     *
     * @param selections The Sneakers object to remove from the list.
     */
    public void remove(Sneakers selections) {
        _sneakersList.remove(selections);
    }

    /**
     * Replaces a Sneakers object in the sneakers list at the specified index.
     *
     * @param sneaker The replacement Sneakers object.
     * @param index   The index at which to replace the Sneakers object.
     */
    public void replaceSneaker(Sneakers sneaker, int index) {
        _sneakersList.remove(index);
        _sneakersList.add(index, sneaker);
    }

}
