import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DenominationController {
    private DenominationDialog view;
    private RegularVendingMachine model;

    public DenominationController(DenominationDialog view, RegularVendingMachine model) {
        this.view = view;
        this.model = model;

        this.view.addSubmitListener(new SubmitButtonListener());
    }

    class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<Integer> quantities = view.getDenominationQuantities();
            model.setDenominationQuantities(quantities);
            view.dispose();
        }
    }

    public void displayView() {
        view.display();
    }
}