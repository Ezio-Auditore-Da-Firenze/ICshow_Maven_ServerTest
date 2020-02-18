package ic_ethereum;

import bean.Patient;
import net.sf.json.JSONObject;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import static ic_ethereum.Idus.load;

public class IC_Administration {
    private HttpService hs;
    private Geth geth;
    private Web3j web3j;
    //private String record_path;
    Credentials credentials;
    public static  IC_Administration ICA=new IC_Administration();

    public IC_Administration(){
        hs=new HttpService("http://127.0.0.1:8544/");
        web3j = Web3j.build(hs); // 创建一个 web3j 的连接
        geth=Geth.build(hs);
        try {
            credentials= WalletUtils.loadCredentials(        // 加载钱包
                    "123",
                    IC_Administration.class.getResource("key_01").getPath()
                    //"F:\\GoWorkSpace\\ether-test\\db\\keystore\\UTC--2020-01-10T13-00-45.976152300Z--1b17a608ca853903c8fada1bacb236bb4ac2e93f"
            );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CipherException e) {
            e.printStackTrace();
        }
        //record_path=IC_Administration.class.getResource("ic_record.txt").getPath();
    }

    public void mine(int num,int threadnum) throws IOException {//挖矿
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request<?, EthBlockNumber> request=geth.ethBlockNumber();
                    int oldblock=Integer.parseInt(request.send().getBlockNumber().toString());
                    System.out.println("MineStart\nTopBlockNumber_OLD="+oldblock);
                    geth.minerStart(threadnum).send();
                    int newblock=oldblock;
                    while(newblock<oldblock+num){
                        Thread.sleep(Long.parseLong("50"));
                        newblock=Integer.parseInt(geth.ethBlockNumber().send().getBlockNumber().toString());
                    }
                    geth.minerStop().send();
                    System.out.println("TopBlockNumber_NEW="+newblock+"\nMineStop\n");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public Patient selectPatient(String pno,String addr) {//查数据
        Patient pa=new Patient();
        try {
            //System.out.println(IC_Administration.class.getResource("key_01").getPath());
            Idus idus= load(addr,web3j,credentials, BigInteger.valueOf(22000000000L), BigInteger.valueOf(4300000L));
            System.out.println("selectPatient_isValid"+idus.isValid());
            List<Type> result = idus.getPatient(pno).send();
            System.out.println(result);
            pa.setPno(result.get(0).toString());
            pa.setPname(result.get(1).toString());
            pa.setIdentity(result.get(2).toString());
            pa.setPsexdes(result.get(3).toString());
            pa.setTotalcost(Double.parseDouble(result.get(4).toString()));
            pa.setNotes("Read Form BlockChain");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pa;
    }
}
