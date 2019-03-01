package com.example.web_example.Service;

import com.example.web_example.ContractUtil.ContractUtil;
import com.example.web_example.ContractUtil.solidity.Friend.Friend;
import com.example.web_example.Dao.UserDao;
import com.example.web_example.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    ContractUtil contractUtil;

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

    /**
     * 合约方法在这一层解决，不进入Dao层
     */
    public void  contract() throws Exception {
        //生成合约类
        Friend friend=contractUtil.FriendLoad();
        //调用合约方法
        TransactionReceipt receipt=friend.addFriend("12","112","123").send();

    }
}
