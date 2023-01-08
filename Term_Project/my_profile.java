import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class my_profile extends JFrame{

    private JButton postB;
    private JButton followerB;
    private JButton followingB;
    private JButton profile_edit;

    my_profile(String userid){
        System.out.println("my_profile: " + userid);
        JFrame frm = new JFrame("My Profile");
        frm.setSize(700,1000);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);


        Container contentPane = getContentPane();
        JPanel upper = new JPanel();
        upper.setBackground(Color.WHITE);

        JdbcSelect a = new JdbcSelect(userid);

        select_followingN b = new select_followingN(userid);
        int followingN = b.getN();

        select_postN c = new select_postN(userid);
        int postN = c.getN();

        GetProfileImage d = new GetProfileImage(userid);
        String profile_image = d.getImage();
        System.out.println(profile_image);

        JLabel id = new JLabel(userid, SwingConstants.LEFT);
        //profile_edit.setBounds(20,0,660,50);
        id.setPreferredSize(new Dimension(660, 50));

        // image = new JLabel("Image",SwingConstants.CENTER);
        JLabel image = new JLabel();
        image.setPreferredSize(new Dimension(150, 150));

        ImageIcon icon = new ImageIcon(
                profile.class.getResource(profile_image)
        );

        Image img = icon.getImage();
        Image updateImg = img.getScaledInstance(150,150, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(updateImg);

        image.setIcon(updateIcon);
        image.setBounds(0,0,150,150);

        postB = new JButton("<html>Post<br>" + postN + "</html>");
        postB.setBounds(200,50,150,150);
        postB.setPreferredSize(new Dimension(150, 150));
        postB.setBackground(Color.WHITE);

        int followerN = a.getN();
        followerB = new JButton("<html>Follower<br>" + followerN + "</html>");
        followerB.setBounds(350,50,150,150);
        followerB.setPreferredSize(new Dimension(150, 150));
        followerB.setHorizontalTextPosition(JButton.CENTER);
        followerB.setBackground(Color.WHITE);
        followerB.addActionListener(new ActionListener() { // 홈 버튼 누를 시
            @Override
            public void actionPerformed(ActionEvent e) {
                new Follower(userid);
                frm.setVisible(false);
            }
        });
        //followerB.TextAlign = ContentAlignment.MiddleCenter;

        followingB = new JButton("<html>Following<br>" + followingN + "</html>");
        followingB.setBounds(500,50,150,150);
        followingB.setPreferredSize(new Dimension(150, 150));
        followingB.setBackground(Color.WHITE);
        followingB.addActionListener(new ActionListener() { // 홈 버튼 누를 시
            @Override
            public void actionPerformed(ActionEvent e) {
                new Following(userid);
                frm.setVisible(false);
            }
        });

        // up.setPreferredSize(new Dimension(200, 100));
        profile_edit = new JButton("Profile Edit");
        profile_edit.setBounds(20,200,660,50);
        profile_edit.setPreferredSize(new Dimension(660, 40));
        profile_edit.setBackground(Color.WHITE);

        upper.add(id);
        upper.add(image);
        upper.add(postB);
        upper.add(followerB);
        upper.add(followingB);
        upper.add(profile_edit);

        upper.setPreferredSize(new Dimension(700, 250));

        //JPanel center = new JPanel();
        //JTextArea textArea = new JTextArea("연습용");
        JScrollPane scrollPane =  new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(700,650));
        scrollPane.setBackground(Color.WHITE);

        String[] arr;
        arr = c.getArray();
        //System.out.println(arr[0]);

        JButton post = new JButton();
        post.setPreferredSize(new Dimension(230, 230));
        //post.setBackground(Color.WHITE);
        ImageIcon postIcon = new ImageIcon(MainInsta.class.getResource(arr[0]));
        Image post_img = postIcon.getImage();
        Image updatePost = post_img.getScaledInstance(230,230,Image.SCALE_SMOOTH);
        ImageIcon updatePoIcon = new ImageIcon(updatePost);
        post.setIcon(updatePoIcon);
        post.setBounds(5,0,230,230);

        scrollPane.add(post);

        post.addActionListener(new ActionListener() { // 홈 버튼 누를 시
            @Override
            public void actionPerformed(ActionEvent e) {
                new Post(userid);
                frm.setVisible(false);
            }
        });


        //center.add(scrollPane);

        //center.setPreferredSize(new Dimension(700, 650));

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

        home_button.addActionListener(new ActionListener() { // 홈 버튼 누를 시
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainInsta(userid);
                frm.setVisible(false);
            }
        });
        search_button.addActionListener(new ActionListener() { // 검색 버튼 누를 시
            @Override
            public void actionPerformed(ActionEvent e) {
                // 아직 안 만듦
            }
        });
        profile_button.addActionListener(new ActionListener() { // 프로필 버튼 누를 시
            @Override
            public void actionPerformed(ActionEvent e) {
                new my_profile(userid); // 생성자 추가해야 함
                frm.setVisible(false);
            }
        });

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

        profile_edit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Profile_modify(userid);
                frm.setVisible(false);
            }
        });

    }

    public static void main(String[] args) {
        String userid = "dbdb";
        new my_profile(userid);
    }
}



