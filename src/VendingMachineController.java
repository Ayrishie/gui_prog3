import javax.swing.*;

public class VendingMachineController {
    private VendingMachineView view;
    private RegularVendingMachine rVendingMachine;
    private SpecialVendingMachine sVendingMachine;
    private MaintenanceView maintenanceView;
    private MaintenanceController maintenanceController;
    private TestRegularMachineView testRegularMachineView;
    private TestRegularMachineController testRegularMachineController;




    public VendingMachineController(VendingMachineView view) {
        this.view = view;
        initController();  // Call this method to set up all the action listeners
    }

    private void initController() {
        if (view.getCreateMachineButton() != null)
            view.getCreateMachineButton().addActionListener(e -> view.showCreateVendingMachinePanel());

        if (view.getTestMachineButton() != null)
            view.getTestMachineButton().addActionListener(e -> testVendingMachine());

        if (view.getExitButton() != null)
            view.getExitButton().addActionListener(e -> exitApp());

        if (view.getRegularButton() != null)
            view.getRegularButton().addActionListener(e -> createRegularVendingMachine());

        if (view.getSpecialButton() != null)
            view.getSpecialButton().addActionListener(e -> createSpecialVendingMachine());

        if (view.getReturnButton() != null)
            view.getReturnButton().addActionListener(e -> view.showMainPanel());

        if (view.getInitiateTestButton() != null)
            view.getInitiateTestButton().addActionListener(e -> initiateTest());

        if (view.getMaintenanceButton() != null)
            view.getMaintenanceButton().addActionListener(e -> performMaintenance());

        if (view.getBackButton() != null)
            view.getBackButton().addActionListener(e -> view.showMainPanel());
    }

    private void testVendingMachine() {
        if (rVendingMachine == null && sVendingMachine == null) {
            JOptionPane.showMessageDialog(null, "No Vending Machine created yet. Please create a Vending Machine first.");
            return;
        }
        view.showTestVendingMachinePanel();
    }

    private void initiateTest() {
        if (rVendingMachine != null) {
            if (testRegularMachineView == null) {
                testRegularMachineView = new TestRegularMachineView(rVendingMachine);
                testRegularMachineController = new TestRegularMachineController(testRegularMachineView, rVendingMachine);
            }
            testRegularMachineView.show();
        } else if (sVendingMachine != null) {
            VendingMachineGUIController guiController = new VendingMachineGUIController(sVendingMachine);
            VendingMachineGUI gui = new VendingMachineGUI(guiController);
        } else {
            JOptionPane.showMessageDialog(null, "No Vending Machine to test. Please create one first.");
        }
    }


    private void performMaintenance() {
        if (rVendingMachine != null) {
            if (maintenanceView == null) {
                maintenanceView = new MaintenanceView();
                maintenanceController = new MaintenanceController(maintenanceView, rVendingMachine, view);
            }
            maintenanceView.show();
        } else {
            JOptionPane.showMessageDialog(null, "Regular Vending Machine not created yet.");
        }
    }


    private void createRegularVendingMachine() {
        rVendingMachine = new RegularVendingMachine();
        JOptionPane.showMessageDialog(null, "Regular Vending Machine created.");
    }

    private void createSpecialVendingMachine() {
        sVendingMachine = new SpecialVendingMachine();
        JOptionPane.showMessageDialog(null, "Special Vending Machine created.");
    }

    private void exitApp() {
        System.exit(0);
    }


}
