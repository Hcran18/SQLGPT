package BusinessLogic.DataAccess.DAO;

import BusinessLogic.DataAccess.Database.DataAccessException;

import java.sql.Connection;

public interface DAOInterface {
    String retrieve(Connection connection, String sql) throws DataAccessException;
}
