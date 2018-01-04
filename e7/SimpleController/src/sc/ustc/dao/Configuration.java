package sc.ustc.dao;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import sc.ustc.controller.Config;
import sc.ustc.controller.Path;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.Connection;

import static sc.ustc.controller.ActionParser.isValidNode;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 18/1/2 下午7:49
 * @Describe :
 */
public class Configuration{
    private static Configuration instance = new Configuration();

    private Configuration(){
        try {
            parse();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getInstance() {
        return instance;
    }

    private JDBC jdbc = new JDBC();
    private Clazz clazz = new Clazz();

    class JDBC{
        /**
         *  内部类，储存连接的数据库信息
         */
        private String driverClass;
        private String urlPath;
        private String dbUserName;
        private String dbUserPass;

        public String getDriverClass() {
            return driverClass;
        }

        public void setDriverClass(String driverClass) {
            this.driverClass = driverClass;
        }

        public String getUrlPath() {
            return urlPath;
        }

        public void setUrlPath(String urlPath) {
            this.urlPath = urlPath;
        }

        public String getDbUserName() {
            return dbUserName;
        }

        public void setDbUserName(String dbUserName) {
            this.dbUserName = dbUserName;
        }

        public String getDbUserPass() {
            return dbUserPass;
        }

        public void setDbUserPass(String dbUserPass) {
            this.dbUserPass = dbUserPass;
        }
    }

    public JDBC getJdbc() {
        return jdbc;
    }

    public Clazz getClazz() {
        return clazz;
    }

    public void parse() throws ParserConfigurationException, IOException, SAXException {

        String file = Path.getInstance().getOrMappingPath();
//        //测试路径
//        String file = "/Users/lzj/Nustore Files/Nutstore/homework/j2ee/j2eeWork/e6/UseSC/src/or_mapping.xml";
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);
        Node root = doc.getFirstChild();
        NodeList subRoot = root.getChildNodes();
        for (int i = 0; i < subRoot.getLength(); i++) {
            Node subNode = subRoot.item(i);
            //jdbc节点
            if (isValidNode(subNode) && subNode.getNodeName().equals("jdbc")) {
                NodeList properties = subNode.getChildNodes();
                for (int j = 0; j < properties.getLength(); j++) {
                    Node property = properties.item(j);
                    if (isValidNode(property) && property.getNodeName().equals("property")) {
                        NodeList propNodeList = property.getChildNodes();
                        String name=null;
                        String value=null;
                        for(int k=0; k<propNodeList.getLength();k++){
                            Node propNode = propNodeList.item(k);
                            if(isValidNode(propNode)){
                                if(propNode.getNodeName().equals("name")) {
                                    name = propNode.getTextContent();
                                }
                                if(propNode.getNodeName().equals("value")){
                                    value= propNode.getTextContent();
                                }
                            }
                            if(name!=null && value!=null){
                                if(name.equals("driver_class"))
                                    jdbc.setDriverClass(value);
                                if(name.equals("url_path"))
                                    jdbc.setUrlPath(value);
                                if(name.equals("db_username"))
                                    jdbc.setDbUserName(value);
                                if(name.equals("db_userpassword"))
                                    jdbc.setDbUserPass(value);
                            }
                        }
                    }
                }
            }

            //class节点
            if (isValidNode(subNode) && subNode.getNodeName().equals("class")) {
                NodeList classRoot = subNode.getChildNodes();
                for(int j=0; j<classRoot.getLength(); j++){
                    Node classSub = classRoot.item(j);
                    if(isValidNode(classSub)){
                        if(classSub.getNodeName().equals("name")){
                            clazz.setName(classSub.getTextContent());
                        }
                        if(classSub.getNodeName().equals("table")){
                            clazz.setTable(classSub.getTextContent());
                        }
                        if(classSub.getNodeName().equals("id")){
                            clazz.setId(classSub.getTextContent().trim());
                        }
                        if(classSub.getNodeName().equals("property")){
                            Clazz.ClassProperty classProperty =  clazz.new ClassProperty () ;
                            NodeList propertyList = classSub.getChildNodes();
                            for(int k=0; k<propertyList.getLength(); k++){
                                Node property = propertyList.item(k);
                                if(isValidNode(property)){
                                    String value = property.getTextContent();
                                    if(property.getNodeName().equals("name"))
                                        classProperty.setName(value);
                                    if(property.getNodeName().equals("column"))
                                        classProperty.setColumn(value);
                                    if(property.getNodeName().equals("type"))
                                        classProperty.setType(value);
                                    if(property.getNodeName().equals("lazy"))
                                        classProperty.setLazy(Boolean.parseBoolean(value));
                                }
                            }
                            this.clazz.getProperties().add(classProperty);
                        }
                    }
                }
            }


        }
    }



}
