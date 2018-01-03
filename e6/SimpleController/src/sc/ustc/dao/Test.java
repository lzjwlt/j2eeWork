package sc.ustc.dao;

import org.xml.sax.SAXException;
import test.UserBean1;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


import static sc.ustc.dao.Conversation.query;
import static sc.ustc.dao.Conversation.update;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 18/1/2 下午8:24
 * @Describe :
 */
public class Test {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        Configuration configuration = Configuration.getInstance();
        System.out.println(configuration.getJdbc().getDriverClass());


        UserBean1 userBean = query("username","lzjustc");

        System.out.println(userBean.getUserPass());
        System.out.println(userBean.getUserName());
    }
}
