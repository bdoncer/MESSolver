import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class App extends Application{
    StartScreen startScreen;

    {
        try {
            startScreen = new StartScreen(this);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    VBox mainScreen = new VBox();
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("MRS");
        mainScreen.setAlignment(Pos.CENTER);
        mainScreen.getChildren().add(startScreen.getWelcomeScreen());
        Scene scene = new Scene(mainScreen, 1000, 800);
        primaryStage.setScene(scene);
        scene.setFill(Color.web("#81c483"));
        primaryStage.show();
    }

    public void startCounting() {
        MESSolver solver = new MESSolver(startScreen.giveN());
        solver.start();
        mainScreen.getChildren().clear();
        mainScreen.getChildren().add(solver.chart.getChart());
    }

}
