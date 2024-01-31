package BusinessLogic.DataAccess.Service;

import BusinessLogic.DataAccess.DAO.DAOInterface;
import BusinessLogic.DataAccess.Database.DataAccessException;
import BusinessLogic.DataAccess.Database.DatabaseInterface;
import BusinessLogic.DataAccess.Database.SQLDatabase;
import BusinessLogic.DataAccess.DAO.SQLDAO;

import java.sql.Connection;

public class DataAccessService implements DASInterface {
    public String retrieveData (String sql) {
        DatabaseInterface db = new SQLDatabase();
        DAOInterface DAO = new SQLDAO();
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
