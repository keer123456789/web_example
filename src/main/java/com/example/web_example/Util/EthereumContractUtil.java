package com.example.web_example.Util;


import com.example.web_example.Ethereum_Contract.Buy.Buy;
import com.example.web_example.Ethereum_Contract.Lesson.Lesson;
import com.example.web_example.Ethereum_Contract.exampleContract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.web3j.codegen.SolidityFunctionWrapperGenerator;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ClientTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;


public class EthereumContractUtil {

    @Value("${web3_url}")
    private String web3_url;

    @Value("{lesson_address}")
    private String lesson_address;


    private static Logger logger = LoggerFactory.getLogger(EthereumContractUtil.class);


    /**
     * 引入配置文件的中已经部署好的lesson智能合约
     * @param account_address 账户地址
     * @return
     */
    public Lesson LessonLoad(String account_address) {
        return LessonLoad(account_address,lesson_address,web3_url);
    }

    /**
     * 如果已经在Ethereum上部署好了智能合约，需要得到智能合约java实例，调用此方法
     * @param account_address msg.sender,调用合约的账户地址
     * @param contract_address 合约地址
     * @param web3_url geth客户端url
     * @return
     */
    public Lesson LessonLoad(String account_address,String contract_address,String web3_url) {
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        TransactionManager clientTransactionManager = new ClientTransactionManager(web3j, account_address);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Lesson.load(contract_address, web3j, clientTransactionManager, contractGasProvider.getGasPrice(), contractGasProvider.getGasLimit());
    }

    /**
     * 在geth客户端使用账户地址部署合约Lesson
     * @param account_address
     * @return
     * @throws Exception
     */
    public Lesson LessonDeploy(String account_address) throws Exception {
       return LessonDeploy(account_address,web3_url);
    }

    /**
     * 在geth客户端使用账户地址部署合约Lesson
     * @param account_address
     * @param web3_url
     * @return
     * @throws Exception
     */
    public Lesson LessonDeploy(String account_address,String web3_url) throws Exception {
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        TransactionManager clientTransactionManager = new ClientTransactionManager(web3j, account_address);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Lesson.deploy(web3j, clientTransactionManager, contractGasProvider.getGasPrice(), contractGasProvider.getGasLimit()).send();
    }

    public Lesson LessonDeployByWallet(String password,String walletPath) throws Exception {
        return LessonDeployByWallet(password,walletPath,web3_url);
    }

    /**
     * 在geth客户端上使用钱包文件部署合约
     * @param password 账户密码
     * @param walletPath 钱包文件
     * @param web3_url geth客户端url
     * @return
     * @throws Exception
     */
    public Lesson LessonDeployByWallet(String password,String walletPath,String web3_url ) throws Exception {
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        Credentials credentials = WalletUtils.loadCredentials(password,walletPath);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Lesson.deploy(web3j,credentials,contractGasProvider.getGasPrice(),contractGasProvider.getGasLimit()).send();
    }

    /**
     * 引入Buy智能合约
     * @param account_address
     * @return
     */
    public Buy BuyLoad(String account_address) {
        return BuyLoad(account_address,lesson_address,web3_url);
    }

    /**
     * 引入Buy智能合约
     * @param account_address
     * @param contract_address
     * @param web3_url
     * @return
     */
    public Buy BuyLoad(String account_address,String contract_address,String web3_url) {
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        TransactionManager clientTransactionManager = new ClientTransactionManager(web3j, account_address);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Buy.load(contract_address, web3j, clientTransactionManager, contractGasProvider.getGasPrice(), contractGasProvider.getGasLimit());
    }

    /**
     * 在geth客户端使用账户地址部署合约Lesson
     * @param account_address
     * @return
     * @throws Exception
     */
    public Buy BuyDeploy(String account_address) throws Exception {
        return BuyDeploy(account_address,web3_url);
    }

    /**
     * 在geth客户端使用账户地址部署合约Lesson
     * @param account_address
     * @param web3_url
     * @return
     * @throws Exception
     */
    public Buy BuyDeploy(String account_address,String web3_url) throws Exception {
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        TransactionManager clientTransactionManager = new ClientTransactionManager(web3j, account_address);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Buy.deploy(web3j, clientTransactionManager, contractGasProvider.getGasPrice(), contractGasProvider.getGasLimit()).send();
    }

    public Buy BuyDeployByWallet(String password,String walletPath) throws Exception {
        return BuyDeployByWallet(password,walletPath,web3_url);
    }

    /**
     * 在geth客户端上使用钱包文件部署合约
     * @param password 账户密码
     * @param walletPath 钱包文件
     * @param web3_url geth客户端url
     * @return
     * @throws Exception
     */
    public Buy BuyDeployByWallet(String password,String walletPath,String web3_url ) throws Exception {
        Web3j web3j = Web3j.build(new HttpService(web3_url));
        Credentials credentials = WalletUtils.loadCredentials(password,walletPath);
        ContractGasProvider contractGasProvider = new DefaultGasProvider();
        return Buy.deploy(web3j,credentials,contractGasProvider.getGasPrice(),contractGasProvider.getGasLimit()).send();
    }

    /**
     * 创建智能合约的java类
     *
     * @param abiFilePath  abi文件的路径
     * @param binFilePath  bin文件的路径
     * @param contractName 智能合约的名称
     * @throws Exception
     */
    public void GenerateContract(String abiFilePath, String binFilePath, String contractName) throws Exception {
        String[] args = new String[7];
        args[0] = "generate";
//        args[1] = "-b";
        args[1] = binFilePath;
//        args[3] = "-a";
        args[2] = abiFilePath;
        args[3] = "-o";
        args[4] = "./src/main/java/";
        args[5] = "-p";
        String path = "src/main/java/com/example/web_example/Ethereum_Contract/" + contractName + "/";
        FileUtil fileUtil = new FileUtil();
        if (fileUtil.makedir(path)) {
            args[6] = exampleContract.class.getPackage().getName() + "." + contractName;
            logger.info("智能合约java类创建成功，路径：" + path);
            SolidityFunctionWrapperGenerator.run(args);
        } else {
            logger.error("***创建合约java类出错！！！***");
        }

    }


    public static void main(String[] args) throws Exception {
        EthereumContractUtil contractUtil = new EthereumContractUtil();
        contractUtil.GenerateContract("./src/main/java/com/example/web_example/Ethereum_Contract/Buy/buy.abi", "./src/main/java/com/example/web_example/Ethereum_Contract/buy/buy.bin", "Buy");
        logger.info(EthereumContractUtil.class.getPackage().getName());



    }


}
