package ustc.lzj.action;

import ustc.lzj.PasswdMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterAction {
    private String inputName ;
    private String inputKey;

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

    public String handleRegister(HttpServletRequest req) throws IOException {
        PasswdMap pm=new PasswdMap(req);
        this.setInputName(req.getParameter("name"));
        this.setInputKey(req.getParameter("password"));
        HttpSession session = req.getSession();
        if(inputKey==null || inputName==null){
            session.setAttribute("ErrorInfo","输入不能为空");
            return "failure";
        }
        if(inputKey.trim().length()==0 || inputName.trim().length()==0){
            session.setAttribute("ErrorInfo","输入不能为空");
            return "failure";
        }
        if(pm.getPasswd(inputName)!=null){
            session.setAttribute("ErrorInfo","用户已存在");
            return "failure";
        }
        else{
            pm.addUser(inputName,inputKey);
            session.setAttribute("userName",inputName);
            return "success";
        }
    }
}
