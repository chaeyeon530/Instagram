import java.sql.*;

public class GetPostImage {
    String post_image = "";
    GetPostImage(String userid, String postid){

        String url = "jdbc:mysql://localhost/modeldb";
        String user = "root", passwd = "1234";

        String sql = "select image_id from post where post_id=? and user_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, postid);
            pstmt.setString(2, userid);

            rs = pstmt.executeQuery();

            //배열을 만들어서 follower_id 값을 저장


            while (rs.next()) {
                post_image = rs.getString("image_id");
            }
            System.out.println(post_image);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getImage(){
        return post_image;
    }

    public static void main(String[] args) {
        new GetPostImage("database12", "0");
    }

}

