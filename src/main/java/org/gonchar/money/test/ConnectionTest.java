package org.gonchar.money.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {

    public static void main(final String[] args) {
        ConnectionTest test = new ConnectionTest();
        test.testConnection();
    }

    public void testConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.99.100:3306/test", "root", "Fghjrcbvfwbz");
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM Persons");
            while (result.next()) {
                System.out.println(result.getInt(1));
                System.out.println(result.getString(2));
            }

            result.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
