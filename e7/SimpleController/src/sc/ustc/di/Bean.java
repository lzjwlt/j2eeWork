package sc.ustc.di;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : Li Zhijun
 * @Email : ustclzj@foxmail.com
 * @Date : 2018/1/4 下午2:21
 * @Description :
 */
public class Bean {
    private String id;
    private String clazz;
    private List<Field> fields = new ArrayList();


    class Field{
        private String name;
        private String beanRef;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBeanRef() {
            return beanRef;
        }

        public void setBeanRef(String beanRef) {
            this.beanRef = beanRef;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public List<Field> getFields() {
        return fields;
    }
}
