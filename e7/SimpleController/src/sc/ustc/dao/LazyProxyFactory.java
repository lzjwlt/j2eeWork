package sc.ustc.dao;

import net.sf.cglib.proxy.Enhancer;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 18/1/3 下午6:18
 * @Describe :
 */
public class LazyProxyFactory {
    public static Object getProxy(Class clazz, String table,
                                  String column, String idColumn,
                                  int id)  {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(new LazyProxy(table,column,idColumn,id));
        return enhancer.create();
    }
}
