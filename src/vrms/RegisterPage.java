package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterPage extends JFrame {
    public RegisterPage() {
        setTitle("VRMS - Customer Registration");
        setSize(460, 510);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel();
        root.setBackground(UIColors.BG_PAGE);
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(25, 45, 20, 45));
        setContentPane(root);

        JLabel title = UIUtils.label("Create Customer Account", Font.BOLD, 22, UIColors.TEXT_DARK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = UIUtils.label(
                "Register to rent or list vehicles on VRMS",
                Font.PLAIN,
                12,
                UIColors.TEXT_MUTED
        );
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JTextField phone = new JTextField();
        JPasswordField password = new JPasswordField();

        root.add(title);
        root.add(Box.createVerticalStrut(4));
        root.add(subtitle);
        root.add(Box.createVerticalStrut(20));

        UIUtils.addField(root, "Name", name);
        UIUtils.addField(root, "Email", email);
        UIUtils.addField(root, "Phone", phone);
        UIUtils.addField(root, "Password", password);

        JButton register = UIUtils.primaryButton("REGISTER");
        register.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.addActionListener(e -> {
            if (name.getText().trim().isEmpty()) {
                UIUtils.error(this, "Enter name");
            } else if (email.getText().trim().isEmpty()) {
                UIUtils.error(this, "Enter email");
            } else if (phone.getText().trim().isEmpty()) {
                UIUtils.error(this, "Enter phone");
            } else if (new String(password.getPassword()).trim().isEmpty()) {
                UIUtils.error(this, "Enter password");
            } else {
                UIUtils.info(this, "User registered");
            }
        });

        JButton back = UIUtils.linkButton("Back to Login");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(e -> UIUtils.info(this, "Login opened"));

        root.add(register);
        root.add(Box.createVerticalStrut(15));
        root.add(back);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterPage().setVisible(true));
    }
}
