
import java.util.*;

/**
 * The Menu class represents the main menu of the vending machine.
 * It provides options for customer operations and maintenance operations.
 */
public class Menu {
    private final Scanner scanner;
    private RegularVendingMachine rVendingMachine;
    private SpecialVendingMachine sVendingMachine;
    private final Maintenance maintenance;
    private int specialVMChecker;
    private Item item;


    /**
     * Constructs a new Menu object.
     * Initializes the scanner, vending machine, and maintenance instance.
     */
    public Menu() {
        scanner = new Scanner(System.in);
        rVendingMachine = null;
        sVendingMachine = null;
        maintenance = new Maintenance(); // Create an instance of Maintenance
    }

    /**
     * The Menu class represents a user interface for interacting with a vending machine.
     * It provides options to create a vending machine, test its features, and perform maintenance tasks.
     */

    private void showTestVendingMachineSubMenu() {
        System.out.println();
        System.out.println();
        System.out.println("\u001B[35m========================================");
        System.out.println("\u001B[35m      Test Vending Machine Menu       ");
        System.out.println("\u001B[35m========================================");
        System.out.println("\u001B[31m  1. Test Vending Features");
        System.out.println("\u001B[31m  2. Maintenance");
        System.out.println("\u001B[31m  3. Go back to main menu");
        System.out.println("\u001B[35m========================================");
        System.out.println("\u001B[0m[STVMS]Enter your choice (1-3)");
        System.out.print("\t\t=> ");
    }

