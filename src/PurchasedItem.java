// PurchasedItem.java
public class PurchasedItem {
    private int slot;
    private int quantity;

    public PurchasedItem(int slot, int quantity) {
        this.slot = slot;
        this.quantity = quantity;
    }

    public int getSlot() {
        return slot;
    }

    public int getQuantity() {
        return quantity;
    }
}