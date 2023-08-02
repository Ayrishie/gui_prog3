import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {
    private static final int SLOT_COUNT = 8;
    private static final int ITEM_CAPACITY = 10;
    private static final double DEFAULT_PRICE = 1.0; // Assuming a default price for all items

    // List to hold the names of all items
    public static final List<String> itemNames = Arrays.asList(
            "Bread", "Pizza Sauce", "Cheese", "Meat toppings",
            "Vegetable toppings", "Condiments", "Box", "Softdrink"
    );

    // Map to hold the properties for each item
    protected static Map<String, Item> itemPropertiesMap = new HashMap<>();

    // Initialize the itemPropertiesMap in the static block
    static {
        itemPropertiesMap.put("Bread", new Item("Bread", ITEM_CAPACITY, DEFAULT_PRICE, 0));
        itemPropertiesMap.put("Pizza Sauce", new Item("Pizza Sauce", ITEM_CAPACITY, DEFAULT_PRICE, 0));
        itemPropertiesMap.put("Cheese", new Item("Cheese", ITEM_CAPACITY, DEFAULT_PRICE, 0));
        itemPropertiesMap.put("Meat toppings", new Item("Meat toppings", ITEM_CAPACITY, DEFAULT_PRICE, 0));
        itemPropertiesMap.put("Vegetable toppings", new Item("Vegetable toppings", ITEM_CAPACITY, DEFAULT_PRICE, 0));
        itemPropertiesMap.put("Condiments", new Item("Condiments", ITEM_CAPACITY, DEFAULT_PRICE, 0));
        itemPropertiesMap.put("Box", new Item("Box", ITEM_CAPACITY, DEFAULT_PRICE, 0));
        itemPropertiesMap.put("Softdrink", new Item("Softdrink", ITEM_CAPACITY, DEFAULT_PRICE, 0));
    }

    private int soldQuantity;
    private String itemName;
    private int quantity;
    private double price;
    private int calories;

    private List<Integer> soldItemQuantities;
    private List<Integer> initialQuantities;

    public Item(String itemName, int quantity, double price, int calories) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.calories = calories;
        soldItemQuantities = new ArrayList<>(SLOT_COUNT);
        initialQuantities = new ArrayList<>(SLOT_COUNT);

        for (int i = 0; i < SLOT_COUNT; i++) {
            soldItemQuantities.add(0);
            initialQuantities.add(ITEM_CAPACITY);
        }

        // Add the item to the itemPropertiesMap using the item's name as the key
        itemPropertiesMap.put(itemName, this);
    }

    // Getters and Setters for quantity, price, and calories
    public static List<String> getItemNames() {
        return itemNames;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    // Method to get initialQuantities
    public List<Integer> getInitialQuantities() {
        return new ArrayList<>(initialQuantities);
    }

    // Getter for item properties
    public static Item getItemProperties(String itemName) {
        return itemPropertiesMap.get(itemName);
    }

    public List<Integer> getSoldItemQuantities() {
        return new ArrayList<>(soldItemQuantities);
    }

    public void decrementQuantity(int quantity) {
        this.quantity -= quantity;
    }

    public void updateSoldQuantity(int quantity) {
        soldQuantity += quantity;
        this.quantity -= quantity; // update the quantity after the sale
    }

    public int getRemainingQuantity() {
        return quantity;
    }


    public static int getItemCapacity() {
        return ITEM_CAPACITY;
    }
}