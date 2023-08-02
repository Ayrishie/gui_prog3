import javax.swing.*;
import java.awt.*;

public class MaintenanceView {
    private JFrame frame;
    private JPanel maintenancePanel;
    private JButton refillButton;
    private JButton restockButton;
    private JButton updatePricesButton;
    private JButton collectPaymentsButton;
    private JButton printSummaryButton;
    private JButton goBackButton;
    private JDialog refillDialog;
    private JTextField slotNumberField, quantityField;
    private JButton confirmRefillButton, cancelRefillButton;
    private JDialog restockChangeDialog;
    private JTextField denominationNumberField, denominationQuantityField;
    private JButton confirmRestockButton, cancelRestockButton;
    private JDialog updatePricesDialog;
    private JTextField updateSlotNumberField, updatePriceField;
    private JButton confirmUpdatePricesButton, cancelUpdatePricesButton;

    public MaintenanceView() {
        frame = new JFrame("Maintenance Menu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        maintenancePanel = new JPanel();
        maintenancePanel.setLayout(new BoxLayout(maintenancePanel, BoxLayout.Y_AXIS));

        refillButton = new JButton("Refill Items");
        restockButton = new JButton("Restock Change");
        updatePricesButton = new JButton("Update Prices");
        collectPaymentsButton = new JButton("Collect Payments");
        printSummaryButton = new JButton("Print Summary");
        goBackButton = new JButton("Go Back");

        maintenancePanel.add(refillButton);
        maintenancePanel.add(restockButton);
        maintenancePanel.add(updatePricesButton);
        maintenancePanel.add(collectPaymentsButton);
        maintenancePanel.add(printSummaryButton);
        maintenancePanel.add(goBackButton);
        frame.add(maintenancePanel, BorderLayout.CENTER);
        initRefillDialog();
        initRestockChangeDialog();
        initUpdatePricesDialog();
    }

    public void show() {
        frame.setVisible(true);
    }
    public JFrame getFrame() {
        return this.frame;
    }


    private void initRefillDialog() {
        refillDialog = new JDialog(frame, "Refill Items", true);
        refillDialog.setSize(300, 200);
        refillDialog.setLayout(new GridLayout(3, 2));

        slotNumberField = new JTextField();
        quantityField = new JTextField();

        confirmRefillButton = new JButton("Confirm");
        cancelRefillButton = new JButton("Cancel");

        refillDialog.add(new JLabel("Slot Number:"));
        refillDialog.add(slotNumberField);

        refillDialog.add(new JLabel("Quantity:"));
        refillDialog.add(quantityField);

        refillDialog.add(confirmRefillButton);
        refillDialog.add(cancelRefillButton);
    }

    private void initRestockChangeDialog() {
        restockChangeDialog = new JDialog(frame, "Restock Change", true);
        restockChangeDialog.setSize(300, 200);
        restockChangeDialog.setLayout(new GridLayout(3, 2));

        denominationNumberField = new JTextField();
        denominationQuantityField = new JTextField();

        confirmRestockButton = new JButton("Confirm");
        cancelRestockButton = new JButton("Cancel");

        restockChangeDialog.add(new JLabel("Denomination Number:"));
        restockChangeDialog.add(denominationNumberField);

        restockChangeDialog.add(new JLabel("Quantity:"));
        restockChangeDialog.add(denominationQuantityField);

        restockChangeDialog.add(confirmRestockButton);
        restockChangeDialog.add(cancelRestockButton);
    }

    private void initUpdatePricesDialog() {
        updatePricesDialog = new JDialog(frame, "Update Prices", true);
        updatePricesDialog.setSize(300, 200);
        updatePricesDialog.setLayout(new GridLayout(3, 2));

        updateSlotNumberField = new JTextField();
        updatePriceField = new JTextField();

        confirmUpdatePricesButton = new JButton("Confirm");
        cancelUpdatePricesButton = new JButton("Cancel");

        updatePricesDialog.add(new JLabel("Slot Number:"));
        updatePricesDialog.add(updateSlotNumberField);

        updatePricesDialog.add(new JLabel("New Price:"));
        updatePricesDialog.add(updatePriceField);

        updatePricesDialog.add(confirmUpdatePricesButton);
        updatePricesDialog.add(cancelUpdatePricesButton);
    }
    public void showUpdatePricesDialog() {
        updatePricesDialog.setVisible(true);
    }

    public void hideUpdatePricesDialog() {
        updatePricesDialog.setVisible(false);
    }

    public JButton getConfirmUpdatePricesButton() { return confirmUpdatePricesButton; }
    public JButton getCancelUpdatePricesButton() { return cancelUpdatePricesButton; }
    public JTextField getUpdateSlotNumberField() { return updateSlotNumberField; }
    public JTextField getUpdatePriceField() { return updatePriceField; }
    public void showRestockChangeDialog() {
        restockChangeDialog.setVisible(true);
    }

    public void hideRestockChangeDialog() {
        restockChangeDialog.setVisible(false);
    }
    public JButton getConfirmRestockButton() { return confirmRestockButton; }
    public JButton getCancelRestockButton() { return cancelRestockButton; }
    public JTextField getDenominationNumberField() { return denominationNumberField; }
    public JTextField getDenominationQuantityField() { return denominationQuantityField; }
    public void showRefillDialog() {
        refillDialog.setVisible(true);
    }

    public void hideRefillDialog() {
        refillDialog.setVisible(false);
    }
    public JButton getConfirmRefillButton() { return confirmRefillButton; }
    public JButton getCancelRefillButton() { return cancelRefillButton; }
    public JTextField getSlotNumberField() { return slotNumberField; }
    public JTextField getQuantityField() { return quantityField; }

    // Getters for the buttons for our controller to use
    public JButton getRefillButton() { return refillButton; }
    public JButton getRestockButton() { return restockButton; }
    public JButton getUpdatePricesButton() { return updatePricesButton; }
    public JButton getCollectPaymentsButton() { return collectPaymentsButton; }
    public JButton getPrintSummaryButton() { return printSummaryButton; }
    public JButton getGoBackButton() { return goBackButton; }
}
