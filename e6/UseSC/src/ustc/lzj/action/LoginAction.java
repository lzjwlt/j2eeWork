package ustc.lzj.action;

import com.sun.deploy.net.HttpRequest;
import ustc.lzj.PasswdMap;
import ustc.lzj.UserBean;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ustc.lzj.UserBean.signIn;

public class LoginAction {
    private String inputName;
    private String inputKey;


    public String handleLogin(HttpServletRequest req) throws IOException {
        inputName=req.getParameter("name");
        inputKey=req.getParameter("password");
        System.out.println("invoke handleLogin:");
        HttpSession session = req.getSession();

        if(inputKey==null || inputName==null){
            session.setAttribute("ErrorInfo","输入不能为空");
            return "failure";
        }
        if(inputKey.trim().length()==0 || inputName.trim().length()==0){
            session.setAttribute("ErrorInfo","输入不能为空");
            return "failure";
        }
        if( signIn(inputName,inputKey)){
            return "success";
        }
        else{
            session.setAttribute("ErrorInfo","用户/密码不正确");
            return "failure";
        }
    }
}
