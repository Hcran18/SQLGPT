package BusinessLogic.DataAccess.Service;

import BusinessLogic.DataAccess.DAO.DAOInterface;
import BusinessLogic.DataAccess.DAO.DataAccessObject;
import BusinessLogic.DataAccess.Database.DataAccessException;
import BusinessLogic.DataAccess.Database.Database;
import BusinessLogic.DataAccess.Database.DatabaseInterface;

import java.sql.Connection;

public class DataAccessService implements DASInterface {
    public String retrieveData (String sql) {
        DatabaseInterface db = new Database();
        DAOInterface DAO = new DataAccessObject();
        Connection connection = null;

        try {
            connection = db.openConnection();

            String tableData = DAO.retrieve(connection, sql);

            db.closeConnection(true);

            return tableData;
        }
        catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            return "Error accessing data";
        }
    }
}
