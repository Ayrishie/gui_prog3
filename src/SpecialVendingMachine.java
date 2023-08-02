import java.util.*;
import java.util.List;

/**
 * A class representing a special vending machine that inherits features from RegularVendingMachine.
 */
public class SpecialVendingMachine extends RegularVendingMachine {

    private Map<String, String> itemPromotions;
    private int soldQuantity;
    // Store the original quantity of items
    List<Integer> originalQuantities = new ArrayList<>();
    private Map<String, Integer> itemQuantities = new HashMap<>();



    /**
     * Constructs a SpecialVendingMachine object and initializes its fields.
     */
    public SpecialVendingMachine() {
        // Call the constructor of the superclass (RegularVendingMachine) to initialize its fields.
        super();

    }

    public boolean processTransaction(List<Integer> slots, List<Integer> quantities, int paymentDenomination, int paymentQuantity) {
// Reset the lists for selected items and their details after a successful transaction
        originalQuantities.clear();
        List<Item> purchasedItems = null;


        double totalCost = 0;
        purchasedItems = new ArrayList<>();
        int quantity = item.getQuantity(); // Get the quantity of the item
        double price = item.getPrice();
        int calories = item.getCalories();


        for (int i = 0; i < slots.size(); i++) {
            int slot = slots.get(i);
            String itemName = Item.getItemNames().get(slot - 1);
            Item item = Item.getItemProperties(itemName);
            originalQuantities.add(item.getQuantity());
        }

        for (int i = 0; i < originalQuantities.size(); i++) {
            quantity = originalQuantities.get(i);
            System.out.println("HEHEItem at index " + i + ": " + quantity);
        }


        // Check if the payment is enough
        System.out.println("after:");
        System.out.println("\n===== DEBUG: Vending Machine Items =====");
        System.out.println("| No. | Item              | Quantity | Price | Calories |");
        System.out.println("|-----|-------------------|----------|-------|----------|");

        for (int i = 0; i < itemNames.size(); i++) {
            String itemName = itemNames.get(i);
            Item item = Item.getItemProperties(itemName);

            int slot = i + 1;
            quantity = item.getQuantity();
            price = item.getPrice();
            calories = item.getCalories();

            System.out.printf("| %3d | %-17s | %8d | %5.2f | %8d |%n", slot, itemName, quantity, price, calories);
        }
        System.out.println("=======================================");


        // Calculate the total cost of the selected items and create new Item objects with the correct quantity
        // Calculate the total cost of the selected items and create new Item objects with the correct quantity
        for (int i = 0; i < slots.size(); i++) {
            int slot = slots.get(i);
            String itemName = Item.getItemNames().get(slot - 1);
            Item item = Item.getItemProperties(itemName);
            int purchasedQuantity = quantities.get(i);

            price = item.getPrice();
            calories = item.getCalories();

            // Update the sold and remaining quantity in the item
            item.updateSoldQuantity(purchasedQuantity); // update soldQuantity in item
            int remainingQuantity = item.getRemainingQuantity(); // now, get the updated remaining quantity

            System.out.println("Item Name: " + itemName + ", Quantity Purchased: " + purchasedQuantity + ", Remaining Quantity: " + remainingQuantity + ", Total Cost: " + (price * purchasedQuantity));
            // Add the purchased item to the list
            purchasedItems.add(new Item(itemName, purchasedQuantity, price, calories));
            totalCost += price * purchasedQuantity;
            transactionCount++;
            totalSales += item.getPrice() * purchasedQuantity;
        }


        System.out.println("Total Cost of the Selected Items: " + totalCost);

        // Calculate the total payment amount
        double paymentAmount = denominationValues.get(paymentDenomination - 1) * paymentQuantity;
        System.out.println("Payment Amount: $" + paymentAmount);

        // Check if the payment is enough
        while (paymentAmount < totalCost) {
            System.out.println("Insufficient payment. Total cost: $" + totalCost + ". Please add more payment.");

            // Ask the user for additional payment
            System.out.print("Enter the denomination (1 - " + DENOMINATION_COUNT + ") and quantity of the payment (or -1 to cancel): ");
            int denomination = scanner.nextInt();
            if (denomination == -1) {
                System.out.println("Transaction canceled.");
                return false;
            }

            // Check if the denomination and quantity are valid
            if (denomination < 1 || denomination > DENOMINATION_COUNT) {
                System.out.println("Invalid denomination.");
                continue;
            }
            System.out.print("Enter the quantity for denomination " + paymentDenomination + ": ");
            quantity = scanner.nextInt();

            // Calculate the additional payment amount and add it to the total payment
            double additionalPayment = denominationValues.get(denomination - 1) * quantity;
            paymentAmount += additionalPayment;

            if (paymentAmount >= totalCost) {
                System.out.println("Payment completed.");
            }
        }

        // After validating that there are enough bills of the chosen denomination
        int currentDenominationQuantity = denominationQuantities.get(paymentDenomination - 1);
        denominationQuantities.set(paymentDenomination - 1, currentDenominationQuantity - paymentQuantity);

        // Calculate change and give change
        // Calculate change and give change
        double change = paymentAmount - totalCost;

        if (change < 0) {
            System.out.println("Insufficient payment. Cannot complete the transaction.");
            return false;
        } else if (change > 0 && !giveChange(change)) {
            System.out.println("Unable to give change.");
            return false;
        }

        System.out.println("Change: $" + change);


        // Print receipt and display updated item quantities
        printMultipleReceipts(purchasedItems, totalSales, transactionCount, paymentAmount - totalCost, soldQuantity, originalQuantities);
        return true;
    }






