package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class AdminLoginPage extends JFrame {

    public AdminLoginPage() {
        setTitle("VRMS - Admin Login");
        setSize(400, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel();
        root.setBackground(UIColors.BG_PAGE);
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(25, 45, 20, 45));
        setContentPane(root);

        JLabel title = new JLabel("ADMIN LOGIN");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel brand = new JLabel("VRMS");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 16));
        brand.setForeground(UIColors.PRIMARY);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        root.add(title);
        root.add(Box.createVerticalStrut(4));
        root.add(brand);
        root.add(Box.createVerticalStrut(20));

        addField(root, "Admin Email", emailField);
        addField(root, "Password", passwordField);

        JButton loginButton = createPrimaryButton("LOGIN AS ADMIN");
        loginButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton backButton = new JButton("Back to Customer Login");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(UIColors.LINK);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        root.add(Box.createVerticalStrut(5));
        root.add(loginButton);
        root.add(Box.createVerticalStrut(15));
        root.add(backButton);
    }

    private void addField(JPanel panel, String labelText, JComponent field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(UIColors.TEXT_DARK);

        JPanel labelWrapper = new JPanel(new BorderLayout());
        labelWrapper.setOpaque(false);
        labelWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
        labelWrapper.add(label, BorderLayout.WEST);

        JPanel fieldWrapper = new JPanel(new BorderLayout());
        fieldWrapper.setOpaque(false);
        fieldWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 34));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldWrapper.add(field, BorderLayout.CENTER);

        panel.add(labelWrapper);
        panel.add(Box.createVerticalStrut(2));
        panel.add(fieldWrapper);
        panel.add(Box.createVerticalStrut(12));
    }

    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setUI(new BasicButtonUI());
        button.setBackground(UIColors.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminLoginPage().setVisible(true));
    }
}
