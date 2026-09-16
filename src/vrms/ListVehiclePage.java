package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ListVehiclePage extends JFrame {
    private static final int FORM_WIDTH = 310;

    public ListVehiclePage(){
        setTitle("VRMS - List Vehicle");
        setSize(500,470);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel root=new JPanel();
        root.setBackground(UIColors.BG_PAGE);
        root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS));
        root.setBorder(new EmptyBorder(28,55,25,55));
        setContentPane(root);

        JLabel title=UIUtils.label("List Your Vehicle",Font.BOLD,23,UIColors.TEXT_DARK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel sub=UIUtils.label("New listings require admin approval",Font.PLAIN,12,UIColors.TEXT_MUTED);
        sub.setAlignmentX(Component.CENTER_ALIGNMENT);

        root.add(title);
        root.add(Box.createVerticalStrut(4));
        root.add(sub);
        root.add(Box.createVerticalStrut(24));

        JTextField name=new JTextField();
        JComboBox<String> type=new JComboBox<>(new String[]{"Car","Bike","Van"});
        JTextField reg=new JTextField();
        JTextField price=new JTextField();

        addAlignedField(root,"Vehicle Name",name);
        addAlignedField(root,"Vehicle Type",type);
        addAlignedField(root,"Registration Number",reg);
        addAlignedField(root,"Price Per Day (Rs.)",price);

        JButton submit=UIUtils.primaryButton("SUBMIT FOR APPROVAL");
        submit.setPreferredSize(new Dimension(FORM_WIDTH,38));
        submit.setMaximumSize(new Dimension(FORM_WIDTH,38));
        submit.setAlignmentX(Component.CENTER_ALIGNMENT);
        submit.addActionListener(e->UIUtils.previewAction(this,"SUBMIT FOR APPROVAL","sends the vehicle listing to the administrator for review"));

        JButton back=UIUtils.linkButton("Back to Catalog");
        back.setAlignmentX(Component.CENTER_ALIGNMENT);
        back.addActionListener(e->UIUtils.showPage(this,new CatalogPage()));

        root.add(Box.createVerticalStrut(5));
        root.add(submit);
        root.add(Box.createVerticalStrut(15));
        root.add(back);
    }

    private void addAlignedField(JPanel panel, String labelText, JComponent field){
        JPanel fieldGroup=new JPanel();
        fieldGroup.setOpaque(false);
        fieldGroup.setLayout(new BoxLayout(fieldGroup,BoxLayout.Y_AXIS));
        fieldGroup.setPreferredSize(new Dimension(FORM_WIDTH,58));
        fieldGroup.setMaximumSize(new Dimension(FORM_WIDTH,58));
        fieldGroup.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label=UIUtils.label(labelText,Font.BOLD,12,UIColors.TEXT_DARK);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);

        field.setFont(new Font("Segoe UI",Font.PLAIN,13));
        field.setPreferredSize(new Dimension(FORM_WIDTH,34));
        field.setMaximumSize(new Dimension(FORM_WIDTH,34));
        field.setAlignmentX(Component.LEFT_ALIGNMENT);

        fieldGroup.add(label);
        fieldGroup.add(Box.createVerticalStrut(5));
        fieldGroup.add(field);

        panel.add(fieldGroup);
        panel.add(Box.createVerticalStrut(10));
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()->new ListVehiclePage().setVisible(true));
    }
}
