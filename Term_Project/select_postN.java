import java.sql.*;

public class select_postN {
    String[] post_arr = new String[100];
    int i = 0;

    select_postN(String ID){
        String url = "jdbc:mysql://localhost/modeldb";
        String user = "root", passwd = "1234";

        String sql = "select image_id from post where user_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, ID);

            rs = pstmt.executeQuery();

            //배열을 만들어서 following_id 값을 저장
            while (rs.next()) {
                post_arr[i] = rs.getString("image_id");
                i++;
            }
            System.out.println(post_arr[0]);
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

    public String[] getArray(){
        return post_arr;
    }

    public int getN(){
        return i;
    }

    public static void main(String[] args) {
/*
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 회원의 ID를 입력하세요.");
        System.out.println(">");

 */
    }
}

