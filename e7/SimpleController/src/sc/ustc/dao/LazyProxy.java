package sc.ustc.dao;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 18/1/3 下午6:06
 * @Describe :
 */
public class LazyProxy implements MethodInterceptor {
    private String table;
    private String column;
    private String idColumn;
    private int id;

    public LazyProxy(String table, String column, String idColumn, int id) {
        this.table = table;
        this.column = column;
        this.idColumn = idColumn;
        this.id = id;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //query
        String sql = String.format("SELECT %s FROM %s WHERE %s = '%d'", column, table, idColumn, id);
        Connection conn = Conversation.openDBConnection();
        Statement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(sql);
        String value = null;
        while(rs.next()){
            value = rs.getString(1);
        }
        return value;
        //
    }
}
