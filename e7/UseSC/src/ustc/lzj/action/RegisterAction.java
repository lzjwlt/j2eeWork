package ustc.lzj.action;

import sc.ustc.dao.Conversation;
import ustc.lzj.UserBean;

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

    private boolean signUp(String name, String pass){
        UserBean queryBean = Conversation.query("username",name);
        if(queryBean != null){
            System.err.println("Username has already existed");
            return false;
        }
        UserBean toInsert = new UserBean();
        toInsert.setUserName(name);
        toInsert.setUserPass(pass);
        return Conversation.insert(toInsert,false) ;
    }

    public String handleRegister(HttpServletRequest req) throws IOException {
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
        if(signUp(inputName,inputKey)){
            session.setAttribute("userName",inputName);
            return "success";
        }
        else{
            session.setAttribute("ErrorInfo","用户已存在");
            return "failure";
        }
    }
}
