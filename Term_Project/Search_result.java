import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Search_result extends JFrame {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs;
    JFrame frm = new JFrame("Search"); // 전체 프레임
    Container contentPane = getContentPane();
    JPanel upper = new JPanel();
    JPanel center = new JPanel();
    JPanel bottom = new JPanel();
    Search_result(String userid, String[] arr, int i){

        frm.setSize(700,1000);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);

        // 상단 패널 ----------------------------------------------------------------------------------------
        // 상단 패널 만들기
        //검색창

        upper.setPreferredSize(new Dimension(700,100)); // 사이즈
        JTextField searchId = new JTextField("");
        searchId.setPreferredSize(new Dimension(500, 50));  //검색창 사이즈
        //검색 버튼 누르면
        JButton searchButton = new JButton();
        searchButton.setPreferredSize(new Dimension(100, 50));
        searchButton.setBackground(Color.WHITE);
        ImageIcon searchIcon1 = new ImageIcon(MainInsta.class.getResource("bottom_nav_search_off_icon.jpg"));
        Image search_img1 = searchIcon1.getImage();
        Image updateSearch1 = search_img1.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon updateSIcon1 = new ImageIcon(updateSearch1);
        searchButton.setIcon(updateSIcon1);

        upper.add(searchId);
        upper.add(searchButton);
        String searchid = searchId.getText();


        // 중앙 패널 ----------------------------------------------------------------------------------------
        // 중앙 패널 만들기 (피드 부분)
        JPanel JP = new JPanel();
        JP.setPreferredSize(new Dimension(700,130));

        JButton[] bt = new JButton[i];

        for (int j = 0; j < i; j++){
            bt[j] = new JButton(arr[j]);
            bt[j].setPreferredSize(new Dimension(500,100));
            bt[j].setBackground(Color.WHITE);
            JP.add(bt[j]);
        }
        JScrollPane scrollPane =  new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(700,900));
        scrollPane.setBackground(Color.WHITE);

        scrollPane.setViewportView(JP);//여기가 포인트

        center.add(scrollPane);
        center.setPreferredSize(new Dimension(700,800));


        // 하단 패널 ----------------------------------------------------------------------------------------
        // 하단 패널 만들기

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
        ImageIcon homeIcon = new ImageIcon(MainInsta.class.getResource("bottom_nav_home_off_icon.jpg"));
        Image home_img = homeIcon.getImage();
        Image updateHome = home_img.getScaledInstance(80,80,Image.SCALE_SMOOTH);
        ImageIcon updateHIcon = new ImageIcon(updateHome);
        home_button.setIcon(updateHIcon);

        JButton search_button = new JButton();
        search_button.setPreferredSize(new Dimension(205, 80));
        search_button.setBackground(Color.WHITE);
        ImageIcon searchIcon = new ImageIcon(MainInsta.class.getResource("bottom_nav_search_on_icon.jpg"));
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
        bottomleft.add(home_button);
        bottomcenter.add(search_button);
        bottomright.add(profile_button);
        bottom.add(bottomleft);
        bottom.add(bottomcenter);
        bottom.add(bottomright);

        // -----------------------------------------------------------------------------------------------------
        // 모든 패널 배경색 흰색으로 설정
        upper.setBackground(Color.WHITE);

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
        //new Search_result(arr[]);
    }
}


