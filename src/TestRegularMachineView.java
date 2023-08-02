import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.ShortLookupTable;
import java.util.ArrayList;
import java.util.Arrays;
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
    private int transactionCount;

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
        columnNames.add("Denomination"); // New column for denomination values

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
            System.out.println("Denomination Value: " + denominationValues.get(i));
            rowData.add(denominationValues.get(i)); // New column for denomination values

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

            if (slot < 0 || slot >= Item.getItemNames().size()) {
                showMessage("Invalid slot number selected.");
                return false;
            }

            showMessage("SLOT : LOOB NG VIEW" + slot);
            double change = vendingMachine.processTransaction(slot, paymentDenomination);

            if (change >= 0) {
                showMessage("Transaction successful!");

                // Update quantities and display items
                Item item = Item.getItemProperties(Item.getItemNames().get(slot));
                int updatedQuantity = item.getQuantity() - 1;
                item.setQuantity(updatedQuantity);

               int[] changeQuantities = vendingMachine.calculateChangeQuantities(change);

                List<Integer> changeQuantitiesList = new ArrayList<>();
                for (int quantity : changeQuantities) {
                    changeQuantitiesList.add(quantity);
                }

                giveChange(change, changeQuantities, vendingMachine.getDenominationQuantities(), vendingMachine.getDenominationValues()); // Corrected parameter list

                JOptionPane.showMessageDialog(frame, "Change: " + change, "Change", JOptionPane.INFORMATION_MESSAGE);

                // Print the receipt
                ReceiptDialog receiptDialog = new ReceiptDialog(frame, slot, updatedQuantity, change,  changeQuantitiesList,
                        vendingMachine.getDenominationValues());

                receiptDialog.showDialog();



                return true;
            } else {
                showMessage("Transaction failed.");
                return false;
            }
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


    public boolean giveChange(double change, int[] changeQuantities, List<Integer> denominationQuantities, List<Integer> denominationValues) {
        try {
            System.out.println("|============================================|");
            System.out.println("|========= Available Bills For Change =======|");
            System.out.println("|============================================|");

            if (changeQuantities == null) {
                return false; // Change cannot be given exactly
            }

            for (int i = changeQuantities.length - 1; i >= 0; i--) {
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
            for (int i = 0; i < changeQuantities.length; i++) {
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



    private class ReceiptDialog extends JDialog {
        private double getTotalSales() {
            return vendingMachine.getTotalSales(); // Call the method from RegularVendingMachine
        }

        private int getTransactionCount() {
            return vendingMachine.getTransactionCount(); // Call the method from RegularVendingMachine
        }

        public ReceiptDialog(JFrame parent, int slot, int quantity, double change, List<Integer> denominationQuantities, List<Integer> denominationValues) {
            super(parent, "Receipt", true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(parent);

            JTextArea receiptTextArea = new JTextArea();
            receiptTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            receiptTextArea.setEditable(false);



            receiptTextArea.append("==============================================\n");
            receiptTextArea.append("|           RAIO  Vending Machine            |\n");
            receiptTextArea.append("|============================================|\n");
            receiptTextArea.append("| Item purchased: " + getItemName(slot) + "\n");
            receiptTextArea.append("|============================================|\n");
            receiptTextArea.append("| Before quantity:         " + (quantity + 1) + "\n");
            receiptTextArea.append("| After quantity:          " + quantity + "\n");
            receiptTextArea.append("|============================================|\n");
            receiptTextArea.append(String.format("| Total Sales: $%.2f             %n", getTotalSales()));
            receiptTextArea.append(String.format("| Total Transactions %d                %n", getTransactionCount()));

            if (change >= 0) {
                receiptTextArea.append(String.format("| Change: $%.2f              %n", change));
            }

            receiptTextArea.append("|--------------------------------------------|\n");
            receiptTextArea.append("|============================================|\n");
            receiptTextArea.append("|     Updated Denomination Quantities        |\n");
            receiptTextArea.append("|============================================|\n");

            for (int i = 0; i < denominationQuantities.size(); i++) {
                int denominationValue = denominationValues.get(i);
                int denominationQuantity = denominationQuantities.get(i);
                receiptTextArea.append("| " + denominationValue + ":\t" + denominationQuantity + "\n");
            }

            receiptTextArea.append("============================================\n");

            JScrollPane scrollPane = new JScrollPane(receiptTextArea);
            getContentPane().add(scrollPane, BorderLayout.CENTER);
        }

        private String getItemName(int slot) {
            // Replace with your logic to get the item name
            return "Item Name";
        }


        public void showDialog() {
            setVisible(true);
        }
    }
    }
