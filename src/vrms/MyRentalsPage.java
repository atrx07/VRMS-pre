package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyRentalsPage extends JFrame {
    public MyRentalsPage() {
        setTitle("VRMS - My Rentals");
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 20));
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(24, 32, 24, 32));
        setContentPane(root);

        root.add(UIUtils.label("My Rentals", Font.BOLD, 28, UIColors.TEXT_DARK), BorderLayout.NORTH);
        root.add(cards(), BorderLayout.CENTER);
        root.add(bottom(), BorderLayout.SOUTH);
    }

    private JScrollPane cards() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);
        grid.add(card(
                "ACTIVE",
                "Maruti Swift",
                "Alwin KJ",
                "2026-09-16",
                "2026-09-18",
                "4500.00",
                "450.00",
                "4950.00",
                false
        ));
        grid.add(card(
                "RETURNED",
                "Honda Activa",
                "Amal Farhan",
                "2026-09-08",
                "2026-09-09",
                "1000.00",
                "100.00",
                "1100.00",
                false
        ));
        grid.add(card(
                "RETURNED",
                "Hyundai i20",
                "Dharshak T Jayan",
                "2026-08-24",
                "2026-08-26",
                "4800.00",
                "480.00",
                "5280.00",
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
            String status,
            String vehicle,
            String owner,
            String start,
            String end,
            String rental,
            String fee,
            String paid,
            boolean deleted
    ) {
        JPanel card = new JPanel();
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, deleted ? 315 : 290));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 18, 18)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        JPanel badges = new JPanel(new BorderLayout(8, 0));
        badges.setOpaque(false);
        badges.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        badges.setAlignmentX(Component.LEFT_ALIGNMENT);
        badges.add(
                UIUtils.badge(
                        status,
                        status.equals("ACTIVE") ? UIColors.SUCCESS_BG : UIColors.BG_SECONDARY_BTN,
                        status.equals("ACTIVE") ? UIColors.SUCCESS : UIColors.TEXT_MUTED
                ),
                BorderLayout.WEST
        );

        if (deleted) {
            badges.add(
                    UIUtils.badge("DELETED BY ADMIN", UIColors.DANGER_BG, UIColors.DANGER),
                    BorderLayout.EAST
            );
        }

        card.add(badges);
        card.add(Box.createVerticalStrut(14));

        JLabel name = UIUtils.label(vehicle, Font.BOLD, 20, UIColors.TEXT_DARK);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel ownerLabel = UIUtils.label("Owner: " + owner, Font.PLAIN, 12, UIColors.TEXT_MUTED);
        ownerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dates = UIUtils.label(start + "  to  " + end, Font.PLAIN, 12, UIColors.TEXT_MUTED);
        dates.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel rentalLabel = UIUtils.label(
                "Rental: Rs. " + rental,
                Font.PLAIN,
                12,
                UIColors.TEXT_MUTED
        );
        rentalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel feeLabel = UIUtils.label("VRMS fee: Rs. " + fee, Font.PLAIN, 12, UIColors.TEXT_MUTED);
        feeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel total = UIUtils.label("Paid: Rs. " + paid, Font.BOLD, 16, UIColors.PRIMARY);
        total.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(name);
        card.add(Box.createVerticalStrut(7));
        card.add(ownerLabel);
        card.add(Box.createVerticalStrut(5));
        card.add(dates);
        card.add(Box.createVerticalStrut(12));
        card.add(rentalLabel);
        card.add(Box.createVerticalStrut(3));
        card.add(feeLabel);
        card.add(Box.createVerticalStrut(8));
        card.add(total);

        if (deleted) {
            JLabel note = UIUtils.label(
                    "This listing was removed from the catalog by an administrator.",
                    Font.PLAIN,
                    10,
                    UIColors.DANGER
            );
            note.setAlignmentX(Component.LEFT_ALIGNMENT);
            card.add(Box.createVerticalStrut(8));
            card.add(note);
        }

        card.add(Box.createVerticalGlue());

        if (status.equals("ACTIVE")) {
            JButton returnButton = UIUtils.primaryButton("Return Vehicle");
            returnButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
            returnButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            returnButton.addActionListener(e -> JOptionPane.showMessageDialog(
                    this,
                    "Vehicle returned in the UI preview.",
                    "VRMS UI Preview",
                    JOptionPane.INFORMATION_MESSAGE
            ));
            card.add(Box.createVerticalStrut(14));
            card.add(returnButton);
        }

        return card;
    }

    private JPanel bottom() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);
        bar.add(UIUtils.secondaryButton("Refresh"));

        JButton back = UIUtils.primaryButton("Back to Catalog");
        back.addActionListener(e -> UIUtils.showPage(this, new CatalogPage()));
        bar.add(back);
        return bar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MyRentalsPage().setVisible(true));
    }
}
