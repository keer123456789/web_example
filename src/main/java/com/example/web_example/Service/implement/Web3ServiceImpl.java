package com.example.web_example.Service.implement;

import com.example.web_example.Domain.WebResult;
import com.example.web_example.Ethereum_Contract.Lesson.Lesson;
import com.example.web_example.Service.Web3Service;
import com.example.web_example.Util.EthereumContractUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: web_example
 * @BelongsPackage: com.example.web_example.Service.implement
 * @Author: keer
 * @CreateTime: 2020-01-10 09:58
 * @Description:
 */
@Service
public class Web3ServiceImpl implements Web3Service {

    private static Logger logger = LoggerFactory.getLogger(Web3ServiceImpl.class);

    /**
     * 在lesson的合约中添加新的用户信息
     *
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public WebResult addUser(Map map) throws Exception {
        WebResult webResult = new WebResult();
        EthereumContractUtil contractUtil = new EthereumContractUtil();
        Lesson lesson = contractUtil.LessonLoad(map.get("address").toString());
        TransactionReceipt receipt = lesson.addNewUser(map.get("name").toString(), new BigInteger(map.get("age").toString())).send();
        List<Lesson.UserInfoEventResponse> list = lesson.getUserInfoEvents(receipt);

        if (list.size() == 0) {
            webResult.setMessage("操作失败");
            webResult.setStatus(WebResult.ERROR);
            logger.error("操作失败");
        } else {
            BigInteger age = list.get(0).age;
            String name = list.get(0).name;
            webResult.setMessage("操作成功，用户名：" + name + "年龄：" + age.toString());
            webResult.setStatus(WebResult.SUCCESS);
            logger.info("操作成功，用户名：" + name + "年龄：" + age.toString());
        }
        return webResult;

    }


    /**
     * 获得相应用户的信息
     *
     * @param address geth客户端的用户地址
     * @return 返回相应的用户信息
     * @throws Exception
     */
    @Override
    public WebResult getUserInfo(String address) throws Exception {
        WebResult webResult = new WebResult();
        EthereumContractUtil contractUtil = new EthereumContractUtil();
        Lesson lesson = contractUtil.LessonLoad(address);
        Tuple2<String, BigInteger> a = lesson.getUserInfo().send();
        webResult.setStatus(WebResult.SUCCESS);
        webResult.setMessage("查询成功");
        webResult.setData(a);
        logger.info("查询结果：" + a.toString());
        return webResult;
    }

    /**
     * 更改相应用户的信息
     *
     * @param address geth客户端的用户地址
     * @param age     要更新的用户信息
     * @return
     * @throws Exception
     */
    @Override
    public WebResult changeUserAge(String address, String age) throws Exception {
        WebResult webResult = new WebResult();
        EthereumContractUtil contractUtil = new EthereumContractUtil();
        Lesson lesson = contractUtil.LessonLoad(address);
        TransactionReceipt receipt = lesson.changeAge(new BigInteger(age)).send();
        List<Lesson.UserInfoEventResponse> list = lesson.getUserInfoEvents(receipt);
        if (list.size() == 0) {
            webResult.setMessage("操作失败");
            webResult.setStatus(WebResult.ERROR);
            logger.error("操作失败");
        } else {
            BigInteger newAge = list.get(0).age;
            String name = list.get(0).name;
            webResult.setMessage("操作成功，用户名：" + name + "年龄：" + newAge.toString());
            webResult.setStatus(WebResult.SUCCESS);
            logger.info("操作成功，更新后的信息如下：用户名：" + name + "年龄：" + newAge.toString());
        }

        return webResult;

    }

}
