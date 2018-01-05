package sc.ustc.di.test;

import org.xml.sax.SAXException;
import sc.ustc.di.BeanParser;
import sc.ustc.di.DIHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2018/1/4 下午2:44
 * @Description :
 */
public class Test {
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException {
        LoginAction loginAction = new LoginAction();
        boolean b = DIHandler.dependencyInject(loginAction,"loginAction");
        System.out.println(b);

    }
}
