package pack1;
import javax.swing.*;
import java.awt.*;
import java.lang.Exception;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



class CreateLoginForm extends JFrame implements ActionListener
{

    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel,headLabel;
    final JTextField  textField1, textField2;

    CreateLoginForm()
    {

        headLabel = new JLabel();
        headLabel.setText("MEETING MANAGEMENT");
        headLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        headLabel.setBounds(200,-70,300,300);


        userLabel = new JLabel();
        userLabel.setText("Username :");
        userLabel.setBounds(120, 150, 100,30);

        textField1 = new JTextField(15);
        textField1.setBounds(230, 150 ,300,30);

        passLabel = new JLabel();
        passLabel.setText("Password :");
        passLabel.setBounds(120, 220 ,100,30);

        textField2 = new JPasswordField(15);
        textField2.setBounds(230, 220 ,300,30);



        b1 = new JButton("SUBMIT");




        b1.setBounds(450, 300 ,100,30);


        newPanel = new JPanel(new GridLayout(6, 10));
        newPanel.add(headLabel);
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);

        newPanel.setLayout(null);


        add(newPanel, BorderLayout.CENTER);


        b1.addActionListener(this);

        setTitle("LOGIN FORM");
        //newPanel.setBackground(Color.YELLOW);



    }

    public void actionPerformed(ActionEvent ae)
    {

        String userValue = textField1.getText();
        String passValue = textField2.getText();


        if (userValue.equals("admin") && passValue.equals("admin")) {

            //setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

            NewPage page = new NewPage();


            //page.setVisible(true);
            textField1.setText("");
            textField2.setText("");


        }
        else{

            JOptionPane.showMessageDialog(null, "Please enter valid username and password");
        }
    }
}
public class Login{
    public static void main(String arg[])
    {
        // ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("C://Users/91903/eclipse-workspace/Login/src/backgroundimage/hosteldaze.jpeg"));
        //  Image i1 = c1.getImage().getScaledInstance(700,600,Image.SCALE_DEFAULT);
        try
        {

            CreateLoginForm form = new CreateLoginForm();

            form.setSize(700,600);


            form.setVisible(true);
            form.setLocation(300, 120);
            form.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        }
        catch(Exception e)
        {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
