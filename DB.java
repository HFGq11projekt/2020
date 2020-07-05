import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DB {
    
    public DB() {
        
        

    }
    
    public ResultSet getTupleFromDatabase(String table, int ID) {
        ResultSet ergebnis = null;
        try{
            ergebnis = connect().executeQuery("SELECT * FROM " + table + " WHERE ID = " + ID);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return ergebnis;
    }
    
    public void countTuples(String table) {
        ResultSet numberOfTuplesSet = null;
        int numberOfTuples = 0;
        try{
            numberOfTuplesSet = connect().executeQuery("SELECT COUNT(ID) FROM " + table);
            //numberOfTuples = numberOfTuplesSet.getInt("COUNT(ID)");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        System.out.println(numberOfTuplesSet);
    }
    
    public Statement connect() {
        
        Connection verbindung = null;
        Statement anfrage = null;
        
        try {
            // Datenbankverbindung herstellen
            verbindung = DriverManager.getConnection("jdbc:sqlite:db/database.db");
            // Objekt erzeugen, das die Abwicklung von Anfragen uebernimmt
            anfrage = verbindung.createStatement();

        }catch (SQLException e) {
            
            System.out.println(e.getMessage());
            
        }finally {
            try {
                if (verbindung != null) {
                    verbindung.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        return anfrage;
        
    }
    
}
