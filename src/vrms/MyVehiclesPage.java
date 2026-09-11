package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class MyVehiclesPage extends JFrame {

    public MyVehiclesPage() {
        setTitle("VRMS - My Vehicles");
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 20));
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(24, 32, 24, 32));
        setContentPane(root);

        JLabel title = new JLabel("My Vehicles");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(UIColors.TEXT_DARK);
        root.add(title, BorderLayout.NORTH);

        root.add(createCards(), BorderLayout.CENTER);
        root.add(createBottomBar(), BorderLayout.SOUTH);
    }

    private JScrollPane createCards() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);
        grid.add(createCard("CAR", "APPROVED", "Maruti Swift", "KL08AB1234", "1500.00", "AVAILABLE"));
        grid.add(createCard("BIKE", "PENDING", "Yamaha FZ", "KL10XY5678", "700.00", "AVAILABLE"));
        grid.add(createCard("CAR", "REJECTED", "Honda City", "KL07MN2468", "1800.00", "AVAILABLE"));

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UIColors.BG_PAGE);
        holder.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(UIColors.BG_PAGE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private JPanel createCard(String type, String approval, String name,
                              String registration, String rate, String availability) {
        JPanel card = new JPanel();
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, 225));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 18, 18)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        top.add(badge(type, UIColors.BG_LEFT, UIColors.PRIMARY), BorderLayout.WEST);

        Color statusColor = approval.equals("APPROVED") ? UIColors.SUCCESS
                : approval.equals("PENDING") ? UIColors.WARNING : UIColors.DANGER;
        Color statusBg = approval.equals("APPROVED") ? UIColors.SUCCESS_BG
                : approval.equals("PENDING") ? UIColors.WARNING_BG : UIColors.DANGER_BG;
        top.add(badge(approval, statusBg, statusColor), BorderLayout.EAST);

        JLabel vehicle = text(name, Font.BOLD, 20, UIColors.TEXT_DARK);
        JLabel reg = text("Registration  " + registration, Font.PLAIN, 12, UIColors.TEXT_MUTED);
        JLabel price = text("Rs. " + rate + " / day", Font.BOLD, 17, UIColors.PRIMARY);
        JLabel state = text("Availability: " + availability, Font.PLAIN, 12, UIColors.TEXT_MUTED);

        card.add(top);
        card.add(Box.createVerticalStrut(14));
        card.add(vehicle);
        card.add(Box.createVerticalStrut(7));
        card.add(reg);
        card.add(Box.createVerticalStrut(16));
        card.add(price);
        card.add(Box.createVerticalStrut(7));
        card.add(state);
        return card;
    }

    private JPanel createBottomBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);
        bar.add(secondaryButton("Refresh"));
        bar.add(primaryButton("List Another Vehicle"));
        bar.add(secondaryButton("Back to Catalog"));
        return bar;
    }

    private JLabel text(String value, int style, int size, Color color) {
        JLabel label = new JLabel(value);
        label.setFont(new Font("Segoe UI", style, size));
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel badge(String value, Color background, Color foreground) {
        JLabel label = new JLabel(value);
        label.setOpaque(true);
        label.setBackground(background);
        label.setForeground(foreground);
        label.setFont(new Font("Segoe UI", Font.BOLD, 10));
        label.setBorder(new EmptyBorder(5, 9, 5, 9));
        return label;
    }

    private JButton primaryButton(String text) {
        JButton button = new JButton(text);
        button.setUI(new BasicButtonUI());
        button.setBackground(UIColors.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorder(new EmptyBorder(9, 15, 9, 15));
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
        SwingUtilities.invokeLater(() -> new MyVehiclesPage().setVisible(true));
    }
}
