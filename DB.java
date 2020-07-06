import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DB {

    Connection verbindung;
    Statement anfrage;
    
    public DB() {
        verbindung = null;
        anfrage = null;

        try {
            // Datenbankverbindung herstellen
            verbindung = DriverManager.getConnection("jdbc:sqlite:db/database.db");
            // Objekt erzeugen, das die Abwicklung von Anfragen uebernimmt
            anfrage = verbindung.createStatement();

        }catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    public ResultSet getTupleFromDatabase(String table, int ID) {
        ResultSet ergebnis = null;

        try {
            ergebnis = anfrage.executeQuery("SELECT * FROM " + table + " WHERE ID = " + ID);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ergebnis;
    }

    public int countTuples(String table) {
        int numberOfTuples = 0;

        try {
            ResultSet numberOfTuplesSet = anfrage.executeQuery("SELECT COUNT(ID) FROM " + table);
            numberOfTuples = numberOfTuplesSet.getInt("COUNT(ID)");
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return numberOfTuples;
    }

    public Solution getSolution(int id){
        Solution solution = null;

        try{
            ResultSet solutionSet = anfrage.executeQuery("SELECT * FROM Diagramme WHERE ID = " + id);
            int key = solutionSet.getInt("ID");
            String name = solutionSet.getString("Name");
            String land = solutionSet.getString("Land");
            int breite = solutionSet.getInt("Breite");
            int laenge = solutionSet.getInt("Länge");
            String pfad = solutionSet.getString("Diagramm");
            solution = new Solution(key, name, land, breite, laenge, pfad);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return solution;
    }

    public FalseAnswer getFalse(int id){
        FalseAnswer falseAnswer = null;
        
        try{
            ResultSet falseSet = anfrage.executeQuery("SELECT ID,Name,Land,Breite,Länge FROM Diagramme WHERE ID = " + id);
            int key = falseSet.getInt("ID");
            String name = falseSet.getString("Name");
            String land = falseSet.getString("Land");
            int breite = falseSet.getInt("Breite");
            int laenge = falseSet.getInt("Länge");
            falseAnswer = new FalseAnswer(key, name, land, breite, laenge);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return falseAnswer;
    }
    
}
