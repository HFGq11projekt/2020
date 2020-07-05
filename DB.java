import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Random;

public class DB {

    public ResultSet getTupleFromDatabase(String table, int ID) {
        ResultSet ergebnis = null;
        Connection verbindung = null;
        Statement anfrage = null;

        try {
            // Datenbankverbindung herstellen
            verbindung = DriverManager.getConnection("jdbc:sqlite:db/database.db");
            // Objekt erzeugen, das die Abwicklung von Anfragen uebernimmt
            anfrage = verbindung.createStatement();
            
            ergebnis = anfrage.executeQuery("SELECT * FROM " + table + " WHERE ID = " + ID);

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
        
        return ergebnis;
    }
    
    public int countTuples(String table) {
        ResultSet numberOfTuplesSet = null;
        Connection verbindung = null;
        Statement anfrage = null;
        int numberOfTuples = 0;

        try {
            // Datenbankverbindung herstellen
            verbindung = DriverManager.getConnection("jdbc:sqlite:db/database.db");
            // Objekt erzeugen, das die Abwicklung von Anfragen uebernimmt
            anfrage = verbindung.createStatement();
            
            numberOfTuplesSet = anfrage.executeQuery("SELECT COUNT(ID) FROM " + table);
            numberOfTuples = numberOfTuplesSet.getInt("COUNT(ID)");

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
       
        return numberOfTuples;
    }

    public Solution getSolution(){
     
        Random rand = new Random();
        ResultSet solutionSet = getTupleFromDatabase("Diagramme", rand.nextInt(countTuples("Diagramme")+1));
        Solution solution = null;
        
        try{
            String name = solutionSet.getString("Name");
            String land = solutionSet.getString("Land");
            int breite = solutionSet.getInt("Breite");
            int laenge = solutionSet.getInt("Länge");
            String path = solutionSet.getString("Diagramm");
            solution = new Solution(name, land, breite, laenge, path);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return solution;
    }
    
}
