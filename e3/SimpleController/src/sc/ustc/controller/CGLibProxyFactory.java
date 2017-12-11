package sc.ustc.controller;

import net.sf.cglib.proxy.Enhancer;

public class CGLibProxyFactory {
    public static Object getProxy(Class clazz,Action action) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new CGLibProxy(action));
        return enhancer.create();
    }
}
