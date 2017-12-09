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


public class ActionParser {


    public static Action parse(HttpServletRequest req, HttpServletResponse resp, String actName) throws Exception{
        //读取xml路径并使用DOM解析
        ArrayList<Result> results = new ArrayList<>();
        String file = req.getServletContext().getRealPath("/WEB-INF/classes/controller.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        Node root = doc.getFirstChild();
        NodeList controllerList = root.getChildNodes();
        for(int i=0;i<controllerList.getLength();i++) {
            Node controllerNode = controllerList.item(i);
            if(isValidNode(controllerNode)&& controllerNode.getNodeName().equals("controller")) {
                NodeList actionList = controllerNode.getChildNodes();
                for (int j = 0; j < actionList.getLength(); j++) {
                    Node actionNode = actionList.item(j);
                    //查找action
                    if(isValidNode(actionNode) == false){continue;}
                    Element act = (Element) actionList.item(j);
                    String actionName = act.getAttribute("name");
                    String className = act.getAttribute("class");
                    String methodName = act.getAttribute("method");
                    System.out.println(actionName);
                    System.out.println(className);
                    System.out.println(methodName);
                    //找到action
                    if (actName.equals(actionName)) {
                        NodeList resultList = ((Node)act).getChildNodes();
                        for(int k=0; k<resultList.getLength(); k++){
                            Node resultNode = resultList.item(k);
                            if(isValidNode(resultNode)&&resultNode.getNodeName().equals("result")){
                                Element resultElem = (Element)resultNode;
                                String resultName = resultElem.getAttribute("name");
                                String resultType = resultElem.getAttribute("type");
                                String resultValue = resultElem.getAttribute("value");
                                Result result = new Result(resultName,resultType,resultValue);
                                results.add(result);
                            }
                        }
                        Action action = new Action(actionName,className,methodName,results);
                        return action;
                    }
            }   }
        }
        return null;
    }

    static boolean isValidNode(Node node){
        if (node.getNodeType() == Node.TEXT_NODE && node.getTextContent().trim().length() == 0){
            return false;
        }
        return true;
    }

}
