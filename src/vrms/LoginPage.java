package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage extends JFrame {
    private final JTextField emailField = new JTextField("customer@vrms.com");
    private final JPasswordField passwordField = new JPasswordField("password");

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

        JLabel welcome = UIUtils.label("Welcome to", Font.PLAIN, 18, UIColors.TEXT_DARK);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel brand = UIUtils.label("VRMS", Font.BOLD, 28, Color.BLACK);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel subtitle = UIUtils.label("Vehicle Rental Management System", Font.PLAIN, 11, UIColors.TEXT_MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(welcome);
        panel.add(Box.createVerticalStrut(2));
        panel.add(brand);
        panel.add(Box.createVerticalStrut(7));
        panel.add(subtitle);
        panel.add(Box.createVerticalStrut(34));

        JLabel vehicleText = UIUtils.label("CAR   |   BIKE   |   VAN", Font.BOLD, 16, UIColors.PRIMARY);
        vehicleText.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel rentalText = UIUtils.label("VEHICLE RENTAL", Font.BOLD, 12, UIColors.SECONDARY);
        rentalText.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(vehicleText);
        panel.add(Box.createVerticalStrut(7));
        panel.add(rentalText);
        panel.add(Box.createVerticalStrut(35));

        addFeature(panel, "Browse available vehicles");
        addFeature(panel, "Rent vehicles easily");
        addFeature(panel, "List your vehicle");
        addFeature(panel, "Manage your rentals");
        panel.add(Box.createVerticalGlue());

        JLabel footer = UIUtils.label("VRMS", Font.BOLD, 11, UIColors.SECONDARY);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(footer);
        return panel;
    }

    private void addFeature(JPanel panel, String text) {
        JPanel row = new JPanel(new BorderLayout(8, 0));
        row.setOpaque(false);
        row.setMaximumSize(new Dimension(254, 26));
        row.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel bullet = UIUtils.label("\u2022", Font.BOLD, 14, UIColors.PRIMARY);
        bullet.setPreferredSize(new Dimension(14, 26));
        row.add(bullet, BorderLayout.WEST);
        row.add(UIUtils.label(text, Font.PLAIN, 13, UIColors.TEXT_DARK), BorderLayout.CENTER);
        panel.add(row);
        panel.add(Box.createVerticalStrut(9));
    }

    private JPanel createLoginArea() {
        JPanel area = new JPanel(new BorderLayout());
        area.setBackground(UIColors.BG_PAGE);
        area.setBorder(new EmptyBorder(40, 45, 18, 32));

        JPanel center = new JPanel(new GridBagLayout());
        center.setOpaque(false);
        center.add(createLoginCard());
        area.add(center, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        bottom.setOpaque(false);
        JLabel adminLink = new JLabel("<html><u>Admin? Sign in here</u></html>");
        adminLink.setForeground(UIColors.LINK);
        adminLink.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        adminLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adminLink.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                UIUtils.showPage(LoginPage.this, new AdminLoginPage());
            }
        });
        bottom.add(adminLink);
        area.add(bottom, BorderLayout.SOUTH);
        return area;
    }

    private JPanel createLoginCard() {
        JPanel card = new JPanel(new GridBagLayout());
        card.setPreferredSize(new Dimension(360, 420));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.BORDER), new EmptyBorder(25, 30, 25, 30)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        JLabel type = UIUtils.label("CUSTOMER LOGIN", Font.BOLD, 11, Color.BLACK);
        type.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(type, gbc);
        gbc.gridy++; gbc.insets = new Insets(8, 0, 0, 0);
        JLabel brand = UIUtils.label("VRMS", Font.BOLD, 24, UIColors.PRIMARY);
        brand.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(brand, gbc);
        gbc.gridy++; gbc.insets = new Insets(4, 0, 25, 0);
        JLabel heading = UIUtils.label("Sign In to Your Account", Font.PLAIN, 16, Color.BLACK);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        card.add(heading, gbc);

        gbc.gridy++; gbc.insets = new Insets(0, 0, 5, 0);
        card.add(UIUtils.label("Email", Font.BOLD, 12, UIColors.TEXT_DARK), gbc);
        gbc.gridy++; gbc.insets = new Insets(0, 0, 15, 0);
        emailField.setPreferredSize(new Dimension(280, 38));
        card.add(emailField, gbc);
        gbc.gridy++; gbc.insets = new Insets(0, 0, 5, 0);
        card.add(UIUtils.label("Password", Font.BOLD, 12, UIColors.TEXT_DARK), gbc);
        gbc.gridy++; gbc.insets = new Insets(0, 0, 25, 0);
        passwordField.setPreferredSize(new Dimension(280, 38));
        passwordField.setEchoChar('\u2022');
        card.add(passwordField, gbc);

        gbc.gridy++; gbc.insets = new Insets(0, 0, 20, 0);
        JButton login = UIUtils.primaryButton("LOG IN");
        login.setPreferredSize(new Dimension(280, 42));
        login.addActionListener(e -> UIUtils.showPage(this, new CatalogPage()));
        card.add(login, gbc);

        gbc.gridy++; gbc.insets = new Insets(0, 0, 0, 0);
        JButton register = UIUtils.linkButton("Don't have an account? Sign Up");
        register.addActionListener(e -> UIUtils.showPage(this, new RegisterPage()));
        card.add(register, gbc);
        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
