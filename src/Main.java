/**
 * This class represents the entry point of the program.
 */
public class Main {
    public static void main(String[] args) {
        VendingMachineView view = new VendingMachineView();
        VendingMachineController controller = new VendingMachineController(view);
        view.show();
    }
}



