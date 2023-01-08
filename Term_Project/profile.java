import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class profile extends JFrame{

    Connection con = null;
    Connection con2 = null;
    PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost/modeldb";
    String user = "root", pw = "1234";
    //String user_id = "yerimming";
    //String follower_id = "yeye";
    String SQL_insert = "insert into modeldb.follower(follower_id, user_id) values (?,?)";
    String SQL_following = "insert into modeldb.following(following_id, user_id) values (?,?)";
    private JButton postB;
    private JButton followerB;
    private JButton followingB;
    private JButton followerP;


    profile(String user_id, String follower_id){
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
        postB.setHorizontalAlignment(SwingConstants.CENTER);
        postB.setBounds(200,50,150,150);
        postB.setPreferredSize(new Dimension(150, 150));
        postB.setBackground(Color.WHITE);
        upper.add(postB);

        //팔로워 개수, 버튼 누르면 follower 창으로 넘어가게, 가운데 정렬 해야 함
        followerB = new JButton("<html>Follower<br>" + followerN + "</html>");
        followerB.setHorizontalAlignment(SwingConstants.CENTER);
        followerB.setBounds(350,50,150,150);
        followerB.setPreferredSize(new Dimension(150, 150));
        followerB.setBackground(Color.WHITE);
        followerB.setHorizontalTextPosition(JButton.CENTER);
        upper.add(followerB);

        //팔로잉 개수, 버튼 누르면 following 창으로 넘어가게, following 수 불러오기, 가운데 정렬 해야 함
        followingB = new JButton("<html>Following<br>" + followingN + "</html>");
        followingB.setHorizontalAlignment(SwingConstants.CENTER);
        followingB.setBounds(500,50,150,150);
        followingB.setPreferredSize(new Dimension(150, 150));
        followingB.setBackground(Color.WHITE);
        upper.add(followingB);

        followerP = new JButton("follow");
        followerP.setBounds(20, 200, 660, 50);
        followerP.setPreferredSize(new Dimension(660, 40));
        followerP.setBackground(Color.WHITE);
        upper.add(followerP);

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
        followerP.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Class.forName(driver);

                    con = DriverManager.getConnection(url,user,pw);
                    con2 = DriverManager.getConnection(url,user,pw);

                    pstmt = con.prepareStatement(SQL_insert);
                    pstmt2 =con2.prepareStatement(SQL_following);

                    pstmt.setString(1,user_id);
                    pstmt.setString(2,follower_id);

                    pstmt2.setString(1,follower_id);
                    pstmt2.setString(2,user_id);

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

                //follower table에 follower_id가 있는지 확인, user_id는 현재 접속 중인 id임
                select_followerExist b = new select_followerExist(user_id, follower_id);
                String fId = b.getId(); //follower_id를 받아옴
                System.out.println(fId);
                System.out.println(user_id);

                if(fId == user_id){
                    new profile(user_id,follower_id);
                }
                else if (fId != user_id){
                    new profileD(user_id, follower_id);
                }
                frm.setVisible(false);

            }

        });

        home_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainInsta(user_id);
                frm.setVisible(false);
            }
        });

        search_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Search(user_id);
                frm.setVisible(false);
            }
        });

        profile_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new my_profile(user_id);
                frm.setVisible(false);
            }
        });

    }

    public static void main(String[] args) {
        new profile("database12", "databasebb");
    }

}
