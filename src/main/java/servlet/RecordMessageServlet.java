package servlet;

import bean.Patient;
import ic_ethereum.IC_Administration;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static ic_ethereum.IC_Administration.ICA;

@WebServlet(name = "RecordMessageServlet")
public class RecordMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        System.out.println("RecordData:" + result);
        JSONObject obj = JSONObject.fromObject(result);
        String pno=obj.getString("pno");
        String addr=obj.getString("addr");
        Patient pa=ICA.selectPatient(pno,addr);

        String newMessage = pa.getPno() + "-" + pa.getPname() + "-" + pa.getIdentity() + "-" + pa.getPsexdes() + "-" + pa.getTotalcost() + "-" + pa.getNotes() + "\n";
        File file =new File("ic_record.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        FileWriter fileWritter = new FileWriter(file.getName(),true);
        //System.out.println(file.getPath());
        fileWritter.write(newMessage);
        fileWritter.flush();
        fileWritter.close();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("RecordReturn:","Recording Now");
        out.write(jsonObject.toString());
        System.out.println("RecordReturn"+jsonObject.toString()+"\n");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
