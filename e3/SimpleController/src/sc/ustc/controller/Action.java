package sc.ustc.controller;

import java.util.List;

public class Action {
    private String name;
    private String className;
    private String methodName;
    private List<Interceptor> interceptors;
    private List<Result> results;

    public Action(String name, String className, String methodName, List<Interceptor> interceptors, List<Result> results) {
        this.name = name;
        this.className = className;
        this.methodName = methodName;
        this.interceptors = interceptors;
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

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
