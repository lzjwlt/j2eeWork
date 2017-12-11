package test;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.util.Date;

public class LogInterceptor {
    private String sTime;
    private String eTime;

    private String actionName;
    private String resultName;

    private String filePath="C:\\log\\log.xml";

    public void preAction(){
        sTime = DateFormat.getDateTimeInstance().format(new Date().getTime());
        System.out.println(sTime);
    }

    public void afterAction(String actionName, String resultName){
        eTime = DateFormat.getDateTimeInstance().format(new Date().getTime());
        this.actionName = actionName;
        this.resultName = resultName;
        System.out.println(eTime);
        createNewLogFile();
        appendXml();
    }

    private void createNewLogFile(){
        File file = new File(filePath);
        if(file.exists()){
            return;
        }
        String headText = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<log>\n" +
                "</log>";
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileWriter fw = new FileWriter(filePath);
            fw.write(headText);
            fw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void appendXml(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(filePath);

            Node log = document.getElementsByTagName("log").item(0);
            Node action = document.createElement ("action");
            Node name = document.createElement("name");
            name.setTextContent(actionName);
            Node st = document.createElement("s-time");
            st.setTextContent(sTime);
            Node et = document.createElement("e-time");
            et.setTextContent(eTime);
            Node result = document.createElement("result");
            result.setTextContent(resultName);
            action.appendChild(name);
            action.appendChild(st);
            action.appendChild(et);
            action.appendChild(result);
            log.appendChild(action);

            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer tf = tff.newTransformer();
            tf.setOutputProperty(OutputKeys.INDENT, "yes");// 设置缩进、换行
            tf.transform(new DOMSource(document), new StreamResult(new File(
                    filePath)));


        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
