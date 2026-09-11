package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class AdminDashboardPage extends JFrame {

    public AdminDashboardPage() {
        setTitle("VRMS - Admin Vehicle Catalog");
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 20));
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(24, 32, 24, 32));
        setContentPane(root);

        root.add(createHeader(), BorderLayout.NORTH);
        root.add(createCards(), BorderLayout.CENTER);
        root.add(createBottomBar(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Vehicle Catalog");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(UIColors.TEXT_DARK);

        JLabel subtitle = new JLabel("Admin view  |  Manage approved listings");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(UIColors.TEXT_MUTED);

        left.add(title);
        left.add(Box.createVerticalStrut(4));
        left.add(subtitle);

        JLabel brand = new JLabel("VRMS ADMIN");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 18));
        brand.setForeground(UIColors.PRIMARY);

        header.add(left, BorderLayout.WEST);
        header.add(brand, BorderLayout.EAST);
        return header;
    }

    private JScrollPane createCards() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);
        grid.add(createCard("CAR", "AVAILABLE", "Maruti Swift", "KL08AB1234", "Arppith", "1500.00"));
        grid.add(createCard("BIKE", "RENTED", "Honda Activa", "KL45CD5678", "Shadow", "500.00"));
        grid.add(createCard("CAR", "AVAILABLE", "Toyota Innova", "KL07EF2468", "Customer", "2200.00"));

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UIColors.BG_PAGE);
        holder.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(UIColors.BG_PAGE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private JPanel createCard(String type, String state, String name,
                              String registration, String owner, String rate) {
        JPanel card = new JPanel();
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, 255));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 18, 18)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        top.add(badge(type, UIColors.BG_LEFT, UIColors.PRIMARY), BorderLayout.WEST);
        top.add(badge(state,
                state.equals("AVAILABLE") ? UIColors.SUCCESS_BG : UIColors.WARNING_BG,
                state.equals("AVAILABLE") ? UIColors.SUCCESS : UIColors.WARNING), BorderLayout.EAST);

        card.add(top);
        card.add(Box.createVerticalStrut(14));
        card.add(text(name, Font.BOLD, 20, UIColors.TEXT_DARK));
        card.add(Box.createVerticalStrut(7));
        card.add(text("Listed by " + owner, Font.PLAIN, 12, UIColors.TEXT_MUTED));
        card.add(Box.createVerticalStrut(4));
        card.add(text("Registration  " + registration, Font.PLAIN, 12, UIColors.TEXT_MUTED));
        card.add(Box.createVerticalStrut(16));
        card.add(text("Rs. " + rate + " / day", Font.BOLD, 17, UIColors.PRIMARY));
        card.add(Box.createVerticalGlue());

        JButton delete = dangerButton("Delete Vehicle");
        delete.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        delete.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(delete);
        return card;
    }

    private JPanel createBottomBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);
        bar.add(secondaryButton("Refresh"));

        JPanel pending = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        pending.setOpaque(false);
        pending.add(primaryButton("Pending Requests"));
        pending.add(badge("2", UIColors.DANGER_BG, UIColors.DANGER));
        bar.add(pending);

        bar.add(secondaryButton("Logout"));
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

    private JButton dangerButton(String text) {
        JButton button = new JButton(text);
        button.setUI(new BasicButtonUI());
        button.setBackground(UIColors.DANGER_BG);
        button.setForeground(UIColors.DANGER);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(235, 190, 190)),
                new EmptyBorder(8, 14, 8, 14)
        ));
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboardPage().setVisible(true));
    }
}
