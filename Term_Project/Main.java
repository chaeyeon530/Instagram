import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
        public static void main(String[] args) {
                Connection con = null;
                PreparedStatement pstmt = null;

                String driver = "com.mysql.cj.jdbc.Driver";

                String url = "jdbc:mysql://localhost/modeldb";
                String user = "root", pw = "1234";

                String SQL = "insert into following( following_id, user_id) values(?,?)";

                try {
                        Class.forName(driver);

                        con = DriverManager.getConnection(url, user, pw);

                        pstmt = con.prepareStatement(SQL);

                        pstmt.setString(1, "yahoo");
                        pstmt.setString(2,"yerimming");

                        int r = pstmt.executeUpdate();

                        //System.out.println(con);
                } catch(ClassNotFoundException e) {
                        e.printStackTrace();
                }catch(SQLException e){
                        e.printStackTrace();
                }

                try{
                        if(con!=null && !con.isClosed())
                                con.close();
                }catch(SQLException e){
                        e.printStackTrace();
                }
        }

}