package sc.ustc.controller;

import java.util.List;


public class Config {
    private List<Action> actions;
    private static List<Interceptor> interceptors;
    private static Config instance = new Config();
    static {
        try {
            interceptors = InterceptorParser.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static List<Interceptor> getInterceptors(){
        try {
            interceptors = InterceptorParser.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return interceptors;
    }

    public List<Action> getActions(){
        return actions;
    }

    public Config() {
        try {
            this.actions = ActionParser.parse();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Config getInstance(){
        return instance;
    }


}
