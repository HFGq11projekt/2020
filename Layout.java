import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.application.Platform;

public class Layout extends Application
{
    @FXML
    private Button Knopf1;
    @FXML
    private Button Knopf2;
    @FXML
    private Button Knopf3;
    @FXML
    private Button Knopf4;
    @FXML
    private ImageView Diagramm;
    private Answer[] answers = new Answer[4];

    DB db;
    Controller control;

    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader =  new  FXMLLoader(getClass().getResource("Layout.fxml"));
        loader.setController(this);

        db = new DB();
        control = new Controller(db);

        Pane layout = loader.load();
        Scene scene =  new  Scene(layout);
        setAnswers();
        setImage();
        stage.setScene(scene);
        stage.show();
    }

    public void setImage()
    {
        Image image = new Image(control.getPathOfSolution());
        Diagramm.setImage(image);
    }

    public void setAnswers()
    {
        answers = control.getAnswers();  
        Knopf1.setText(answers[0].name + " , " + answers[0].land);
        Knopf2.setText(answers[1].name + " , " + answers[1].land);
        Knopf3.setText(answers[2].name + " , " + answers[2].land); 
        Knopf4.setText(answers[3].name + " , " + answers[3].land); 
    }

    public void Knopf1Gedrueckt()
    {

        if(control.checkAnswer(answers[0].id) == true)
        {
          
            Knopf1.setStyle("-fx-background-color: #00ff00");
            
            Reset();
            
        }
        else
        {
            Knopf1.setStyle("-fx-background-color: #ff0000");
            Platform.exit();
        }

    }

    public void Knopf2Gedrueckt()
    {
        if(control.checkAnswer(answers[1].id) == true)
        {
           
            Knopf2.setStyle("-fx-background-color: #00ff00");
            
            Reset();

        }
        else
        {
            Knopf2.setStyle("-fx-background-color: #ff0000");
            Platform.exit();
        }  
    }

    public void Knopf3Gedrueckt()
    {
        if(control.checkAnswer(answers[2].id) == true)
        {
            
            Knopf3.setStyle("-fx-background-color: #00ff00");
            
            Reset();
           
        }
        else
        {
            Knopf3.setStyle("-fx-background-color: #ff0000");
            Platform.exit();
        }   
    }

    public void Knopf4Gedrueckt()
    {
        if(control.checkAnswer(answers[3].id) == true)
        {
            
           
            Knopf4.setStyle("-fx-background-color: #00ff00");
            
            Reset();
            
            
        }
        else
        {
            Knopf4.setStyle("-fx-background-color: #ff0000");
            Platform.exit();
        } 
    }

    public void Reset()
    {
        Platform.runLater(new Runnable() {
            public void run()
            {
                try
                {
                    Thread.sleep(2000);

                }
                catch(Exception e)
                {

                }
                resetAll();
            }
        });

    }

    public void resetAll()
    {
        
        control.newRound();
        setAnswers();
        setImage();
        
        Knopf1.setStyle("-fx-background-color: #ffffff");
        Knopf2.setStyle("-fx-background-color: #ffffff");
        Knopf3.setStyle("-fx-background-color: #ffffff");
        Knopf4.setStyle("-fx-background-color: #ffffff");
      
    }
}
