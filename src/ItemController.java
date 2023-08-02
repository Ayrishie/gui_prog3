import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

public class ItemController {
    private InitializeItemsDialog view;
    private Map<String, Item> items;

    public ItemController(InitializeItemsDialog view, Map<String, Item> items) {
        this.view = view;
        this.items = items;

        this.view.addSubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItemProperties();
            }
        });
    }

    private void updateItemProperties() {
        Map<String, Item> newItemProperties = view.getItemProperties();

        for (String itemName : newItemProperties.keySet()) {
            Item updatedItem = newItemProperties.get(itemName);
            Item existingItem = this.items.get(itemName);

            existingItem.setPrice(updatedItem.getPrice());
            existingItem.setCalories(updatedItem.getCalories());
        }
    }

    public void displayView() {
        view.display();
    }
}
