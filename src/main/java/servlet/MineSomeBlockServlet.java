package servlet;

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
import static ic_ethereum.IC_Administration.ICA;

public class MineSomeBlockServlet extends javax.servlet.http.HttpServlet {

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
        System.out.println("MineData:" + result);
        JSONObject obj = JSONObject.fromObject(result);
        int num=Integer.parseInt(obj.getString("minenumber"));
        int threadnum=Integer.parseInt(obj.getString("threadnum"));
        ICA.mine(num,threadnum);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("minedata","Mining Now");
        out.write(jsonObject.toString());
        System.out.println("MineReturn:"+jsonObject.toString());
        out.flush();
        out.close();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }

}
