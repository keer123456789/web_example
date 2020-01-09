package com.example.web_example.Domain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.http.HttpService;

/**
 * @BelongsProject: web_example
 * @BelongsPackage: com.example.web_example.Domain
 * @Author: keer
 * @CreateTime: 2020-01-09 17:58
 * @Description: 引用智能合约java实例需要的数据
 */
public class ContractInfo {
    private Web3j web3j;
    private String accountAddress;
    private String password;
    private String contractAddress;

    public ContractInfo() {
    }

    public ContractInfo(String web3_url){
        this.web3j= Web3j.build(new HttpService(web3_url));
    }

    public Web3j getWeb3j() {
        return web3j;
    }

    public void setWeb3j(Web3j web3j) {
        this.web3j = web3j;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }
}
