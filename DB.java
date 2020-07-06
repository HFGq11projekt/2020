import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Random;

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

    public Solution getSolution(){

        Random rand = new Random();
        Solution solution = null;

        try{
            ResultSet solutionSet = anfrage.executeQuery("SELECT * FROM Diagramme WHERE ID = " + rand.nextInt(countTuples("Diagramme")+1));
            String name = solutionSet.getString("Name");
            String land = solutionSet.getString("Land");
            int breite = solutionSet.getInt("Breite");
            int laenge = solutionSet.getInt("Länge");
            String pfad = solutionSet.getString("Diagramm");
            solution = new Solution(name, land, breite, laenge, pfad);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return solution;
    }

}
