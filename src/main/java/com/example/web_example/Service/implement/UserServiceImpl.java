package com.example.web_example.Service.implement;

import com.example.web_example.Dao.UserDao;
import com.example.web_example.Domain.User;
import com.example.web_example.Domain.WebResult;
import com.example.web_example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: web_example
 * @BelongsPackage: com.example.web_example.Service.implement
 * @Author: keer
 * @CreateTime: 2020-01-10 09:53
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    /**
     * 数据库方法，进入Dao层
     *
     * @param user
     * @return
     */
    @Override
    public WebResult addUser(User user) {
        WebResult webResult = new WebResult();
        if (userDao.insert(user) == 1) {
            webResult.setMessage("插入成功！");
            webResult.setData(1);
            webResult.setStatus(WebResult.SUCCESS);
        } else {
            webResult.setMessage("插入失败！");
            webResult.setData(0);
            webResult.setStatus(WebResult.ERROR);
        }

        return webResult;
    }

    /**
     * 查询数据库表User信息
     *
     * @return
     */
    @Override
    public WebResult get() {
        WebResult webResult = new WebResult();
        List<User> list = userDao.select();
        webResult.setData(list);
        if (list.size() == 0) {
            webResult.setMessage("查询成功！");
            webResult.setStatus(WebResult.SUCCESS);
        } else {
            webResult.setMessage("查询失败！");
            webResult.setStatus(WebResult.ERROR);
        }
        return webResult;
    }


}


