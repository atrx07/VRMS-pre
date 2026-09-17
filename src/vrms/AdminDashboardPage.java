package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        root.add(header(), BorderLayout.NORTH);
        root.add(cards(), BorderLayout.CENTER);
        root.add(bottom(), BorderLayout.SOUTH);
    }

    private JPanel header() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(UIUtils.label("Vehicle Catalog", Font.BOLD, 28, UIColors.TEXT_DARK));
        text.add(Box.createVerticalStrut(4));
        text.add(UIUtils.label(
                "Admin view  |  Approved listings visible to customers",
                Font.PLAIN,
                13,
                UIColors.TEXT_MUTED
        ));

        header.add(text, BorderLayout.WEST);
        header.add(
                UIUtils.label("Admin: VRMS Admin", Font.BOLD, 12, UIColors.SECONDARY),
                BorderLayout.EAST
        );
        return header;
    }

    private JScrollPane cards() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);
        grid.add(card("CAR", "AVAILABLE", "Maruti Swift", "Alwin KJ", "KL08AB1234", "1500.00", true));
        grid.add(card("BIKE", "RENTED", "Honda Activa", "Amal Farhan", "KL45CD5678", "500.00", false));
        grid.add(card("CAR", "AVAILABLE", "Toyota Innova", "Arppith Andrews", "KL07EF2468", "2200.00", true));

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UIColors.BG_PAGE);
        holder.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(UIColors.BG_PAGE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private JPanel card(
            String type,
            String status,
            String name,
            String owner,
            String reg,
            String rate,
            boolean deletable
    ) {
        JPanel card = new JPanel(new BorderLayout(0, 12));
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, 255));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 16, 18)
        ));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(UIUtils.badge(type, UIColors.BG_LEFT, UIColors.PRIMARY), BorderLayout.WEST);
        top.add(
                UIUtils.badge(
                        status,
                        deletable ? UIColors.SUCCESS_BG : UIColors.WARNING_BG,
                        deletable ? UIColors.SUCCESS : UIColors.WARNING
                ),
                BorderLayout.EAST
        );
        card.add(top, BorderLayout.NORTH);

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel nameLabel = UIUtils.label(name, Font.BOLD, 20, UIColors.TEXT_DARK);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel ownerLabel = UIUtils.label("Listed by " + owner, Font.PLAIN, 12, UIColors.TEXT_MUTED);
        ownerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel registrationLabel = UIUtils.label(
                "Registration  " + reg,
                Font.PLAIN,
                12,
                UIColors.TEXT_MUTED
        );
        registrationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priceLabel = UIUtils.label("Rs. " + rate + " / day", Font.BOLD, 17, UIColors.PRIMARY);
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        details.add(nameLabel);
        details.add(Box.createVerticalStrut(7));
        details.add(ownerLabel);
        details.add(Box.createVerticalStrut(4));
        details.add(registrationLabel);
        details.add(Box.createVerticalStrut(14));
        details.add(priceLabel);
        card.add(details, BorderLayout.CENTER);

        JButton deleteButton = UIUtils.dangerButton(
                deletable ? "Delete Vehicle" : "Cannot Delete While Rented"
        );
        deleteButton.setEnabled(deletable);
        if (deletable) {
            deleteButton.addActionListener(e -> UIUtils.info(this, "Vehicle deleted"));
        }
        card.add(deleteButton, BorderLayout.SOUTH);
        return card;
    }

    private JPanel bottom() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);

        JButton refresh = UIUtils.secondaryButton("Refresh");
        refresh.addActionListener(e -> UIUtils.info(this, "Refreshed"));
        bar.add(refresh);

        JButton earnings = UIUtils.secondaryButton("Earnings");
        earnings.addActionListener(e -> UIUtils.info(this, "Earnings opened"));
        bar.add(earnings);

        JButton pending = UIUtils.secondaryButton("Pending Requests");
        pending.addActionListener(e -> UIUtils.info(this, "Requests opened"));

        JPanel pendingControl = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pendingControl.setOpaque(false);
        pendingControl.add(pending);
        pendingControl.add(UIUtils.badge("2", UIColors.DANGER, Color.WHITE));
        bar.add(pendingControl);

        JButton logout = UIUtils.secondaryButton("Logout");
        logout.addActionListener(e -> UIUtils.info(this, "Logged out"));
        bar.add(logout);
        return bar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboardPage().setVisible(true));
    }
}
