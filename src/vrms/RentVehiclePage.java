package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class RentVehiclePage extends JFrame {
    private static final String[] SAMPLE = {
            "1", "2", "Maruti Swift", "Car", "KL08AB1234", "1500.00", "AVAILABLE", "APPROVED"
    };

    private final String[] vehicle;
    private final JTextField startField = new JTextField();
    private final JTextField endField = new JTextField();
    private final JLabel totalLabel = new JLabel("Rental amount: Rs. 0.00");

    public RentVehiclePage() {
        this(SAMPLE);
    }

    public RentVehiclePage(String[] vehicle) {
        this.vehicle = vehicle;

        setTitle("VRMS - Rent Vehicle");
        setSize(520, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel();
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(28, 42, 28, 42));
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        setContentPane(root);

        JLabel title = UIUtils.label("Rent Vehicle", Font.BOLD, 24, UIColors.TEXT_DARK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel name = UIUtils.label(vehicle[2], Font.BOLD, 20, UIColors.PRIMARY);
        name.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel owner = UIUtils.label("Listed by Alwin KJ", Font.PLAIN, 12, UIColors.TEXT_MUTED);
        owner.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel rate = UIUtils.label(
                "Rs. " + vehicle[5] + " / day",
                Font.BOLD,
                16,
                UIColors.TEXT_DARK
        );
        rate.setAlignmentX(Component.LEFT_ALIGNMENT);

        root.add(title);
        root.add(Box.createVerticalStrut(18));
        root.add(name);
        root.add(Box.createVerticalStrut(4));
        root.add(owner);
        root.add(Box.createVerticalStrut(4));
        root.add(rate);
        root.add(Box.createVerticalStrut(24));

        UIUtils.addField(root, "Start Date (YYYY-MM-DD)", startField);
        UIUtils.addField(root, "End Date (YYYY-MM-DD)", endField);

        JButton calculateButton = UIUtils.secondaryButton("Calculate Rental");
        calculateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        calculateButton.addActionListener(e -> calculate());
        root.add(calculateButton);
        root.add(Box.createVerticalStrut(15));

        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalLabel.setForeground(UIColors.PRIMARY);
        totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        root.add(totalLabel);

        JLabel fee = UIUtils.label(
                "A 10% VRMS service fee is added on the payment page.",
                Font.PLAIN,
                11,
                UIColors.TEXT_MUTED
        );
        fee.setAlignmentX(Component.LEFT_ALIGNMENT);
        root.add(Box.createVerticalStrut(5));
        root.add(fee);
        root.add(Box.createVerticalGlue());

        JPanel actions = new JPanel(new GridLayout(1, 2, 10, 0));
        actions.setOpaque(false);
        actions.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        actions.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton back = UIUtils.secondaryButton("Back");
        back.addActionListener(e -> UIUtils.info(this, "Catalog opened"));

        JButton pay = UIUtils.primaryButton("Continue to Payment");
        pay.addActionListener(e -> {
            if (validateDates() != null) {
                return;
            }
            UIUtils.info(this, "Payment opened");
        });

        actions.add(back);
        actions.add(pay);
        root.add(actions);
    }

    private LocalDate[] validateDates() {
        String startText = startField.getText().trim();
        String endText = endField.getText().trim();

        if (startText.isEmpty()) {
            UIUtils.error(this, "Enter start date");
            return null;
        }
        if (endText.isEmpty()) {
            UIUtils.error(this, "Enter end date");
            return null;
        }

        try {
            LocalDate start = LocalDate.parse(startText);
            LocalDate end = LocalDate.parse(endText);
            if (end.isBefore(start)) {
                UIUtils.error(this, "End date is before start date");
                return null;
            }
            return new LocalDate[]{start, end};
        } catch (DateTimeParseException ex) {
            UIUtils.error(this, "Enter valid dates");
            return null;
        }
    }

    private void calculate() {
        LocalDate[] dates = validateDates();
        if (dates == null) {
            return;
        }

        long days = ChronoUnit.DAYS.between(dates[0], dates[1]) + 1;
        double amount = days * Double.parseDouble(vehicle[5]);
        totalLabel.setText(String.format("Rental amount: Rs. %.2f", amount));
        UIUtils.info(this, "Rent calculated");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RentVehiclePage().setVisible(true));
    }
}
