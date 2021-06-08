/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sengthavongphilavong
 */
public class DBManager {
    
    private static final String USER_NAME = "adventurer";
    private static final String PASSWORD = "dungeon";
    
    private static final String URL = "jdbc:derby:SavedCharacter;create=true";
    
    Connection conn;
    
    public DBManager() {
        establishConnection();
    }

    public Connection getConnection() {
        return conn;
    }
    
    public void establishConnection() {
        if (this.conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void closeConnections() {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public ResultSet myQuery(String sql) {
        Connection connection = this.conn;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        return resultSet;
    }
    
}
