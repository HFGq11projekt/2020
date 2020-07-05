import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DB {
    
    public DB() {
        testen();

    }

    public static void testen() {
        Connection verbindung = null;
        try {
            // Datenbankverbindung herstellen
            verbindung = DriverManager.getConnection("jdbc:sqlite:db/database.db");
            System.out.println("Test");
            // Objekt erzeugen, das die Abwicklung von Anfragen uebernimmt
            Statement anfrage = verbindung.createStatement();

            //executeQuery gibt die Ergebnistabelle als ResultSet-Objekt zurueck (Alternative z. B.: executeUpdate, gibt Anzahl der modifizierten Zeilen oder 0 bei keinem Ergebnis zurueck)
            ResultSet ergebnis = anfrage.executeQuery("SELECT * FROM Diagramme;");

            //Ergebnistabelle ausdrucken (anstatt der Spaltenbezeichner koennten auch ganzzahlige Spaltennummern - erste Nummer: 1 - angegeben werden)
            //(Entsprechend den SQL-Typen der einzelnen Spaltenwerte koennen hier verschiedene Methoden zum Einlesen als passender Java-Typ verwendet werden.)
            while (ergebnis.next()) {
                System.out.println(ergebnis.getString("Land") + ", " + ergebnis.getString("Name") + ", " + ergebnis.getString("Länge"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (verbindung != null) {
                    verbindung.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
