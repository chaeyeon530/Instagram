import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Following extends JFrame {
    private JButton followerBt;
    private JButton followingBt;
    Following(String userid){
        System.out.println("Following: " + userid);
        JFrame frm = new JFrame("Follower");
        frm.setSize(700,1000);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);

        Container contentPane = getContentPane();
        JPanel upper = new JPanel();
        upper.setBackground(Color.WHITE);

        JLabel id = new JLabel(userid,SwingConstants.CENTER);
        Font font = new Font(userid, Font.BOLD,20);
        id.setFont(font);
        //profile_edit.setBounds(20,0,660,50);
        id.setPreferredSize(new Dimension(660, 50));

        followerBt = new JButton("Follower");
        followerBt.setBounds(350,50,300,70);
        followerBt.setBackground(Color.WHITE);
        followerBt.setPreferredSize(new Dimension(300, 70));

        followingBt = new JButton("Following");
        followingBt.setBounds(500,50,300,70);
        followingBt.setBackground(Color.WHITE);
        followingBt.setPreferredSize(new Dimension(300, 70));

        upper.add(id);
        upper.add(followerBt);
        upper.add(followingBt);

        upper.setPreferredSize(new Dimension(700, 130));

        Following_read a  = new Following_read(userid);
        String[] arr;
        arr = a.getArray();
        int n = a.getN();

        System.out.println(n);
        System.out.println(arr);

        JPanel JP = new JPanel();
        JP.setPreferredSize(new Dimension(700, 130));

        JLabel space = new JLabel();
        space.setPreferredSize(new Dimension(500, 10));

        JButton[] bt = new JButton[n];

        for(int i=0; i<n;i++){
            bt[i] = new JButton(arr[i]);
            bt[i].setPreferredSize(new Dimension(500, 100));
            bt[i].setBackground(Color.WHITE);
            JP.add(bt[i]);
        }


        /*
        JButton button1 = new JButton("Button1");
        button1.setPreferredSize(new Dimension(500, 100));
        JP.add(button1);
        JP.add(space);

        JButton button2= new JButton("Button2");
        button2.setPreferredSize(new Dimension(500, 100));
        JP.add(button2);
        JP.add(space);

        JButton button3= new JButton("Button3");
        button3.setPreferredSize(new Dimension(500, 100));
        JP.add(button3);
        JP.add(space);
*/
        JPanel center = new JPanel();
        center.setBackground(Color.WHITE);

        JScrollPane scrollPane =  new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(700,900));

        scrollPane.setViewportView(JP);//여기가 포인트

        center.add(scrollPane);

        center.setPreferredSize(new Dimension(700, 770));

        JPanel bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
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

        bottomleft.setBackground(Color.WHITE);
        bottomcenter.setBackground(Color.WHITE);
        bottomright.setBackground(Color.WHITE);

        bottom.add(bottomleft);
        bottom.add(bottomcenter);
        bottom.add(bottomright);

        contentPane.add(upper, BorderLayout.NORTH);
        contentPane.add(center, BorderLayout.CENTER);
        contentPane.add(bottom, BorderLayout.SOUTH);

        frm.add(contentPane);
        frm.setVisible(true);

        followerBt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Follower(userid);
                frm.setVisible(false);
            }
        });

    }

    public static void main(String[] args){
        String userid="";
        new Following("database12");
    }
}


