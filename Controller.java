
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    
    //Bereits aus der Datenbank ausgelesene Städte als Antwortmöglichkeiten
    int[] usedOptions = new int[4];
    //Bereits aus der Datenbank ausgelesene Klimadiagramme
    ArrayList<Integer> usedDiagrams = new ArrayList<>();
    
    DB db;
    
    public Controller(DB db){
        this.db = db;
    }

    //Holt das Diagramm und die dazugehörige Stadt aus der Datenbank
    //Diese darf im Spiel nur einmal vorkommen
    
    public Solution getUnusedQuizContent() {
        
        Random rand = new Random();
        int randInt = rand.nextInt(db.countTuples("Diagramme"));
        
        while(usedDiagrams.contains(randInt)){
           randInt = rand.nextInt(db.countTuples("Diagramme"));
        }
        
        usedDiagrams.add(randInt);
        usedOptions[0] = randInt;
        return db.getSolution(randInt); 
    }
    
    public FalseAnswer[] getFalseAnswers(int idOfTrueAnswer) {
       for(int i=0; i<3; i++){
           
       }
       return null;
    }
}
