package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseUtility {

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    // Connect to database
    public void connectToDatabase(String dbUrl,
                                  String dbUser,
                                  String dbPassword) {

        try {

            connection = DriverManager.getConnection(
                    dbUrl,
                    dbUser,
                    dbPassword);

            System.out.println("Database connection successful");

        } catch (Exception e) {

            System.out.println("Database connection failed");
            e.printStackTrace();
        }
    }

    // Execute SELECT query
    public ResultSet executeQuery(String query) {

        try {

            statement = connection.createStatement();

            resultSet = statement.executeQuery(query);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return resultSet;
    }

    // Close database connection
    public void closeConnection() {

        try {

            if(resultSet != null) {
                resultSet.close();
            }

            if(statement != null) {
                statement.close();
            }

            if(connection != null) {
                connection.close();
            }

            System.out.println("Database connection closed");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}