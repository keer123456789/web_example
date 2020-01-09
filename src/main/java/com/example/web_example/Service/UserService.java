package com.example.web_example.Service;

import com.example.web_example.BDQLParser.BDQLUtil;
import com.example.web_example.Util.EthereumContractUtil;
import com.example.web_example.Dao.UserDao;
import com.example.web_example.Domain.BigchainDB.ParserResult;
import com.example.web_example.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    EthereumContractUtil contractUtil;

    @Autowired
    BDQLUtil bdqlUtil;

    /**
     * 数据库方法，进入Dao层
     * @param user
     * @return
     */
    public String addUser(User user){
       return userDao.insert(user);
    }

    public List<User> get(){
        return userDao.select();
    }



    public ParserResult work(String sql){
        return bdqlUtil.work(sql);
    }

}
