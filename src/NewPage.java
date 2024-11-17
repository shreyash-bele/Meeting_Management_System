import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;



class NewPage extends JFrame implements ActionListener
{

    NewPage()
    {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        /*setBounds(400,120,500,500);
        setTitle("Welcome");
        setSize(700, 600);*/
        JFrame jf = new JFrame();
        JLabel wel_label = new JLabel("Welcome");
        JButton ab = new JButton("Add Details");






        wel_label.setBounds(300, 50,  300, 100);
        wel_label.setFont(new Font("Times New Roman", Font.BOLD, 22));
        ab.setBounds(250, 180,  200, 40);


        jf.setSize(700, 600);
        jf.setLocation(300, 120);
        jf.add(wel_label);
        jf.add(ab);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        ab.addActionListener(this);

        jf.setLayout(null);
        jf.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent qe) {
        // TODO Auto-generated method stub

        Meeting st = new Meeting();
        //st.setVisible(true);


    }







}