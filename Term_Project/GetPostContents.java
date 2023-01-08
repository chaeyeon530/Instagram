import java.sql.*;

public class GetPostContents {
    String post_content = "";
    GetPostContents(String userid){

        String url = "jdbc:mysql://localhost/modeldb";
        String user = "root", passwd = "1234";

        String sql = "select content from post where user_id=?";

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
                post_content = rs.getString("content");
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

    public String getContent(){
        return post_content;
    }


    public static void main(String[] args) {
/*
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 회원의 ID를 입력하세요.");
        System.out.println(">");

 */
    }

}


