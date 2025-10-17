import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PackWiseApp extends JFrame {
    private ChecklistManager manager = new ChecklistManager();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> checklistView = new JList<>(listModel);

    public PackWiseApp() {
        setTitle("PackWise - Smart Packing Checklist");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        JComboBox<String> destinationBox = new JComboBox<>(new String[]{"BEACH", "MOUNTAINS", "CITY", "ABROAD"});
        JComboBox<String> durationBox = new JComboBox<>(new String[]{"SHORT", "MEDIUM", "LONG"});
        JCheckBox hikingBox = new JCheckBox("Hiking");
        JCheckBox photoBox = new JCheckBox("Photography");
        JCheckBox businessBox = new JCheckBox("Business");

        inputPanel.add(new JLabel("Destination:"));
        inputPanel.add(destinationBox);
        inputPanel.add(new JLabel("Duration:"));
        inputPanel.add(durationBox);
        inputPanel.add(hikingBox);
        inputPanel.add(photoBox);
        inputPanel.add(businessBox);

        JButton generateBtn = new JButton("Generate Checklist");
        generateBtn.addActionListener(e -> {
            TripProfile profile = new TripProfile(
                TripProfile.Destination.valueOf((String) destinationBox.getSelectedItem()),
                TripProfile.Duration.valueOf((String) durationBox.getSelectedItem()),
                hikingBox.isSelected(), photoBox.isSelected(), businessBox.isSelected()
            );
            List<String> items = ChecklistGenerator.generateChecklist(profile);
            manager.setChecklist(items);
            refreshChecklist();
        });

        JPanel checklistPanel = new JPanel(new BorderLayout());
        checklistView.setFont(new Font("Arial", Font.PLAIN, 14));
        checklistPanel.add(new JScrollPane(checklistView), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JTextField newItemField = new JTextField(15);
        JButton addBtn = new JButton("Add");
        JButton removeBtn = new JButton("Remove");
        JButton saveBtn = new JButton("Save");

        addBtn.addActionListener(e -> {
            String item = newItemField.getText().trim();
            if (!item.isEmpty()) {
                manager.addItem(item);
                refreshChecklist();
                newItemField.setText("");
            }
        });

        removeBtn.addActionListener(e -> {
            int index = checklistView.getSelectedIndex();
            if (index != -1) {
                manager.removeItem(index);
                refreshChecklist();
            }
        });

        saveBtn.addActionListener(e -> {
            manager.saveToFile("checklist.txt");
            JOptionPane.showMessageDialog(this, "Checklist saved to checklist.txt");
        });

        buttonPanel.add(newItemField);
        buttonPanel.add(addBtn);
        buttonPanel.add(removeBtn);
        buttonPanel.add(saveBtn);

        add(inputPanel, BorderLayout.NORTH);
        add(generateBtn, BorderLayout.CENTER);
        add(checklistPanel, BorderLayout.WEST);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshChecklist() {
        listModel.clear();
        for (String item : manager.getChecklist()) {
            listModel.addElement(item);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PackWiseApp().setVisible(true));
    }
}