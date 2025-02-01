package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CategoryView {
    private JPanel panel;
    private JComboBox<String> categoryDropdown;

    public CategoryView() {
        initializeUI();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initializeUI() {
        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBackground(new Color(240, 240, 240));

    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    // Method to populate the dropdown with static category data
    public void updateDropdown() {
        categoryDropdown.removeAllItems();

        // Static data (hardcoded categories)
        String[] categories = {"Electronics", "Books", "Clothing", "Home Appliances"};

        for (String category : categories) {
            categoryDropdown.addItem(category);
        }
    }

    public String getSelectedCategory() {
        return (String) categoryDropdown.getSelectedItem();
    }
}
