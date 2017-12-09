package sc.ustc.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


public class SimpleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        //获取action名称
        String servletPath = req.getServletPath();
        String actionName = servletPath.replaceAll("\\.sc","").substring(1);
        //解析action
        try {
            Action action = ActionParser.parse(req,resp,actionName);
            if(action == null){
                resp.sendRedirect(req.getServletContext().getContextPath()+ "/pages/error_action.jsp");
                return;
            }
            Class cls = Class.forName(action.getClassName());
            Object actObj = cls.newInstance();
            Method method = cls.getMethod(action.getMethodName(), HttpServletRequest.class);
            String Value = (String)method.invoke(actObj,req);

            for(Result result : action.getResults()){
                if(result.getName().equals(Value)){
                    String contextPath = req.getContextPath();
                    System.out.println("contextPath= "+contextPath);
                    if(result.getType().equals("redirect")){
                        resp.sendRedirect(contextPath+"/"+result.getValue());
                        return;
                    }
                    else if(result.getType().equals("forward")){
                        req.getRequestDispatcher("/"+result.getValue()).forward(req,resp);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
