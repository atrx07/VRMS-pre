package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ListVehiclePage extends JFrame {
    public ListVehiclePage(){
        setTitle("VRMS - List Vehicle"); setSize(500,470); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setResizable(false);
        JPanel root=new JPanel(); root.setBackground(UIColors.BG_PAGE); root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS)); root.setBorder(new EmptyBorder(28,55,25,55)); setContentPane(root);
        JLabel title=UIUtils.label("List Your Vehicle",Font.BOLD,23,UIColors.TEXT_DARK); title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sub=UIUtils.label("New listings require admin approval",Font.PLAIN,12,UIColors.TEXT_MUTED); sub.setAlignmentX(Component.CENTER_ALIGNMENT);
        root.add(title); root.add(Box.createVerticalStrut(4)); root.add(sub); root.add(Box.createVerticalStrut(24));
        JTextField name=new JTextField("Honda City"); JComboBox<String> type=new JComboBox<>(new String[]{"Car","Bike","Van"}); JTextField reg=new JTextField("KL08AA1234"); JTextField price=new JTextField("1800");
        UIUtils.addField(root,"Vehicle Name",name); UIUtils.addField(root,"Vehicle Type",type); UIUtils.addField(root,"Registration Number",reg); UIUtils.addField(root,"Price Per Day (Rs.)",price);
        JButton submit=UIUtils.primaryButton("SUBMIT FOR APPROVAL"); submit.setMaximumSize(new Dimension(Integer.MAX_VALUE,38)); submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.addActionListener(e->JOptionPane.showMessageDialog(this,"Vehicle submitted for admin approval.","VRMS UI Preview",JOptionPane.INFORMATION_MESSAGE));
        JButton back=UIUtils.linkButton("Back to Catalog"); back.setAlignmentX(Component.CENTER_ALIGNMENT); back.addActionListener(e->UIUtils.showPage(this,new CatalogPage()));
        root.add(submit); root.add(Box.createVerticalStrut(15)); root.add(back);
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(()->new ListVehiclePage().setVisible(true)); }
}
