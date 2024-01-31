package BusinessLogic.DataAccess.Database;

import java.sql.Connection;

public interface DatabaseInterface {
    Connection openConnection() throws DataAccessException;
    Connection getConnection() throws DataAccessException;
    void closeConnection(boolean commit);
}
