package ustc.lzj;

import sc.ustc.dao.*;

public class UserBean {
    private int id;
    private Object userName;
    private Object userPass;


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
