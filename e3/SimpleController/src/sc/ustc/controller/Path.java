package sc.ustc.controller;

public class Path {
    private  String contextPath;
    private  String XMLPath;
    private static Path instance = new Path();

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getXMLPath() {
        return XMLPath;
    }

    public void setXMLPath(String XMLPath) {
        this.XMLPath = XMLPath;
    }
    public static Path getInstance(){
        return instance;
    }


}
