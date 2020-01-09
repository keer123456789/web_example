package com.example.web_example.Ethereum_Contract.Lesson;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.4.0.
 */
public class Lesson extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b50610a4f806100206000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635d8d15851461005c5780635e439f66146100f3578063ad3b5577146101a0575b600080fd5b34801561006857600080fd5b50610071610293565b6040518080602001838152602001828103825284818151815260200191508051906020019080838360005b838110156100b757808201518184015260208101905061009c565b50505050905090810190601f1680156100e45780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b3480156100ff57600080fd5b5061011e600480360381019080803590602001909291905050506103be565b6040518080602001838152602001828103825284818151815260200191508051906020019080838360005b83811015610164578082015181840152602081019050610149565b50505050905090810190601f1680156101915780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b3480156101ac57600080fd5b50610211600480360381019080803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929080359060200190929190505050610672565b6040518080602001838152602001828103825284818151815260200191508051906020019080838360005b8381101561025757808201518184015260208101905061023c565b50505050905090810190601f1680156102845780820380516001836020036101000a031916815260200191505b50935050505060405180910390f35b606060008060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010154818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103af5780601f10610384576101008083540402835291602001916103af565b820191906000526020600020905b81548152906001019060200180831161039257829003601f168201915b50505050509150915091509091565b60606000826000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101819055507f40f702b4734eaea9878b86fb3f419a1fdcb54b1e00ec82ae2a99fea24cdb01416000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010154604051808060200183815260200182810382528481815460018160011615610100020316600290048152602001915080546001816001161561010002031660029004801561053b5780601f106105105761010080835404028352916020019161053b565b820191906000526020600020905b81548152906001019060200180831161051e57829003601f168201915b5050935050505060405180910390a16000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010154818054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156106625780601f1061063757610100808354040283529160200191610662565b820191906000526020600020905b81548152906001019060200180831161064557829003601f168201915b5050505050915091509150915091565b60606000836000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060000190805190602001906106cb92919061097e565b50826000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101819055507f40f702b4734eaea9878b86fb3f419a1fdcb54b1e00ec82ae2a99fea24cdb01416000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206001015460405180806020018381526020018281038252848181546001816001161561010002031660029004815260200191508054600181600116156101000203166002900480156108455780601f1061081a57610100808354040283529160200191610845565b820191906000526020600020905b81548152906001019060200180831161082857829003601f168201915b5050935050505060405180910390a16000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206000016000803373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010154818054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561096c5780601f106109415761010080835404028352916020019161096c565b820191906000526020600020905b81548152906001019060200180831161094f57829003601f168201915b50505050509150915091509250929050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106109bf57805160ff19168380011785556109ed565b828001600101855582156109ed579182015b828111156109ec5782518255916020019190600101906109d1565b5b5090506109fa91906109fe565b5090565b610a2091905b80821115610a1c576000816000905550600101610a04565b5090565b905600a165627a7a72305820fbe7ffc0c90afd6253e82f409d16623c20b5b8b579ef17b190c1bf2dbe1fe82b0029";

    public static final String FUNC_GETUSERINFO = "getUserInfo";

    public static final String FUNC_CHANGEAGE = "changeAge";

    public static final String FUNC_ADDNEWUSER = "addNewUser";

    public static final Event USERINFO_EVENT = new Event("userInfo", 
            Arrays.<TypeReference<?>>asList(),
            Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
    ;

    protected Lesson(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Lesson(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<Tuple2<String, BigInteger>> getUserInfo() {
        final Function function = new Function(FUNC_GETUSERINFO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple2<String, BigInteger>>(
                new Callable<Tuple2<String, BigInteger>>() {
                    @Override
                    public Tuple2<String, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> changeAge(BigInteger _newage) {
        final Function function = new Function(
                FUNC_CHANGEAGE, 
                Arrays.<Type>asList(new Uint256(_newage)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addNewUser(String _name, BigInteger _age) {
        final Function function = new Function(
                FUNC_ADDNEWUSER,
                Arrays.<Type>asList(new Utf8String(_name),
                new Uint256(_age)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public List<UserInfoEventResponse> getUserInfoEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(USERINFO_EVENT, transactionReceipt);
        ArrayList<UserInfoEventResponse> responses = new ArrayList<UserInfoEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            UserInfoEventResponse typedResponse = new UserInfoEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.age = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<UserInfoEventResponse> userInfoEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, UserInfoEventResponse>() {
            @Override
            public UserInfoEventResponse call(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(USERINFO_EVENT, log);
                UserInfoEventResponse typedResponse = new UserInfoEventResponse();
                typedResponse.log = log;
                typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.age = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<UserInfoEventResponse> userInfoEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(USERINFO_EVENT));
        return userInfoEventObservable(filter);
    }

    public static RemoteCall<Lesson> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Lesson.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Lesson> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Lesson.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static Lesson load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Lesson(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Lesson load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Lesson(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class UserInfoEventResponse {
        public Log log;

        public String name;

        public BigInteger age;
    }
}
