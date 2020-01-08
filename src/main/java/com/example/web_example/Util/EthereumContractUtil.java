package com.example.web_example.Util;


import com.example.web_example.Ethereum_Contract.Lesson.Lesson;
import com.example.web_example.Ethereum_Contract.Power.Power;
import com.example.web_example.Ethereum_Contract.exampleContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.codegen.SolidityFunctionWrapperGenerator;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;

import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.io.IOException;

@Component
public class EthereumContractUtil {

    @Value("${web3_url}")
    private String web3_url;

    @Value("${Power_address}")
    private String Example_address;

    @Value("${account_address}")
    private String account_address;

    @Value("{lesson_address}")
    private String lesson_address;

    @Autowired
    private FileUtil fileUtil;

    private static Logger logger = LoggerFactory.getLogger(EthereumContractUtil.class);


    public Lesson LessonLoad() {
        return LessonLoad(account_address);
    }

    public Lesson LessonLoad(String address) {
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        TransactionManager clientTransactionManager = new ClientTransactionManager(web3j, address);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Lesson.load(Example_address, web3j, clientTransactionManager, contractGasProvider.getGasPrice(), contractGasProvider.getGasLimit());
    }

    public Lesson LessonDeploy(String account_address) throws Exception {
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        TransactionManager clientTransactionManager = new ClientTransactionManager(web3j, account_address);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Lesson.deploy(web3j, clientTransactionManager, contractGasProvider.getGasPrice(), contractGasProvider.getGasPrice()).send();
    }

    public void GenerateContract(String abiFilePath, String binFilePath, String contractName) {
        String[] args = new String[7];
        args[0] = "generate";
        args[1] = binFilePath;
        args[2] = abiFilePath;
        args[3] = "-o";
        args[4] = "./src/main/java/";
        args[5] = "-p";

        String path = "./src/main/java/com/example/web_example/Ethereum_Contract/" + contractName;
        if (fileUtil.createFile(path)) {
            args[6] = exampleContract.class.getPackage().getName() + "." + contractName;
            logger.info("智能合约java类创建成功，路径：" + path);
        } else {
            logger.error("***创建合约java类出错！！！***");
        }

    }

    /**
     * 创建新用户
     *
     * @param password 密码
     * @return address
     */
    public String createNewAccount(String password) {
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


    public static void main(String[] args) throws Exception {
        EthereumContractUtil contractUtil = new EthereumContractUtil();
        contractUtil.GenerateContract("./src/main/java/com/example/web_example/Ethereum_Contract/Lesson/Lesson.abi", "./src/main/java/com/example/web_example/Ethereum_Contract/Lesson/Lesson.bin", "pig");
        logger.info(EthereumContractUtil.class.getPackage().getName());

    }


}
