import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VendingMachineGUI {

    private JFrame frame;
    private JTextArea displayArea;
    private JTextField itemNumberField, quantityField, paymentDenominationField, paymentQuantityField;
    private JButton itemNumberButton, quantityButton, finalizeButton, paymentButton;
    private JLabel totalPriceLabel, totalCaloriesLabel;
    private VendingMachineGUIController controller;

    public VendingMachineGUI(VendingMachineGUIController controller) {
        this.controller = controller;

        frame = new JFrame("Special Vending Machine");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));  // Adjusted the grid layout

        itemNumberField = new JTextField();
        quantityField = new JTextField();
        paymentDenominationField = new JTextField();
        paymentQuantityField = new JTextField();

        itemNumberButton = new JButton("Enter Item Number");
        quantityButton = new JButton("Enter Quantity");
        paymentButton = new JButton("Enter Payment");
        finalizeButton = new JButton("Finalize Transaction");

        totalPriceLabel = new JLabel("Total Price: ");
        totalCaloriesLabel = new JLabel("Total Calories: ");

        // Add components to panel in a more organized manner
        panel.add(new JLabel("Item Number:"));
        panel.add(itemNumberField);
        panel.add(itemNumberButton);

        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(quantityButton);

        panel.add(new JLabel("Payment Denomination:"));
        panel.add(paymentDenominationField);
        panel.add(new JLabel("Payment Quantity:"));
        panel.add(paymentQuantityField);
        panel.add(paymentButton);

        panel.add(totalPriceLabel);
        panel.add(totalCaloriesLabel);
        panel.add(new JLabel());  // Placeholder to align Finalize button properly
        panel.add(finalizeButton);

        frame.add(displayArea, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        initializeButtonActions();

        frame.setVisible(true);
    }

    private void initializeButtonActions() {
        // Initialize actions for each button
        itemNumberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int itemNumber = Integer.parseInt(itemNumberField.getText());
                    int qty = Integer.parseInt(quantityField.getText());
                    String message = controller.selectItem(itemNumber, qty);
                    displayArea.append(message + "\n");
                } catch (Exception ex) {
                    displayArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });

        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int denomination = Integer.parseInt(paymentDenominationField.getText());
                    int qty = Integer.parseInt(paymentQuantityField.getText());
                    String message = controller.enterPayment(denomination, qty);
                    displayArea.append(message + "\n");
                } catch (Exception ex) {
                    displayArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });

        finalizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String message = controller.finalizeTransaction();
                displayArea.append(message + "\n");
            }
        });
    }
}
