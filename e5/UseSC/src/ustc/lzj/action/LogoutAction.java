package ustc.lzj.action;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction {
    public String handleLogout(HttpServletRequest req){
        HttpSession session= req.getSession();
        session.setAttribute("userName","");
        return "success";
    }
}
