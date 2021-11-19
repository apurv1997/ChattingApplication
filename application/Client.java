package chatting.application;
/*
 * All the decorations will be global.
 * All the coding will be done in constructor only.
 * */

import javax.swing.*;      // import swing package
import java.awt.*;         // import awt package
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


public class Client extends JFrame implements ActionListener {        //class JFrame in swing package

    JPanel p1;   // Access globally
    JTextField t1;    // text area
    JButton b1;  // clickable button;
    static JTextArea a1;

    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;


    Client() {    //constructor

        p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/LeftArrow.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l1 = new JLabel(i3);
        l1.setBounds(5, 17, 30, 30);
        p1.add(l1);

        l1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/person1.jpg"));
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel l2 = new JLabel(i6);
        l2.setBounds(40, 10, 45, 45);
        p1.add(l2);

        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/videoCall.png"));
        Image i8 = i7.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel l5 = new JLabel(i9);
        l5.setBounds(290, 20, 35, 30);
        p1.add(l5);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/call.jpeg"));
        Image i12 = i11.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel l6 = new JLabel(i13);
        l6.setBounds(350, 20, 35, 30);
        p1.add(l6);

        ImageIcon i14 = new ImageIcon(ClassLoader.getSystemResource("chatting/application/icons/dot_icon.jpg"));
        Image i15 = i14.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(i15);
        JLabel l7 = new JLabel(i16);
        l7.setBounds(410, 20, 25, 30);
        p1.add(l7);

        JLabel l3 = new JLabel("Baba");
        l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110, 15, 100, 20);
        p1.add(l3);

        JLabel l4 = new JLabel("Online");
        l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110, 15, 100, 70);
        p1.add(l4);

        a1 = new JTextArea();
        a1.setBounds(5, 75, 440, 570);
        a1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        a1.setEditable(false);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
//        a1.setBackground(Color.PINK);
        add(a1);

        t1 = new JTextField();            // JTextField ka object
        t1.setBounds(5, 655, 310, 40);
        t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(t1);

        b1 = new JButton("Send");    // JButton ka object
        b1.setBounds(320, 655, 123, 40);
        b1.setBackground(new Color(7, 94, 84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        b1.addActionListener(this);
        add(b1);

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setSize(450, 700);
        setLocation(1100, 200);
        setUndecorated(true);
        setVisible(true);      // by default they are false so we make them true
    }

    public void actionPerformed(ActionEvent ae) {
        try{
            String out = t1.getText();
            a1.setText(a1.getText()+"\n\t\t\t"+out);
            dout.writeUTF(out);
            t1.setText("");
        }catch(Exception e){

        }

    }

    public static void main(String[] args) {
        new Client().setVisible(true);


        try{

            s = new Socket("127.0.0.1", 6001);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            String msgInput = "";

            msgInput = din.readUTF();
            a1.setText(a1.getText()+"\n"+msgInput);

        }catch(Exception e){}
    }

}
