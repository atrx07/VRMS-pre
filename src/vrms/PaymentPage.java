package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PaymentPage extends JFrame {
    private static final String[] SAMPLE = {
            "1", "2", "Maruti Swift", "Car", "KL08AB1234", "1500.00", "AVAILABLE", "APPROVED"
    };

    private final String[] vehicle;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double rentalAmount;
    private final double fee;
    private final double total;

    public PaymentPage() {
        this(SAMPLE, LocalDate.now(), LocalDate.now().plusDays(1));
    }

    public PaymentPage(String[] vehicle, LocalDate startDate, LocalDate endDate) {
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;

        long days = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        rentalAmount = days * Double.parseDouble(vehicle[5]);
        fee = rentalAmount * 0.10;
        total = rentalAmount + fee;

        setTitle("VRMS - Payment");
        setSize(520, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel();
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(30, 42, 30, 42));
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        setContentPane(root);

        JLabel title = UIUtils.label("Payment", Font.BOLD, 26, UIColors.TEXT_DARK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel sub = UIUtils.label(
                "Review the rental amount before payment",
                Font.PLAIN,
                12,
                UIColors.TEXT_MUTED
        );
        sub.setAlignmentX(Component.LEFT_ALIGNMENT);

        root.add(title);
        root.add(Box.createVerticalStrut(4));
        root.add(sub);
        root.add(Box.createVerticalStrut(22));
        root.add(summary());
        root.add(Box.createVerticalGlue());

        JPanel actions = new JPanel(new GridLayout(1, 2, 10, 0));
        actions.setOpaque(false);
        actions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        actions.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton back = UIUtils.secondaryButton("Back");
        back.addActionListener(e -> UIUtils.info(this, "Rental opened"));

        JButton pay = UIUtils.primaryButton(String.format("PAY Rs. %.2f", total));
        pay.addActionListener(e -> UIUtils.info(this, "Payment successful"));

        actions.add(back);
        actions.add(pay);
        root.add(actions);
    }

    private JPanel summary() {
        JPanel card = new JPanel();
        card.setBackground(UIColors.CARD_BG);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(20, 20, 20, 20)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 340));

        JLabel name = UIUtils.label(vehicle[2], Font.BOLD, 20, UIColors.TEXT_DARK);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel owner = UIUtils.label("Listed by Alwin KJ", Font.PLAIN, 12, UIColors.TEXT_MUTED);
        owner.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dates = UIUtils.label(
                startDate + "  to  " + endDate,
                Font.PLAIN,
                12,
                UIColors.TEXT_MUTED
        );
        dates.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(name);
        card.add(Box.createVerticalStrut(5));
        card.add(owner);
        card.add(Box.createVerticalStrut(4));
        card.add(dates);
        card.add(Box.createVerticalStrut(24));
        card.add(money("Rental amount", rentalAmount, false));
        card.add(Box.createVerticalStrut(10));
        card.add(money("VRMS service fee (10%)", fee, false));
        card.add(Box.createVerticalStrut(14));

        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        card.add(separator);
        card.add(Box.createVerticalStrut(14));
        card.add(money("Total payable", total, true));
        card.add(Box.createVerticalStrut(18));

        JLabel payout = UIUtils.label(
                String.format("Vehicle owner receives: Rs. %.2f", rentalAmount),
                Font.PLAIN,
                11,
                UIColors.TEXT_MUTED
        );
        payout.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.add(payout);
        return card;
    }

    private JPanel money(String text, double amount, boolean strong) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);

        row.add(
                UIUtils.label(
                        text,
                        strong ? Font.BOLD : Font.PLAIN,
                        strong ? 14 : 13,
                        UIColors.TEXT_DARK
                ),
                BorderLayout.WEST
        );
        row.add(
                UIUtils.label(
                        String.format("Rs. %.2f", amount),
                        Font.BOLD,
                        strong ? 17 : 13,
                        strong ? UIColors.PRIMARY : UIColors.TEXT_DARK
                ),
                BorderLayout.EAST
        );
        return row;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaymentPage().setVisible(true));
    }
}
