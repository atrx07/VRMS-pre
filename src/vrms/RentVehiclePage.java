package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RentVehiclePage extends JFrame {

    public RentVehiclePage() {
        setTitle("VRMS - Rent Vehicle");
        setSize(520, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel();
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(28, 42, 28, 42));
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        setContentPane(root);

        JLabel title = new JLabel("Rent Vehicle");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(UIColors.TEXT_DARK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel vehicle = new JLabel("Maruti Swift");
        vehicle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        vehicle.setForeground(UIColors.PRIMARY);
        vehicle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel owner = small("Listed by Arppith");
        JLabel rate = new JLabel("Rs. 1500.00 / day");
        rate.setFont(new Font("Segoe UI", Font.BOLD, 16));
        rate.setForeground(UIColors.TEXT_DARK);
        rate.setAlignmentX(Component.LEFT_ALIGNMENT);

        root.add(title);
        root.add(Box.createVerticalStrut(18));
        root.add(vehicle);
        root.add(Box.createVerticalStrut(4));
        root.add(owner);
        root.add(Box.createVerticalStrut(4));
        root.add(rate);
        root.add(Box.createVerticalStrut(24));

        addField(root, "Start Date (YYYY-MM-DD)", "2026-09-12");
        addField(root, "End Date (YYYY-MM-DD)", "2026-09-14");

        JButton calculate = secondaryButton("Calculate Total");
        calculate.setAlignmentX(Component.LEFT_ALIGNMENT);
        root.add(calculate);
        root.add(Box.createVerticalStrut(18));

        JLabel total = new JLabel("Total: Rs. 3000.00");
        total.setFont(new Font("Segoe UI", Font.BOLD, 18));
        total.setForeground(UIColors.PRIMARY);
        total.setAlignmentX(Component.LEFT_ALIGNMENT);
        root.add(total);
        root.add(Box.createVerticalGlue());

        JPanel actions = new JPanel(new GridLayout(1, 2, 10, 0));
        actions.setOpaque(false);
        actions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        actions.setAlignmentX(Component.LEFT_ALIGNMENT);
        actions.add(secondaryButton("Back"));
        actions.add(primaryButton("Confirm Rental"));
        root.add(actions);
    }

    private JLabel small(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(UIColors.TEXT_MUTED);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private void addField(JPanel panel, String labelText, String value) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(UIColors.TEXT_DARK);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField field = new JTextField(value);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(15));
    }

    private JButton primaryButton(String text) {
        JButton button = new JButton(text);
        button.setUI(new BasicButtonUI());
        button.setBackground(UIColors.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorder(new EmptyBorder(9, 14, 9, 14));
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return button;
    }

    private JButton secondaryButton(String text) {
        JButton button = new JButton(text);
        button.setUI(new BasicButtonUI());
        button.setBackground(UIColors.BG_SECONDARY_BTN);
        button.setForeground(UIColors.TEXT_DARK);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER_DARK),
                new EmptyBorder(8, 14, 8, 14)
        ));
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RentVehiclePage().setVisible(true));
    }
}
