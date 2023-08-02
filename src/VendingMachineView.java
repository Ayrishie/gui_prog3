import javax.swing.*;
import java.awt.*;

public class VendingMachineView {
    private JFrame frame;
    private JButton createMachineButton;
    private JButton testMachineButton;
    private JButton exitButton;
    private JPanel mainPanel;
    private JPanel createVendingMachinePanel;
    private JButton regularButton;
    private JButton specialButton;
    private JButton returnButton;
    private JPanel testVendingMachinePanel;
    private JButton initiateTestButton;
    private JButton maintenanceButton;
    private JButton backButton;

    public VendingMachineView() {
        frame = new JFrame("RAIO VENDING MACHINE FACTORY");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 300);
        frame.setLayout(new BorderLayout());

        // Top Panel
        JLabel topLabel = new JLabel("WELCOME TO RAIO VENDING MACHINE FACTORY", JLabel.CENTER);
        topLabel.setForeground(new Color(128, 0, 0)); // Dark red color
        frame.add(topLabel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        initMainPanel();
        frame.add(mainPanel, BorderLayout.CENTER);

        initCreateVendingMachinePanel();
        initTestVendingMachinePanel();
    }
    public JButton getReturnButton() {
        return returnButton;
    }



    public void initMainPanel() {
        mainPanel.setBackground(new Color(255, 223, 186));  // Pizza crust color
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Buttons with revised pizza themed colors
        createMachineButton = new JButton("Create a Vending Machine");
        styleButton(createMachineButton);
        testMachineButton = new JButton("Test a Vending Machine");
        styleButton(testMachineButton);
        exitButton = new JButton("Exit");
        styleButton(exitButton);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(createMachineButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(testMachineButton);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(exitButton);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(255, 153, 0)); // Cheese color
        button.setForeground(Color.BLACK);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public void show() {
        frame.setVisible(true);
    }

    public JButton getCreateMachineButton() {
        return createMachineButton;
    }

    public JButton getTestMachineButton() {
        return testMachineButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void initCreateVendingMachinePanel() {
        createVendingMachinePanel = new JPanel();
        createVendingMachinePanel.setBackground(new Color(255, 223, 186)); // Pizza crust color
        createVendingMachinePanel.setLayout(new BoxLayout(createVendingMachinePanel, BoxLayout.Y_AXIS));

        // Buttons
        regularButton = new JButton("Regular Vending Machine");
        styleButton(regularButton);
        specialButton = new JButton("Special Vending Machine");
        styleButton(specialButton);

        // Initialize the return button
        returnButton = new JButton("Return");
        styleButton(returnButton); // Adding styling to the return button

        // Add the buttons in the desired order
        createVendingMachinePanel.add(regularButton);
        createVendingMachinePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        createVendingMachinePanel.add(specialButton);
        createVendingMachinePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        createVendingMachinePanel.add(returnButton);
    }

    public void initTestVendingMachinePanel() {
        testVendingMachinePanel = new JPanel();
        testVendingMachinePanel.setBackground(new Color(255, 223, 186)); // Pizza crust color
        testVendingMachinePanel.setLayout(new BoxLayout(testVendingMachinePanel, BoxLayout.Y_AXIS));

        // Label
        JLabel testLabel = new JLabel("Test Vending Machine Features", JLabel.CENTER);
        testVendingMachinePanel.add(testLabel);

        // Buttons
        initiateTestButton = new JButton("Initiate Test");
        styleButton(initiateTestButton);
        maintenanceButton = new JButton("Maintenance");
        styleButton(maintenanceButton);

        backButton = new JButton("Back");
        styleButton(backButton);

        // Add buttons to the panel
        testVendingMachinePanel.add(initiateTestButton);
        testVendingMachinePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        testVendingMachinePanel.add(maintenanceButton);
        testVendingMachinePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        testVendingMachinePanel.add(backButton); // Adding the back button
    }



    public JButton getInitiateTestButton() {
        return initiateTestButton;
    }

    public JButton getMaintenanceButton() {
        return maintenanceButton;
    }

    public JButton getBackButton() { return backButton; }


    public void showCreateVendingMachinePanel() {
        frame.remove(mainPanel);
        frame.add(createVendingMachinePanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public JButton getRegularButton() {
        return regularButton;
    }

    public JButton getSpecialButton() {
        return specialButton;
    }


    public void showTestVendingMachinePanel() {
        frame.remove(mainPanel);
        if (testVendingMachinePanel != null) {  // Check if testVendingMachinePanel is not null
            frame.add(testVendingMachinePanel, BorderLayout.CENTER);
        } else {
            // Handle this situation, e.g., show an error message or initialize the panel.
            JOptionPane.showMessageDialog(frame, "Test Vending Machine Panel has not been initialized.");
        }
        frame.revalidate();
        frame.repaint();
    }

    public void showMainPanel() {
        if (createVendingMachinePanel != null) {
            frame.remove(createVendingMachinePanel);
        }
        if (testVendingMachinePanel != null) {
            frame.remove(testVendingMachinePanel);
        }

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }


}
