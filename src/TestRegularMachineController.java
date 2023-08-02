import javax.swing.*;

public class TestRegularMachineController {
    private TestRegularMachineView view;
    private RegularVendingMachine model;

    public TestRegularMachineController(TestRegularMachineView view, RegularVendingMachine model) {
        this.view = view;
        this.model = model;
        view.getExecuteButton().addActionListener(e -> testFeatures());
    }

    private void testFeatures() {
        // Your existing logic for testing features comes here,
        // but adjust it to fetch inputs from the GUI and display messages.
        int itemNumber = Integer.parseInt(view.getItemNumber());  // Add error handling
        int paymentDenomination = Integer.parseInt(view.getPaymentDenomination());  // Add error handling

        // Your existing testing logic here, but use `view.showMessage(message)`
        // instead of `System.out.println(message)`.
    }

}