    /**
     * Constructs a Menu object with default values.
     * Initializes the scanner, vending machine, and maintenance objects.
     */
    private void showTitleScreen() {
        System.out.println();
        System.out.println(String.format("%60s", "\u001B[38;5;196m========== Test Vending Machine =========="));
        System.out.println(String.format("%52s", "\u001B[38;5;202mRRRR    AAAA  IIIIII   OOOOO"));
        System.out.println(String.format("%52s", "\u001B[38;5;208mR   R  A    A   II     O   O"));
        System.out.println(String.format("%52s", "\u001B[38;5;214mRRRR   AAAAAA   II     O   O"));
        System.out.println(String.format("%52s", "\u001B[38;5;220mR  R   A    A   II     O   O"));
        System.out.println(String.format("%52s", "\u001B[38;5;226mR   R  A    A  IIIII   OOOOO"));
        System.out.print(String.format("%60s", "\u001B[38;5;190m============================================="));

        System.out.println("\t\t\nWelcome to RAIO Vending Machine Factory and Store!");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the main menu and handles user input.
     * Allows the user to create a vending machine, test its features, or exit the program.
     */
    private void showColoredMenu() {
        System.out.println();
        System.out.println();
        System.out.println("\u001B[32m====== Vending Machine Menu ======");
        System.out.println("\u001B[32m=================================");
        System.out.println("\u001B[33m1. Create a Vending Machine");
        System.out.println("\u001B[33m2. Test a Vending Machine");
        System.out.println("\u001B[33m3. Exit");
        System.out.println("\u001B[32m=================================");

        System.out.print("\t\t=> ");

    }


    /**
     * The function creates a vending machine and prompts the user to choose between a regular or
     * special vending machine.
     */
    public void createVendingMachine() {
        while (true) {
            System.out.println("\u001B[36m===============================");
            System.out.println("|    \u001B[34mR for Regular          \u001B[36m|");
            System.out.println("|    \u001B[34mS for Special          \u001B[36m|");
            System.out.println("===============================\u001B[0m");
            System.out.println("\u001B[0mEnter your choice (R or S)");

            System.out.print("\t\t\t=> ");
            String machineType = scanner.next();
            scanner.nextLine(); // Consume the newline character

            try {
                if (machineType.equalsIgnoreCase("R")) {
                    this.rVendingMachine = new RegularVendingMachine();
                    System.out.println();
                    System.out.println();
                    String createVMText =
                            "\u001B[35m\t  ============================\n" +
                                    "\t  |                          |\n" +
                                    "\t  |  Regular Vending Machine |\n" +
                                    "\t  |       created.           |\n" +
                                    "\t  |                          |\n" +
                                    "\t  ============================\u001B[0m";

                    System.out.println(createVMText);
                    System.out.println();
                    System.out.println();
                    specialVMChecker = -1;
                    break; // Exit the loop after successful creation
                } else if (machineType.equalsIgnoreCase("S")) {
                    this.sVendingMachine = new SpecialVendingMachine();
                    System.out.println();
                    System.out.println();
                    String createVMText =
                            "\u001B[35m\t  ============================\n" +
                                    "\t  |                          |\n" +
                                    "\t  |  Special Vending Machine |\n" +
                                    "\t  |       created.           |\n" +
                                    "\t  |                          |\n" +
                                    "\t  ============================\u001B[0m";

                    System.out.println(createVMText);
                    System.out.println();
                    System.out.println();

                    specialVMChecker = 1;
                    break; // Exit the loop after successful creation
                } else {
                    System.out.println("Invalid choice. Please enter either 'R' or 'S'.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter either 'R' or 'S'.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }


    /**
     * The performMaintenance() function displays a maintenance menu and allows the user to perform
     * various maintenance tasks on a vending machine.
     */
    private void performMaintenance() {


        int option;
        do {
            System.out.println();
            System.out.println();
            System.out.println("==============================");
            System.out.println("|     Maintenance Menu       |");
            System.out.println("==============================");
            System.out.println("| 1. Refill Items            |");
            System.out.println("| 2. Restock Change          |");
            System.out.println("| 3. Update Prices           |");
            System.out.println("| 4. Collect Payments        |");
            System.out.println("| 5. Print Summary           |");
            System.out.println("| 6. Go Back                 |");
            System.out.println("==============================");
            System.out.print("\u001B[92mEnter your choice (1-6): ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    maintenance.refillItem(rVendingMachine);
                    break;
                case 2:
                    maintenance.restockChange(rVendingMachine);
                    break;
                case 3:
                    //maintenance.updatePrices(rVendingMachine);
                    break;
                case 4:
                    maintenance.dispenseTotalPayments(rVendingMachine);
                    break;
                case 5:
                    //rVendingMachine.printSummary();
                    break;
                case 6:
                    System.out.println("Going back to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (option != 6);
    }

    /**
     * The function "displayMenu" displays a menu with options to create a vending machine, test the
     * vending machine, or exit the program, and continues to display the menu until the user chooses
     * to exit.
     */
    public void displayMenu() {
        showTitleScreen(); // display once
        int choice;
        do {
            clearScreen();
            showColoredMenu();
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1 -> createVendingMachine();
                    case 2 -> testVendingMachineSubMenu();
                    case 3 -> System.out.println("Exiting...");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
                choice = 0; // Set choice to 0 to continue the loop
            }
        } while (choice != 3);
    }


    private void testVendingFeatures() {
        if (specialVMChecker == -1) {
            testRegularVendingFeatures();
        } else if (specialVMChecker == 1) {
            testSpecialVendingFeatures();
        } else {
            System.out.println("Invalid vending machine type. Please create a vending machine first.");
        }
    }

    /**
     * The function "testVendingMachineSubMenu" displays a menu for testing vending machine features and
     * allows the user to choose an option.
     */
    public void testVendingMachineSubMenu() {
        if (rVendingMachine == null && sVendingMachine == null) {
            System.out.println("\u001b[31m No Vending Machine created yet.");
            System.out.println("\u001b[31m Please create a Vending Machine first.\u001B[0m");
            return;
        }

        int option;
        do {
            clearScreen();
            showTestVendingMachineSubMenu();
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1:
                    testVendingFeatures();
                    break;
                case 2:
                    performMaintenance();
                    break;
                case 3:
                    System.out.println("Going back to the main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (option != 3);
    }


    /**
     * The clearScreen() function clears the console screen in Java.
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * The function allows the user to test vending machine features by selecting an item and making a
     * payment.
     */

    private void testRegularVendingFeatures() {

        rVendingMachine.displayItems();

        System.out.print("Enter the item number you want to purchase (1-" + rVendingMachine.getSlotCount() + "): ");
        int itemNumber;
        try {
            if (scanner.hasNextInt()) {
                itemNumber = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } else {
                System.out.println("Invalid input. Please enter a valid item number.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid item number.");
            scanner.nextLine(); // Consume the invalid input
            return;
        }

        // Adjust the itemNumber value to match zero-based indexing
        int slotNumber = itemNumber;

        System.out.println("[menu DEBUG] Slot number: " + slotNumber);
        System.out.println("[menu DEBUG] Slot count: " + rVendingMachine.getSlotCount());

        if ((slotNumber < 0) || (slotNumber >= rVendingMachine.getSlotCount())) {
            System.out.println("Item slot doesn't exist");
            return;
        }


        rVendingMachine.displayUpdatedDenominationQuantities();
        System.out.print("Enter the payment denomination (1-9): ");
        int paymentDenomination;
        if (scanner.hasNextInt()) {
            paymentDenomination = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
        } else {
            System.out.println("Invalid input. Please enter a valid payment denomination.");
            return;
        }

        System.out.println("[menu DEBUG] Payment Denomination: " + paymentDenomination);

        if ((paymentDenomination < 1) || (paymentDenomination > 9)) {
            System.out.println("Denomination doesn't exist");
            return;
        }

        if (rVendingMachine.processTransaction(slotNumber, paymentDenomination)) {
            System.out.println();
            System.out.println("Transaction completed successfully.");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("Transaction failed.");
            System.out.println();
        }

    }


    private void testSpecialVendingFeatures() {
        try {  // Special Vending Machine
            double itemPrice;
            int itemCalories;
            int quantity = 0;
            int itemNumber;

            List<Integer> selectedSlotNumbers = new ArrayList<>();
            List<Integer> quantities = new ArrayList<>();
            List<Double> chosenItemPrices = new ArrayList<>();
            List<Integer> chosenItemCalories = new ArrayList<>();

            // Reset the lists for selected items and their details after a successful transaction
            selectedSlotNumbers.clear();
            quantities.clear();
            chosenItemPrices.clear();
            chosenItemCalories.clear();

            while (true) {
                sVendingMachine.displayItems();
                System.out.print("Enter the item number you want to purchase (1-" + sVendingMachine.getSlotCount() + ") or -1 to cancel: ");
                try {
                    itemNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    if (itemNumber == -1) {
                        System.out.println("Transaction canceled. Returning to the main menu.");
                        // Revert any changes made to the item quantities and sold item quantities
                        for (int i = 0; i < selectedSlotNumbers.size(); i++) {
                            int slot = selectedSlotNumbers.get(i);
                            int qty = quantities.get(i);
                            Item selectedItem = Item.getItemProperties(Item.getItemNames().get(slot - 1));
                            selectedItem.setQuantity(selectedItem.getQuantity() + qty);
                            selectedItem.getSoldItemQuantities().set(slot - 1, selectedItem.getSoldItemQuantities().get(slot - 1) - qty);
                        }
                        return;
                    }

                    if (itemNumber <= 0) {
                        System.out.println("Invalid quantity. Please enter a positive value.");
                        continue; // Ask for the quantity again
                    } else if (quantity > 10) {
                        System.out.println("Maximum quantity allowed is 10. Please enter a valid quantity.");
                        continue; // Ask for the quantity again
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid quantity.");
                    scanner.nextLine(); // Consume the invalid input
                    return;
                }
                // Adjust the itemNumber value to match zero-based indexing
                int slotNumber = itemNumber - 1;

                if ((slotNumber < 0) || (slotNumber >= sVendingMachine.getSlotCount())) {
                    System.out.println("Item slot doesn't exist");
                    continue; // Ask for the item number again
                }

                System.out.print("Enter the quantity you want to purchase for item " + itemNumber + ": ");
                quantity = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if (quantity == -1) {
                    System.out.println("Transaction canceled. Returning to the main menu.");
                    // Revert any changes made to the item quantities and sold item quantities
                    for (int i = 0; i < selectedSlotNumbers.size(); i++) {
                        int slot = selectedSlotNumbers.get(i);
                        int qty = quantities.get(i);
                        Item selectedItem = Item.getItemProperties(Item.getItemNames().get(slot - 1));
                        selectedItem.setQuantity(selectedItem.getQuantity() + qty);
                        selectedItem.getSoldItemQuantities().set(slot - 1, selectedItem.getSoldItemQuantities().get(slot - 1) - qty);
                    }
                    return;
                }
                if (quantity <= 0) {
                    System.out.println("Invalid quantity. Please enter a positive value.");
                    continue; // Ask for the quantity again
                } else if (quantity > 10) {
                    System.out.println("Maximum quantity allowed is 10. Please enter a valid quantity.");
                    continue; // Ask for the quantity again
                }


                String itemName = Item.getItemNames().get(slotNumber);
                Item item = Item.getItemProperties(itemName);
                itemPrice = item.getPrice();
                itemCalories = item.getCalories();

                double totalPrice = itemPrice * quantity;
                int totalCalories = itemCalories * quantity;

                int updatedQuantity = item.getQuantity() - quantity;
                if (updatedQuantity < 0) {
                    System.out.println("Not enough quantity available for item " + itemNumber);
                    continue; // Ask for the item number again
                }
                // Add the selected item and its details to the lists
                selectedSlotNumbers.add(itemNumber);
                quantities.add(quantity);
                chosenItemPrices.add(totalPrice);
                chosenItemCalories.add(totalCalories);



                System.out.println("Total Price for " + quantity + " items: " + totalPrice);
                System.out.println("Total Calories for " + quantity + " items: " + totalCalories);

                item.setQuantity(updatedQuantity);
                item.getSoldItemQuantities().set(slotNumber, item.getSoldItemQuantities().get(slotNumber) + quantity);

                System.out.print("Do you want to purchase another item? (Y/N): ");
                String choice = scanner.nextLine().trim().toUpperCase();
                try {
                    if (choice.equals("N")) {
                        break;
                    } else if (!choice.equals("Y")) {
                        throw new IllegalArgumentException("Invalid input. Please enter Y or N.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            System.out.println("Debug: selectedSlotNumbers: " + selectedSlotNumbers);
            System.out.println("Debug: quantities: " + quantities);
            System.out.println("Debug: chosenItemPrices: " + chosenItemPrices);
            System.out.println("Debug: chosenItemCalories: " + chosenItemCalories);


            // Calculate the total price of all selected items
            // Calculate the total price of all selected items
            double totalCost = 0;
            for (double price : chosenItemPrices) {
                totalCost += price;
            }

            // Display the selected items and their respective prices and calories
            System.out.println();
            System.out.println("Selected Items and Their Details:");
            for (int i = 0; i < selectedSlotNumbers.size(); i++) {
                itemNumber = selectedSlotNumbers.get(i) - 1; // Adjust itemNumber to 0-indexed
                Item item = Item.getItemProperties(sVendingMachine.getItemSlots().get(itemNumber));
                itemPrice = chosenItemPrices.get(i);
                itemCalories = chosenItemCalories.get(i);
                System.out.println(item.getItemName() + " - Price: " + itemPrice + " - Calories: " + itemCalories);
            }

            // Display the total price
            System.out.println("Total Price of the Selected Items: " + totalCost);
            sVendingMachine.displayUpdatedDenominationQuantities();

            System.out.print("Enter the payment denomination (1-9): ");
            int paymentDenomination = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if ((paymentDenomination < 1) || (paymentDenomination > 9)) {
                System.out.println("Denomination doesn't exist");
                return;
            }



            // Prompt for quantity of selected denomination
            System.out.print("Enter the quantity for denomination " + paymentDenomination + ": ");
            int paymentQuantity = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            if (paymentQuantity == -1) {
                System.out.println("Transaction canceled. Returning to the main menu.");
                // Revert any changes made to the item quantities and sold item quantities
                for (int i = 0; i < selectedSlotNumbers.size(); i++) {
                    int slot = selectedSlotNumbers.get(i);
                    int qty = quantities.get(i);
                    Item selectedItem = Item.getItemProperties(Item.getItemNames().get(slot - 1));
                    selectedItem.setQuantity(selectedItem.getQuantity() + qty);
                    selectedItem.getSoldItemQuantities().set(slot - 1, selectedItem.getSoldItemQuantities().get(slot - 1) - qty);
                }
                return;
            }


            // Process the transaction with the given selectedSlotNumbers, quantities, paymentDenomination, and paymentQuantity
            if (sVendingMachine.processTransaction(selectedSlotNumbers, quantities, paymentDenomination, paymentQuantity)) {
                System.out.println("Transaction completed successfully.");

                // Update the item quantities after successful transaction
                for (int i = 0; i < selectedSlotNumbers.size(); i++) {
                    int slot = selectedSlotNumbers.get(i);
                    int qty = quantities.get(i);
                    Item selectedItem = Item.getItemProperties(Item.getItemNames().get(slot - 1));

                    selectedItem.getSoldItemQuantities().set(slot - 1, selectedItem.getSoldItemQuantities().get(slot - 1) + qty);
                }
                System.out.println("Transaction completed successfully.");
            } else {
                System.out.println("Transaction failed.");
            }
        } catch (InputMismatchException e) {
            // Catch and handle the InputMismatchException
            System.out.println("Invalid input. Please enter a valid choice.");
            e.printStackTrace(); // You can choose to print the stack trace for debugging purposes
        }
    }

}