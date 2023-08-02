import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class TestRegularMachineView {
    private JFrame frame;
    private JTextField itemNumberField;
    private JTextField paymentDenominationField;
    private JButton executeButton;
    private JTextArea messageArea;
    private JTable itemTable;
    private DefaultTableModel tableModel;
    private RegularVendingMachine vendingMachine;

    public TestRegularMachineView(RegularVendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        frame = new JFrame("Test Regular Vending Machine");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setSize(500, 400);

        // Table for displaying item details
        Vector<String> columnNames = new Vector<>();
        columnNames.add("No");
        columnNames.add("Item");
        columnNames.add("Quantity");
        columnNames.add("Price");
        columnNames.add("Calories");

        tableModel = new DefaultTableModel(columnNames, 0);  // 0 indicates no rows initially
        itemTable = new JTable(tableModel);
        updateItemTable(); // Fill the table with data from the Item class

        itemNumberField = new JTextField(10);
        paymentDenominationField = new JTextField(10);
        executeButton = new JButton("Execute Test");
        executeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGUIBasedTransaction(); // Call the method when the button is clicked
            }
        });
        messageArea = new JTextArea(10, 30);
        messageArea.setEditable(false);

        frame.add(new JScrollPane(itemTable));  // Wrap table in scroll pane
        frame.add(new JLabel("Enter item number:"));
        frame.add(itemNumberField);
        frame.add(new JLabel("Enter payment denomination:"));
        frame.add(paymentDenominationField);
        frame.add(executeButton);
        frame.add(new JScrollPane(messageArea));
    }

    public void updateItemTable() {
        tableModel.setRowCount(0); // Clear the table
        for (int i = 0; i < Item.getItemNames().size(); i++) {
            String itemName = Item.getItemNames().get(i);
            Item item = Item.getItemProperties(itemName);
            Vector<Object> rowData = new Vector<>();
            rowData.add(i + 1);
            rowData.add(itemName);
            rowData.add(item.getQuantity());
            rowData.add("$" + item.getPrice());
            rowData.add(item.getCalories());
            List<Integer> denominationValues = vendingMachine.getDenominationValues();
            rowData.add(denominationValues.get(i)); // Assuming the denominationValues list contains Integer values.
            tableModel.addRow(rowData);
        }
    }

    public void showMessage(String message) {
        messageArea.append(message + "\n");
    }

    public String getItemNumber() {
        return itemNumberField.getText();
    }

    public String getPaymentDenomination() {
        return paymentDenominationField.getText();
    }

    public JButton getExecuteButton() {
        return executeButton;
    }

    public void show() {
        frame.setVisible(true);
    }

    public void hide() {
        frame.setVisible(false);
    }

    public boolean processGUIBasedTransaction() {
        try {
            int slot = Integer.parseInt(getItemNumber());
            int paymentDenomination = Integer.parseInt(getPaymentDenomination());
            slot -= 1;

            if (slot < 0 || slot >= Item.getItemNames().size()) {
                showMessage("Invalid slot number selected.");
                return false;
            }

            String itemName = Item.getItemNames().get(slot);
            Item item = Item.getItemProperties(itemName);

            // Check if the item is available
            int quantity = item.getInitialQuantities().get(slot);
            if (quantity <= 0) {
                showMessage("Item out of stock.");
                return false;
            }

            // Check if the payment denomination is valid
            List<Integer> denominationValues = vendingMachine.getDenominationValues();
            if (paymentDenomination <= 0 || paymentDenomination > denominationValues.size()) {
                showMessage("Invalid payment denomination.");
                return false;
            }

            double paymentAmount = denominationValues.get(paymentDenomination - 1);

            if (paymentAmount < item.getPrice()) {
                showMessage("Insufficient payment. Please add more or cancel.");
                return false;
            }

            // Calculate change
            double change = paymentAmount - item.getPrice();
            int[] changeQuantities = vendingMachine.calculateChangeQuantities(change);

            if (changeQuantities == null) {
                showMessage("Unable to give change. Transaction failed.");
                return false;
            }


            if (!vendingMachine.giveChange(change)) {
                showMessage("Unable to give change. Transaction failed.");
                return false;
            }

            showAvailableChange(changeQuantities, vendingMachine.getDenominationValues());


            int updatedQuantity = item.getQuantity() - 1;
            item.setQuantity(updatedQuantity);

            // For this demo, I'm not adding the logic of transactionCount and totalSales.

            showMessage("Transaction successful! Change: $" + change);
            updateItemTable();  // Update the table view to reflect the new quantity

            return true;
        } catch (NumberFormatException e) {
            showMessage("Please enter valid numbers for item number and payment denomination.");
            return false;
        } catch (IndexOutOfBoundsException e) {
            showMessage("Invalid item or denomination selection.");
            return false;
        } catch (Exception e) {
            showMessage("An unexpected error occurred: " + e.getMessage());
            return false;
        }
    }

    public void showAvailableChange(int[] changeQuantities, List<Integer> denominationValues) {
        JFrame changeFrame = new JFrame("Change");
        changeFrame.setLayout(new BoxLayout(changeFrame.getContentPane(), BoxLayout.Y_AXIS));

        JTextArea changeTextArea = new JTextArea(10, 30);
        changeTextArea.setEditable(false);

        for (int i = 0; i < changeQuantities.length; i++) {
            if (changeQuantities[i] > 0) {
                changeTextArea.append("$" + denominationValues.get(i) + " x " + changeQuantities[i] + "\n");
            }
        }

        changeFrame.add(new JScrollPane(changeTextArea));
        changeFrame.pack();
        changeFrame.setVisible(true);
    }



}
