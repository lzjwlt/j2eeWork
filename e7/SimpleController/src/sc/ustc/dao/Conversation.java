package sc.ustc.dao;


import java.lang.reflect.Field;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 18/1/2 下午7:49
 * @Describe :
 */
public class Conversation {
    private static Configuration.JDBC jdbc = Configuration.getInstance().getJdbc();
    private static Clazz clazz = Configuration.getInstance().getClazz();
    private static Connection connection = null;
    private static Statement statement =null;
    private static ResultSet resultSet = null;

    private static Class cls;

    static {
        try {
            cls = Class.forName(clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    static Connection openDBConnection() {
        /**
         * @author : Li Zhijun
         * @description :
         * @paras   []
         * @date:   18/1/2 下午8:08
         */
        try{
            Class.forName(jdbc.getDriverClass());
        }catch (ClassNotFoundException e){
            System.err.println("找不到驱动");
            e.printStackTrace();
        }
        try{
            connection = DriverManager.getConnection(jdbc.getUrlPath(), jdbc.getDbUserName(), jdbc.getDbUserPass());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    static boolean closeDBConnection() {
        /**
         * @author : Li Zhijun
         * @description :
         * @paras   []
         * @date:   18/1/2 下午8:08
         */
        if(connection != null){
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static <T> T query(int id){
        /**
         * @author : Li Zhijun
         * @description :
         * @paras   [id]
         * @date:   18/1/3 下午3:09
         */
        return query(clazz.getId(),""+id);
    }

    public static <T> T query(String attr, String value){
        /**
         * @author : Li Zhijun
         * @description : 根据属性值来查询记录，返回Bean对象
         * @paras   [attr, value]
         * @date:   18/1/3 下午2:29
         */
        String className = clazz.getName();
        T obj = null;
        try {
            obj = (T)cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(obj == null){
            System.err.println("Class not found");
            return null;
        }

        //Set ID
        int id=-1;
        String idColumn = clazz.getId();
        String idSql = String.format("SELECT %s FROM %s WHERE %s='%s'",idColumn,clazz.getTable(),attr,value) ;
        try {
            openDBConnection();
            statement = connection.prepareStatement(idSql);
            resultSet = statement.executeQuery(idSql);
            while(resultSet.next()){
                id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeDBConnection();
        }
        if(id == -1){
            System.err.println("Failed to query id");
            return null;
        }

        Field idField = null;
        try {
            idField = cls.getDeclaredField(clazz.getId());
            idField.setAccessible(true);
            idField.setInt(obj,id);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        //Set Properties
        for(Clazz.ClassProperty property : clazz.getProperties()){
            String name = property.getName();
            String column = property.getColumn();
            String type = property.getType();
            boolean lazy = property.getLazy();
            Field[] fields = cls.getDeclaredFields();
            for(Field field : fields) {
                if(! field.getName().equals(name))
                    continue;
                field.setAccessible(true);

                //懒加载, 先不加载，当调用get方法时再加载
                if(lazy){
                    String table = clazz.getTable();
                    try {
                        field.set(obj,LazyProxyFactory.getProxy(Object.class,table,column,idColumn,id));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                //非懒加载
                String sql = String.format("SELECT %s FROM %s WHERE %s = '%s'", column, clazz.getTable(), attr, value);
                try {
                    openDBConnection();
                    statement = connection.prepareStatement(sql);
                    resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        if (type.equals("String")) {
                            field.set(obj, resultSet.getString(1));
                        }
                        if (type.equals("int")) {
                            field.setInt(obj, resultSet.getInt(1));
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }finally {
                    closeDBConnection();
                }
            }
        }
        return obj;

    }

    public static boolean update(int id, String attr, String value){
        /**
         * @author : Li Zhijun
         * @description :
         * @paras   [id, attr, value]
         * @date:   18/1/3 下午4:10
         */

        String sql = String.format("UPDATE %s SET %s='%s' WHERE ID='%d' ",clazz.getTable(),attr,value,id);

        openDBConnection();
        int c = -1;
        try {
            statement = connection.prepareStatement(sql);
            c = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(c != 1){
            System.err.println(sql);
            System.err.println("Failed to update");
            return false;
        }
        return true;
    }


    public static boolean update(Object obj){
        /**
         * @author : Li Zhijun
         * @description :
         * @paras   [obj]
         * @date:   18/1/3 下午4:10
         */
        int id = -1;
        try {
            Field idField = cls.getDeclaredField("id");
            idField.setAccessible(true);
            id = idField.getInt(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for(Clazz.ClassProperty property : clazz.getProperties()){
            String name = property.getName();
            String column = property.getColumn();
            String type = property.getType();
            boolean lazy = property.getLazy();
            Field[] fields = cls.getDeclaredFields();
            for(Field field : fields) {
                if (!field.getName().equals(name))
                    continue;
                field.setAccessible(true);
                String sql = null;
                if(type.equals("int")){
                    int val = -1;
                    try {
                        val = field.getInt(obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    sql = String.format("UPDATE %s SET %s='%d' WHERE ID='%d'",clazz.getTable(),column,val,id);
                }
                if(type.equals("String")){
                    String val=null;
                    try {
                        val = (String)field.get(obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    sql = String.format("UPDATE %s SET %s='%s' WHERE ID='%d'",clazz.getTable(),column,val,id);
                }
                openDBConnection();
                try {
                    statement = connection.prepareStatement(sql);
                    int count = statement.executeUpdate(sql);
                    if(count != 1){
                        System.err.println("Failed to update");
                        return false;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }finally {
                    closeDBConnection();
                }
            }
        }
        return true;
    }

    public static boolean delete(int id){
        /**
         * @author : Li Zhijun
         * @description : 
         * @paras   [id]
         * @date:   18/1/3 下午4:25
         */
        String table = clazz.getTable();
        String idName = clazz.getId();
        String sql = String.format("DELETE FROM %s WHERE %s='%d'",table,idName,id);
        int count = -1;
        openDBConnection();
        try {
            statement = connection.prepareStatement(sql);
            count = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(count == 1)
            return true;
        return false;
    }

    public static boolean delete(Object obj){
        /**
         * @author : Li Zhijun
         * @description : 根据obj的id属性删除数据库的记录
         * @paras   [obj]
         * @date:   18/1/3 下午4:30
         */
        try {
            Field idField = cls.getDeclaredField(clazz.getId());
            int id = idField.getInt(obj);
            return delete(id);
        }  catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insert(Object obj,boolean coverFlag){
        /**
         * @author : Li Zhijun
         * @description :若coverFlag为false，则不插入id属性，由数据库自增
         * @paras   [obj]
         * @date:   18/1/3 下午4:52
         */
        //cls是否是obj的父类
        if(cls.isAssignableFrom(obj.getClass()) == false){
            System.err.println("Error Para");
            return false;
        }
        HashMap<String,String> hashMap = new HashMap<>();

        int id = -1;
        try {
            Field idField = cls.getDeclaredField(clazz.getId());
            idField.setAccessible(true);
            id = idField.getInt(obj);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if(coverFlag)
            hashMap.put(clazz.getId(),id+"");

        for(Clazz.ClassProperty property : clazz.getProperties()){
            String name = property.getName();
            String column = property.getColumn();
            String value = null;
            Field field = null;
            try {
                field = cls.getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            field.setAccessible(true);
            try {
                value = (String)field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            hashMap.put(column,value);
        }
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for(Map.Entry<String,String> entry :hashMap.entrySet()){
            sb1.append(entry.getKey()+",");
            sb2.append("'"+entry.getValue()+"',");
        }
        String columns = sb1.substring(0,sb1.length()-1);
        String values = sb2.substring(0,sb2.length()-1);

        String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", clazz.getTable(),columns,values);

        openDBConnection();
        int c = -1;
        try {
            statement = connection.prepareStatement(sql);
            c = statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(c == 1)
            return true;
        return false;
    }

    public static boolean insert(Object obj){
        /**
         * @author : Li Zhijun
         * @description : 插入数据库，不包含id
         * @paras   [
         *          obj：被插入的对象
         *          coverFlag：是否写入ID
         *          ]
         * @date:   18/1/3 下午5:17
         */
        return insert(obj,false);
    }
}
