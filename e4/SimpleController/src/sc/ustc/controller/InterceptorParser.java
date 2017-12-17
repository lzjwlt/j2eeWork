package sc.ustc.controller;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.util.ArrayList;

import static sc.ustc.controller.ActionParser.isValidNode;

public class InterceptorParser {

    public static ArrayList<Interceptor> parse() throws Exception{
        ArrayList<Interceptor>interceptors = new ArrayList<>();
        //配置文件
        String file = Path.getInstance().getXmlPath();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        Node root = doc.getFirstChild();
        NodeList interceptionList = root.getChildNodes();
        for(int i=0;i<interceptionList.getLength();i++) {
            Node interceptionNode = interceptionList.item(i);
            if (isValidNode(interceptionNode) && interceptionNode.getNodeName().equals("interception")) {
                    Element intercpt = (Element) interceptionNode;
                    String interceptorName = intercpt.getAttribute("name");
                    String className = intercpt.getAttribute("class");
                    String preDo = intercpt.getAttribute("predo");
                    String afterDo = intercpt.getAttribute("afterdo");
                        Interceptor interceptor = new Interceptor(interceptorName, className, preDo, afterDo);
                        interceptors.add(interceptor);
                }
            }
        return interceptors;
    }
}
