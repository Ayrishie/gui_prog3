import java.util.List;
import java.util.ArrayList;

public class VendingMachineGUIController {
    private SpecialVendingMachine sVendingMachine;
    private List<Integer> selectedSlotNumbers;
    private List<Integer> quantities;
    private List<Double> chosenItemPrices;
    private List<Integer> chosenItemCalories;
    private int paymentDenomination;
    private int paymentQuantity;

    public VendingMachineGUIController(SpecialVendingMachine sVendingMachine) {
        this.paymentDenomination = paymentDenomination;
        this.paymentQuantity = paymentQuantity;
        this.sVendingMachine = sVendingMachine;
        this.selectedSlotNumbers = new ArrayList<>();
        this.quantities = new ArrayList<>();
        this.chosenItemPrices = new ArrayList<>();
        this.chosenItemCalories = new ArrayList<>();
    }

    public String selectItem(int itemNumber, int quantity) {
        if (itemNumber == -1) {
            // ... revert changes logic ...
            return "Transaction canceled.";
        }

        int slotNumber = itemNumber - 1;

        if ((slotNumber < 0) || (slotNumber >= sVendingMachine.getSlotCount())) {
            return "Item slot doesn't exist";
        }

        if (quantity <= 0 || quantity > 10) {
            return "Invalid quantity. Please enter a positive value up to 10.";
        }

        String itemName = Item.getItemNames().get(slotNumber);
        Item item = Item.getItemProperties(itemName);
        double itemPrice = item.getPrice();
        int itemCalories = item.getCalories();

        double totalPrice = itemPrice * quantity;
        int totalCalories = itemCalories * quantity;

        int updatedQuantity = item.getQuantity() - quantity;
        if (updatedQuantity < 0) {
            return "Not enough quantity available for item " + itemNumber;
        }

        // Update item details
        item.setQuantity(updatedQuantity);
        item.getSoldItemQuantities().set(slotNumber, item.getSoldItemQuantities().get(slotNumber) + quantity);

        // Add the selected item and its details to the lists
        selectedSlotNumbers.add(itemNumber);
        quantities.add(quantity);
        chosenItemPrices.add(totalPrice);
        chosenItemCalories.add(totalCalories);

        return "Selected " + quantity + " of item " + itemNumber + ". Total Price: " + totalPrice + ". Total Calories: " + totalCalories;
    }

    public String enterPayment(int paymentDenomination, int paymentQuantity) {
        this.paymentDenomination = paymentDenomination;  // Add this
        this.paymentQuantity = paymentQuantity;
        if ((paymentDenomination < 1) || (paymentDenomination > 9)) {
            return "Denomination doesn't exist";
        }

        if (paymentQuantity == -1) {
            // ... revert changes logic ...
            return "Transaction canceled.";
        }

        // ... logic for processing the payment ...

        return "Payment of " + paymentQuantity + " in denomination " + paymentDenomination + " accepted.";
    }

    public String finalizeTransaction() {
        // Here, you'd handle final transaction logic, like updating item stocks,
        // dispensing items, handling change, etc.

        if (sVendingMachine.processTransaction(selectedSlotNumbers, quantities, paymentDenomination, paymentQuantity)) {
            // Clear the selected items after a successful transaction
            selectedSlotNumbers.clear();
            quantities.clear();
            chosenItemPrices.clear();
            chosenItemCalories.clear();
            return "Transaction completed successfully.";
        } else {
            return "Transaction failed.";
        }
    }
}
