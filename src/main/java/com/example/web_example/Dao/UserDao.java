package com.example.web_example.Dao;

import com.example.web_example.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<User> select()
    {
        return jdbcTemplate.query("select *  from users ",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int i)
                            throws SQLException {
                        User u = new User();
                        u.setName(resultSet.getString("username"));
                        u.setPassWord(resultSet.getString("passwd"));
                        return u;
                    }
                });
    }

    public String insert(User user){
        try {
            jdbcTemplate.update("insert into user (name,password) value(?,?)", user.getName(),user.getPassWord());
            return "插入成功";
        }catch(Exception e) {
            return e.toString();
        }
    }
}


