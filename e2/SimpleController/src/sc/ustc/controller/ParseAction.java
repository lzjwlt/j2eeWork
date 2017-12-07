package sc.ustc.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.reflect.Method;


public class ParseAction {


    public static void parse(HttpServletRequest req, HttpServletResponse resp, String actName) throws Exception{
        //读取xml路径并使用DOM解析
        String file = req.getServletContext().getRealPath("/WEB-INF/classes/controller.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        Node root = doc.getFirstChild();
        NodeList controllerList = root.getChildNodes();
        for(int i=0;i<controllerList.getLength();i++) {
            Node controllerNode = controllerList.item(i);
            if (!(controllerNode.getNodeType() == Node.TEXT_NODE && controllerNode.getTextContent().trim().length() == 0)) {
                NodeList actionList = controllerNode.getChildNodes();
                for (int j = 0; j < actionList.getLength(); j++) {
                    Node actionNode = actionList.item(j);
                    if (!(actionNode.getNodeType() == Node.TEXT_NODE && actionNode.getTextContent().trim().length() == 0)) {
                        Element act = (Element) actionList.item(j);
                        String actionName = act.getAttribute("name");
                        String className = act.getAttribute("class");
                        String methodName = act.getAttribute("method");
                        System.out.println(actionName);
                        System.out.println(className);
                        System.out.println(methodName);
                        if (actName.equals(actionName)) {
                            Class cls = Class.forName(className);
                            Object actObj = cls.newInstance();
                            Method method = cls.getMethod(methodName, HttpServletRequest.class);
                            String result = (String)method.invoke(actObj,req);
                            NodeList resultList = ((Node)act).getChildNodes();
                            for(int k=0; k<resultList.getLength(); k++){
                                Node resultNode = resultList.item(k);
                                if (!(resultNode.getNodeType() == Node.TEXT_NODE && resultNode.getTextContent().trim().length() == 0)) {
                                    Element resultElem = (Element)resultList.item(k);
                                    String resultName = resultElem.getAttribute("name");
                                    String resultType = resultElem.getAttribute("type");
                                    String resultValue = resultElem.getAttribute("value");
                                    if(resultName.equals(result)){
                                        String contextPath = req.getContextPath();
                                        System.out.println("contextPath= "+contextPath);
                                        if(resultType.equals("redirect")){
                                            resp.sendRedirect(contextPath+"/"+resultValue);
                                            return;
                                        }
                                        else if(resultType.equals("forward")){
                                            req.getRequestDispatcher("/"+resultValue).forward(req,resp);
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        resp.sendRedirect(req.getServletContext().getContextPath()+ "/pages/error_action.jsp");
    }
}
