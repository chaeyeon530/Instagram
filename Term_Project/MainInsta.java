import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainInsta extends JFrame {
    MainInsta(String userid){
        JFrame frm = new JFrame("Main"); // 전체 프레임
        frm.setSize(700,1000);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);

        Container contentPane = getContentPane();

        // 상단 패널 ----------------------------------------------------------------------------------------
        // 상단 패널 만들기
        JPanel upper = new JPanel();
        upper.setPreferredSize(new Dimension(700,100)); // 사이즈
        // 세 칸으로 나누기
        JPanel upperleft = new JPanel();
        JPanel uppercenter = new JPanel();
        JPanel upperright = new JPanel();
        // 각각 사이즈
        upperleft.setPreferredSize(new Dimension(300,90)); // 로고 넣을 패널
        uppercenter.setPreferredSize(new Dimension(280,90));
        upperright.setPreferredSize(new Dimension(120,90)); // 게시물 추가 버튼 넣을 패널
        upperleft.setBounds(0,0,300,90);
        // 좌측 - 로고 만들고 추가
        JLabel up = new JLabel("Smartstagram");
        Font font = new Font("Sefif", Font.BOLD, 20);
        up.setFont(font);
        up.setPreferredSize(new Dimension(300, 80));
        up.setBounds(0,0,300,80);
        upperleft.add(up);
        upper.add(upperleft);
        // 중앙 - 빈 패널
        upper.add(uppercenter);
        // 우측 - 게시물 추가 버튼 만들고 추가
        JButton addbutton = new JButton();
        addbutton.setPreferredSize(new Dimension(80, 80));
        addbutton.setBackground(Color.WHITE);
        ImageIcon addIcon = new ImageIcon(MainInsta.class.getResource("bottom_nav_upload_icon.jpg"));
        Image add_img = addIcon.getImage();
        Image updateAdd = add_img.getScaledInstance(80,80,Image.SCALE_SMOOTH);
        ImageIcon updateAIcon = new ImageIcon(updateAdd);
        addbutton.setIcon(updateAIcon);

        addbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SelectFile(userid);
                frm.setVisible(false);
            }
        });
        upperright.add(addbutton);
        upper.add(upperright);

        // 중앙 패널 ----------------------------------------------------------------------------------------
        // 중앙 패널 만들기 (피드 부분)
        JPanel center = new JPanel();
        center.setPreferredSize(new Dimension(700,800));

        // 하단 패널 ----------------------------------------------------------------------------------------
        // 하단 패널 만들기
        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(700,100));
        // 세 칸으로 나누기
        JPanel bottomleft = new JPanel();
        JPanel bottomcenter = new JPanel();
        JPanel bottomright = new JPanel();
        // 각 패널 사이즈
        bottomleft.setPreferredSize(new Dimension(215,90));
        bottomcenter.setPreferredSize(new Dimension(215,90));
        bottomright.setPreferredSize(new Dimension(215,90));
        // 버튼 세 개 만들고 패널에 추가
        JButton home_button = new JButton();
        home_button.setPreferredSize(new Dimension(205, 80));
        home_button.setBackground(Color.WHITE);
        ImageIcon homeIcon = new ImageIcon(MainInsta.class.getResource("bottom_nav_home_on_icon.jpg"));
        Image home_img = homeIcon.getImage();
        Image updateHome = home_img.getScaledInstance(80,80,Image.SCALE_SMOOTH);
        ImageIcon updateHIcon = new ImageIcon(updateHome);
        home_button.setIcon(updateHIcon);

        JButton search_button = new JButton();
        search_button.setPreferredSize(new Dimension(205, 80));
        search_button.setBackground(Color.WHITE);
        ImageIcon searchIcon = new ImageIcon(MainInsta.class.getResource("bottom_nav_search_off_icon.jpg"));
        Image search_img = searchIcon.getImage();
        Image updateSearch = search_img.getScaledInstance(80,80,Image.SCALE_SMOOTH);
        ImageIcon updateSIcon = new ImageIcon(updateSearch);
        search_button.setIcon(updateSIcon);

        JButton profile_button = new JButton();
        profile_button.setPreferredSize(new Dimension(205, 80));
        profile_button.setBackground(Color.WHITE);
        ImageIcon profileIcon = new ImageIcon(MainInsta.class.getResource("default_image.png"));
        Image profile_img = profileIcon.getImage();
        Image updateProfile = profile_img.getScaledInstance(80,80,Image.SCALE_SMOOTH);
        ImageIcon updatePIcon = new ImageIcon(updateProfile);
        profile_button.setIcon(updatePIcon);

        bottomleft.add(home_button);
        bottomcenter.add(search_button);
        bottomright.add(profile_button);

        bottomleft.setBackground(Color.WHITE);
        bottomcenter.setBackground(Color.WHITE);
        bottomright.setBackground(Color.WHITE);

        bottom.add(bottomleft);
        bottom.add(bottomcenter);
        bottom.add(bottomright);


        home_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainInsta(userid);
                frm.setVisible(false);
            }
        });

        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(userid);
                frm.setVisible(false);
            }
        });

        profile_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new my_profile(userid);
                frm.setVisible(false);
            }
        });
        // -----------------------------------------------------------------------------------------------------
        // 모든 패널 배경색 흰색으로 설정
        upper.setBackground(Color.WHITE);
        upperleft.setBackground(Color.WHITE);
        uppercenter.setBackground(Color.WHITE);
        upperright.setBackground(Color.WHITE);

        center.setBackground(Color.WHITE);

        bottom.setBackground(Color.WHITE);
        bottomleft.setBackground(Color.WHITE);
        bottomcenter.setBackground(Color.WHITE);
        bottomright.setBackground(Color.WHITE);

        // 모든 패널 프레임에 추가
        contentPane.add(upper, BorderLayout.NORTH);
        contentPane.add(center, BorderLayout.CENTER);
        contentPane.add(bottom, BorderLayout.SOUTH);

        frm.add(contentPane);

        frm.setVisible(true);
    }


    public static void main(String[] args) {
    }

}
