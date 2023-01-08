import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class pwd extends JFrame{
    InstaDAO dao = new InstaDAO();
    //InstaDTO dto = new InstaDTO();
    JTextField Idtext;
    JTextField mailtext;
    JTextField phonetext;

    pwd(){
        setTitle("Pwd Finder");
        setSize(700,1000);
        getContentPane().setBackground(Color.WHITE);

        JLabel IdLabel = new JLabel("ID");
        IdLabel.setBounds(200,300,80,30);
        add(IdLabel);
        Idtext = new JTextField();
        Idtext.setBounds(300,300,200,30);
        add(Idtext);

        JLabel mailLabel = new JLabel("E-mail");
        mailLabel.setBounds(200,350,80,30);
        add(mailLabel);

        mailtext = new JTextField(20);
        mailtext.setBounds(300,350,200,30);
        add(mailtext);

        JLabel phoneLabel = new JLabel("Phone number");
        phoneLabel.setBounds(180,400,100,30);
        add(phoneLabel);

        phonetext = new JTextField(20);
        phonetext.setBounds(300,400,200,30);
        add(phonetext);

        JButton button = new JButton("NEXT");
        button.setBounds(270,500,150,30);
        button.setBackground(Color.WHITE);
        add(button);

        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //변경
                insta_search();
                //new Password_change();
                //setVisible(false);
            }
        });
    }

    public static void main(String[] args){

        new pwd();
    }

    public void insta_search(){
        String id = Idtext.getText();
        // String arr = dao.searchPw(id)
        String mail = mailtext.getText();
        String phonenum = phonetext.getText();

        if (Idtext.getText().length() == 0){
            JOptionPane.showMessageDialog(this, "아이디 입력 필수!");
            Idtext.requestFocus();
            //return;
        } else if (mailtext.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "e-mail 입력 필수");
            mailtext.requestFocus();
            //return;
        } else if (phonetext.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "phone number 입력 필수");
            phonetext.requestFocus();
            //return;
        }
        //MemberDAO 인스턴스 가져오기
            /*try {
                boolean result = dao.login2(id, mail, phonenum);
                if (result) {
                    JOptionPane.showMessageDialog(this, "새 비밀번호를 입력해주세요");
                }
            } catch(FailedLoginException e){
                JOptionPane.showMessageDialog(this, e.getMessage());
            }*/
        boolean result = dao.login2(id, mail, phonenum);
        if (result) {
            //dto.setId(id);
            JOptionPane.showMessageDialog(this, "새 비밀번호를 입력해주세요");
            new Password_change(id);
            setVisible(false);
        }
        else{
            JOptionPane.showMessageDialog(this, "일치하는 정보가 없습니다");
        }
    }


}
