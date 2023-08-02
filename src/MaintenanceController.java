import javax.swing.*;
public class MaintenanceController {
    private MaintenanceView mView;
    private RegularVendingMachine rVendingMachine;
    private Maintenance maintenance; // Assuming you have a Maintenance class
    private VendingMachineView mainView;


    public MaintenanceController(MaintenanceView view, RegularVendingMachine machine, VendingMachineView mainView) {
        this.mView = view;
        this.rVendingMachine = machine;
        this.mainView = mainView;
        maintenance = new Maintenance();  // Initialize the maintenance class
        initController();
    }


    private void initController() {
        mView.getCollectPaymentsButton().addActionListener(e -> maintenance.dispenseTotalPayments(rVendingMachine));
        mView.getRefillButton().addActionListener(e -> {mView.showRefillDialog();});

        mView.getConfirmRefillButton().addActionListener(e -> {
            try {
                int slotNumber = Integer.parseInt(mView.getSlotNumberField().getText());
                int quantity = Integer.parseInt(mView.getQuantityField().getText());

                String resultMessage = rVendingMachine.refillItem(slotNumber, quantity);
                JOptionPane.showMessageDialog(null, resultMessage);

                mView.hideRefillDialog();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for slot and quantity.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        });
        mView.getCancelRefillButton().addActionListener(e -> mView.hideRefillDialog());
        mView.getRestockButton().addActionListener(e -> {
            mView.showRestockChangeDialog();
        });

        mView.getConfirmRestockButton().addActionListener(e -> {
            try {
                int denominationNumber = Integer.parseInt(mView.getDenominationNumberField().getText());
                int quantity = Integer.parseInt(mView.getDenominationQuantityField().getText());

                String resultMessage = rVendingMachine.restockChange(denominationNumber, quantity);
                JOptionPane.showMessageDialog(null, resultMessage);

                mView.hideRestockChangeDialog();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for denomination and quantity.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        });

        mView.getCancelRestockButton().addActionListener(e -> mView.hideRestockChangeDialog());

        mView.getUpdatePricesButton().addActionListener(e -> mView.showUpdatePricesDialog());

        mView.getConfirmUpdatePricesButton().addActionListener(e -> {
            try {
                int slotNumber = Integer.parseInt(mView.getUpdateSlotNumberField().getText());
                double newPrice = Double.parseDouble(mView.getUpdatePriceField().getText());

                // Since this method originally took the vending machine as an argument,
                // we now use the rVendingMachine variable which is already available in this class
                maintenance.updatePrices(rVendingMachine, slotNumber, newPrice);

                mView.hideUpdatePricesDialog();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers for slot and price.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        });

        mView.getCancelUpdatePricesButton().addActionListener(e -> mView.hideUpdatePricesDialog());
        mView.getGoBackButton().addActionListener(e -> {
            mView.getFrame().dispose();
            mainView.showTestVendingMachinePanel();
        });

        mView.getCollectPaymentsButton().addActionListener(e -> {
            double totalPayments = maintenance.dispenseTotalPayments(rVendingMachine);
            JOptionPane.showMessageDialog(mView.getFrame(), "Total payments: $" + totalPayments + "\nPayments dispensed.");
        });
        mView.getPrintSummaryButton().addActionListener(e -> {
            String summary = rVendingMachine.generateSummary();
            JOptionPane.showMessageDialog(mView.getFrame(), summary, "Item Summary", JOptionPane.INFORMATION_MESSAGE);
        });
    }



}
