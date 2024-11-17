package pack1;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.awt.event.ActionEvent;

public class ScheduledMeeting extends JFrame {

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

    public ScheduledMeeting() {

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

//					int cols = rsmd.getColumnCount();
//					String[] colName = new String[cols];
//					for(int i = 0 ;i<cols;i++) {
//						colName[i]=rsmd.getColumnName(i+1);
//					}
//					model.setColumnIdentifiers(colName);

                    String[] colName = {"date", "time"};
                    model.setColumnIdentifiers(colName);

                    int id,date,time;

                    PriorityQueue<PairDate> pQ = new PriorityQueue<>(new Comparator<PairDate>() {
                        public int compare(PairDate p1, PairDate p2) {
                            if (p1.getDate()==p2.getDate()) {
                                return p1.getTime().compareTo(p2.getTime());
                            }
                            return p1.getDate().compareTo(p2.getDate());
                        }
                    });

                    while(rs.next()) {
                        id= Integer.parseInt(rs.getString(1));
                        date=Integer.parseInt(rs.getString(3));
                        time=Integer.parseInt(rs.getString(4));

                        PairDate pd = new PairDate(date,time);
                        pQ.add(pd);
                    }

                    int pqs = pQ.size();

                    for(int i = 0; i < pqs; i++) {
                        PairDate p = pQ.poll();
                        String[] row= {Integer.toString(p.getDate()),Integer.toString(p.getTime())};
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

        JLabel lblNewLabel = new JLabel("Recent details");
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


class PairDate {
    public int date;
    public int time;

    public PairDate(int date, int time) {
        this.date = date;
        this.time = time;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
