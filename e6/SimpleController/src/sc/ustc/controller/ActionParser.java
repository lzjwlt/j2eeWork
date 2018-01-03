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
    public static ArrayList<Action> parse() throws Exception{
        ArrayList<Action> actions = new ArrayList<>();
        //读取xml路径并使用DOM解析
        String file = Path.getInstance().getXmlPath();
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
//                    if (actName.equals(actionName)) {
                        //继续找interception-ref
                    NodeList interceptorRefList = act.getChildNodes();
                    ArrayList<Interceptor> interceptors = new ArrayList<>();
                    ArrayList<Result> results = new ArrayList<>();
                        for(int k=0;k<interceptorRefList.getLength();k++){
                            Node interceptorRefNode = interceptorRefList.item(k);
                            if(isValidNode(interceptorRefNode)&&interceptorRefNode.getNodeName().equals("interceptor-ref")){
                                Element interceptorElem = (Element)interceptorRefNode;
                                String interceptorName = interceptorElem.getAttribute("name");
                                for(Interceptor interceptor:Config.getInterceptors()){
                                    if(interceptorName.equals(interceptor.getName())) {
                                        interceptors.add(interceptor);
                                    }
                                }
                            }
                        }
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
                        Action action = new Action(actionName,className,methodName,interceptors,results);
                    actions.add(action);
                }
            }
        }
        return  actions;
    }

    public static boolean isValidNode(Node node){
        if(node == null){
            return false;
        }
        if (node.getNodeType() == Node.TEXT_NODE && node.getTextContent().trim().length() == 0){
            return false;
        }
        return true;
    }
}
