package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTest {
    PreparedStatement pstmt = null;
    Connection connection = null;
    ResultSet resultSet = null;

    @Before
    public void init() throws SQLException {
        connection = JDBCUtils.getConnection();

    }

    @After
    public void close() {
        JDBCUtils.close(resultSet, pstmt, connection);
    }

    @Test
    public void testInsert() {
        try {
            String sql = "insert into userInfo(name,email) value (?,?)";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "conan");
            pstmt.setString(2, "123461278@qq.com");
            int cnt = pstmt.executeUpdate();
            System.out.println(cnt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        try {
            String sql = "delete from userInfo where name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "conan");

            int cnt = pstmt.executeUpdate();
            System.out.println(cnt);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate() {
        try {
            String sql = "update userInfo set name=? where id=5";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "conan");
            int cnt = pstmt.executeUpdate();
            System.out.println(cnt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        try {
            String sql = "select * from userInfo where name=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "zhangsan");
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("id: " + id + " name: " + name + " email: " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTransaction() {
        try {
            /*关闭自动提交事务*/
            connection.setAutoCommit(false);
            String sql = "update userInfo set email=? where name=";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "1");
            pstmt.setString(1, "zhangsan");
            /*手动制造错误*/
            int num=1/0;
            int cnt = pstmt.executeUpdate();
            System.out.println(cnt);
            connection.commit();
        } catch (Exception e) {
            try {
                if(connection!=null){
                    connection.rollback();
                }
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}

