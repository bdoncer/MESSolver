import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

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
        primaryStage.setTitle("MES");
        mainScreen.setAlignment(Pos.CENTER);
        mainScreen.getChildren().add(startScreen.getWelcomeScreen());
        Scene scene = new Scene(mainScreen, 1000, 800);
        primaryStage.setScene(scene);
        scene.setFill(Color.web("#81c483"));
        primaryStage.show();
    }

    public void startCounting() {
        try{
            MESSolver solver = new MESSolver(startScreen.giveN());
            solver.start();
            mainScreen.getChildren().clear();
            mainScreen.getChildren().add(solver.chart.getChart());
        }catch (IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Would you like to try again?", ButtonType.YES);
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Would you like to try again?");
            alert.showAndWait();
            System.exit(1);
        }
    }

}
