
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    
    //Bereits aus der Datenbank ausgelesene Städte als Antwortmöglichkeiten
    ArrayList<Integer> usedOptions = new ArrayList<>();
    //Bereits aus der Datenbank ausgelesene Klimadiagramme
    ArrayList<Integer> usedDiagrams = new ArrayList<>();
    
    DB db;
    
            
    Random rand = new Random();
    
    public Controller(DB db){
        this.db = db;
    }

    //Holt das Diagramm und die dazugehörige Stadt aus der Datenbank
    //Diese darf im Spiel nur einmal vorkommen
    
    public Solution getUnusedQuizContent() {

        int randInt = rand.nextInt(db.countTuples("Diagramme"));
        
        while(usedDiagrams.contains(randInt)){
           randInt = rand.nextInt(db.countTuples("Diagramme"));
        }
        
        usedDiagrams.add(randInt);
        usedOptions.add(randInt);
        return db.getSolution(randInt); 
    }
    
    public FalseAnswer[] getFalseAnswers() {
        
       FalseAnswer[] fA = new FalseAnswer[3];
       
       for(int i=0; i<3; i++){
            int randInt = rand.nextInt(db.countTuples("Diagramme"));
            
            while(usedOptions.contains(randInt)){
                randInt = rand.nextInt(db.countTuples("Diagramme"));
            }
            
            usedOptions.add(randInt);
            fA[i] = db.getFalse(randInt);
            
       }
       return fA;
    }
}
