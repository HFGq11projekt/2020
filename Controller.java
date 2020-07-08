
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    
    //Bereits aus der Datenbank ausgelesene Städte als Antwortmöglichkeiten
    ArrayList<Integer> usedOptions = new ArrayList<>();
    //Bereits aus der Datenbank ausgelesene Klimadiagramme
    ArrayList<Integer> usedDiagrams = new ArrayList<>();
    
    DB db;
           
    Random rand = new Random();
    
    int currentSolution = 0;
    
    public Controller(DB db){
        this.db = db;
    }
    
    public String getPathOfSolution() {
        return db.getAnswer(currentSolution).pfad;
    }
    
    public Answer[] getAnswers() {
        
       Answer[] fA = new Answer[4];
       int randInt = rand.nextInt(db.countTuples("Diagramme"))+1;
       
       while(usedDiagrams.contains(randInt)){
           randInt = rand.nextInt(db.countTuples("Diagramme"))+1;
        }
       
       fA[0] = db.getAnswer(randInt);
       currentSolution = 0;
       usedDiagrams.add(randInt);
        
       for(int i=0; i<3; i++){
            

            while(usedOptions.contains(randInt)){
                randInt = rand.nextInt(db.countTuples("Diagramme"))+1;
            }
            
            usedOptions.add(randInt);
            fA[i] = db.getAnswer(randInt);
            
       }
       return fA;
    }
    
    public boolean checkAnswer(int id) {
        
        if(id == currentSolution){
            return true;
        }else{
            return false;
        }
        
    }
    
    public void newRound() {
        usedOptions.clear();
        currentSolution = 0;
    }
    
    
    
    
}
