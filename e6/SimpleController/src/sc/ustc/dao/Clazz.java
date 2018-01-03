package sc.ustc.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 18/1/2 下午7:56
 * @Describe :
 */
public class Clazz {
    private String name;
    private String table;
    private String id;
    private List<ClassProperty> properties;

    public List<ClassProperty> getProperties() {
        return properties;
    }

    public Clazz() {
        properties = new ArrayList<>();
    }


    public class ClassProperty{
        private String name;
        private String column;
        private String type;
        private boolean lazy;

        public String getName() {
            return name;
        }

        public ClassProperty setName(String name) {
            this.name = name;
            return this;
        }

        public String getColumn() {
            return column;
        }

        public ClassProperty setColumn(String column) {
            this.column = column;
            return this;
        }

        public String getType() {
            return type;
        }

        public ClassProperty setType(String type) {
            this.type = type;
            return this;
        }

        public boolean getLazy() {
            return lazy;
        }

        public ClassProperty setLazy(boolean lazy) {
            this.lazy = lazy;
            return this;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
