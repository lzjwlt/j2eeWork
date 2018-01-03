package ustc.lzj.action;

import javax.servlet.http.HttpServletRequest;

public class AdminAction {


    public String handleAdmin(HttpServletRequest req){
        if(req.getParameter("adminName").equals("admin") && req.getParameter("password").equals("admin")){
            req.getSession().setAttribute("Authority","admin");
            return "success";
        }
        else {
            req.getSession().setAttribute("ErrorInfo","管理员账号/密码错误");
            return "failure";
        }
    }
}
