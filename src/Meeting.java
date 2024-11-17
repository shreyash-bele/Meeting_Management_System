import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class Meeting extends JFrame {
    JButton add,view,update,delete,back_but;

    Meeting()
    {

        JFrame kk =new JFrame();
        JLabel l = new JLabel("Meeting Details");
        l.setBounds(265, 50,  300, 100);
        l.setFont(new Font("Times New Roman", Font.BOLD, 24));
        //l.setHorizontalAlignment(JLabel.CENTER);
        kk.setSize(700, 600);
        kk.setLocation(300, 120);
        kk.add(l);

        add = new JButton("Add Meeting Details");
        view = new JButton("View Details");
        update = new JButton("Recent Meetings");
        delete = new JButton("Delete");
        back_but = new JButton("BACK");

        add.setBounds(220, 150,  250, 40);
        view.setBounds(220, 210,  250, 40);
        update.setBounds(220,270,250,40);
        delete.setBounds(220,330,250,40);

        back_but.setBounds(70,480,80,30);

        kk.add(add);
        kk.add(view);
        kk.add(update);
        kk.add(delete);
        //kk.add(leaved);
        kk.add(back_but);


        kk.setLayout(null);
        kk.setVisible(true);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Addmeetingdetails as = new Addmeetingdetails();
                kk.dispose();

            }

        });
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewMeetingdetails vs = new ViewMeetingdetails();
                vs.setVisible(true);

            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScheduledMeeting sm = new ScheduledMeeting();
                sm.setVisible(true);

            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deletemeeting ds = new Deletemeeting();
                kk.dispose();

            }
        });

        back_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ne) {
                if (ne.getActionCommand().equals("BACK"))
                {
                    kk.setVisible(false);
                    NewPage np = new NewPage();
                    np.dispose();
                }

            }
        });
        kk.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public static class ViewMeetingdetails extends JFrame {

        public JPanel contentPane;
        public JTable table;


        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        ViewMeetingdetails frame = new ViewMeetingdetails();
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }


        public ViewMeetingdetails() {

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 1323, 790);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

            setContentPane(contentPane);
            contentPane.setLayout(null);

            JButton btnShow = new JButton("Display Data");
            btnShow.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/hostel","root","");
                        Statement st = con.createStatement();
                        String query = "select * from meeting";
                        ResultSet rs=st.executeQuery(query);
                        ResultSetMetaData rsmd = rs.getMetaData();
                        DefaultTableModel model = (DefaultTableModel) table.getModel();

                        int cols = rsmd.getColumnCount();
                        String[] colName = new String[cols];
                        for(int i = 0 ;i<cols;i++) {
                            colName[i]=rsmd.getColumnName(i+1);
                        }
                        model.setColumnIdentifiers(colName);
                        String id,name,fathername,mothername,email,address,admission_date,period,fees,phone_no,room;
                        while(rs.next()) {
                            id=rs.getString(1);
                            name=rs.getString(2);
                            fathername=rs.getString(3);
                            mothername=rs.getString(4);
                            email=rs.getString(5);
                            address=rs.getString(6);
                            admission_date=rs.getString(7);
                            period=rs.getString(8);
                            fees=rs.getString(9);
                            phone_no=rs.getString(10);
                            room=rs.getString(11);

                            String[] row= {id,name,fathername,mothername,email,address,admission_date,period,fees,phone_no,room};
                            model.addRow(row);
                        }
                        st.close();
                        con.close();


                    } catch (ClassNotFoundException | SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            btnShow.setFont(new Font("Tahoma", Font.PLAIN, 11));
            btnShow.setBounds(585, 55, 105, 25);
            contentPane.add(btnShow);

            JLabel lblNewLabel = new JLabel("View Meeting Details");
            lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
            lblNewLabel.setBounds(530, 25, 200, 25);
            contentPane.add(lblNewLabel);

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(39, 89, 1212, 624);
            contentPane.add(scrollPane);

            table = new JTable();
            scrollPane.setViewportView(table);

            JButton clrButton = new JButton("Clear");
            clrButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    table.setModel(new DefaultTableModel());
                }
            });
            clrButton.setBounds(40, 55, 90, 25);
            contentPane.add(clrButton);
        }
    }
}