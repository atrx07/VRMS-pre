package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminEarningsPage extends JFrame {
    public AdminEarningsPage() {
        setTitle("VRMS - Earnings");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 18));
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(24, 30, 24, 30));
        setContentPane(root);

        root.add(header(), BorderLayout.NORTH);
        root.add(center(), BorderLayout.CENTER);
        root.add(bottom(), BorderLayout.SOUTH);
    }

    private JPanel header() {
        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(UIUtils.label("VRMS Earnings", Font.BOLD, 28, UIColors.TEXT_DARK));
        header.add(Box.createVerticalStrut(4));
        header.add(UIUtils.label(
                "10% service fee collected from completed payments",
                Font.PLAIN,
                13,
                UIColors.TEXT_MUTED
        ));
        return header;
    }

    private JPanel center() {
        JPanel center = new JPanel(new BorderLayout(0, 18));
        center.setOpaque(false);

        JPanel summary = new JPanel(new GridLayout(1, 3, 14, 0));
        summary.setOpaque(false);
        summary.add(summaryCard("VRMS earnings", "Rs. 1030.00", UIColors.SUCCESS));
        summary.add(summaryCard("Owner payouts", "Rs. 10300.00", UIColors.PRIMARY));
        summary.add(summaryCard("Customer payments", "Rs. 11330.00", UIColors.TEXT_DARK));
        center.add(summary, BorderLayout.NORTH);

        JPanel grid = new JPanel(new GridLayout(0, 2, 14, 14));
        grid.setOpaque(false);
        grid.add(transaction(
                "Rental #1  |  Maruti Swift",
                "Arppith Andrews",
                "2026-09-16 to 2026-09-18",
                "Rs. 4950.00",
                "Rs. 450.00"
        ));
        grid.add(transaction(
                "Rental #2  |  Honda Activa",
                "Dharshak T Jayan",
                "2026-09-08 to 2026-09-09",
                "Rs. 1100.00",
                "Rs. 100.00"
        ));
        grid.add(transaction(
                "Rental #3  |  Toyota Innova",
                "Amal Farhan",
                "2026-08-28 to 2026-08-29",
                "Rs. 4840.00",
                "Rs. 440.00"
        ));

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UIColors.BG_PAGE);
        holder.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(UIColors.BG_PAGE);
        center.add(scroll, BorderLayout.CENTER);
        return center;
    }

    private JPanel summaryCard(String title, String value, Color color) {
        JPanel card = new JPanel();
        card.setBackground(UIColors.CARD_BG);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(16, 16, 16, 16)
        ));
        card.add(UIUtils.label(title, Font.PLAIN, 12, UIColors.TEXT_MUTED));
        card.add(Box.createVerticalStrut(6));
        card.add(UIUtils.label(value, Font.BOLD, 20, color));
        return card;
    }

    private JPanel transaction(String title, String customer, String dates, String paid, String earned) {
        JPanel card = new JPanel();
        card.setBackground(UIColors.CARD_BG);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel titleLabel = UIUtils.label(title, Font.BOLD, 15, UIColors.TEXT_DARK);
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel customerLabel = UIUtils.label(
                "Customer: " + customer,
                Font.PLAIN,
                11,
                UIColors.TEXT_MUTED
        );
        customerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel datesLabel = UIUtils.label(dates, Font.PLAIN, 11, UIColors.TEXT_MUTED);
        datesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel paidLabel = UIUtils.label("Paid: " + paid, Font.PLAIN, 12, UIColors.TEXT_DARK);
        paidLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel earnedLabel = UIUtils.label("VRMS earned: " + earned, Font.BOLD, 14, UIColors.SUCCESS);
        earnedLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titleLabel);
        card.add(Box.createVerticalStrut(7));
        card.add(customerLabel);
        card.add(Box.createVerticalStrut(3));
        card.add(datesLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(paidLabel);
        card.add(Box.createVerticalStrut(4));
        card.add(earnedLabel);
        return card;
    }

    private JPanel bottom() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);

        JButton refresh = UIUtils.secondaryButton("Refresh");
        refresh.addActionListener(e -> UIUtils.info(this, "Refreshed"));
        bar.add(refresh);

        JButton back = UIUtils.primaryButton("Back to Admin Catalog");
        back.addActionListener(e -> UIUtils.info(this, "Admin catalog opened"));
        bar.add(back);
        return bar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminEarningsPage().setVisible(true));
    }
}
