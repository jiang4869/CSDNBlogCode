package com.jxj4869.jdbctempate;

import com.jxj4869.domain.MyClass;
import com.jxj4869.domain.Student;
import com.jxj4869.utils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo {
    /*
     *传统jdbc操作
     */
    @Test
    public void demo1() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        Student s1 = new Student();
        String sql = "select * from student where id=1";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();
        //s1.setCid(resultSet.getInt("cid"));
        s1.setGender(resultSet.getString("gender"));
        s1.setId(resultSet.getInt("id"));
        s1.setName(resultSet.getString("name"));
        s1.setScore(resultSet.getInt("score"));
        System.out.println(s1);
    }


    @Test
    public void testUpdate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "update student set score=? where id=?";
        int cnt = jdbcTemplate.update(sql, 80, 6);
        System.out.println(cnt);

    }

    @Test
    public void testInsert() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "insert into student(cid,name,gender,score) value(?,?,?,?)";
        int cnt = jdbcTemplate.update(sql, 1, "王玉民", "male", 99);
        System.out.println(cnt);
    }

    @Test
    public void testDelete() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "delete from student where id=?";
        int cnt = jdbcTemplate.update(sql, 7);
        System.out.println(cnt);
    }

    /**
     * 按id查询，并封装到Map集合
     */
    @Test
    public void testFindById() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from student where id=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, 1);

        System.out.println(map);
    }

    /**
     * 查询所有，并封装到List集合
     */
    @Test
    public void testFindAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from student";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }

    /**
     * 查询所有，并封装为Student对象的List集合
     */
    @Test
    public void testFindAll1() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select class.cname ,student.* from student ,class where student.cid=class.id";
        List<Student> list = jdbcTemplate.query(sql, new RowMapper<Student>() {
            @Override
            public Student mapRow(ResultSet resultSet, int i) throws SQLException {
                Student s1 = new Student();
                //s1.setCid(resultSet.getInt("cid"));
                s1.setGender(resultSet.getString("gender"));
                s1.setId(resultSet.getInt("id"));
                s1.setName(resultSet.getString("name"));
                s1.setScore(resultSet.getInt("score"));
                MyClass myClass=new MyClass();
                myClass.setCid(resultSet.getInt("cid"));
                myClass.setName(resultSet.getString("cname"));
                s1.setMyClass(myClass);
                return s1;
            }
        });
        for (Student student : list) {
            System.out.println(student);
        }
    }

    /**
     * 查询所有，并封装为Student对象的List集合
     */
    @Test
    public void testFindAll2() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select class.cname ,student.* from student ,class where student.cid=class.id;";
        List<Student> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Student>(Student.class));

        for (Student student : list) {
            System.out.println(student);
        }
    }

    /**
     * 查询总记录数
     */
    @Test
    public void testTotal() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select count(id) from student";
        Long total = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println(total);
    }
}