    private void printMultipleReceipts(List<Item> purchasedItems, double totalSales, int transactionCount, double change, int soldQuantity, List<Integer> originalQuantities) {
        try {

            Scanner scanner = new Scanner(System.in); // Create a Scanner object

            System.out.println("\n==============================================");
            System.out.println("|           RAIO  Vending Machine            |");
            System.out.println("|============================================|");
            System.out.println(soldQuantity + " quantity inside the printMultipleReceipts");

            for (Item purchasedItem : purchasedItems) {
                String itemName = purchasedItem.getItemName();
                int quantity = purchasedItem.getQuantity(); // Get the quantity of the purchased item
                double itemPrice = purchasedItem.getPrice();
                int itemCalories = purchasedItem.getCalories();

                System.out.println("| Item purchased: " + itemName);
                System.out.println("| Quantity: " + quantity); // Print the correct quantity for this item
                System.out.println("| Price per item: $" + itemPrice);
                System.out.println("| Total cost for this item: $" + (itemPrice * quantity)); // Calculate the total cost for this item based on its quantity
                System.out.println("| Calories per item: " + itemCalories);
                System.out.println("|--------------------------------------------|");

                // Restore the original quantity for this item
                int originalQuantity = originalQuantities.get(purchasedItems.indexOf(purchasedItem));
                purchasedItem.setQuantity(originalQuantity);


            }

            System.out.printf("| Total Sales: $%.2f             %n", totalSales);
            System.out.printf("| Total Transactions: %d                %n", transactionCount);

            if (change >= 0) {
                System.out.printf("| Change: $%.2f              %n", change);
            }
            prepareCustomizableProduct(purchasedItems);


            displayUpdatedDenominationQuantities();


            System.out.println("============================================");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds. Please enter a valid slot number.");
        } catch (NullPointerException e) {
            System.out.println("Error: Null pointer. Please ensure the itemNames list is not empty.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

        displayItems();

    }

    public void prepareCustomizableProduct(List<Item> selectedItems) {
        CustomizableProduct customizableProduct = new CustomizableProduct();
        customizableProduct.prepareProduct(selectedItems);
    }


}


/**

 public void customizeProduct() {
 boolean continueSelecting = true;

 List<Item> selectedItems = new ArrayList<>();

 while (continueSelecting) {
 // Display the available items to choose from
 displayItems();

 System.out.print("Enter the item number you want to add to the product (1-" + getSlotCount() + "): ");
 int itemNumber = scanner.nextInt();
 scanner.nextLine(); // Consume the newline character

 if (itemNumber < 1 || itemNumber > getSlotCount()) {
 System.out.println("Invalid item number.");
 continue;
 }

 // Get the selected item
 Item item = items.get(itemNumber - 1);
 selectedItems.add(item);

 System.out.print("Do you want to add another item to the product? (Y/N): ");
 String choice = scanner.nextLine().trim().toUpperCase();
 if (!choice.equals("Y")) {
 continueSelecting = false;
 }
 }

 // Calculate the total calories for the product
 int totalCalories = 0;
 for (Item item : selectedItems) {
 totalCalories += item.getCalories();
 }

 // Display the preparation process
 System.out.println("Preparing the product...");
 for (Item item : selectedItems) {
 System.out.println("Adding " + item.getItemName() + "...");
 }
 System.out.println("Product Done!");

 // Display the total calories for the product
 System.out.println("Total Calories for the Product: " + totalCalories);
 }
 */




