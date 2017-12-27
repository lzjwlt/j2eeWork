package sc.ustc.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyHandler {
    private List<Interceptor> interceptors;
    private String actionName;
    private HashMap<Object,Method[]> intercptObjs = new HashMap<>();

    public ProxyHandler(Action action) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        this.actionName = action.getName();
        this. interceptors = action.getInterceptors();
        for(Interceptor interceptor : interceptors){
            Class cls = Class.forName(interceptor.getClassName());
            String preDo = interceptor.getPreDo();
            String afterDo = interceptor.getAfterDo();
            Method[] methods = new Method[2];
            if(preDo != null){
                Method preDoMethod = cls.getMethod(preDo);//参数
                methods[0] = preDoMethod;
            }
            if(afterDo != null){
                Method afterDoMethod = cls.getMethod(afterDo,String.class,String.class);
                methods[1] = afterDoMethod;
            }
            intercptObjs.put(cls.newInstance(),methods);
            System.out.println("size of hashmap:"+intercptObjs.size());
        }
    }

    public void preDo() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println("PRE_DO");
        for(Map.Entry<Object,Method[]> entry : intercptObjs.entrySet()){
            if(entry.getValue() [0] != null ){
                Method preDoMethod = entry.getValue()[0];
                preDoMethod.invoke(entry.getKey());
            }
        }
    }

    public void afterDo(String resultName) throws InvocationTargetException, IllegalAccessException {
        System.out.println("AFTER_DO");
        for(Map.Entry<Object,Method[]> entry : intercptObjs.entrySet()){
            if(entry.getValue() [1] != null ){
                Method afterDoMethod = entry.getValue()[1];
                afterDoMethod.invoke(entry.getKey(),actionName,resultName);
            }
        }
    }
}
