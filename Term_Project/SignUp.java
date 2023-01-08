import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.sql.*;

public class SignUp extends JFrame{
    Connection con = null;
    PreparedStatement pstmt = null;

    String driver = "com.mysql.cj.jdbc.Driver";

    String url = "jdbc:mysql://localhost/modeldb";
    String user = "root", pw = "1234";

    String SQL = "insert into user(user_id, password, email, phone, gender, birth, name) values (?,?,?,?,?,?,?)";
    SignUp() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Instagram", JLabel.CENTER);
        title.setBounds(0, 70, 700, 50);
        Font Lfont = new Font("Arial", Font.PLAIN, 40);
        title.setFont(Lfont);

        JLabel idL = new JLabel("ID");
        idL.setBounds(200, 200, 80, 30);
        JTextField idF = new JTextField();
        idF.setBounds(300, 200, 200, 30);

        JLabel emailL = new JLabel("e-mail");
        emailL.setBounds(200, 250, 80, 30);
        JTextField emailF = new JTextField();
        emailF.setBounds(300, 250, 200, 30);

        JLabel pwL = new JLabel("Password");
        pwL.setBounds(200, 300, 80, 30);
        JPasswordField pwF = new JPasswordField();
        pwF.setBounds(300, 300, 200, 30);

        JLabel pnL = new JLabel("PhoneNumber");
        pnL.setBounds(200, 350, 85, 30);
        JPasswordField pnF = new JPasswordField();
        pnF.setBounds(300, 350, 200, 30);

        JLabel gL = new JLabel("Gender");
        gL.setBounds(200, 400, 80, 30);
        JPasswordField gF = new JPasswordField();
        gF.setBounds(300, 400, 200, 30);

        JLabel birthL = new JLabel("Birth");
        birthL.setBounds(200, 450, 80, 30);
        JPasswordField birthF = new JPasswordField();
        birthF.setBounds(300, 450, 200, 30);

        JLabel nnameL = new JLabel("Nickname");
        nnameL.setBounds(200, 500, 80, 30);
        JPasswordField nnameF = new JPasswordField();
        nnameF.setBounds(300, 500, 200, 30);

        JButton bt = new JButton("Sign In");
        bt.setBounds(250, 600, 140, 30);
        bt.setBackground(Color.WHITE);

        add(title);
        add(idL);
        add(idF);
        add(emailL);
        add(emailF);
        add(pwL);
        add(pwF);
        add(pnL);
        add(pnF);
        add(gL);
        add(gF);
        add(birthL);
        add(birthF);
        add(nnameL);
        add(nnameF);
        add(bt);

        bt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String ID = idF.getText();
                    String PWD = new String(pwF.getPassword());
                    String Email = emailF.getText();
                    String Phone = new String(pnF.getPassword());
                    String Gender= new String(gF.getPassword());
                    String Birth = new String(birthF.getPassword());
                    String Name= new String(nnameF.getPassword());

                    Class.forName(driver);

                    con = DriverManager.getConnection(url,user,pw);
                    pstmt = con.prepareStatement(SQL);

                    pstmt.setString(1, ID);
                    pstmt.setString(2,PWD);
                    pstmt.setString(3,Email);
                    pstmt.setString(4,Phone);
                    pstmt.setString(5,Gender);
                    pstmt.setString(6,Birth);
                    pstmt.setString(7,Name);

                    int r = pstmt.executeUpdate();

                } catch(SQLException ex){
                    ex.printStackTrace();
                }catch(ClassNotFoundException ex){
                    ex.printStackTrace();
                }

                try{
                    if(con!=null && !con.isClosed())
                        con.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
                new Login();
                setVisible(false);
            }
        });

        setTitle("Join membership");
        setSize(700, 1000);
        setLayout(null);
        setVisible(true);
        getContentPane().setBackground(Color.WHITE);

    }

    public static void main(String[] args){
        new SignUp();
    }

}
