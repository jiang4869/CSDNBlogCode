package druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {
    @Test
    public void demo1() throws Exception {
        Properties pro = new Properties();
        //1.读取配置文件
        InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);
        //2.获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //3.获取连接
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testMaxConnect() throws Exception {
        Properties pro = new Properties();
        //1.读取配置文件
        InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);
        //2.获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //3.获取连接
        for (int i = 1; i <= 11; i++) {
            Connection conn = ds.getConnection();
            System.out.println(i + ":" + conn);
        }
    }

    @Test
    public void testMaxConnect1() throws Exception {
        Properties pro = new Properties();
        //1.读取配置文件
        InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);
        //2.获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //3.获取连接
        for (int i = 1; i <= 12; i++) {
            Connection conn = ds.getConnection();
            System.out.println(i + ":" + conn);
            if (i % 5 == 0) {
                conn.close();
            }
        }
    }

}
