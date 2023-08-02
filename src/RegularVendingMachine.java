import java.util.*;
import java.util.List; // Add this import statement
import java.util.ArrayList; // Add this import statement

/**
 * A class representing a regular vending machine.
 */
public class RegularVendingMachine {
    private static final int SLOT_COUNT = 8;
    private static final int ITEM_CAPACITY = 10;
    protected static final int DENOMINATION_COUNT = 9;
    protected static final double DEFAULT_PRICE = 0.0;
    private final ArrayList<Integer> itemCalories;
    public List<String> itemNames;
    protected List<Integer> denominationValues;
    protected List<Integer> itemQuantities;
    //private Map<String, ItemProperties> itemPropertiesMap; // Map to associate item names with their properties
    protected double totalSales;
    protected int transactionCount;
    protected List<Integer> initialItemQuantities;
    protected List<Integer> soldItemQuantities;
    protected final Scanner scanner;

    private List<String> denominationNames;
    protected List<Item> items;
    private boolean itemsInitialized = false;

    protected List<Integer> denominationQuantities;
    Item item;
    protected int quantity;
    private int price;

    private int calories;
    private String itemName;

    /**
     * Constructs a RegularVendingMachine object and initializes its fields.
     */
    public RegularVendingMachine() {
        System.out.println("Before creating RegularVendingMachine: itemNames is " + Item.itemNames);
        scanner = new Scanner(System.in);
        items = new ArrayList<>();
        item = new Item(itemName, ITEM_CAPACITY, DEFAULT_PRICE, 0);
        itemNames = Item.getItemNames();
        itemQuantities = new ArrayList<>(Collections.nCopies(SLOT_COUNT, ITEM_CAPACITY));
        initialItemQuantities = new ArrayList<>(Collections.nCopies(SLOT_COUNT, ITEM_CAPACITY));
        soldItemQuantities = new ArrayList<>(Collections.nCopies(SLOT_COUNT, 0));
        denominationQuantities = new ArrayList<>(Collections.nCopies(DENOMINATION_COUNT, 0));
        denominationValues = Arrays.asList(1000, 500, 200, 100, 50, 25, 10, 5, 1);
        itemCalories = new ArrayList<>(Collections.nCopies(SLOT_COUNT, 0));

        // Create the item properties map and initialize with default values


        //initializeItems();
        InitializeItemsDialog dialog = new InitializeItemsDialog(null);
        ItemController controller = new ItemController(dialog, Item.itemPropertiesMap);

        controller.displayView();

        System.out.println("itemQuantities size: " + itemQuantities.size());
        // System.out.println("itemPrices size: " + itemPropertiesMap.size());
        System.out.println("itemNames size: " + Item.itemNames.size());
        System.out.println("itemCalories size: " + itemCalories.size());


        //List<String> itemNames = Item.getItemNames();
        setDenominationValues();
        setDenominationNames();
        //setDenominationQuantities();
        setDenominationsGUI();
    }



    //special

    // Example of calling the testSpecialVendingFeatures method from RegularVendingMachine


    // Rest of the RegularVendingMachine class


    /**
     * Sets the names of the denominations used in the vending machine.
     */
    private void setDenominationNames() {
        denominationNames = new ArrayList<>();
        denominationNames.add("1000");
        denominationNames.add("500");
        denominationNames.add("200");
        denominationNames.add("100");
        denominationNames.add("50");
        denominationNames.add("20");
        denominationNames.add("10");
        denominationNames.add("5");
        denominationNames.add("1");
    }

    /**
     * Sets the values of the denominations used in the vending machine.
     */
    private void setDenominationValues() {
        denominationValues = new ArrayList<>();
        denominationValues.add(1000);
        denominationValues.add(500);
        denominationValues.add(200);
        denominationValues.add(100);
        denominationValues.add(50);
        denominationValues.add(20);
        denominationValues.add(10);
        denominationValues.add(5);
        denominationValues.add(1);

    }

