package com.example.web_example.Util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.JsonRpc2_0Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.admin.methods.response.PersonalListAccounts;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.JsonRpc2_0Web3j;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: web_example
 * @BelongsPackage: com.example.web_example.Util
 * @Author: keer
 * @CreateTime: 2020-01-09 17:15
 * @Description: 控制geth工具
 */
public class EthereumGethUtil {
    private Logger logger = LoggerFactory.getLogger(EthereumGethUtil.class);


    private String web3_url=PropertyUtil.getProperties("web3_url");


    /**
     * 创建新用户
     *
     * @param password 密码
     * @return address
     */
    public String createNewAccount(String password) {
        return createNewAccount(password, web3_url);
    }

    /**
     * 创建新用户
     *
     * @param password
     * @param web3_url 需要连接的geth客户端的url
     * @return
     */
    public String createNewAccount(String password, String web3_url) {
        Admin web3j = Admin.build(new HttpService(web3_url));
        NewAccountIdentifier newAccountIdentifier = null;
        try {
            newAccountIdentifier = web3j.personalNewAccount(password).send();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return newAccountIdentifier.getAccountId();
    }

    /**
     * 停止挖矿
     *
     * @return
     */
    public boolean minerStop() {
        return minerStop(web3_url);
    }

    /**
     * 停止指定的geth客户端挖矿
     *
     * @param web3_url
     * @return
     */
    public boolean minerStop(String web3_url) {
        Map map = new HashMap<>();
        map.put("jsonrpc", "2.0");
        map.put("method", "miner_stop");
        map.put("params", new ArrayList<>());
        map.put("id", 74);
        String json = JSON.toJSONString(map);
        String resp = HttpUtil.httpPost(web3_url, json);
        Map map1 = (Map) JSON.parse(resp);
        return (boolean) map1.get("result");
    }

    /**
     * 开始挖矿
     *
     * @return
     */
    public boolean minerStart() {
        return minerStart(web3_url);
    }

    public boolean minerStart(String web3_url) {
        List list = new ArrayList();
        list.add(1);
        Map map = new HashMap<>();
        map.put("jsonrpc", "2.0");
        map.put("method", "miner_start");
        map.put("params", list);
        map.put("id", 74);
        String json = JSON.toJSONString(map);
        String resp = HttpUtil.httpPost(web3_url, json);
//        Map map1 = (Map) JSON.parse(resp);
//        if (!map1.containsValue("result")) {
//            return true;
//        } else {
//            return false;
//        }
        logger.info(resp);
        return true;
    }

    /**
     * 获取地址的余额
     *
     * @param accountAddress
     * @return
     */
    public String getBlance(String accountAddress) {
        return getBlance(accountAddress, web3_url);
    }

    /**
     * 获取指定账户的余额
     *
     * @param accountAddress
     * @param web3_url
     * @return
     */
    public String getBlance(String accountAddress, String web3_url) {
        Web3j web3j = new JsonRpc2_0Web3j(new HttpService(web3_url));
        try {
            EthGetBalance ethGetBalance = web3j.ethGetBalance(accountAddress, DefaultBlockParameterName.LATEST).send();
            BigInteger balance = ethGetBalance.getBalance();

            balance = balance.divide(new BigInteger("1000000000000000000"));
            logger.info("获取地址为：" + accountAddress + "的余额成功，余额为：" + balance);
            return balance.toString();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("获取地址为" + accountAddress + "的余额失败！");
            return null;
        }
    }


    /**
     * 解锁账户
     *
     * @param accountAddress 需要解锁的账户
     * @param password       密码
     * @return
     */
    public Boolean UnlockAccount(String accountAddress, String password) {
        return UnlockAccount(accountAddress, password, web3_url);
    }


    /**
     * 解锁账户
     *
     * @param accountAddress 需要解锁的账户
     * @param password       密码
     * @param web3_url       geth客户端的url
     * @return
     */
    public Boolean UnlockAccount(String accountAddress, String password, String web3_url) {
        Admin web3j = Admin.build(new HttpService(web3_url));
        PersonalUnlockAccount personalUnlockAccount = null;
        try {
            personalUnlockAccount = web3j.personalUnlockAccount(accountAddress, password).send();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (personalUnlockAccount.accountUnlocked() == null) {
            return true;
        }
        return personalUnlockAccount.accountUnlocked();
    }


    /**
     * \
     *
     * @param address_from 发起转账的用户地址
     * @param address_to   接收转账的用户地址
     * @param password     发起转账用户的密码
     * @param from_value   转账的金额，单位：eth 例子：10.0
     * @return
     */
    public boolean sendTransaction(String address_from, String address_to, String password, String from_value) {
        return sendTransaction(address_from, address_to, password, from_value, web3_url);
    }

    /**
     * 转账
     *
     * @param address_from 发起转账的用户地址
     * @param address_to   接收转账的用户地址
     * @param password     发起转账用户的密码
     * @param from_value   转账的金额，单位：eth 例子：10.0
     * @param web3_url     geth的url
     * @return
     */
    public boolean sendTransaction(String address_from, String address_to, String password, String from_value, String web3_url) {
        Admin web3j = new JsonRpc2_0Admin(new HttpService(web3_url));
        UnlockAccount(address_from, password);
        BigInteger value = Convert.toWei(from_value, Convert.Unit.ETHER).toBigInteger();
        Transaction transaction = Transaction.createEtherTransaction(address_from, Transaction.DEFAULT_GAS, Transaction.DEFAULT_GAS, Transaction.DEFAULT_GAS, address_to, value);
        try {
            EthSendTransaction ethSendTransaction = web3j.personalSendTransaction(transaction, password).send();
            String hash = ethSendTransaction.getTransactionHash();
            if (!hash.equals(null)) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 获得系统内的所有用户地址
     *
     * @return
     */
    public List<String> getAllAccount() {
        return getAllAccount(web3_url);
    }

    /**
     * @param web3_url geth 的url
     * @return
     */
    public List<String> getAllAccount(String web3_url) {
        Admin web3j = Admin.build(new HttpService(web3_url));
        PersonalListAccounts listAccounts = null;
        try {
            listAccounts = web3j.personalListAccounts().send();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return listAccounts.getAccountIds();
    }

    public static void main(String[] args) {
        EthereumGethUtil ethereumGethUtil=new EthereumGethUtil();
        ethereumGethUtil.minerStart("http://192.168.85.147:8545");
    }

}
