package com.example.web_example.Ethereum_Contract.Buy;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Buy extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b50610255806100206000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633e58c58c146100725780636f9fb98a146100a857806375eb2ad1146100d357806388a5a020146100fe578063f1147dca14610108575b600080fd5b6100a6600480360381019080803573ffffffffffffffffffffffffffffffffffffffff16906020019092919050505061013e565b005b3480156100b457600080fd5b506100bd610188565b6040518082815260200191505060405180910390f35b3480156100df57600080fd5b506100e86101a7565b6040518082815260200191505060405180910390f35b6101066101c6565b005b61013c600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506101c8565b005b8073ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015610184573d6000803e3d6000fd5b5050565b60003073ffffffffffffffffffffffffffffffffffffffff1631905090565b60003373ffffffffffffffffffffffffffffffffffffffff1631905090565b565b8073ffffffffffffffffffffffffffffffffffffffff166108fc3073ffffffffffffffffffffffffffffffffffffffff16319081150290604051600060405180830381858888f19350505050158015610225573d6000803e3d6000fd5b50505600a165627a7a72305820b34641e42b6cfc5d5b8bb43d964c93d685c36e4d553d5e262135e7a28d5678880029";

    public static final String FUNC_SEND = "send";

    public static final String FUNC_GETCONTRACTBALANCE = "getContractBalance";

    public static final String FUNC_GETADDRESSBALANCE = "getAddressBalance";

    public static final String FUNC_SENDCONTRACT = "sendContract";

    public static final String FUNC_SENDFROMCONTRACT = "sendFromContract";

    protected Buy(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Buy(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> send(String _to, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SEND, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> getContractBalance() {
        final Function function = new Function(FUNC_GETCONTRACTBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> getAddressBalance() {
        final Function function = new Function(FUNC_GETADDRESSBALANCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> sendContract(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SENDCONTRACT, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> sendFromContract(String _to, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SENDFROMCONTRACT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public static RemoteCall<Buy> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Buy.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Buy> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Buy.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Buy load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Buy(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Buy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Buy(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }
}
