package ic_ethereum;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.4.
 */
@SuppressWarnings("rawtypes")
public class Idus extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b50610bf9806100206000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063a7df8e921461005c578063df3109fc146101dd578063e79a7a2e14610208575b600080fd5b34801561006857600080fd5b506101db600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f016020809104026020016040519081016040528093929190818152602001838380828437820191505050505050919291929050505061049a565b005b3480156101e957600080fd5b506101f26106af565b6040518082815260200191505060405180910390f35b34801561021457600080fd5b5061026f600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506106b5565b60405180806020018060200180602001806020018060200186810386528b818151815260200191508051906020019080838360005b838110156102bf5780820151818401526020810190506102a4565b50505050905090810190601f1680156102ec5780820380516001836020036101000a031916815260200191505b5086810385528a818151815260200191508051906020019080838360005b8381101561032557808201518184015260208101905061030a565b50505050905090810190601f1680156103525780820380516001836020036101000a031916815260200191505b50868103845289818151815260200191508051906020019080838360005b8381101561038b578082015181840152602081019050610370565b50505050905090810190601f1680156103b85780820380516001836020036101000a031916815260200191505b50868103835288818151815260200191508051906020019080838360005b838110156103f15780820151818401526020810190506103d6565b50505050905090810190601f16801561041e5780820380516001836020036101000a031916815260200191505b50868103825287818151815260200191508051906020019080838360005b8381101561045757808201518184015260208101905061043c565b50505050905090810190601f1680156104845780820380516001836020036101000a031916815260200191505b509a505050505050505050505060405180910390f35b60a06040519081016040528086815260200185815260200184815260200183815260200182815250600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020866040518082805190602001908083835b6020831015156105375780518252602082019150602081019050602083039250610512565b6001836020036101000a03801982511681845116808217855250505050505090500191505090815260200160405180910390206000820151816000019080519060200190610586929190610b28565b5060208201518160010190805190602001906105a3929190610b28565b5060408201518160020190805190602001906105c0929190610b28565b5060608201518160030190805190602001906105dd929190610b28565b5060808201518160040190805190602001906105fa929190610b28565b50905050336002866040518082805190602001908083835b6020831015156106375780518252602082019150602081019050602083039250610612565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505050505050565b60005481565b60608060608060606000600160006002896040518082805190602001908083835b6020831015156106fb57805182526020820191506020810190506020830392506106d6565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020876040518082805190602001908083835b6020831015156107bf578051825260208201915060208101905060208303925061079a565b6001836020036101000a038019825116818451168082178552505050505050905001915050908152602001604051809103902090508060000181600101826002018360030184600401848054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561089d5780601f106108725761010080835404028352916020019161089d565b820191906000526020600020905b81548152906001019060200180831161088057829003601f168201915b50505050509450838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109395780601f1061090e57610100808354040283529160200191610939565b820191906000526020600020905b81548152906001019060200180831161091c57829003601f168201915b50505050509350828054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156109d55780601f106109aa576101008083540402835291602001916109d5565b820191906000526020600020905b8154815290600101906020018083116109b857829003601f168201915b50505050509250818054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610a715780601f10610a4657610100808354040283529160200191610a71565b820191906000526020600020905b815481529060010190602001808311610a5457829003601f168201915b50505050509150808054600181600116156101000203166002900480601f016020809104026020016040519081016040528092919081815260200182805460018160011615610100020316600290048015610b0d5780601f10610ae257610100808354040283529160200191610b0d565b820191906000526020600020905b815481529060010190602001808311610af057829003601f168201915b50505050509050955095509550955095505091939590929450565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610b6957805160ff1916838001178555610b97565b82800160010185558215610b97579182015b82811115610b96578251825591602001919060010190610b7b565b5b509050610ba49190610ba8565b5090565b610bca91905b80821115610bc6576000816000905550600101610bae565b5090565b905600a165627a7a72305820b955390585ea3ad1634fd8352810db439e3d413e5e102705a6bbd7c0d12201270029";

    public static final String FUNC_ADDPATIENTTOSTORE = "addPatientToStore";

    public static final String FUNC_GETPATIENT = "getPatient";

    public static final String FUNC_PATIENTINDEX = "patientIndex";

    @Deprecated
    protected Idus(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

//    protected Idus(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
//    }

    @Deprecated
    protected Idus(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

//    protected Idus(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
//    }

    public RemoteCall<TransactionReceipt> addPatientToStore(String pno, String pname, String identity, String psexdes, String totalcost) {
        final Function function = new Function(
                FUNC_ADDPATIENTTOSTORE,
                Arrays.<Type>asList(new Utf8String(pno),
                        new Utf8String(pname),
                        new Utf8String(identity),
                        new Utf8String(psexdes),
                        new Utf8String(totalcost)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }
    public RemoteCall<List<Type>> getPatient(String pno) {
        final Function function = new Function(FUNC_GETPATIENT, Arrays.<Type>asList(new Utf8String(pno)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {
                }, new TypeReference<Utf8String>() {
                }, new TypeReference<Utf8String>() {
                }, new TypeReference<Utf8String>() {
                }, new TypeReference<Utf8String>() {
                }));
        return executeRemoteCallMultipleValueReturn(function);
    }
//    public RemoteFunctionCall<TransactionReceipt> getPatient(String pno) {
//        final Function function = new Function(
//                FUNC_GETPATIENT,
//                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(pno)),
//                Collections.<TypeReference<?>>emptyList());
//        return executeRemoteCallTransaction(function);
//    }

    public RemoteCall<TransactionReceipt> patientIndex() {
        final Function function = new Function(
                FUNC_PATIENTINDEX,
                Arrays.<Type>asList(),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Idus load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Idus(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Idus load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Idus(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

//    public static Idus load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return new Idus(contractAddress, web3j, credentials, contractGasProvider);
//    }

//    public static Idus load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return new Idus(contractAddress, web3j, transactionManager, contractGasProvider);
//    }

//    public static RemoteCall<Idus> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(Idus.class, web3j, credentials, contractGasProvider, BINARY, "");
//    }
//
//    public static RemoteCall<Idus> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
//        return deployRemoteCall(Idus.class, web3j, transactionManager, contractGasProvider, BINARY, "");
//    }

    @Deprecated
    public static RemoteCall<Idus> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Idus.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }
    @Deprecated
    public static RemoteCall<Idus> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Idus.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}