package sc.ustc.dao;

public abstract class BaseDAO implements DAO{
    protected String driver;
    protected String url;
    protected String userName;
    protected String userPassword;

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    protected abstract Object query(String sql);
    protected abstract boolean insert(String sql);
    protected abstract boolean update(String sql);
    protected abstract boolean delete(String sql);
}
