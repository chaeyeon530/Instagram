import java.sql.*;

public class GetProfileImage {
    String profile_image = "";
    GetProfileImage(String userid){

        String url = "jdbc:mysql://localhost/modeldb";
        String user = "root", passwd = "1234";

        String sql = "select profile_image from user where user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userid);

            rs = pstmt.executeQuery();

            //배열을 만들어서 follower_id 값을 저장

            while (rs.next()) {
                profile_image = rs.getString("profile_image");
            }
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
        return profile_image;
    }

    public static void main(String[] args) {
    }

}


