package sc.ustc.controller;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CGLibProxy implements MethodInterceptor {
    ProxyHandler proxyHandler;//处理业务逻辑

    public CGLibProxy(Action action) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.proxyHandler = new ProxyHandler(action);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        proxyHandler.preDo();
        String object = (String) methodProxy.invokeSuper(obj, args);
        proxyHandler.afterDo(object);
        return object;
    }
}
