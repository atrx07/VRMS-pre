package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class CatalogPage extends JFrame {

    public CatalogPage() {
        setTitle("VRMS - Vehicle Catalog");
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 20));
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(24, 32, 24, 32));
        setContentPane(root);

        root.add(createHeader(), BorderLayout.NORTH);
        root.add(createCardArea(), BorderLayout.CENTER);
        root.add(createBottomBar(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Available Vehicles");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(UIColors.TEXT_DARK);

        JLabel subtitle = new JLabel("Welcome, customer  |  Pick a vehicle that works for you");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(UIColors.TEXT_MUTED);

        text.add(title);
        text.add(Box.createVerticalStrut(4));
        text.add(subtitle);

        JLabel brand = new JLabel("VRMS");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 20));
        brand.setForeground(UIColors.PRIMARY);

        header.add(text, BorderLayout.WEST);
        header.add(brand, BorderLayout.EAST);
        return header;
    }

    private JScrollPane createCardArea() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);

        grid.add(createVehicleCard("CAR", "AVAILABLE", "Maruti Swift", "KL08AB1234", "Arppith", "1500.00", false));
        grid.add(createVehicleCard("BIKE", "AVAILABLE", "Honda Activa", "KL45CD5678", "Shadow", "500.00", false));
        grid.add(createVehicleCard("CAR", "AVAILABLE", "Toyota Innova", "KL07EF2468", "Customer", "2200.00", true));

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UIColors.BG_PAGE);
        holder.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(UIColors.BG_PAGE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private JPanel createVehicleCard(String type, String status, String name,
                                     String registration, String owner, String rate, boolean ownListing) {
        JPanel card = new JPanel();
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, 245));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 18, 18)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        top.add(createBadge(type, UIColors.BG_LEFT, UIColors.PRIMARY), BorderLayout.WEST);
        top.add(createBadge(status, UIColors.SUCCESS_BG, UIColors.SUCCESS), BorderLayout.EAST);

        JLabel vehicleName = label(name, Font.BOLD, 20, UIColors.TEXT_DARK);
        JLabel ownerLabel = label("Listed by " + owner + (ownListing ? " (You)" : ""), Font.PLAIN, 12, UIColors.TEXT_MUTED);
        JLabel registrationLabel = label("Registration  " + registration, Font.PLAIN, 12, UIColors.TEXT_MUTED);
        JLabel price = label("Rs. " + rate + " / day", Font.BOLD, 17, UIColors.PRIMARY);

        JButton action = primaryButton(ownListing ? "Your Listing" : "Rent Vehicle");
        action.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        action.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(top);
        card.add(Box.createVerticalStrut(14));
        card.add(vehicleName);
        card.add(Box.createVerticalStrut(7));
        card.add(ownerLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(registrationLabel);
        card.add(Box.createVerticalStrut(16));
        card.add(price);
        card.add(Box.createVerticalGlue());
        card.add(action);
        return card;
    }

    private JPanel createBottomBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);
        bar.add(secondaryButton("Refresh"));
        bar.add(secondaryButton("My Rentals"));
        bar.add(secondaryButton("My Vehicles"));
        bar.add(primaryButton("List Vehicle"));
        bar.add(secondaryButton("Logout"));
        return bar;
    }

    private JLabel label(String text, int style, int size, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", style, size));
        label.setForeground(color);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel createBadge(String text, Color background, Color foreground) {
        JLabel badge = new JLabel(text);
        badge.setOpaque(true);
        badge.setBackground(background);
        badge.setForeground(foreground);
        badge.setFont(new Font("Segoe UI", Font.BOLD, 10));
        badge.setBorder(new EmptyBorder(5, 9, 5, 9));
        return badge;
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
        SwingUtilities.invokeLater(() -> new CatalogPage().setVisible(true));
    }
}
