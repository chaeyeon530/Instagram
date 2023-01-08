import java.sql.*;

public class select_followerExist {

    String id;

    select_followerExist(String user_id, String follower_id){
        System.out.println(user_id);
        System.out.println(follower_id);

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/modeldb";
        String user = "root", pw = "1234";

        String SQL_exist = "select follower_id from follower where exists (select follower_id from follower where follower_id = ? and user_id = ?) and follower_id = ? and user_id = ?;";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try{
            Class.forName(driver);

            con = DriverManager.getConnection(url,user,pw);
            pstmt = con.prepareStatement(SQL_exist);
            pstmt.setString(1, user_id);
            pstmt.setString(2, follower_id);
            pstmt.setString(3,user_id);
            pstmt.setString(4,follower_id);

            rs = pstmt.executeQuery();

            //System.out.println(pstmt);

            while(rs.next()){
                id = rs.getString("follower_id");
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                pstmt.close();
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String getId(){
        return id;
    }
}


