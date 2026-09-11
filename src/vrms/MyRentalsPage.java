package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class MyRentalsPage extends JFrame {

    public MyRentalsPage() {
        setTitle("VRMS - My Rentals");
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 20));
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(24, 32, 24, 32));
        setContentPane(root);

        JLabel title = new JLabel("My Rentals");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(UIColors.TEXT_DARK);
        root.add(title, BorderLayout.NORTH);

        root.add(createCards(), BorderLayout.CENTER);
        root.add(createBottomBar(), BorderLayout.SOUTH);
    }

    private JScrollPane createCards() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);
        grid.add(createCard("ACTIVE", "Maruti Swift", "Arppith", "2026-09-12", "2026-09-14", "3000.00", true));
        grid.add(createCard("RETURNED", "Honda Activa", "Shadow", "2026-09-01", "2026-09-02", "500.00", false));

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UIColors.BG_PAGE);
        holder.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(UIColors.BG_PAGE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private JPanel createCard(String statusText, String vehicle, String owner,
                              String start, String end, String total, boolean active) {
        JPanel card = new JPanel();
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, 265));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 18, 18)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JLabel status = badge(statusText,
                active ? UIColors.SUCCESS_BG : UIColors.BG_SECONDARY_BTN,
                active ? UIColors.SUCCESS : UIColors.TEXT_MUTED);
        status.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel name = text(vehicle, Font.BOLD, 20, UIColors.TEXT_DARK);
        JLabel ownerLabel = text("Owner: " + owner, Font.PLAIN, 12, UIColors.TEXT_MUTED);
        JLabel dates = text(start + "  to  " + end, Font.PLAIN, 12, UIColors.TEXT_MUTED);
        JLabel price = text("Total: Rs. " + total, Font.BOLD, 16, UIColors.PRIMARY);

        card.add(status);
        card.add(Box.createVerticalStrut(14));
        card.add(name);
        card.add(Box.createVerticalStrut(7));
        card.add(ownerLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(dates);
        card.add(Box.createVerticalStrut(14));
        card.add(price);
        card.add(Box.createVerticalGlue());

        if (active) {
            JButton returnButton = primaryButton("Return Vehicle");
            returnButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            returnButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.add(Box.createVerticalStrut(14));
            card.add(returnButton);
        }
        return card;
    }

    private JPanel createBottomBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);
        bar.add(secondaryButton("Refresh"));
        bar.add(primaryButton("Back to Catalog"));
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
        SwingUtilities.invokeLater(() -> new MyRentalsPage().setVisible(true));
    }
}
