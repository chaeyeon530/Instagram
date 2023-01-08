import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Post extends JFrame {
    JLabel imageLabel1 = new JLabel(); // 선택한 파일 이미지 - 게시물 사진
    JLabel imageLabel2 = new JLabel(); // 선택한 파일 이미지 - 프사
    Toolkit toolkit = getToolkit(); // 이미지를 가져오기 위해서
    Post(String userid){

        GetPostContents getContent = new GetPostContents(userid);
        String postcontent = getContent.getContent();
        //GetPostImage getImage = new GetPostImage(userid, postid);
        //String postimage = getImage.getImage(); // 이미지 경로 불러와야 함
        select_postN getImage = new select_postN(userid);
        String[] arr;
        arr = getImage.getArray();
        GetProfileImage getProfileImage = new GetProfileImage(userid);
        String profileimage = getProfileImage.getImage();

        System.out.println("MainInsta: " + userid);
        JFrame frm = new JFrame("Main"); // 전체 프레임
        frm.setSize(700,1000);
        frm.setLocationRelativeTo(null);
        frm.setResizable(false);

        Container contentPane = getContentPane();
        Font font1 = new Font("Arial", Font.BOLD, 14);

        // 상단 패널 ----------------------------------------------------------------------------------------
        // 상단 패널 만들기
        JPanel upper = new JPanel();
        upper.setPreferredSize(new Dimension(700,100)); // 사이즈
        // 세 칸으로 나누기

        JPanel upperleft = new JPanel();
        JPanel uppercenter = new JPanel();
        JPanel upperright = new JPanel();
        // 각각 사이즈
        upperleft.setPreferredSize(new Dimension(120,90)); // 뒤로가기 버튼 넣을 패널
        uppercenter.setPreferredSize(new Dimension(400,90));
        upperright.setPreferredSize(new Dimension(120,90));
        upperleft.setBounds(0,0,120,90);

        /*JLabel up = new JLabel("Instagram");
        Font font = new Font("Sefif", Font.BOLD, 20);
        up.setFont(font);
        up.setPreferredSize(new Dimension(100, 80));*/

        // 좌측 - 뒤로가기 버튼
        /*
        JButton goback = new JButton("<");
        goback.setPreferredSize(new Dimension(80, 80));
        goback.setBounds(0,0,80,80);
         */
        JButton goback = new JButton();
        goback.setPreferredSize(new Dimension(80, 80));
        goback.setBounds(0,0,80,80);
        ImageIcon gobackIcon = new ImageIcon(MainInsta.class.getResource("back_icon.jpg"));
        Image goback_img = gobackIcon.getImage();
        Image updateGoback = goback_img.getScaledInstance(80,80,Image.SCALE_SMOOTH);
        ImageIcon updateGIcon = new ImageIcon(updateGoback);
        goback.setIcon(updateGIcon);

        goback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new my_profile(userid);
                frm.setVisible(false);
            }
        });
        upperleft.add(goback);
        upper.add(upperleft);
        upper.add(uppercenter);
        upper.add(upperright);

        // 중앙 패널 ----------------------------------------------------------------------------------------
        // 중앙 패널 만들기 (피드 부분)
        JPanel center =  new JPanel();
        center.setPreferredSize(new Dimension(700,800));
        // 상단 ------------------------------------------------------------------
        JPanel centerupper = new JPanel();
        centerupper.setPreferredSize(new Dimension(700,70));

        /*
        JPanel centerupper_proimg = new JPanel(); // 프사 들어가야 함
        Image image3 = toolkit.getImage(profileimage);
        Image image4 = image3.getScaledInstance(55, 55, Image.SCALE_SMOOTH); // 사이즈 조절
        ImageIcon icon = new ImageIcon(image4);
        imageLabel2.setIcon(icon); // 이미지 라벨에 이미지 아이콘 추가
        centerupper_proimg.add(imageLabel2); // upper 패널에 선택된 이미지 나오도록
        centerupper_proimg.setPreferredSize(new Dimension(60, 60));

         */
        //centerupper_proimg.setBackground(Color.PINK);
        imageLabel2.setPreferredSize(new Dimension(55,55));
        ImageIcon profileIcon = new ImageIcon(Post.class.getResource(profileimage));
        Image pimg = profileIcon.getImage();
        Image updatePImg = pimg.getScaledInstance(55,55,Image.SCALE_SMOOTH);
        ImageIcon updatePIcon = new ImageIcon(updatePImg);
        imageLabel2.setIcon(updatePIcon);
        imageLabel2.setBounds(0,0,55,55);

        JLabel id1 = new JLabel(userid);
        id1.setFont(font1);
        id1.setPreferredSize(new Dimension(80, 55));

        JPanel r1 = new JPanel();
        r1.setPreferredSize(new Dimension(530, 60));
        r1.setBackground(Color.WHITE);

        centerupper.add(imageLabel2);
        centerupper.add(id1);
        centerupper.add(r1);



        // 중앙 ------------------------------------------------------------------
        JPanel centermid = new JPanel();
        centermid.setPreferredSize(new Dimension(700,600));
        //centermid.setBackground(Color.RED);

        /*
        JPanel midimage = new JPanel();
        midimage.setPreferredSize(new Dimension(700,530));
        Image image1 = toolkit.getImage(postimage);
        Image image2 = image1.getScaledInstance(690, 520, Image.SCALE_SMOOTH); // 사이즈 조절
        ImageIcon icon2 = new ImageIcon(image2);
        imageLabel1.setIcon(icon2); // 이미지 라벨에 이미지 아이콘 추가
        midimage.add(imageLabel1); // upper 패널에 선택된 이미지 나오도록
         */

        //JPanel midimage = new JPanel();
        //midimage.setPreferredSize(new Dimension(700,530));
        imageLabel1.setPreferredSize(new Dimension(520,520));
        ImageIcon poIcon = new ImageIcon(Post.class.getResource(arr[0]));
        Image poImg = poIcon.getImage();
        Image updatePoImg = poImg.getScaledInstance(520,520,Image.SCALE_SMOOTH);
        ImageIcon updatePoIcon = new ImageIcon(updatePoImg);
        imageLabel1.setIcon(updatePoIcon);
        imageLabel1.setBounds(5,0,520,520);
        //midimage.add(imageLabel1);

        JPanel likecomment = new JPanel();
        likecomment.setPreferredSize(new Dimension(700,60));
        likecomment.setBackground(Color.WHITE);
        //likecomment.setBackground(Color.RED);

        JButton likeBtn = new JButton();
        likeBtn.setPreferredSize(new Dimension(50, 50));
        ImageIcon likeIcon = new ImageIcon(MainInsta.class.getResource("bottom_nav_active_off_icon.jpg"));
        Image like_img = likeIcon.getImage();
        Image updateLike = like_img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon updateLIcon = new ImageIcon(updateLike);
        likeBtn.setIcon(updateLIcon);

        JButton commentBtn = new JButton();
        commentBtn.setPreferredSize(new Dimension(50, 50));
        ImageIcon commentIcon = new ImageIcon(MainInsta.class.getResource("reply_icon.jpg"));
        Image comment_img = commentIcon.getImage();
        Image updateComment = comment_img.getScaledInstance(50,50,Image.SCALE_SMOOTH);
        ImageIcon updateCIcon = new ImageIcon(updateComment);
        commentBtn.setIcon(updateCIcon);

        JPanel r = new JPanel(); // 우측 빈 공간
        r.setPreferredSize(new Dimension(550, 50));
        r.setBackground(Color.WHITE);

        likecomment.add(likeBtn);
        likecomment.add(commentBtn);
        likecomment.add(r);

        //likecomment.setBackground(Color.BLACK);
        centermid.add(imageLabel1);
        centermid.add(likecomment);

        // 하단 ------------------------------------------------------------------
        JPanel centerbottom = new JPanel();
        centerbottom.setPreferredSize(new Dimension(700,70));
        JLabel id2 = new JLabel(userid);
        id2.setFont(font1);
        id2.setPreferredSize(new Dimension(80, 55));
        JLabel content = new JLabel(postcontent);
        Font font2 = new Font("Arial", Font.PLAIN, 13);
        content.setFont(font2);
        content.setPreferredSize(new Dimension(590, 55));

        centerbottom.add(id2);
        centerbottom.add(content);


        //JPanel center = new JPanel();
        //center.setPreferredSize(new Dimension(700,800));
        center.add(centerupper);
        center.add(centermid);
        center.add(centerbottom);

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
        ImageIcon profileIcon2 = new ImageIcon(MainInsta.class.getResource("default_image.png"));
        Image profile_img = profileIcon2.getImage();
        Image updateProfile = profile_img.getScaledInstance(80,80,Image.SCALE_SMOOTH);
        ImageIcon updatePIcon2 = new ImageIcon(updateProfile);
        profile_button.setIcon(updatePIcon2);

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
        upperleft.setBackground(Color.WHITE);
        uppercenter.setBackground(Color.WHITE);
        upperright.setBackground(Color.WHITE);


        center.setBackground(Color.WHITE);
        centerupper.setBackground(Color.WHITE);
        centermid.setBackground(Color.WHITE);
        centerbottom.setBackground(Color.WHITE);

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
        String userid = "database12";
        new Post(userid);
    }
}
