package com.example.web_example.Service;

import com.example.web_example.Ethereum_Contract.Lesson.Lesson;
import com.example.web_example.Util.EthereumContractUtil;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class web3Service {


    /**
     * 在lesson的合约中添加新的用户信息
     *
     * @param map
     * @return
     * @throws Exception
     */
    public String addUser(Map map) throws Exception {
        EthereumContractUtil contractUtil = new EthereumContractUtil();
        Lesson lesson = contractUtil.LessonLoad(map.get("address").toString());
        TransactionReceipt receipt = lesson.addNewUser(map.get("name").toString(), new BigInteger(map.get("age").toString())).send();
        List<Lesson.UserInfoEventResponse> list = lesson.getUserInfoEvents(receipt);
        BigInteger age = list.get(0).age;
        String name = list.get(0).name;
        return "操作成功，用户名：" + name + "年龄：" + age.toString();

    }


    /**
     * 获得相应用户的信息
     *
     * @param address geth客户端的用户地址
     * @return 返回相应的用户信息
     * @throws Exception
     */
    public String getUserInfo(String address) throws Exception {
        EthereumContractUtil contractUtil = new EthereumContractUtil();
        Lesson lesson = contractUtil.LessonLoad(address);
        Tuple2<String, BigInteger> a = lesson.getUserInfo().send();
        return a.toString();
    }

    /**
     * 更改相应用户的信息
     *
     * @param address geth客户端的用户地址
     * @param age     要更新的用户信息
     * @return
     * @throws Exception
     */
    public String changeUserAge(String address, String age) throws Exception {
        EthereumContractUtil contractUtil = new EthereumContractUtil();
        Lesson lesson = contractUtil.LessonLoad(address);
        TransactionReceipt receipt = lesson.changeAge(new BigInteger(age)).send();
        List<Lesson.UserInfoEventResponse> list = lesson.getUserInfoEvents(receipt);
        BigInteger newAge = list.get(0).age;
        String name = list.get(0).name;
        return "操作成功，用户名：" + name + "年龄：" + newAge.toString();

    }

}
