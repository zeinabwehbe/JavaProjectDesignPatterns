package view;

import controller.CategoryController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryView {
    private final CategoryController categoryController;
    private JPanel panel;
    private JTextField categoryIdField;
    private JTextField categoryNameField;
    private JButton addCategoryButton;
    private JComboBox<String> categoryDropdown;

    public CategoryView(CategoryController categoryController) {
        this.categoryController = categoryController;
        initializeUI();
    }

    public JPanel getPanel() {
        return panel;
    }

    private void initializeUI() {
        panel = new JPanel(new GridLayout(4, 2));
        panel.setBackground(new Color(240, 240, 240));

        panel.add(createLabel("Category ID:"));
        categoryIdField = new JTextField();
        panel.add(categoryIdField);

        panel.add(createLabel("Category Name:"));
        categoryNameField = new JTextField();
        panel.add(categoryNameField);

        addCategoryButton = createButton("Add Category");
        addCategoryButton.addActionListener(new AddCategoryButtonListener());
        panel.add(addCategoryButton);

        panel.add(createLabel("Select Category:"));
        categoryDropdown = new JComboBox<>();
        panel.add(categoryDropdown);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        return button;
    }

    public void updateDropdown() {
        categoryDropdown.removeAllItems();
        for (var category : categoryController.getCategories()) {
            categoryDropdown.addItem(category.getCategoryName());
        }
    }

    private class AddCategoryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String categoryId = categoryIdField.getText();
            String categoryName = categoryNameField.getText();

            if (categoryId.isEmpty() || categoryName.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "All fields must be filled out!");
                return;
            }

            categoryController.addCategory(categoryId, categoryName);

            // Clear fields
            categoryIdField.setText("");
            categoryNameField.setText("");
        }
    }
}
