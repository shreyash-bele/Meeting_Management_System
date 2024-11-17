import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Deletemeeting {
    Deletemeeting(){
        JFrame kk =new JFrame();
        JLabel header,ask_id;
        JTextField ask_id1;
        kk.setSize(700, 600);
        kk.setLocation(300, 120);
        kk.setVisible(true);
        kk.setLayout(null);

        header = new JLabel("Delete Meeting Details");
        header.setBounds(220,10,500,40);
        kk.add(header);
        header.setFont(new Font("Times New Roman",Font.BOLD,22));
        JButton click = new JButton("Click Here!");
        JLabel go_to = new JLabel("To view ID of meeting :");
        kk.add(click);
        kk.add(go_to);
        click.setBounds(270, 150, 120, 30);
        go_to.setBounds(30, 150, 220, 30);
        ask_id = new JLabel("Enter ID of meeting to remove :");
        ask_id.setBounds(30, 400, 220, 30);
        kk.add(ask_id);
        ask_id1 = new JTextField(20);
        ask_id1.setBounds(255, 400, 120, 30);
        kk.add(ask_id1);
        JButton back_but = new JButton("BACK");
        back_but.setBounds(70, 480, 80, 30);
        kk.add(back_but);
        click.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                ViewMeetingdetails vs = new ViewMeetingdetails();
                vs.setVisible(true);
                kk.dispose();
            }
        });
        JButton delete = new JButton("Delete");
        delete.setBounds(395,400,120,30);
        kk.add(delete);
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try{
                    String g_ask_id1 = ask_id1.getText();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");
                    Statement st = conn.createStatement();
                    String sql = "DELETE FROM `student` WHERE id = "+g_ask_id1;
                    PreparedStatement ptst = conn.prepareStatement(sql);
                    ptst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Deleted.");
                    conn.close();
                    ask_id1.setText("");
                }catch(Exception ae){
                    JOptionPane.showMessageDialog(null,ae);
                }
            }
        });
        back_but.addActionListener((ActionListener) new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Meeting em= new Meeting();
            }
        });
        JButton dash;
        dash = new JButton("Dashboard");
        kk.add(dash);
        dash.setBounds(10,20,105,40);
        dash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kk.setVisible(false);
                NewPage np = new NewPage();
                kk.dispose();

            }
        });

    }
}