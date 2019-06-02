package com.example.web_example.ContractUtil;



import com.example.web_example.ContractUtil.Lesson.Lesson;
import com.example.web_example.ContractUtil.Power.Power;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;

import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

@Component
public class ContractUtil {

    @Value("${web3_url}")
    private String web3_url;

    @Value("${Power_address}")
    private String Example_address;

    @Value("${account_address}")
    private String account_address;

    @Value("{lesson_address}")
    private String lesson_address;


    public Power PowerLoad() {
        return PowerLoad(account_address);
    }

    public Power PowerLoad(String address){
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        TransactionManager clientTransactionManager = new ClientTransactionManager(web3j, address);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Power.load(Example_address, web3j, clientTransactionManager, contractGasProvider.getGasPrice(), contractGasProvider.getGasLimit());
    }

    public Lesson LessonLoad() {
        return LessonLoad(account_address);
    }

    public Lesson LessonLoad(String address){
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        TransactionManager clientTransactionManager = new ClientTransactionManager(web3j, address);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Lesson.load(Example_address, web3j, clientTransactionManager, contractGasProvider.getGasPrice(), contractGasProvider.getGasLimit());
    }






}
