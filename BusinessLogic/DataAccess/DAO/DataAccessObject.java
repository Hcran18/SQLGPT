package BusinessLogic.DataAccess.DAO;

import BusinessLogic.DataAccess.Database.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringJoiner;

public class DataAccessObject implements DAOInterface {
    public String retrieve(Connection connection, String sql) throws DataAccessException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String result = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            StringJoiner resultJoiner = new StringJoiner(", "); // Adjust separator based on your needs

            while (resultSet.next()) {
                // Iterate through all columns in the result set
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    // Example: Retrieve data from each column
                    String columnName = resultSet.getMetaData().getColumnName(i);
                    String columnValue = resultSet.getString(i);

                    resultJoiner.add(columnName + ": " + columnValue);
                }
            }

            if (resultJoiner.toString() != null) {
                result = resultJoiner.toString();
            }
            else {
                return "Empty result";
            }

            return result;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error retrieving from Database");
        }
    }
}
