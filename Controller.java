
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
       currentSolution = randInt;
       usedDiagrams.add(randInt);
        
       for(int i=1; i<4; i++){
            
            while(usedOptions.contains(randInt) || currentSolution == randInt){
                randInt = rand.nextInt(db.countTuples("Diagramme"))+1;
            }
            
            usedOptions.add(randInt);
            fA[i] = db.getAnswer(randInt);
            
       }
       return mixAnswers(fA);
    }
    
    public boolean checkAnswer(int id) {
        
        if(id == currentSolution){
            return true;
        }else{
            return false;
        }
        
    }
    
    private Answer[] mixAnswers(Answer[] array) {
        Answer[] newArray = new Answer[4];
        ArrayList<Integer> usedInts = new ArrayList<>();
        
        int randInt = rand.nextInt(4);
        newArray[0] = array[randInt];
        usedInts.add(randInt);
        
        for(int i=1;i<4;i++){
            randInt = rand.nextInt(4);
            while(usedInts.contains(randInt)){   
                randInt = rand.nextInt(4);
            }
            usedInts.add(randInt);
            newArray[i] = array[randInt];
        }
        
        return newArray;
    }
    
    public void newRound() {
        usedOptions.clear();
        currentSolution = 0;
    }
    
    
    
    
}
