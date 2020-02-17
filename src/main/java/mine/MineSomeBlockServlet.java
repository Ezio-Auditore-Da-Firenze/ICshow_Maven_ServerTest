package mine;

import net.sf.json.JSONObject;
//import org.json.JSONObject;
//import org.web3j.abi.datatypes.Int;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MineSomeBlockServlet extends javax.servlet.http.HttpServlet {
    HttpService hs=new HttpService("http://127.0.0.1:8544/");
    Geth geth=Geth.build(hs);
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        PrintWriter out = response.getWriter();
        // 获取客户端数据
        BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line = null;
        String result = "";
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        result = sb.toString();
        System.out.println("mine接受数据是" + result);
        //result="{{\"minedata\":4}}";
        JSONObject obj = JSONObject.fromObject(result);
        int num=Integer.parseInt(obj.getString("minenumber"));
        mine(num);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("minedata","**Mining Now**");
        out.write(jsonObject.toString());
        System.out.println("mine返回"+jsonObject.toString());
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }

    public void mine(int num) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Request<?, EthBlockNumber> request=geth.ethBlockNumber();
                    int oldblock=Integer.parseInt(request.send().getBlockNumber().toString());
                    System.out.println("MineStart\nTopBlockNumber_OLD="+oldblock);
                    //Admin admin=Admin.build(new HttpService());
                    geth.minerStart(40).send();
                    int newblock=oldblock;
                    while(newblock<oldblock+num){
                        Thread.sleep(Long.parseLong("300"));
                        newblock=Integer.parseInt(geth.ethBlockNumber().send().getBlockNumber().toString());
                    }
                    geth.minerStop().send();
                    System.out.println("TopBlockNumber_NEW="+newblock+"\nMineStop");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
