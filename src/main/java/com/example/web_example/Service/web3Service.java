package com.example.web_example.Service;

import com.example.web_example.ContractUtil.ContractUtil;
import com.example.web_example.ContractUtil.Lesson.Lesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class web3Service {

    @Autowired
    ContractUtil contractUtil;

    public String addUser(Map map) throws Exception {
        Lesson lesson=contractUtil.LessonLoad(map.get("address").toString());

        TransactionReceipt receipt=lesson.addNewUser(map.get("name").toString(),new BigInteger(map.get("age").toString())).send();

        List<Lesson.UserInfoEventResponse> list= lesson.getUserInfoEvents(receipt);
        BigInteger age=list.get(0).age;
        String name=list.get(0).name;

        return "操作成功，用户名："+name+"年龄："+age.toString();
    }



    public String getUserInfo(String address) throws Exception {
        Lesson lesson=contractUtil.LessonLoad(address);

        Tuple2<String, BigInteger> a= lesson.getUserInfo().send();

        return a.toString();
    }

}
