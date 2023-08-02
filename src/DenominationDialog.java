import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionListener;


public class DenominationDialog extends JDialog {
    private List<JTextField> denominationFields;
    private JButton submitButton;
    private List<String> denominationNames;

    public DenominationDialog(JFrame owner, List<String> denominationNames) {
        super(owner, "Set Denominations", true);
        setSize(300, 400);
        setLayout(new GridLayout(0, 2));

        this.denominationNames = denominationNames;
        denominationFields = new ArrayList<>();

        for (String denominationName : denominationNames) {
            add(new JLabel(denominationName));
            JTextField denominationField = new JTextField();
            denominationFields.add(denominationField);
            add(denominationField);
        }

        submitButton = new JButton("Submit");
        add(submitButton);
    }

    public List<Integer> getDenominationQuantities() {
        List<Integer> quantities = new ArrayList<>();
        for (JTextField field : denominationFields) {
            quantities.add(Integer.parseInt(field.getText()));
        }
        return quantities;
    }

    public void addSubmitListener(ActionListener listenForSubmitButton) {
        submitButton.addActionListener(listenForSubmitButton);
    }

    public void display() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}