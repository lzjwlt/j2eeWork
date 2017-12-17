package ustc.lzj.action;

import com.sun.deploy.net.HttpRequest;
import ustc.lzj.PasswdMap;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginAction {
    private String inputName;
    private String inputKey;


    public String handleLogin(HttpServletRequest req) throws IOException {
        inputName=req.getParameter("name");
        inputKey=req.getParameter("password");
        System.out.println("invoke handleLogin:");
        PasswdMap passwdMap = new PasswdMap(req);
        HttpSession session = req.getSession();

        if(inputKey==null || inputName==null){
            session.setAttribute("ErrorInfo","输入不能为空");
            return "failure";
        }
        if(inputKey.trim().length()==0 || inputName.trim().length()==0){
            session.setAttribute("ErrorInfo","输入不能为空");
            return "failure";
        }
        if(passwdMap.getPasswd(inputName)==null){
            session.setAttribute("ErrorInfo","用户/密码不正确");
            return "failure";
        }
        if(passwdMap.getPasswd(inputName).equals(inputKey)){
            session.setAttribute("userName",inputName);
            return "success";
        }
        else{
            session.setAttribute("ErrorInfo","用户/密码不正确");
            return "failure";
        }
    }
}
