import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class profileD extends JFrame{
    Connection con = null;
    Connection con2 = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/modeldb";
    String user = "root", pw = "1234";
    //String user_id = "yerimming";
    //String follower_id = "yeye";
    String SQL_follower = "delete from modeldb.follower where follower_id = ? and user_id = ?";
    String SQL_following = "delete from modeldb.following where following_id = ? and user_id = ?";
    private JButton postB;
    private JButton followerB;
    private JButton followingB;
    private JButton deleteB;

    profileD(String userid, String follower_id){
        //follower table에서 follower의 수를 받아옴
        JdbcSelect a = new JdbcSelect(follower_id);
        int followerN = a.getN();

        select_followingN b = new select_followingN(follower_id);
        int followingN = b.getN();

        select_postN c = new select_postN(follower_id);
        int postN = c.getN();

        JFrame frm = new JFrame("Profile");
        frm.setSize(700,1000);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);

        Container contentPane = getContentPane();
        //위: 현 프로필 유저 id, image, post, follower, following을 뜨게 함
        JPanel upper = new JPanel();
        upper.setPreferredSize(new Dimension(700, 250));
        upper.setBackground(Color.WHITE);

        //follower_id를 표시
        JLabel id = new JLabel(follower_id,SwingConstants.LEFT);
        id.setPreferredSize(new Dimension(660, 50));
        upper.add(id);

        //팔로워의 프로필 이미지 표시
        JLabel image = new JLabel();
        image.setPreferredSize(new Dimension(150, 150));
        ImageIcon icon = new ImageIcon(
                profile.class.getResource("cat.jpg")
        );
        Image img = icon.getImage();
        Image updateImg = img.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);

        image.setIcon(updateIcon);
        image.setBounds(0,0,150,150);
        upper.add(image);

        //포스트 개수, 버튼 말고 라벨로 해도 될 듯
        postB = new JButton("<html>Post<br>" + postN + "</html>");
        postB.setBounds(200,50,150,150);
        postB.setPreferredSize(new Dimension(150, 150));
        postB.setBackground(Color.WHITE);
        upper.add(postB);

        //팔로워 개수, 버튼 누르면 follower 창으로 넘어가게, 가운데 정렬 해야 함
        followerB = new JButton("<html>Follower<br>" + followerN + "</html>");
        followerB.setBounds(350,50,150,150);
        followerB.setPreferredSize(new Dimension(150, 150));
        followerB.setHorizontalTextPosition(JButton.CENTER);
        followerB.setBackground(Color.WHITE);
        upper.add(followerB);

        //팔로잉 개수, 버튼 누르면 following 창으로 넘어가게, following 수 불러오기, 가운데 정렬 해야 함
        followingB = new JButton("<html>Following<br>" + followingN + "</html>");
        followingB.setBounds(500,50,150,150);
        followingB.setPreferredSize(new Dimension(150, 150));
        followingB.setBackground(Color.WHITE);
        upper.add(followingB);

         deleteB = new JButton("Delete");
         deleteB.setBounds(20, 200, 660, 50);
         deleteB.setPreferredSize(new Dimension(660, 40));
         deleteB.setBackground(Color.WHITE);
         upper.add(deleteB);

        //가운데: 게시물들 뜨게 하는 스크롤 창
        JScrollPane scrollPane =  new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(700,650));

        //아래: 버튼 3개
        JPanel bottom = new JPanel();
        bottom.setPreferredSize(new Dimension(700,100));
        bottom.setBackground(Color.WHITE);
        // 세 칸으로 나누기
        JPanel bottomleft = new JPanel();
        JPanel bottomcenter = new JPanel();
        JPanel bottomright = new JPanel();

        bottomleft.setBackground(Color.WHITE);
        bottomright.setBackground(Color.WHITE);
        bottomcenter.setBackground(Color.WHITE);
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
        bottom.add(bottomleft);
        bottom.add(bottomcenter);
        bottom.add(bottomright);

        contentPane.add(upper, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(bottom, BorderLayout.SOUTH);

        frm.add(contentPane);
        frm.setVisible(true);

        //팔로우하는 버튼 누르면 follower table에 값 추가하기
        deleteB.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Class.forName(driver);

                    con = DriverManager.getConnection(url,user,pw);
                    con2 = DriverManager.getConnection(url,user,pw);

                    pstmt = con.prepareStatement(SQL_follower);
                    pstmt2 =con2.prepareStatement(SQL_following);

                    pstmt.setString(1,userid);
                    pstmt.setString(2,follower_id);

                    pstmt2.setString(1,follower_id);
                    pstmt2.setString(2,userid);

                    System.out.println(pstmt);
                    System.out.println(pstmt2);

                    int r = pstmt.executeUpdate();
                    int r2 = pstmt2.executeUpdate();
                }catch(SQLException ex){
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

                try{
                    if(con2!=null && !con2.isClosed())
                        con2.close();
                }catch(SQLException ex){
                    ex.printStackTrace();
                }

                select_followerExist b = new select_followerExist(userid, follower_id);
                String fId = b.getId(); //follower_id를 받아옴
                System.out.println(fId);
                System.out.println(userid);
                if(fId != userid){
                    new profile(userid,follower_id);
                }
                else if(fId == userid){
                    new profileD(userid,follower_id);
                }
                frm.setVisible(false);

            }

        });

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

    }

    public static void main(String[] args) {
        new profileD("database12","databasebb");
    }
}
