package sc.ustc.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
            ParseAction.parse(req,resp,actionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
