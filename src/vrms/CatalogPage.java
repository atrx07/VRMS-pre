package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class CatalogPage extends JFrame {
    public CatalogPage() {
        setTitle("VRMS - Vehicle Catalog");
        setSize(1100, 680);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel root = new JPanel(new BorderLayout(0, 20));
        root.setBackground(UIColors.BG_PAGE);
        root.setBorder(new EmptyBorder(24, 32, 24, 32));
        setContentPane(root);
        root.add(createHeader(), BorderLayout.NORTH);
        root.add(createCardArea(), BorderLayout.CENTER);
    }

    private JPanel createHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        JLabel title = UIUtils.label("Available Vehicles", Font.BOLD, 28, UIColors.TEXT_DARK);
        JLabel welcome = UIUtils.label("Welcome, Arppith  |  Pick a vehicle that works for you", Font.PLAIN, 13, UIColors.TEXT_MUTED);
        textPanel.add(title); textPanel.add(Box.createVerticalStrut(4)); textPanel.add(welcome);

        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right, BoxLayout.X_AXIS));
        JLabel brand = UIUtils.label("VRMS", Font.BOLD, 20, UIColors.PRIMARY);
        brand.setAlignmentY(Component.CENTER_ALIGNMENT);
        JButton menuButton = createMenuButton();
        menuButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        JPopupMenu menu = createCustomerMenu();
        menuButton.addActionListener(e -> menu.show(menuButton,
                menuButton.getWidth() - menu.getPreferredSize().width, menuButton.getHeight() + 4));
        right.add(brand); right.add(Box.createHorizontalStrut(18)); right.add(menuButton);

        header.add(textPanel, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    private JPopupMenu createCustomerMenu() {
        JPopupMenu menu = new JPopupMenu();
        menu.setBorder(BorderFactory.createLineBorder(UIColors.BORDER_DARK));
        JMenuItem refresh = menuItem("Refresh Catalog");
        JMenuItem rentals = menuItem("My Rentals");
        rentals.addActionListener(e -> UIUtils.previewAction(this, "My Rentals", "opens the customer's rental history and active rentals"));
        JMenuItem vehicles = menuItem("My Vehicles");
        vehicles.addActionListener(e -> UIUtils.previewAction(this, "My Vehicles", "opens the customer's vehicle listings and their approval status"));
        JMenuItem list = menuItem("List Vehicle");
        list.addActionListener(e -> UIUtils.previewAction(this, "List Vehicle", "opens the form for submitting a vehicle listing for admin approval"));
        JMenuItem profile = menuItem("Profile");
        profile.addActionListener(e -> UIUtils.previewAction(this, "Profile", "opens the customer's profile details"));
        JMenuItem logout = menuItem("Logout");
        logout.setForeground(UIColors.DANGER);
        logout.addActionListener(e -> UIUtils.previewAction(this, "Logout", "ends the current session and returns to the login screen"));
        menu.add(refresh); menu.addSeparator(); menu.add(rentals); menu.add(vehicles); menu.add(list); menu.add(profile);
        menu.addSeparator(); menu.add(logout);
        return menu;
    }

    private JMenuItem menuItem(String text) {
        JMenuItem item = new JMenuItem(text);
        item.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        item.setForeground(UIColors.TEXT_DARK);
        item.setBackground(Color.WHITE);
        item.setBorder(new EmptyBorder(8, 14, 8, 14));
        return item;
    }

    private JButton createMenuButton() {
        JButton button = new JButton("Menu");
        button.setUI(new BasicButtonUI());
        button.setPreferredSize(new Dimension(96, 42));
        button.setBackground(Color.WHITE);
        button.setForeground(UIColors.PRIMARY);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(UIColors.PRIMARY, 2), new EmptyBorder(8, 13, 8, 13)));
        button.setFocusPainted(false);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setHorizontalTextPosition(SwingConstants.LEFT);
        button.setIconTextGap(9);
        button.setIcon(new Icon() {
            public int getIconWidth() { return 18; }
            public int getIconHeight() { return 14; }
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(UIColors.PRIMARY);
                g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine(x + 1, y + 2, x + 17, y + 2);
                g2.drawLine(x + 1, y + 7, x + 17, y + 7);
                g2.drawLine(x + 1, y + 12, x + 17, y + 12);
                g2.dispose();
            }
        });
        return button;
    }

    private JScrollPane createCardArea() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16));
        grid.setOpaque(false);
        grid.add(vehicleCard("CAR", "Maruti Swift", "Alwin KJ", "KL08AB1234", "1500.00", false));
        grid.add(vehicleCard("BIKE", "Honda Activa", "Amal Farhan", "KL45CD5678", "500.00", false));
        grid.add(vehicleCard("CAR", "Toyota Innova", "Arppith Andrews", "KL07EF2468", "2200.00", true));
        JPanel holder = new JPanel(new BorderLayout());
        holder.setBackground(UIColors.BG_PAGE);
        holder.add(grid, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(holder);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(UIColors.BG_PAGE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private JPanel vehicleCard(String type, String name, String owner, String registration, String rate, boolean own) {
        JPanel card = new JPanel(new BorderLayout(0, 12));
        card.setBackground(UIColors.CARD_BG);
        card.setPreferredSize(new Dimension(310, 255));
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(UIColors.BORDER),
                new EmptyBorder(18, 18, 16, 18)));
        JPanel top = new JPanel(new BorderLayout());
        top.setOpaque(false);
        top.add(UIUtils.badge(type, UIColors.BG_LEFT, UIColors.PRIMARY), BorderLayout.WEST);
        top.add(UIUtils.badge("AVAILABLE", UIColors.SUCCESS_BG, UIColors.SUCCESS), BorderLayout.EAST);
        card.add(top, BorderLayout.NORTH);

        JPanel details = new JPanel();
        details.setOpaque(false);
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        JLabel n = UIUtils.label(name, Font.BOLD, 20, UIColors.TEXT_DARK); n.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel o = UIUtils.label("Listed by " + owner + (own ? " (You)" : ""), Font.PLAIN, 12, UIColors.TEXT_MUTED); o.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel r = UIUtils.label("Registration  " + registration, Font.PLAIN, 12, UIColors.TEXT_MUTED); r.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel p = UIUtils.label("Rs. " + rate + " / day", Font.BOLD, 17, UIColors.PRIMARY); p.setAlignmentX(Component.LEFT_ALIGNMENT);
        details.add(n); details.add(Box.createVerticalStrut(7)); details.add(o); details.add(Box.createVerticalStrut(4)); details.add(r);
        details.add(Box.createVerticalStrut(14)); details.add(p);
        card.add(details, BorderLayout.CENTER);

        JButton action = own ? UIUtils.secondaryButton("Your Listing") : UIUtils.primaryButton("Rent Vehicle");
        if (own) action.setEnabled(false);
        else action.addActionListener(e -> UIUtils.previewAction(this, "Rent Vehicle", "opens the rental details screen for the selected vehicle"));
        card.add(action, BorderLayout.SOUTH);
        return card;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CatalogPage().setVisible(true));
    }
}
