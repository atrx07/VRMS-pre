package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class RegisterPage extends JFrame {

    public RegisterPage() {
        setTitle("VRMS - Customer Registration");
        setSize(460, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                backToLogin();
            }
        });
        setResizable(false);

        JPanel root = new JPanel();
        root.setBackground(UIColors.BG_PAGE);
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(28, 45, 22, 45));
        setContentPane(root);

        JLabel title = new JLabel("Create Customer Account");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Register to rent or list vehicles on VRMS");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(UIColors.TEXT_MUTED);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        root.add(title);
        root.add(Box.createVerticalStrut(6));
        root.add(subtitle);
        root.add(Box.createVerticalStrut(28));

        addField(root, "Name", nameField);
        addField(root, "Email", emailField);
        addField(root, "Phone", phoneField);
        addField(root, "Password", passwordField);

        JButton registerButton = createPrimaryButton("REGISTER");
        registerButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all registration fields.",
                        "Missing Details",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Registration UI complete. Account saving will be added in a later phase.",
                    "VRMS",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        JButton backButton = new JButton("Back to Login");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setForeground(UIColors.LINK);
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> backToLogin());

        root.add(Box.createVerticalStrut(2));
        root.add(registerButton);
        root.add(Box.createVerticalStrut(14));
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
        fieldWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        fieldWrapper.add(field, BorderLayout.CENTER);

        panel.add(labelWrapper);
        panel.add(Box.createVerticalStrut(3));
        panel.add(fieldWrapper);
        panel.add(Box.createVerticalStrut(13));
    }

    private JButton createPrimaryButton(String text) {
        JButton button = new JButton(text);
        button.setUI(new BasicButtonUI());
        button.setBackground(UIColors.PRIMARY);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        return button;
    }

    private void backToLogin() {
        new LoginPage().setVisible(true);
        dispose();
    }
}
