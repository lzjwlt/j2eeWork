package sc.ustc.di;

import org.xml.sax.SAXException;

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
        HashSet hashSet = BeanParser.parse();

        System.out.println();

    }
}
