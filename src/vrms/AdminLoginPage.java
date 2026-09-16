package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminLoginPage extends JFrame {
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
        JTextField email = new JTextField("admin@vrms.com");
        JPasswordField password = new JPasswordField("admin123");

        root.add(title); root.add(Box.createVerticalStrut(4)); root.add(brand); root.add(Box.createVerticalStrut(20));
        UIUtils.addField(root, "Admin Email", email);
        UIUtils.addField(root, "Password", password);

        JButton login = UIUtils.primaryButton("LOGIN AS ADMIN");
        login.setMaximumSize(new Dimension(Integer.MAX_VALUE, 36));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.addActionListener(e -> UIUtils.showPage(this, new AdminDashboardPage()));
        JButton back = UIUtils.linkButton("Back to Customer Login");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(e -> UIUtils.showPage(this, new LoginPage()));
        root.add(login); root.add(Box.createVerticalStrut(15)); root.add(back);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminLoginPage().setVisible(true));
    }
}
