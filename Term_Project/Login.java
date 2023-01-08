import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame{

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    Login(){

        JFrame frm = new JFrame("Instagram");
        frm.setSize(700,1000);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);
        frm.getContentPane().setLayout(null);
        frm.getContentPane().setBackground(Color.WHITE);

        JLabel title = new JLabel("Instagram", JLabel.CENTER);
        title.setBounds(0, 70, 700, 50);
        JLabel idL = new JLabel("ID");
        idL.setBounds(200,300, 80,30);

        JTextField idF = new JTextField();
        idF.setBounds(300,300, 200,30);

        JLabel pwL = new JLabel("Password");
        pwL.setBounds(200,350, 80,30);

        JPasswordField pwF = new JPasswordField();
        pwF.setBounds(300,350,200,30);

        JButton bt1 = new JButton("Login");
        bt1.setBounds(270,450, 150,30);
        bt1.setBackground(Color.WHITE);

        JButton bt2 = new JButton("Forgot Password?");
        bt2.setBounds(200,700, 140,30);
        bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new pwd();
                frm.setVisible(false);
            }
        });
        bt2.setBackground(Color.WHITE);

        JButton bt3 = new JButton("Create Account");
        bt3.setBounds(350,700, 140,30);
        bt3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUp();
                frm.setVisible(false);
            }
        });
        bt3.setBackground(Color.WHITE);

        frm.add(title);
        frm.add(idL);
        frm.add(idF);
        frm.add(pwL);
        frm.add(pwF);
        frm.add(bt1);
        frm.add(bt2);
        frm.add(bt3);

        setTitle("Instagram Login");
        setLayout(null);
        frm.setVisible(true);

        try{
            String dbURL = "jdbc:mysql://localhost/modeldb";
            String dbID = "root";
            String dbPW = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(dbURL, dbID, dbPW);

        } catch (Exception e) {
            e.printStackTrace();
        }

        bt1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userid = new String(idF.getText());
                String pw = new String(pwF.getPassword());
                System.out.println("입력된 id: " + userid);
                System.out.println("입력된 pw: " + pw);

                if(userid.equals("") || pw.equals("")){
                    JOptionPane.showMessageDialog(null, "Enter ID and Password!");
                }
                else {
                    int result = login(userid, pw);
                    if (result == 1) { // 성공
                        System.out.println("로그인 성공: " + userid);
                        JOptionPane.showMessageDialog(null, userid + ",\nLogin Success!");
                        new MainInsta(userid);
                        frm.setVisible(false);
                    } else if (result == 0) {
                        JOptionPane.showMessageDialog(null, "Wrong Password!");
                        pwF.requestFocus();
                        System.out.println("비번 틀림");
                    } else if (result == -1) {
                        JOptionPane.showMessageDialog(null, "ID doesn't exist!");
                        idF.requestFocus();
                        System.out.println("아이디 존재하지 않음");
                        // 아이디 틀림
                    } else if (result == -2) {
                        JOptionPane.showMessageDialog(null, "Database Error!");
                        System.out.println("데베 오류");
                        // 데이터베이스 오류 발생
                    }
                }
            }
        });
    }

    public int login(String userID, String userPW){
        String SQL = "SELECT password FROM USER WHERE user_id=?";
        try{
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userID);
            //쿼리 실행
            rs = pstmt.executeQuery();
            if (rs.next()) { // 다음 것이 있나? true/false로 반환
                if(rs.getString(1).contentEquals(userPW)) {
                    //sql로 실행한 비번과 접속시도한 비번이 맞으면 로그인 성공
                    return 1; // 로그인 성공
                } else {
                    return 0; // 비밀번호 불일치
                }
            }
            return -1; // 아이디가 없음
        } catch(Exception e){
            e.printStackTrace();
        }
        return -2; // 데이터베이스 오류 의미
    }

    public static void main(String[] args) {
        new Login();
    }
}