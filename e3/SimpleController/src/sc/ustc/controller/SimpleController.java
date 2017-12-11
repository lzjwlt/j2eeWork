package sc.ustc.controller;

import net.sf.cglib.proxy.Enhancer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

public class SimpleController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected synchronized void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        Path.getInstance().setXMLPath(req.getServletContext().getRealPath("/WEB-INF/classes/controller.xml"));
        //获取action名称
        String servletPath = req.getServletPath();
        String actionName = servletPath.replaceAll("\\.sc","").substring(1);
        //解析action
        Action action = null;
        try {
            for(Action thisAction:Config.getInstance().getActions()){
                if(actionName.equals(thisAction.getName())){
                    action=thisAction;
                }
            }
            if(action == null){
                resp.sendRedirect(req.getServletContext().getContextPath()+ "/pages/error_action.jsp");
                return;
            }

            Class cls = Class.forName(action.getClassName());
            Method method = cls.getMethod(action.getMethodName(), HttpServletRequest.class);
            // 无代理
//            Object obj = cls.newInstance();
            //代理
            Object obj = CGLibProxyFactory.getProxy(cls,action);
            System.out.println("invoke proxy");
            String value = (String)method.invoke(obj,req);;
            System.out.println("proxy finished");
            for(Result result : action.getResults()){
                if(result.getName().equals(value)){
                    String contextPath = req.getContextPath();
                    System.out.println("contextPath= "+contextPath);
                    if(result.getType().equals("redirect")){
                        resp.sendRedirect(contextPath+"/"+result.getValue());
                        return;

                    }
                    else if(result.getType().equals("forward")){
                        req.getRequestDispatcher("/"+result.getValue()).forward(req,resp);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
