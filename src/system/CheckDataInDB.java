/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author sengthavongphilavong
 * 
 * Use this class to test with DataBase in game
 */
public class CheckDataInDB {

    DBManager dBManager;

    public CheckDataInDB() {
        dBManager = new DBManager();
    }

    public void writeOutAllSaveInDB() {
        
        System.out.println("Printing DB data");
        
        ResultSet rs = dBManager.myQuery("SELECT * FROM SavedCharacter");
        
        try {
            while (rs.next()) {                
                String name = rs.getString(1);
                int exp = rs.getInt(2);
                int potion = rs.getInt(3);
                String job = rs.getString(4);
                
                System.out.println("Name: "+name+", EXP: "+exp+", Potion: "+potion+", JOB: "+job);
                
            }
            
            dBManager.closeConnections();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void createTable() {
        try {
            Statement statement = dBManager.getConnection().createStatement();
            
            String sqlCreate = "CREATE TABLE SavedCharacter ( NAME VARCHAR(20) "
                    + "NOT NULL, EXP INT, POTION INT, CLASS VARCHAR(10))";
            
            statement.executeUpdate(sqlCreate);
            
            String printOutString = "Created Table";
            
            System.out.println(printOutString);
            
            dBManager.closeConnections();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void deleteFromTable(String characterName) {
        try {
            Statement statement = dBManager.getConnection().createStatement();
            
            String sqlDelete = "DELETE FROM SavedCharacter WHERE NAME = '"+characterName+"'";
            
            statement.executeUpdate(sqlDelete);
            
            System.out.println("Deleted Data: "+characterName);
            
            dBManager.closeConnections();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertToTable(String name, int exp, int potion, String job) {
        
        try {
            dBManager = new DBManager();
            
            Statement statement = dBManager.getConnection().createStatement();
            
            String sqlInsert = "INSERT INTO SAVEDCHARACTER VALUES ('"+name+"',"
                    + " "+exp+", "+potion+", '"+job+"')";
            statement.executeUpdate(sqlInsert);
            
            System.out.println("Added!");
            
            dBManager.closeConnections();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }

    public static void main(String[] args) {
        CheckDataInDB tdb = new CheckDataInDB();
        
//        tdb.testCreateTable();
        
        tdb.writeOutAllSaveInDB();
        
//        tdb.insertToTable("AJAX", 100, 10, "warrior");

//        tdb.deleteFromTable("RIPPER");
    }
}
