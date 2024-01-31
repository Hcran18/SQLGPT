package BusinessLogic.DataAccess.Database.Service;

import BusinessLogic.DataAccess.Database.DAO.DataAccessObject;
import BusinessLogic.DataAccess.Database.DataAccessException;
import BusinessLogic.DataAccess.Database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataAccessService {
    private Database db;
    private DataAccessObject DAO;

    public String retrieveData (String sql) {
        db = new Database();
        DAO = new DataAccessObject();
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
