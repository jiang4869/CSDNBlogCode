package c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Demo {

    @Test
    public void demo1() throws SQLException {
        //1.创建数据库连接池对象
        DataSource ds  = new ComboPooledDataSource();
        //2. 获取连接对象
        Connection conn = ds.getConnection();
        //3. 输出conn信息，不为null则连接成功
        System.out.println(conn);

    }
    @Test
    public  void testMaxConnect() throws SQLException {
        // 1.1 获取DataSource，使用指定名称配置
        DataSource ds  = new ComboPooledDataSource();
        //2.获取连接
        for (int i = 1; i <= 10 ; i++) {
            Connection conn = ds.getConnection();
            System.out.println(i+":"+conn);

        }
    }
    @Test
    public  void testMaxConnect1() throws SQLException {
        // 1.1 获取DataSource，使用指定名称配置
        DataSource ds  = new ComboPooledDataSource();
        //2.获取连接
        for (int i = 1; i <= 12 ; i++) {
            Connection conn = ds.getConnection();
            System.out.println(i+":"+conn);
            if(i%5==0){
                conn.close();
            }
        }
    }
}
