package ustc.lzj.action;

import sc.ustc.dao.Conversation;
import ustc.lzj.UserBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginAction {
    private UserBean userBean;

    private boolean signIn(){
        /**
         * @author : Li Zhijun
         * @description :
         * @paras   [name, pass]
         * @date:   18/1/3 下午7:29
         */
        UserBean queryBean = Conversation.query("userName",userBean.getUserName().toString());
        if(queryBean == null){
            System.err.println("Username can't be found in db");
            return false;
        }
        if(userBean.getUserPass().equals(queryBean.getUserPass().toString())){
            return true;
        }
        return false;
    }


    public String handleLogin(HttpServletRequest req) throws IOException {
        if(userBean == null){
            System.err.println("null userBean");
            return "failure";
        }
        userBean.setUserName(req.getParameter("name"));
        userBean.setUserPass(req.getParameter("password"));
        System.out.println("invoke handleLogin:");
        HttpSession session = req.getSession();

        if(userBean.getUserName()==null || userBean.getUserPass()==null){
            session.setAttribute("ErrorInfo","输入不能为空");
            return "failure";
        }
        if(userBean.getUserName().toString().trim().length()==0 || userBean.getUserPass().toString().trim().length()==0){
            session.setAttribute("ErrorInfo","输入不能为空");
            return "failure";
        }
        if( signIn()){
            return "success";
        }
        else{
            session.setAttribute("ErrorInfo","用户/密码不正确");
            return "failure";
        }
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
}
