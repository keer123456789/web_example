package com.example.web_example.Service;

import com.example.web_example.BDQLParser.BDQLUtil;
import com.example.web_example.ContractUtil.ContractUtil;
import com.example.web_example.ContractUtil.Power;
import com.example.web_example.Dao.UserDao;
import com.example.web_example.Domain.ParserResult;
import com.example.web_example.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    ContractUtil contractUtil;

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

    /**
     * 合约方法在这一层解决，不进入Dao层
     */
    public boolean addPower(Map map) throws Exception {
        //生成合约类
        Power power =contractUtil.PowerLoad();
        TransactionReceipt receipt=power.addPower(new BigInteger(map.get("powerID").toString()),map.get("powerName").toString(),map.get("powerInfo").toString()).send();
        List<Power.NewPowerEventResponse> list =power.getNewPowerEvents(receipt);
        return list.get(0)._isUse;

    }

    public Map getPowerInfo(String  powerID) throws Exception {
        Power power=contractUtil.PowerLoad();
        Tuple4<BigInteger, String, String, Boolean> tuple4= power.getPowerInfoBypowerId(new BigInteger(powerID)).send();
        Map map=new HashMap();
        map.put("powerID",tuple4.getValue1().toString());
        map.put("powerName",tuple4.getValue2());
        map.put("powerInfo",tuple4.getValue3());
        return map;
    }

    public ParserResult work(String sql){
        return bdqlUtil.work(sql);
    }

}
