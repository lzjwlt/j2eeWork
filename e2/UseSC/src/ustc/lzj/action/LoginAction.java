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

    public LoginAction() {
        System.out.println("Init a LoginAction");
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public void setInputKey(String inputKey) {
        this.inputKey = inputKey;
    }

    public String getInputName() {

        return inputName;
    }

    public String getInputKey() {
        return inputKey;
    }

    public String handleLogin(HttpServletRequest req) throws IOException {
        this.setInputName(req.getParameter("name"));
        this.setInputKey(req.getParameter("password"));
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
