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

}
