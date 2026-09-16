package vrms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RentVehiclePage extends JFrame {
    private static final String[] SAMPLE = {"1","2","Maruti Swift","Car","KL08AB1234","1500.00","AVAILABLE","APPROVED"};
    private final String[] vehicle;
    private final JTextField startField = new JTextField();
    private final JTextField endField = new JTextField();
    private final JLabel totalLabel = new JLabel("Rental amount: Rs. 0.00");

    public RentVehiclePage(){ this(SAMPLE); }

    public RentVehiclePage(String[] vehicle){
        this.vehicle=vehicle;
        setTitle("VRMS - Rent Vehicle"); setSize(520,520); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); setResizable(false);
        JPanel root=new JPanel(); root.setBackground(UIColors.BG_PAGE); root.setBorder(new EmptyBorder(28,42,28,42)); root.setLayout(new BoxLayout(root,BoxLayout.Y_AXIS)); setContentPane(root);
        JLabel title=UIUtils.label("Rent Vehicle",Font.BOLD,24,UIColors.TEXT_DARK); title.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel name=UIUtils.label(vehicle[2],Font.BOLD,20,UIColors.PRIMARY); name.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel owner=UIUtils.label("Listed by Alwin KJ",Font.PLAIN,12,UIColors.TEXT_MUTED); owner.setAlignmentX(Component.LEFT_ALIGNMENT);
        JLabel rate=UIUtils.label("Rs. "+vehicle[5]+" / day",Font.BOLD,16,UIColors.TEXT_DARK); rate.setAlignmentX(Component.LEFT_ALIGNMENT);
        root.add(title); root.add(Box.createVerticalStrut(18)); root.add(name); root.add(Box.createVerticalStrut(4)); root.add(owner); root.add(Box.createVerticalStrut(4)); root.add(rate); root.add(Box.createVerticalStrut(24));
        UIUtils.addField(root,"Start Date (YYYY-MM-DD)",startField); UIUtils.addField(root,"End Date (YYYY-MM-DD)",endField);
        JButton calc=UIUtils.secondaryButton("Calculate Rental"); calc.setAlignmentX(Component.LEFT_ALIGNMENT); calc.addActionListener(e->calculate()); root.add(calc); root.add(Box.createVerticalStrut(15));
        totalLabel.setFont(new Font("Segoe UI",Font.BOLD,18)); totalLabel.setForeground(UIColors.PRIMARY); totalLabel.setAlignmentX(Component.LEFT_ALIGNMENT); root.add(totalLabel);
        JLabel fee=UIUtils.label("A 10% VRMS service fee is added on the payment page.",Font.PLAIN,11,UIColors.TEXT_MUTED); fee.setAlignmentX(Component.LEFT_ALIGNMENT); root.add(Box.createVerticalStrut(5)); root.add(fee); root.add(Box.createVerticalGlue());
        JPanel actions=new JPanel(new GridLayout(1,2,10,0)); actions.setOpaque(false); actions.setMaximumSize(new Dimension(Integer.MAX_VALUE,40)); actions.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton back=UIUtils.secondaryButton("Back"); back.addActionListener(e->UIUtils.showPage(this,new CatalogPage()));
        JButton pay=UIUtils.primaryButton("Continue to Payment"); pay.addActionListener(e->UIUtils.previewAction(this,"Continue to Payment","opens the payment summary for the selected rental dates")); actions.add(back); actions.add(pay); root.add(actions);
    }

    private double amount(){
        LocalDate start=LocalDate.parse(startField.getText().trim()); LocalDate end=LocalDate.parse(endField.getText().trim());
        if(end.isBefore(start)) throw new IllegalArgumentException();
        long days=ChronoUnit.DAYS.between(start,end)+1; return days*Double.parseDouble(vehicle[5]);
    }
    private void calculate(){
        try{ totalLabel.setText(String.format("Rental amount: Rs. %.2f",amount())); }
        catch(Exception ex){ totalLabel.setText("Rental amount: enter valid dates"); }
    }
    public static void main(String[] args){ SwingUtilities.invokeLater(()->new RentVehiclePage().setVisible(true)); }
}
