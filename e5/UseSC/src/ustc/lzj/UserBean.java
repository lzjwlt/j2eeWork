package ustc.lzj;

public class UserBean {
    private int userId;
    private String userName;
    private String userPass;

    private UserDAO userDAO =new UserDAO(
            "com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/sc",
            "root",
            "12345678");

    public boolean signIn(String name, String pass){
        userDAO.openDBConnection();
        UserBean queryBean = (UserBean) userDAO.query("SELECT * FROM USER WHERE USERNAME="+name);
        if(queryBean == null){
            userDAO.closeDBConnection();
            return false;
        }
        if(pass.equals(queryBean.userPass)){
            userDAO.closeDBConnection();
            return true;
        }
        userDAO.closeDBConnection();
        return false;
    }

    public boolean signUp(String name, String pass){
        userDAO.openDBConnection();
        UserBean queryBean = (UserBean) userDAO.query("SELECT * FROM USER WHERE USERNAME="+name);
        if(queryBean.userName != null || queryBean.userPass!=null){
            userDAO.closeDBConnection();
            return false;
        }
        String sql = String.format("INSERT INTO USER(username,userpass) VALUES ('%s' , '%s')", name,pass);
        if(userDAO.insert(sql) == true)
        {
            userDAO.closeDBConnection();
            return true;
        }
        userDAO.closeDBConnection();
        return false;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

}
