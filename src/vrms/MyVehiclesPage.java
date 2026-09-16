package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MyVehiclesPage extends JFrame {
    public MyVehiclesPage() {
        setTitle("VRMS - My Vehicles");
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
        JPanel p = new JPanel(); p.setOpaque(false); p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(UIUtils.label("My Vehicles", Font.BOLD, 28, UIColors.TEXT_DARK));
        p.add(Box.createVerticalStrut(4));
        p.add(UIUtils.label("Vehicles listed by Arppith Andrews", Font.PLAIN, 13, UIColors.TEXT_MUTED));
        return p;
    }

    private JScrollPane cards() {
        JPanel grid = new JPanel(new GridLayout(0, 3, 16, 16)); grid.setOpaque(false);
        grid.add(card("CAR", "APPROVED", "Toyota Innova", "KL07EF2468", "2200.00", "AVAILABLE", false));
        grid.add(card("BIKE", "PENDING", "Yamaha FZ", "KL45XY4321", "800.00", "AVAILABLE", false));
        grid.add(card("CAR", "DELETED BY ADMIN", "Hyundai i20", "KL08ZX9001", "1600.00", "UNAVAILABLE", true));
        JPanel holder = new JPanel(new BorderLayout()); holder.setBackground(UIColors.BG_PAGE); holder.add(grid, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane(holder); scroll.setBorder(null); scroll.getViewport().setBackground(UIColors.BG_PAGE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); return scroll;
    }

    private JPanel card(String type, String approval, String name, String reg, String rate, String availability, boolean deleted) {
        JPanel card = new JPanel(new BorderLayout(0, 14));
        card.setBackground(UIColors.CARD_BG); card.setPreferredSize(new Dimension(310, deleted ? 250 : 225));
        card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(UIColors.BORDER), new EmptyBorder(18,18,18,18)));
        JPanel top = new JPanel(new BorderLayout()); top.setOpaque(false);
        top.add(UIUtils.badge(type, UIColors.BG_LEFT, UIColors.PRIMARY), BorderLayout.WEST);
        Color bg = approval.equals("APPROVED") ? UIColors.SUCCESS_BG : approval.equals("PENDING") ? UIColors.WARNING_BG : UIColors.DANGER_BG;
        Color fg = approval.equals("APPROVED") ? UIColors.SUCCESS : approval.equals("PENDING") ? UIColors.WARNING : UIColors.DANGER;
        top.add(UIUtils.badge(approval, bg, fg), BorderLayout.EAST); card.add(top, BorderLayout.NORTH);
        JPanel d = new JPanel(); d.setOpaque(false); d.setLayout(new BoxLayout(d, BoxLayout.Y_AXIS));
        JLabel n=UIUtils.label(name,Font.BOLD,20,UIColors.TEXT_DARK); n.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel r=UIUtils.label("Registration  "+reg,Font.PLAIN,12,UIColors.TEXT_MUTED); r.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel p=UIUtils.label("Rs. "+rate+" / day",Font.BOLD,17,deleted?UIColors.TEXT_MUTED:UIColors.PRIMARY); p.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel a=UIUtils.label("Availability: "+availability.substring(0,1)+availability.substring(1).toLowerCase(),Font.PLAIN,12,UIColors.TEXT_DARK); a.setAlignmentX(Component.LEFT_ALIGNMENT);
        d.add(n); d.add(Box.createVerticalStrut(8)); d.add(r); d.add(Box.createVerticalStrut(18)); d.add(p); d.add(Box.createVerticalStrut(9)); d.add(a);
        if(deleted){ JLabel note=UIUtils.label("Removed from the catalog by an administrator.",Font.PLAIN,10,UIColors.DANGER); note.setAlignmentX(Component.LEFT_ALIGNMENT); d.add(Box.createVerticalStrut(8)); d.add(note); }
        card.add(d,BorderLayout.CENTER); return card;
    }

    private JPanel bottom(){
        JPanel bar=new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0)); bar.setOpaque(false);
        bar.add(UIUtils.secondaryButton("Refresh"));
        JButton list=UIUtils.primaryButton("List Another Vehicle");
        list.addActionListener(e->UIUtils.previewAction(this,"List Another Vehicle","opens the vehicle listing form"));
        bar.add(list);
        JButton back=UIUtils.secondaryButton("Back to Catalog");
        back.addActionListener(e->UIUtils.showPage(this,new CatalogPage()));
        bar.add(back);
        return bar;
    }

    public static void main(String[] args){ SwingUtilities.invokeLater(()->new MyVehiclesPage().setVisible(true)); }
}