    public void setDenominationQuantities(List<Integer> quantities) {
        this.denominationQuantities = quantities;
    }

    /**
     * Prints the receipt for a transaction.
     *
     * @param slot     the slot number of the item purchased
     * @param quantity the quantity of the item purchased
     * @param change   the change amount given to the customer
     */
    protected void printReceipt(int slot, int quantity, double change) {
        try {
            String itemName = itemNames.get(slot);

            Item item = Item.itemPropertiesMap.get(Item.itemNames.get(slot));

            System.out.println("[DEBUG] itemNames contents: " + itemNames);
            System.out.println("[DEBUG] SLOT NUMBER " + slot);

            System.out.println("[DEBUG] printReceipt() called with slot: " + slot + ", quantity: " + quantity + ", and change: " + change);

            if (slot < 0 || slot >= SLOT_COUNT) {
                System.out.println("[DEBUG] Invalid slot number: " + slot);
                System.out.println("[DEBUG] Quantity: " + quantity);
                return;
            }

            if (itemNames.isEmpty()) {
                System.out.println("[DEBUG] Error: itemNames list is empty.");
                return;
            }

            System.out.println("[DEBUG] SA WAKAS.");
            String ANSI_RESET = "\u001B[0m";
            String ANSI_RED = "\u001B[31m";
            String ANSI_YELLOW = "\u001B[33m";

            System.out.println("[DEBUG] ANSI codes initialized.");

            System.out.println("\n==============================================");
            System.out.println("|           RAIO  Vending Machine            |");
            System.out.println("|============================================|");
            System.out.println("| Item purchased: " + itemName);
            System.out.println("|============================================|");
            System.out.println("| Before quantity:         " + (quantity + 1));
            System.out.println("| After quantity:          " + quantity);
            System.out.println("|============================================|");
            System.out.printf("| Total Sales: $%.2f             %n", totalSales);
            System.out.printf("| Total Transactions %d                %n", transactionCount);

            if (change >= 0) {
                System.out.printf("| Change: $%.2f              %n", change);
            }

            System.out.println("|--------------------------------------------|");
            System.out.println("|============================================|");
            System.out.println("|     Updated Denomination Quantities        |");
            System.out.println("|============================================|");

            for (int i = 0; i < denominationQuantities.size(); i++) {
                int denominationValue = denominationValues.get(i);
                int denominationQuantity = denominationQuantities.get(i);
                System.out.println("| " + denominationValue + ":\t" + denominationQuantity);
            }

            System.out.println("============================================");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds. Please enter a valid slot number.");
        } catch (NullPointerException e) {
            System.out.println("Error: Null pointer. Please ensure the itemNames list is not empty.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    public double getTotalSales() {
        // Replace this with your logic to calculate the actual total sales
        return totalSales;
    }

    public int getTransactionCount() {
        // Replace this with your logic to calculate the actual transaction count
        return transactionCount;
    }


    public List<Integer> getDenominationQuantities() {
        return Collections.unmodifiableList(denominationQuantities);
    }



    public String generateSummary() {
        StringBuilder summary = new StringBuilder();
        try {
            summary.append("\n====================================\n");
            summary.append("|           Item Summary           |\n");
            summary.append("====================================\n");

            List<String> itemNames = Item.getItemNames();

            for (String itemName : itemNames) {
                Item item = Item.itemPropertiesMap.get(itemName);
                int initialQuantity = item.getInitialQuantities().get(0);
                int soldQuantity = (int) item.getSoldItemQuantities().get(0);

                summary.append("| Item: " + itemName + "\n");
                summary.append("| Before Quantity: " + initialQuantity + "\n");
                summary.append("| After Quantity: " + (initialQuantity - soldQuantity) + "\n");
                summary.append("| Price: $" + item.getPrice() + "\n");
                summary.append("| Calories: " + item.getCalories() + "\n");
                summary.append("|----------------------------------\n");
            }

            summary.append("| Number of transactions: " + transactionCount + "\n");
            summary.append("| Total sales: " + totalSales + "\n");
            summary.append("|----------------------------------\n");

        } catch (IndexOutOfBoundsException e) {
            return "Change: Index out of bounds. Please ensure the item quantities are initialized properly.";
        } catch (NullPointerException e) {
            return "Error: Null pointer. Please ensure the item properties map is not empty.";
        } catch (Exception e) {
            return "An unexpected error occurred: " + e.getMessage();
        }
        return summary.toString();
    }






    /**
     * Returns the number of slots in the vending machine.
     *
     * @return the number of slots
     */
    public int getSlotCount() {
        return SLOT_COUNT;
    }


    /**
     * Gives change to the customer for the transaction.
     *
     * @param change the change amount to be given
     * @return
     */
    public boolean giveChange(double change) {
        try {
            System.out.println("|============================================|");
            System.out.println("|========= Available Bills For Change =======|");
            System.out.println("|============================================|");

            int[] changeQuantities = calculateChangeQuantities(change);
            if (changeQuantities == null) {
                return false; // Change cannot be given exactly
            }

            for (int i = DENOMINATION_COUNT - 1; i >= 0; i--) {
                int denominationValue = denominationValues.get(i);
                int availableBillCount = denominationQuantities.get(i);
                int usedBillCount = changeQuantities[i];

                System.out.printf("| %2d.......$%-4d: %3d%28s |\n", i + 1, denominationValue, availableBillCount, "");

                if (change <= 0) {
                    break;
                }
            }
            System.out.println("|============================================|");

            // Update the denominationQuantities only if the transaction is successful
            for (int i = 0; i < DENOMINATION_COUNT; i++) {
                int availableBillCount = denominationQuantities.get(i);
                int usedBillCount = changeQuantities[i];
                denominationQuantities.set(i, availableBillCount - usedBillCount);
            }

            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Bills : Index out of bounds. Please ensure the denomination quantities are initialized properly.");
            return false;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error occurred: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }




    public List<Integer> getDenominationValues() {
        return Collections.unmodifiableList(denominationValues);
    }

    /**
     * Displays the updated denomination quantities in the vending machine.
     */
    public void displayUpdatedDenominationQuantities() {
        System.out.println();
        System.out.println("\u001B[36m|============================================|");
        System.out.println("\u001B[36m|========= Available Bills For Change =======|");
        System.out.println("\u001B[36m|============================================|");
        for (int i = 0; i < denominationQuantities.size(); i++) {
            int denomination = denominationQuantities.get(i);
            System.out.printf("\u001B[33m| %2d.......$%3d: %2d                       \t|%n\u001B[0m", i + 1, denominationValues.get(i), denomination);
        }
        System.out.println("\u001B[36m|============================================|");

    }



    public void displayItems() {
        // Print debug information
        System.out.println("Number of items: " + Item.getItemNames().size());

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("\u001B[36m======================================================");
        System.out.println("\u001B[36m|             Vending Machine Items                  |");
        System.out.println("\u001B[36m======================================================");
        System.out.printf("\u001B[36m| \u001B[33m%2s. %-20s%-8s%10s  %s%n", "No", "Item", "Quantity", "Price", "Calories   \u001B[36m|");

        for (int i = 0; i < Item.getItemNames().size(); i++) {
            String itemName = Item.getItemNames().get(i);
            Item item = Item.getItemProperties(itemName);
            int quantity = item.getQuantity(); // Get the initial quantity for the item
            double price = item.getPrice();
            int calories = item.getCalories();

            System.out.printf("\u001B[36m| \u001B[33m%2d. %-20s%8d%13s  %5d  \u001B[36m |%n\u001B[0m", i + 1, itemName, quantity, "$" + price, calories);
        }
        System.out.println("\u001B[36m|======================================================|");
    }

    /**
     * The function `processTransaction` processes a transaction by checking if the item is available
     * and the payment is sufficient, updating the item quantities and sales, giving change, printing a
     * receipt, and displaying the updated item quantities.
     *
     * @param slot The slot parameter represents the index of the item in the inventory. It is used to
     * retrieve the price and quantity of the item from the itemPrices and itemQuantities lists
     * respectively.
     * @param paymentDenomination The paymentDenomination parameter represents the denomination of the
     * payment made by the customer. It is an integer value that corresponds to the index of the
     * denomination in the denominationValues list.
     * @return The method is returning a boolean value.
     */
    public double processTransaction(int slot, int paymentDenomination) throws IllegalArgumentException {
        try {
            // Get item properties from the itemPropertiesMap
            slot -= 1; // Subtract 1 from the slot to convert to 0-based index
            String itemName = Item.getItemNames().get(slot);
            Item item = Item.getItemProperties(itemName);

            System.out.println("[DEBUG] Selected Slot: " + slot);
            System.out.println("[DEBUG] Selected Item Name: " + itemName);
            System.out.println("[DEBUG] Item Properties: " + item);

            // Check if the item is available
            int quantity = item.getInitialQuantities().get(slot); // Assuming you want the initial quantity for slot 0
            if (quantity <= 0) {
                System.out.println("Item out of stock.");
                return -1.0;
            }


            // Check if the payment denomination is valid
            if (paymentDenomination < 1 || paymentDenomination > DENOMINATION_COUNT) {
                System.out.println("Invalid payment denomination.");
                return -1.0;
            }

            // Check if payment amount is sufficient
            double price = item.getPrice();

            // Calculate payment amount based on the payment denomination
            double paymentAmount = denominationValues.get(paymentDenomination - 1);

            System.out.println("[DEBUG] Payment Amount: $" + paymentAmount);

            if (paymentAmount < price) {
                System.out.println("Insufficient payment. Please add more to the payment or enter 0 to cancel.");
                int additionalPaymentDenomination = scanner.nextInt();

                if (additionalPaymentDenomination == 0) {
                    System.out.println("Transaction canceled.");
                    return -1.0;
                }

                double additionalPaymentAmount = denominationValues.get(additionalPaymentDenomination - 1);
                paymentAmount += additionalPaymentAmount;

                System.out.println("[DEBUG] Updated Payment Amount: $" + paymentAmount);

                if (paymentAmount < price) {
                    System.out.println("Still insufficient payment. Transaction canceled.");
                    return -1.0;
                }
            }

            // Calculate change and give change
            double change = paymentAmount - price;

            if (!giveChange(change)) {
                return -1.0;
            }


            // Update item quantities and sales
            int updatedQuantity = item.getQuantity() - 1;
            System.out.println("[DEBUG] Updated Quantity: " + updatedQuantity);
            item.setQuantity(updatedQuantity);

            transactionCount++;
            totalSales += price;
            soldItemQuantities.set(slot, soldItemQuantities.get(slot) + 1); // Increment sold quantity

            // Print receipt and display updated item quantities
            printReceipt(slot, item.getInitialQuantities().get(slot), change);
            displayItems();

            return change;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Process: Index out of bounds. Please ensure the item quantities are initialized properly.");
            return -1.0;
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic error occurred: " + e.getMessage());
            return -1.0;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return -1.0;
        }
    }



    /**
     * The refillItem function refills a specific slot in a vending machine with a specified quantity of
     * items, checking for valid inputs and updating the item quantities accordingly.
     *
     * @param slotNumber The slot number represents the specific slot in a vending machine where an item
     * is stored. It is an integer value that ranges from 1 to the total number of slots in the vending
     * machine.
     * @param quantity The quantity parameter represents the number of items to refill in a specific
     * slot.
     */
    public String refillItem(int slotNumber, int quantity) {
        if (slotNumber < 1 || slotNumber > SLOT_COUNT) {
            return "Invalid slot number. Please try again.";
        }

        String itemName = Item.getItemNames().get(slotNumber - 1);
        Item item = Item.getItemProperties(itemName);
        int remainingCapacity = ITEM_CAPACITY - item.getQuantity();

        if (quantity > remainingCapacity) {
            return "Cannot refill. The slot does not have enough capacity.";
        }

        if (quantity < 0) {
            return "Invalid input";
        }

        int updatedQuantity = item.getQuantity() + quantity;
        item.setQuantity(updatedQuantity);

        resetItemQuantities();
        transactionCount = 0;
        resetTotalSales();

        return "\u001B[33mItem refilled successfully.\u001B[0m";
    }



    /**
     * The restockChange function restocks the quantity of a specific denomination of change.
     *
     * @param denominationNumber The denominationNumber parameter represents the number of the
     * denomination that needs to be restocked.
     * @param quantity The quantity parameter represents the number of units of a specific denomination
     * that you want to restock.
     */
    public String restockChange(int denominationNumber, int quantity) {
        try {
            if (denominationNumber < 1 || denominationNumber > DENOMINATION_COUNT) {
                return "Invalid denomination number. Please try again.";
            }

            int denominationQuantity = denominationQuantities.get(denominationNumber - 1);

            if (quantity < 0) {
                return "Invalid input";
            }

            int newQuantity = denominationQuantity + quantity;
            denominationQuantities.set(denominationNumber - 1, newQuantity);

            return "\u001B[33mChange restocked successfully.\u001B[0m";
        } catch (Exception e) {
            return "An unexpected error occurred while restocking change.";
        }
    }

    /**
     * The function updates the price of an item in a vending machine given the slot number and the new
     * price.
     *
     * @param slot The slot parameter represents the slot number of the item whose price needs to be
     * updated.
     * @param newPrice The new price that you want to update for the item in the specified slot.
     */
    /**
     * The function updates the price of an item in a vending machine given the slot number and the new
     * price.
     *
     * @param slot     The slot parameter represents the slot number of the item whose price needs to be
     *                updated.
     * @param newPrice The new price that you want to update for the item in the specified slot.
     */
    public String updateItemPrice(int slot, double newPrice) {
        try {
            if (slot < 1 || slot > SLOT_COUNT) {
                return "Invalid slot number. Please try again.";
            }

            if (newPrice < 0) {
                return "Invalid input";
            }

            int index = slot - 1;
            Item item = Item.itemPropertiesMap.get(Item.itemNames.get(index));
            item.setPrice(newPrice);

            return "Item price updated successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "An unexpected error occurred while updating item price.";
        }
    }


    public int[] calculateChangeQuantities(double change) {
        int[] changeQuantities = new int[DENOMINATION_COUNT];
        for (int i = DENOMINATION_COUNT - 1; i >= 0; i--) {
            int denominationValue = denominationValues.get(i);
            int billCount = (int) (change / denominationValue);
            int availableBillCount = denominationQuantities.get(i);

            if (billCount > availableBillCount) {
                billCount = availableBillCount;
            }

            changeQuantities[i] = billCount;
            change -= billCount * denominationValue;

            if (change <= 0) {
                break;
            }
        }

        if (change > 0) {
            return null; // Change cannot be given exactly
        }
        return changeQuantities;
    }




    /**
     * The function "resetItemQuantities" creates new ArrayLists to store the initial and sold
     * quantities of items.
     */
    public void resetItemQuantities() {
        initialItemQuantities = new ArrayList<>(itemQuantities);
        soldItemQuantities = new ArrayList<>(Collections.nCopies(SLOT_COUNT, 0));
    }


    /** The above code is defining a method called "resetTotalSales" in a Java class. This method sets
     * the value of a variable called "totalSales" to 0.0.
     */
    public void resetTotalSales() {
        totalSales = 0.0;
    }

    // In the RegularVendingMachine class
    protected List<String> getItemSlots() {
        return itemNames;
    }

    public void setDenominationsGUI() {
        DenominationDialog dialog = new DenominationDialog(null, denominationNames);
        DenominationController controller = new DenominationController(dialog, this);
        controller.displayView();
    }
}