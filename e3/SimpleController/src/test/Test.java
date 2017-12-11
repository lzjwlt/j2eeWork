package test;

import sc.ustc.controller.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        Path.getInstance().setXMLPath("C:\\Users\\lzjwlt\\Desktop\\temp\\j2ee\\E3\\UseSC\\src\\controller.xml");
        List interceptors = InterceptorParser.parse();
        Config config = Config.getInstance();
        System.out.println(interceptors);

    }
}
