import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Profile_modify extends JFrame{

    JLabel imageLabel = new JLabel();
    Toolkit toolkit = getToolkit();
    private PreparedStatement pstmt1;
    //private PreparedStatement pstmt2;

    private Connection conn;

    private int rs;

    String Name="";
    String ID="";
    String Intro="";

    String fileName;
    String filepath;
    Profile_modify(String userid){
        System.out.println("Profile_modify: " + userid);

        JLabel title = new JLabel("Instagram", JLabel.CENTER);
        title.setBounds(0,50,700,50);
        Font font = new Font("Instagram", Font.BOLD,40);
        title.setFont(font);

        getContentPane().setBackground(Color.WHITE);

        JLabel profileLabel = new JLabel("Profile Image ");
        imageLabel.setBounds(200,250,130,30);

        JButton imageBt = new JButton("Select Image");
        imageBt.setBounds(300, 250, 200, 30);
        imageBt.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name : ");
        nameLabel.setBounds(200,500,80,30);

        JTextField nameTf = new JTextField();
        nameTf.setBounds(300,500,200,30);

        JLabel idLabel = new JLabel("ID : ");
        idLabel.setBounds(200,600,80,30);

        JTextField idTf = new JTextField();
        idTf.setBounds(300,600,200,30);

        JLabel introduceLabel = new JLabel("Introduce : ");
        introduceLabel.setBounds(200,700,80,30);

        JTextField introduceTf = new JTextField();
        introduceTf.setBounds(300,700,200,30);

        JButton finishBt = new JButton("Finish");
        finishBt.setBounds(270, 800, 150, 30);
        finishBt.setBackground(Color.WHITE);

        add(imageLabel);
        add(title);
        add(profileLabel);
        add(imageBt);
        add(nameLabel);
        add(nameTf);
        add(idLabel);
        add(idTf);
        add(introduceLabel);
        add(introduceTf);
        add(finishBt);

        setTitle("Profile modify");
        setSize(700,1000);
        setLayout(null);
        setVisible(true);

        finishBt.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Name = nameTf.getText();
                ID = idTf.getText();

                Intro = introduceTf.getText();

                if(Name==null || ID==null || Intro==null){
                    JOptionPane.showMessageDialog(null, "Please enter all values!");

                }
                else{
                    JOptionPane.showMessageDialog(null, "Modified!");

                }
            }
        });

        imageBt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Select_Image a = new Select_Image(userid);
                filepath = a.getFilePath();
                fileName = a.getFileName();
                setVisible(false);
            }
        });

        finishBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Name = nameTf.getText();
                //ID = idTf.getText();

                //Intro = introduceTf.getText();

                String SQL1 = "update user set name = ? , user_id = ?, introduce = ?, profile_image=? where user_id=?";

                try{
                    pstmt1 = conn.prepareStatement(SQL1);
                    pstmt1.setString(1, Name);
                    pstmt1.setString(2, ID);
                    pstmt1.setString(3, Intro);
                    pstmt1.setString(4, fileName);
                    pstmt1.setString(5, userid);

                    //쿼리 실행
                    rs = pstmt1.executeUpdate();


                } catch(Exception e1){
                    e1.printStackTrace();
                }

                new my_profile(userid);
                setVisible(false);

            }
        });

        try{
            String dbURL = "jdbc:mysql://localhost/modeldb";
            String dbID = "root";
            String dbPW = "1234";

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(dbURL, dbID, dbPW);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args){
        String userid="";
        //String Path="";
        //String Name="";
        new Profile_modify(userid);
    }
}
