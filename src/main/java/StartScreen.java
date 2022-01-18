import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StartScreen {
    VBox welcomeScreen = new VBox();
    TextArea nText = new TextArea("");
    Label n = new Label("n = ");
    Label title = new Label("MRS Solver");
    Button startButton = new Button("Start :))");
    public StartScreen(App app) throws FileNotFoundException {
        startButton.setOnAction(event -> {
            app.startCounting();
        });
        startButton.setPrefWidth(200);
        startButton.setPrefHeight(60);
        welcomeScreen.setPrefHeight(400);
        welcomeScreen.setPrefWidth(300);
        nText.setPrefHeight(10);
        nText.setPrefWidth(60);
        startButton.setStyle("-fx-background-color: White");
        welcomeScreen.getStyleClass().add("color-palette");
        welcomeScreen.setBackground(new Background(new BackgroundFill(Color.web("#FFB6C1"), CornerRadii.EMPTY, Insets.EMPTY)));
        //n.setTextFill(Color.web("FF1493"));
        HBox write = new HBox(n,nText);
        write.setAlignment(Pos.CENTER);
        title.setFont(new Font("Cambria", 30));
        n.setFont(new Font("Cambria", 18));
        welcomeScreen.getChildren().add(title);
        welcomeScreen.getChildren().add(write);
        Image image = new Image(new FileInputStream("src/main/resources/doggo.jpg"));
        ImageView pic = new ImageView();
        pic.setFitWidth(200);
        pic.setFitHeight(163);
        pic.setImage(image);
        VBox b = new VBox(pic);
        b.setAlignment(Pos.CENTER);
        welcomeScreen.getChildren().add(startButton);
        welcomeScreen.getChildren().add(b);
        welcomeScreen.setAlignment(Pos.CENTER);
    }

    public VBox getWelcomeScreen() {
        return welcomeScreen;
    }

    public int giveN(){
        int n = Integer.parseInt(nText.getText());
        if(n < 0){
            throw new IllegalArgumentException("n can't be negative");
        }
        return n;
    }

}


