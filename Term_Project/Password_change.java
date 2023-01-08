import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Password_change extends JFrame{
    InstaDAO dao = new InstaDAO();

    Password_change(String id){


        JLabel title = new JLabel("Instagram", JLabel.CENTER);
        title.setBounds(0, 50, 700, 50);
        Font font = new Font("Instagram", Font.BOLD, 40);
        title.setFont(font);

        JLabel pwdLabel = new JLabel("new Password : ");
        pwdLabel.setBounds(180, 250, 150, 30);

        JPasswordField pwdTf = new JPasswordField();
        pwdTf.setBounds(280, 250, 200, 30);

        JLabel pwdreLabel = new JLabel("Password check : ");
        pwdreLabel.setBounds(160, 350, 170, 30);

        JPasswordField pwdreTf = new JPasswordField();
        pwdreTf.setBounds(280, 350, 200, 30);

        JButton finishBt = new JButton("Finish");
        finishBt.setBounds(250, 650, 150, 30);
        finishBt.setBackground(Color.WHITE);

        add(title);
        add(pwdLabel);
        add(pwdTf);
        add(pwdreLabel);
        add(pwdreTf);
        add(finishBt);

        setTitle("Password change");
        setSize(700, 1000);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setVisible(true);

        finishBt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Modified!");
            }
        });

        finishBt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                setVisible(false);
            }
        });

    }

    public static void main(String[] args) {
        String userid = "database12";
        new Password_change(userid);
    }
}


