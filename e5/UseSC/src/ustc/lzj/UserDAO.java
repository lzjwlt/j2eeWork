package ustc.lzj;

import sc.ustc.dao.BaseDAO;

import java.sql.*;

public  class UserDAO extends BaseDAO {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public UserDAO(String driver, String url, String userName, String userPassword) {
        this.setDriver(driver);
        this.setUrl(url);
        this.setUserName(userName);
        this.setUserPassword(userPassword);
    }

    @Override
    public Connection openDBConnection() {
        try{
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            System.err.println("找不到驱动");
            e.printStackTrace();
        }
        try{
            connection = DriverManager.getConnection(url, userName, userPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean closeDBConnection() {
        if(connection != null){
            try {
                if(resultSet != null){
                    resultSet.close();
                }
                if(statement != null){
                    statement.close();
                }
                connection.close();
            }catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }


    @Override
    protected Object query(String sql) {
        UserBean userBean = new UserBean();
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            if(!resultSet.equals(null)){
                while (resultSet.next()){
                    userBean.setUserId(resultSet.getInt(1));
                    userBean.setUserName(resultSet.getString(2));
                    userBean.setUserPass(resultSet.getString(3));
                }
            }
            else{
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBean;
    }

    @Override
    protected boolean insert(String sql) {
        try{
            statement.execute(sql);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean update(String sql) {
        try{
            statement.execute(sql);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean delete(String sql) {
        try{
            statement.execute(sql);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
