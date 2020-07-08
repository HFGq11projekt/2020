import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;


public class Layout
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
        stage.setScene(scene);
        stage.show();
    }

  public void setImage()
  {
      Image image = new Image(control.getPath());
      Diagramm.setImage(image);
  }
  public void setAnswers()
  {
   var answers = control.getAnswers();  
   Knopf1.setText(answers[0].name + answers[0].land);
   Knopf2.setText(answers[1].name + answers[1].land);
   Knopf3.setText(); 
   Knopf4.setText(); 
  }
  public void Knopf1Gedrueckt()
  {
      
  }
  public void Knopf2Gedrueckt()
  {
      
  }
  public void Knopf3Gedrueckt()
  {
      
  }
  public void Knopf4Gedrueckt()
  {
      
  }
 

}
