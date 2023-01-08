import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Search extends JFrame {
    InstaDAO dao = new InstaDAO();
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs;
    JFrame frm = new JFrame("Main"); // 전체 프레임
    Container contentPane = getContentPane();
    JPanel upper = new JPanel();
    JPanel center = new JPanel();
    JPanel bottom = new JPanel();
    Search(String userid){

        frm.setSize(700,1000);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);
        frm.getContentPane().setBackground(Color.WHITE);


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

        searchButton.addActionListener(new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                String searchid = searchId.getText();
                search(userid, searchid);
            }
        });

        // 중앙 패널 ----------------------------------------------------------------------------------------
        // 중앙 패널 만들기 (피드 부분)

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
    public void search(String userid, String searchId){
        //id 검색시 거기에 해당하는 id로 시작하는 사람들 뜨게 하기
        String[] arr = new String[100];
        int i = 0;

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/modeldb";
        String user = "root", pw = "1234";
        String SQL = "SELECT user_id FROM user WHERE user_id like ?";

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pw);
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, "%"+searchId+"%");
            rs = pstmt.executeQuery();

            while (rs.next()){
                arr[i] = rs.getString("user_id");
                //출력해보기
                //System.out.println(arr[i]);
                //버튼 생성
                JButton user_button = new JButton(arr[i]);
                user_button.setPreferredSize(new Dimension(200,200));
                center.add(user_button);
                i++;
            }
            new Search_result(userid,arr, i);
            frm.setVisible(false);

            /*contentPane.add(center);
            frm.add(contentPane);
            setVisible(true);*/

            /*JButton user_button = new JButton("dd");
            user_button.setPreferredSize(new Dimension(500,200));
            center.add(user_button);*/
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (pstmt != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (con != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
        }
    }


    public static void main(String[] args) {
        new Search("yerimming");
    }
}


