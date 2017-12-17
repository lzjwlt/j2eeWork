package test;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {
    Dog dog=new Dog();
    public CGLibProxy() {

    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        dog.bark();
        methodProxy.invokeSuper(o,objects);
        dog.woo("foo","bar");
        return null;
    }
}
