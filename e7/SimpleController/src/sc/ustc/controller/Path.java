package sc.ustc.controller;

public class Path {
    private String contextPath;
    private String xmlPath;
    private String logfilePath;
    private String orMappingPath;
    private String diPath;

    public String getDiPath() {
        return diPath;
    }

    public void setDiPath(String diPath) {
        this.diPath = diPath;
    }

    public String getOrMappingPath() {
        return orMappingPath;
    }

    public void setOrMappingPath(String orMappingPath) {
        this.orMappingPath = orMappingPath;
    }

    private static Path instance = new Path();

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String XMLPath) {
        this.xmlPath = XMLPath;
    }
    public static Path getInstance(){
        return instance;
    }

    public String getLogfilePath() {
        return logfilePath;
    }

    public void setLogfilePath(String logfilePath) {
        this.logfilePath = logfilePath;
    }
}
