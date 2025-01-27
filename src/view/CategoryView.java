package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import controller.CategoryController;

public class CategoryView {
    private JPanel panel;
    private JTextField categoryIdField;
    private JTextField categoryNameField;
    private JButton addCategoryButton;
    private JTextArea displayArea;
    private CategoryController categoryController;

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

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        panel.add(new JScrollPane(displayArea));
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

    public String getCategoryId() {
        return categoryIdField.getText();
    }

    public String getCategoryName() {
        return categoryNameField.getText();
    }

    public void setDisplayArea(String text) {
        displayArea.setText(text);
    }

    private class AddCategoryButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String categoryId = getCategoryId();
            String categoryName = getCategoryName();

            if (categoryId.isEmpty() || categoryName.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "All fields must be filled out!");
                return;
            }

            categoryController.addCategory(categoryId, categoryName);

            // Update display area
            StringBuilder displayText = new StringBuilder("Categories:\n");
            for (var category : categoryController.getCategories()) {
                displayText.append("ID: ").append(category.getCategoryId()).append(", Name: ").append(category.getCategoryName()).append("\n");
            }
            setDisplayArea(displayText.toString());

            // Clear fields
            categoryIdField.setText("");
            categoryNameField.setText("");
        }
    }
}
