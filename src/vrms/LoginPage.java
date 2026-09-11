package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class LoginPage extends JFrame {

    public LoginPage() {
        setTitle("VRMS - Vehicle Rental Management System");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout());
        root.add(createWelcomePanel(), BorderLayout.WEST);
        root.add(createLoginArea(), BorderLayout.CENTER);
        setContentPane(root);
    }

    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(330, 550));
        panel.setBackground(UIColors.BG_LEFT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(42, 38, 30, 38));

        addCentered(panel, "Welcome to", Font.PLAIN, 26, UIColors.TEXT_DARK);
        addCentered(panel, "VRMS", Font.BOLD, 28, Color.BLACK);
        panel.add(Box.createVerticalStrut(7));
        addCentered(panel, "Vehicle Rental Management System", Font.PLAIN, 11, UIColors.TEXT_MUTED);
        panel.add(Box.createVerticalStrut(34));
        addCentered(panel, "CAR   |   BIKE   |   VAN", Font.BOLD, 19, UIColors.PRIMARY);
        panel.add(Box.createVerticalStrut(7));
        addCentered(panel, "VEHICLE RENTAL", Font.BOLD, 14, UIColors.SECONDARY);
        panel.add(Box.createVerticalStrut(35));

        addFeature(panel, "Browse available vehicles");
        addFeature(panel, "Rent vehicles easily");
        addFeature(panel, "List your vehicle");
        addFeature(panel, "Manage your rentals");

        panel.add(Box.createVerticalGlue());
        addCentered(panel, "VRMS", Font.BOLD, 12, UIColors.SECONDARY);
        return panel;
    }

    private JPanel createLoginArea() {
        JPanel area = new JPanel(new BorderLayout());
        area.setBackground(UIColors.BG_PAGE);
        area.setBorder(new EmptyBorder(40, 45, 18, 32));

        JPanel holder = new JPanel(new GridBagLayout());
        holder.setOpaque(false);
        holder.add(createLoginCard());
        area.add(holder, BorderLayout.CENTER);

        JLabel admin = new JLabel("<html><u>Admin? Sign in here</u></html>");
        admin.setForeground(UIColors.LINK);
        admin.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        bottom.setOpaque(false);
        bottom.add(admin);
        area.add(bottom, BorderLayout.SOUTH);
        return area;
    }

    private JPanel createLoginCard() {
        JPanel card = new JPanel();
        card.setPreferredSize(new Dimension(360, 420));
        card.setBackground(UIColors.CARD_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(24, 26, 24, 26)
        ));
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        addCentered(card, "CUSTOMER LOGIN", Font.BOLD, 13, Color.BLACK);
        card.add(Box.createVerticalStrut(14));
        addCentered(card, "VRMS", Font.BOLD, 18, UIColors.PRIMARY);
        card.add(Box.createVerticalStrut(10));
        addCentered(card, "Sign In to Your Account", Font.PLAIN, 18, Color.BLACK);
        card.add(Box.createVerticalStrut(20));

        JTextField email = new JTextField();
        JPasswordField password = new JPasswordField();
        addField(card, "Email", email);
        addField(card, "Password", password);

        JButton login = primaryButton("LOG IN");
        login.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(2));
        card.add(login);
        card.add(Box.createVerticalStrut(18));

        JLabel signup = new JLabel("<html><u>Don't have an account? Sign Up</u></html>");
        signup.setForeground(UIColors.LINK);
        signup.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        signup.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(signup);
        return card;
    }

    private void addField(JPanel panel, String labelText, JComponent field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(UIColors.TEXT_DARK);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
        panel.add(field);
        panel.add(Box.createVerticalStrut(14));
    }

    private void addCentered(JPanel panel, String text, int style, int size, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", style, size));
        label.setForeground(color);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
    }

    private void addFeature(JPanel panel, String text) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(254, 26));
        row.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bullet = new JLabel("\u2022");
        bullet.setFont(new Font("Segoe UI", Font.BOLD, 16));
        bullet.setForeground(UIColors.PRIMARY);
        bullet.setPreferredSize(new Dimension(14, 26));

        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(UIColors.TEXT_DARK);
        row.add(bullet, BorderLayout.WEST);
        row.add(label, BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(9));
    }

    private JButton primaryButton(String text) {
        JButton button = new JButton(text);
        button.setUI(new BasicButtonUI());
        button.setBackground(UIColors.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
