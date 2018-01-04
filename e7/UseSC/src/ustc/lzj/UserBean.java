package ustc.lzj;

import sc.ustc.dao.*;

public class UserBean {
    private int id;
    private Object userName;
    private Object userPass;


    public static boolean signIn(String name, String pass){
        /**
         * @author : Li Zhijun
         * @description :
         * @paras   [name, pass]
         * @date:   18/1/3 下午7:29
         */
        UserBean queryBean = Conversation.query("userName",name);
        if(queryBean == null){
            System.err.println("Username can't be found in db");
            return false;
        }
        if(pass.equals(queryBean.userPass)){
            return true;
        }
        return false;
    }

    public static boolean signUp(String name, String pass){
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getUserName() {
        return userName;
    }

    public void setUserName(Object userName) {
        this.userName = userName;
    }

    public Object getUserPass() {
        return userPass;
    }

    public void setUserPass(Object userPass) {
        this.userPass = userPass;
    }
}
