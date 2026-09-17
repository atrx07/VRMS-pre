package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminLoginPage extends JFrame {
    private static final int CONTROL_WIDTH = 310;

    public AdminLoginPage() {
        setTitle("VRMS - Admin Login");
        setSize(400, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel();
        root.setBackground(UIColors.BG_PAGE);
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(25, 45, 20, 45));
        setContentPane(root);

        JLabel title = UIUtils.label("ADMIN LOGIN", Font.BOLD, 22, UIColors.TEXT_DARK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel brand = UIUtils.label("VRMS", Font.BOLD, 16, UIColors.PRIMARY);
        brand.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField email = new JTextField();
        JPasswordField password = new JPasswordField();

        root.add(title);
        root.add(Box.createVerticalStrut(4));
        root.add(brand);
        root.add(Box.createVerticalStrut(20));

        addAlignedField(root, "Admin Email", email);
        addAlignedField(root, "Password", password);

        JButton login = UIUtils.primaryButton("LOGIN AS ADMIN");
        login.setPreferredSize(new Dimension(CONTROL_WIDTH, 36));
        login.setMaximumSize(new Dimension(CONTROL_WIDTH, 36));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.addActionListener(e -> {
            String emailText = email.getText().trim();
            String passwordText = new String(password.getPassword()).trim();

            if (emailText.isEmpty() && passwordText.isEmpty()) {
                UIUtils.error(this, "Enter email and password");
            } else if (emailText.isEmpty()) {
                UIUtils.error(this, "Enter email");
            } else if (passwordText.isEmpty()) {
                UIUtils.error(this, "Enter password");
            } else {
                UIUtils.info(this, "Logged in");
            }
        });

        JButton back = UIUtils.linkButton("Back to Customer Login");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(e -> UIUtils.info(this, "Customer login opened"));

        root.add(login);
        root.add(Box.createVerticalStrut(15));
        root.add(back);
    }

    private void addAlignedField(JPanel panel, String labelText, JComponent field) {
        JPanel fieldGroup = new JPanel();
        fieldGroup.setOpaque(false);
        fieldGroup.setLayout(new BoxLayout(fieldGroup, BoxLayout.Y_AXIS));
        fieldGroup.setPreferredSize(new Dimension(CONTROL_WIDTH, 56));
        fieldGroup.setMaximumSize(new Dimension(CONTROL_WIDTH, 56));
        fieldGroup.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = UIUtils.label(labelText, Font.BOLD, 12, UIColors.TEXT_DARK);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setPreferredSize(new Dimension(CONTROL_WIDTH, 36));
        field.setMaximumSize(new Dimension(CONTROL_WIDTH, 36));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        fieldGroup.add(label);
        fieldGroup.add(Box.createVerticalStrut(5));
        fieldGroup.add(field);

        panel.add(fieldGroup);
        panel.add(Box.createVerticalStrut(15));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminLoginPage().setVisible(true));
    }
}
