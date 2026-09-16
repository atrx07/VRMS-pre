package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProfilePage extends JFrame {
    public ProfilePage(){
        setTitle("VRMS - Profile"); setSize(560,560); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setResizable(false);
        JPanel root=new JPanel(new BorderLayout(0,18)); root.setBackground(UIColors.BG_PAGE); root.setBorder(new EmptyBorder(28,42,28,42)); setContentPane(root);
        JPanel header=new JPanel(); header.setOpaque(false); header.setLayout(new BoxLayout(header,BoxLayout.Y_AXIS)); header.add(UIUtils.label("My Profile",Font.BOLD,28,UIColors.TEXT_DARK)); header.add(Box.createVerticalStrut(4)); header.add(UIUtils.label("Update your personal details used by VRMS",Font.PLAIN,12,UIColors.TEXT_MUTED)); root.add(header,BorderLayout.NORTH);
        JPanel card=new JPanel(); card.setBackground(UIColors.CARD_BG); card.setLayout(new BoxLayout(card,BoxLayout.Y_AXIS)); card.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(UIColors.BORDER),new EmptyBorder(22,24,22,24)));
        JLabel account=UIUtils.label("Customer Account",Font.BOLD,15,UIColors.PRIMARY); account.setAlignmentX(Component.LEFT_ALIGNMENT); card.add(account); card.add(Box.createVerticalStrut(18));
        JTextField name=new JTextField("Arppith Andrews"); JTextField email=new JTextField("customer@vrms.com"); JTextField phone=new JTextField("9876543210"); JPasswordField password=new JPasswordField();
        UIUtils.addField(card,"Name",name); UIUtils.addField(card,"Email",email); UIUtils.addField(card,"Phone",phone); UIUtils.addField(card,"New Password",password);
        JLabel note=UIUtils.label("Leave password blank to keep the current password.",Font.PLAIN,11,UIColors.TEXT_MUTED); note.setAlignmentX(Component.LEFT_ALIGNMENT); card.add(note); root.add(card,BorderLayout.CENTER);
        JPanel actions=new JPanel(new GridLayout(1,2,10,0)); actions.setOpaque(false); JButton back=UIUtils.secondaryButton("Back to Catalog"); back.addActionListener(e->UIUtils.showPage(this,new CatalogPage())); JButton save=UIUtils.primaryButton("Save Changes"); save.addActionListener(e->JOptionPane.showMessageDialog(this,"Profile changes saved in the UI preview.","VRMS UI Preview",JOptionPane.INFORMATION_MESSAGE)); actions.add(back); actions.add(save); root.add(actions,BorderLayout.SOUTH);
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(()->new ProfilePage().setVisible(true)); }
}
