package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyVehiclesPage extends JFrame {
    public MyVehiclesPage() {
        setTitle("VRMS - My Vehicles");
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(UIUtils.label("My Vehicles", Font.BOLD, 28, UIColors.TEXT_DARK));
        panel.add(Box.createVerticalStrut(4));
        panel.add(UIUtils.label(
                "Vehicles listed by Arppith Andrews",
                Font.PLAIN,
                13,
                UIColors.TEXT_MUTED
        ));
        return panel;
    }

    private JScrollPane cards() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);
        grid.add(card(
                "CAR",
                "APPROVED",
                "Toyota Innova",
                "KL07EF2468",
                "2200.00",
                "AVAILABLE",
                false
        ));
        grid.add(card(
                "BIKE",
                "PENDING",
                "Yamaha FZ",
                "KL45XY4321",
                "800.00",
                "AVAILABLE",
                false
        ));
        grid.add(card(
                "CAR",
                "DELETED BY ADMIN",
                "Hyundai i20",
                "KL08ZX9001",
                "1600.00",
                "UNAVAILABLE",
                true
        ));

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
            String approval,
            String name,
            String reg,
            String rate,
            String availability,
            boolean deleted
    ) {
        JPanel card = new JPanel(new BorderLayout(0, 14));
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, deleted ? 250 : 225));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 18, 18)
        ));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(UIUtils.badge(type, UIColors.BG_LEFT, UIColors.PRIMARY), BorderLayout.WEST);

        Color bg = approval.equals("APPROVED")
                ? UIColors.SUCCESS_BG
                : approval.equals("PENDING") ? UIColors.WARNING_BG : UIColors.DANGER_BG;
        Color fg = approval.equals("APPROVED")
                ? UIColors.SUCCESS
                : approval.equals("PENDING") ? UIColors.WARNING : UIColors.DANGER;

        top.add(UIUtils.badge(approval, bg, fg), BorderLayout.EAST);
        card.add(top, BorderLayout.NORTH);

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));

        JLabel nameLabel = UIUtils.label(name, Font.BOLD, 20, UIColors.TEXT_DARK);
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel registrationLabel = UIUtils.label(
                "Registration  " + reg,
                Font.PLAIN,
                12,
                UIColors.TEXT_MUTED
        );
        registrationLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel priceLabel = UIUtils.label(
                "Rs. " + rate + " / day",
                Font.BOLD,
                17,
                deleted ? UIColors.TEXT_MUTED : UIColors.PRIMARY
        );
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel availabilityLabel = UIUtils.label(
                "Availability: " + availability.substring(0, 1) + availability.substring(1).toLowerCase(),
                Font.PLAIN,
                12,
                UIColors.TEXT_DARK
        );
        availabilityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        details.add(nameLabel);
        details.add(Box.createVerticalStrut(8));
        details.add(registrationLabel);
        details.add(Box.createVerticalStrut(18));
        details.add(priceLabel);
        details.add(Box.createVerticalStrut(9));
        details.add(availabilityLabel);

        if (deleted) {
            JLabel note = UIUtils.label(
                    "Removed from the catalog by an administrator.",
                    Font.PLAIN,
                    10,
                    UIColors.DANGER
            );
            note.setAlignmentX(Component.LEFT_ALIGNMENT);
            details.add(Box.createVerticalStrut(8));
            details.add(note);
        }

        card.add(details, BorderLayout.CENTER);
        return card;
    }

    private JPanel bottom() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);
        bar.add(UIUtils.secondaryButton("Refresh"));

        JButton list = UIUtils.primaryButton("List Another Vehicle");
        list.addActionListener(e -> UIUtils.previewAction(
                this,
                "List Another Vehicle",
                "opens the vehicle listing form"
        ));
        bar.add(list);

        JButton back = UIUtils.secondaryButton("Back to Catalog");
        back.addActionListener(e -> UIUtils.showPage(this, new CatalogPage()));
        bar.add(back);
        return bar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyVehiclesPage().setVisible(true));
    }
}
