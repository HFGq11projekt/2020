import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;


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
  
  DB db;
  Controller control;
   
    public void start(Stage stage) throws Exception
    {
        FXMLLoader loader =  new  FXMLLoader(getClass().getResource("Layout.fxml"));
        
        db = new DB();
        control = new Controller(db);
        
        Pane layout = loader.load();
        Scene scene =  new  Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

  public void setImage()
  {
      Image image = new Image("Klimadiagramme/Ottawa.gif");
      Diagramm.setImage(image);
  }


}
