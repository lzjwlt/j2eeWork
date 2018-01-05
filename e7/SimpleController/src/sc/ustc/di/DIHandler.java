package sc.ustc.di;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;

/**
 * @author : Li Zhijun
 * @email : ustclzj@foxmail.com
 * @date : 2018/1/5 下午2:40
 * @description :
 */
public class DIHandler {
    private static HashSet<Bean> beanHashSet ;


    public static boolean dependencyInject(Object subject, String actionClassName){
        boolean existsBean = false;
        try {
            beanHashSet = BeanParser.parse();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        for(Bean bean: beanHashSet){
            if(bean.getClazz().equals(actionClassName)){
                //存在与actionName同名的bean
                existsBean = true;
                List<Bean.Field> fields = bean.getFields();
                if(fields.isEmpty()){
                    //不存在依赖
                    return false;
                }
                //处理依赖
                for(Bean.Field field : fields) {
                    String fieldName = field.getName();
                    String beanRef = field.getBeanRef();
                    Object object = null;
                    //构造实例beanRef
                    for (Bean bean1 : beanHashSet) {
                        if (bean1.getId().equals(beanRef)) {
                            try {
                                object = Class.forName(bean1.getClazz()).newInstance();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    try {
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, Class.forName(bean.getClazz()));
                        Method methodSetBeanField = propertyDescriptor.getWriteMethod();
                        methodSetBeanField.invoke(subject, object);
                    } catch (IntrospectionException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
        if(existsBean == false){
            return false;
        }
        return true;
    }


}
