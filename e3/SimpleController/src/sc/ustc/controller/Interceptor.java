package sc.ustc.controller;

public class Interceptor{
    private String name;
    private String className;
    private String preDo;
    private String afterDo;

    public Interceptor(String name, String className, String preDo, String afterDo) {
        this.name = name;
        this.className = className;
        this.preDo = preDo;
        this.afterDo = afterDo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPreDo() {
        return preDo;
    }

    public void setPreDo(String preDo) {
        this.preDo = preDo;
    }

    public String getAfterDo() {
        return afterDo;
    }

    public void setAfterDo(String afterDo) {
        this.afterDo = afterDo;
    }
}
