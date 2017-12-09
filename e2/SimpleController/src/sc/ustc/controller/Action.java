package sc.ustc.controller;

import java.util.List;

public class Action {
    private String name;
    private String className;
    private String methodName;
    private List<Result> results;

    public Action(String name, String className, String methodName,  List<Result> results) {
        this.name = name;
        this.className = className;
        this.methodName = methodName;
        this.results = results;
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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
