package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminApprovalPage extends JFrame {
    public AdminApprovalPage() {
        setTitle("VRMS - Pending Approvals");
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
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel text = new JPanel();
        text.setOpaque(false);
        text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
        text.add(UIUtils.label("Pending Vehicle Approvals", Font.BOLD, 28, UIColors.TEXT_DARK));
        text.add(Box.createVerticalStrut(4));
        text.add(UIUtils.label(
                "Review listings before they enter the public catalog",
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
        grid.add(card("CAR", "Honda City", "Dharshak T Jayan", "KL08AA1234", "1800.00"));
        grid.add(card("BIKE", "Yamaha FZ", "Arppith Andrews", "KL45XY4321", "800.00"));

        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UIColors.BG_PAGE);
        holder.add(grid, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(UIColors.BG_PAGE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private JPanel card(String type, String name, String owner, String reg, String rate) {
        JPanel card = new JPanel(new BorderLayout(0, 14));
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, 265));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 16, 18)
        ));

        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(UIUtils.badge(type, UIColors.BG_LEFT, UIColors.PRIMARY), BorderLayout.WEST);
        top.add(UIUtils.badge("PENDING", UIColors.WARNING_BG, UIColors.WARNING), BorderLayout.EAST);
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
        details.add(Box.createVerticalStrut(16));
        details.add(priceLabel);
        card.add(details, BorderLayout.CENTER);

        JPanel actions = new JPanel(new GridLayout(1, 2, 8, 0));
        actions.setOpaque(false);

        JButton reject = UIUtils.dangerButton("Reject");
        reject.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Vehicle rejected in the UI preview.",
                "VRMS",
                JOptionPane.INFORMATION_MESSAGE
        ));

        JButton approve = UIUtils.primaryButton("Approve");
        approve.addActionListener(e -> JOptionPane.showMessageDialog(
                this,
                "Vehicle approved in the UI preview.",
                "VRMS",
                JOptionPane.INFORMATION_MESSAGE
        ));

        actions.add(reject);
        actions.add(approve);
        card.add(actions, BorderLayout.SOUTH);
        return card;
    }

    private JPanel bottom() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        bar.setOpaque(false);
        bar.add(UIUtils.secondaryButton("Refresh"));

        JButton back = UIUtils.primaryButton("Back to Admin Catalog");
        back.addActionListener(e -> UIUtils.showPage(this, new AdminDashboardPage()));
        bar.add(back);
        return bar;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminApprovalPage().setVisible(true));
    }
}
