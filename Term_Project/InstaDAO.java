import javax.security.auth.login.FailedLoginException;
import java.sql.*;
import java.util.Vector;

public class InstaDAO {
    private Connection con = null;
    private PreparedStatement ps = null;
    ResultSet rs;
    //InstaDTO dto = new InstaDTO();
    int cmd = 0;
    public static final int NONE = 0;
    public static final int ADD = 1;
    public static final int DEL = 2;
    public static final int FIND = 3;
    public static final int ALL = 4;

    /*public InstaDAO(){
        con = DBCon.getConnection();
        System.out.println("DB연결:"+con);
    }*/

    //회원가입시 멤버 추가
    /*public int insertMember(InstaDTO dto) throws SQLException{

    }*/

    //비밀번호 찾기
    /*public String searchPw(String id){

    }*/
    public void passwordChange(String user_id, String passWord){
        Connection con = null;
        PreparedStatement pstmt = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/modeldb";
        String user = "root", pw = "1234";
        String SQL = "UPDATE user SET password = ? WHERE user_id=?";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pw);
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, passWord);
            pstmt.setString(2,user_id);
            pstmt.executeUpdate();
            /*if (rs.next()) {
                if (email.equals(rs.getString("email"))) {

                }
            }*/
        }  catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e){ }
            }
            if (pstmt != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (con != null){
                try {
                    rs.close();
                } catch(Exception e){}
            }
        }
    }
    public boolean login2(String user_id, String email, String phone){
        boolean b = false;
        //데이터베이스의 user table의 id, email, phonenumber와 비교하여
        //둘다 일치하면 true, 아니면 false 리턴
        Connection con = null;
        PreparedStatement pstmt = null;
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/modeldb";
        String user = "root", pw = "1234";
        String SQL = "SELECT email, phone FROM user WHERE user_id=?";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pw);
            pstmt = con.prepareStatement(SQL);
            pstmt.setString(1, user_id);
            rs = pstmt.executeQuery();
            if (rs.next()){
                if (email.equals(rs.getString("email"))){
                    if (phone.equals(rs.getString("phone"))){
                        b = true;
                    } else{
                        b = false;
                    }
                } else {
                    b = false;
                }
            } else {
                b = false;
            }
        }  catch(ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        } /*catch (FailedLoginException e) {
            throw new RuntimeException(e);
        } */finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e){ }
            }
            if (pstmt != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                }
            }
            if (con != null){
                try {
                    rs.close();
                } catch(Exception e){}
            }
        }
        return b;
    }
}
