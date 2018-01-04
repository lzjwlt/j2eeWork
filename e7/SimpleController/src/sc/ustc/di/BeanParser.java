package sc.ustc.di;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

import static sc.ustc.controller.ActionParser.isValidNode;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2018/1/4 下午2:18
 * @Description :
 */
public class BeanParser {
    private static String file = "/Users/lzj/Projects/J2eeWork/e7/UseSC/src/di.xml";
    private static HashSet<Bean> beanHashSet = new HashSet<>();

    public static HashSet parse() throws ParserConfigurationException, IOException, SAXException {
        /**
         * @author : Li Zhijun
         * @date : 2018/1/4 下午2:44
         * @param : []
         * @descrption : 解析file中的bean配置
         */
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        Node root = doc.getFirstChild();
        NodeList beans = root.getChildNodes();
        for(int i=0; i<beans.getLength(); ++i){
            Node beanNode = beans.item(i);
            if(isValidNode(beanNode) && beanNode.getNodeName().equals("bean")){
                Bean bean = new Bean();
                Element beanElem = (Element)beanNode;
                bean.setId(beanElem.getAttribute("id"));
                bean.setClazz(beanElem.getAttribute("class"));
                //处理field
                NodeList fields = beanNode.getChildNodes();
                for(int j=0; j<fields.getLength(); j++){
                    Node fieldNode = fields.item(j);
                    if(isValidNode(fieldNode) && fieldNode.getNodeName().equals("field")){
                        Element fieldElem = (Element)fieldNode;
                        Bean.Field field = bean.new Field();
                        field.setName( fieldElem.getAttribute("name"));
                        field.setBeanRef(fieldElem.getAttribute("bean-ref"));
                        bean.getFields().add(field);
                    }
                }
                beanHashSet.add(bean);
            }
        }
        return beanHashSet;

    }
}
