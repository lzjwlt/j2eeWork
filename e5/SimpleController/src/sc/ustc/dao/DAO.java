package sc.ustc.dao;

import java.sql.Connection;

public interface DAO {
    Connection openDBConnection();
    boolean closeDBConnection();
}
