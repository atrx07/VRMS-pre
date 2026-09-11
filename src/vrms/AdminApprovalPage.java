package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class AdminApprovalPage extends JFrame {

    public AdminApprovalPage() {
        setTitle("VRMS - Pending Vehicle Approvals");
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

        JLabel title = new JLabel("Pending Vehicle Approvals");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(UIColors.TEXT_DARK);

        JLabel subtitle = new JLabel("Review customer listings before they enter the public catalog");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(UIColors.TEXT_MUTED);

        left.add(title);
        left.add(Box.createVerticalStrut(4));
        left.add(subtitle);

        JLabel admin = new JLabel("Admin Preview");
        admin.setFont(new Font("Segoe UI", Font.BOLD, 12));
        admin.setForeground(UIColors.SECONDARY);

        header.add(left, BorderLayout.WEST);
        header.add(admin, BorderLayout.EAST);
        return header;
    }

    private JScrollPane createCards() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);
        grid.add(createCard("CAR", "Maruti Baleno", "KL08PQ1122", "Arppith", "1600.00"));
        grid.add(createCard("BIKE", "Royal Enfield Classic", "KL45RT7788", "Shadow", "900.00"));

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UIColors.BG_PAGE);
        holder.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(UIColors.BG_PAGE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private JPanel createCard(String type, String name, String registration, String owner, String rate) {
        JPanel card = new JPanel(new BorderLayout(0, 14));
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, 265));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 16, 18)
        ));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(badge(type, UIColors.BG_LEFT, UIColors.PRIMARY), BorderLayout.WEST);
        top.add(badge("PENDING", UIColors.WARNING_BG, UIColors.WARNING), BorderLayout.EAST);
        card.add(top, BorderLayout.NORTH);

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        details.add(text(name, Font.BOLD, 20, UIColors.TEXT_DARK));
        details.add(Box.createVerticalStrut(7));
        details.add(text("Listed by  " + owner, Font.PLAIN, 12, UIColors.TEXT_MUTED));
        details.add(Box.createVerticalStrut(4));
        details.add(text("Registration  " + registration, Font.PLAIN, 12, UIColors.TEXT_MUTED));
        details.add(Box.createVerticalStrut(16));
        details.add(text("Rs. " + rate + " / day", Font.BOLD, 17, UIColors.PRIMARY));
        card.add(details, BorderLayout.CENTER);

        JPanel actions = new JPanel(new GridLayout(1, 2, 8, 0));
        actions.setOpaque(false);
        actions.add(dangerButton("Reject"));
        actions.add(primaryButton("Approve"));
        card.add(actions, BorderLayout.SOUTH);
        return card;
    }

    private JPanel createBottomBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);
        bar.add(secondaryButton("Refresh"));
        bar.add(primaryButton("Back to Admin Catalog"));
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
        SwingUtilities.invokeLater(() -> new AdminApprovalPage().setVisible(true));
    }
}
