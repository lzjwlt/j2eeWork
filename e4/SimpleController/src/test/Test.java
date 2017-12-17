package test;

import net.sf.cglib.proxy.Enhancer;


public class Test {
    public static void main(String[] args) throws Exception {
        CGLibProxy cgLibProxy = new CGLibProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Cat.class);
        enhancer.setCallback(cgLibProxy);
        Cat cat = (Cat)enhancer.create();
        cat.miao();


    }
}
