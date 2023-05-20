package DoodleJump.Pages;

import DoodleJump.Main;
import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameOverPage extends Pane {

    private Stage PrimaryStage;
    private ImageView Background6_iv = new ImageView(Images.Background6);
    private ImageView Character_iv = new ImageView(Images.Character);
    private ImageView PlayAgain_iv = new ImageView(Images.PlayAgain);
    private ImageView Main_iv = new ImageView(Images.Main);
    private ImageView bat_iv = new ImageView(Images.Bat1);

    private Line line = new Line(525, 905, 525, 620);
    private int time = 1;
    private long time2 = System.currentTimeMillis();
    private String data;
    private Text score = new Text(1425, 850, FileIO.Read("Score.txt"));

    public GameOverPage(Stage stage) {
        this.PrimaryStage = stage;
    }

    public void start() {
        double x = 340.3;
        double y = 129;

        if (Integer.parseInt(FileIO.Read("TopScore.txt")) < Integer.parseInt(FileIO.Read("Score.txt"))) {
            FileIO.Write(FileIO.Read("Score.txt"), "TopScore.txt");
        }
        score.setFill(Color.BLACK);
        score.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 50));

        PlayAgain_iv.setX(940);
        PlayAgain_iv.setY(500);
        PlayAgain_iv.setFitHeight(y);
        PlayAgain_iv.setFitWidth(x);
        PlayAgain_iv.setOnMouseEntered(e -> {
            PlayAgain_iv.setImage(Images.PlayAgain2);
            PlayAgain_iv.setCursor(Cursor.HAND);
            PlayAgain_iv.setFitWidth(x + 20);
            PlayAgain_iv.setFitHeight(y + 7.56);
        });
        PlayAgain_iv.setOnMouseExited(e -> {
            PlayAgain_iv.setImage(Images.PlayAgain);
            PlayAgain_iv.setFitHeight(y);
            PlayAgain_iv.setFitWidth(x);
        });
        PlayAgain_iv.setOnMouseClicked(e -> {
            FileIO.Write("OFF", "AudioState.txt");
            PrimaryStage.setScene(new DifficultyPage(PrimaryStage).Create());
        });

        ////////////////////////////////////////////////////////////////////////////////
        Main_iv.setX(1430);
        Main_iv.setY(500);
        Main_iv.setFitHeight(y);
        Main_iv.setFitWidth(x);
        Main_iv.setOnMouseEntered(e -> {
            Main_iv.setImage(Images.Main2);
            Main_iv.setCursor(Cursor.HAND);
            Main_iv.setFitWidth(x + 20);
            Main_iv.setFitHeight(y + 7.56);
        });
        Main_iv.setOnMouseExited(e -> {
            Main_iv.setImage(Images.Main);
            Main_iv.setFitHeight(y);
            Main_iv.setFitWidth(x);
        });
        Main_iv.setOnMouseClicked(e -> {
            PrimaryStage.setScene(new MainPage(PrimaryStage).Create());
        });

        ////////////////////////////////////////////////////////////////////////////////
        Character_iv.setX(490);
        Character_iv.setY(740);
        Character_iv.setFitWidth(89);
        Character_iv.setFitHeight(87.2);
        line.setStroke(Color.color(0, 0, 0, 0));
        PathTransition path = new PathTransition(Duration.millis(900), line, Character_iv);
        path.setAutoReverse(true);
        path.setCycleCount(Timeline.INDEFINITE);
        path.play();

        ////////////////////////////////////////////////////////////////////////////////
        bat_iv.setFitHeight(67);
        bat_iv.setFitWidth(120);
        bat_iv.setX(770);
        bat_iv.setY(110);
        bat_iv.setRotate(10);
        AnimationTimer Timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() - time2 > 200) {
                    if (time == 1) {
                        bat_iv.setImage(Images.Bat3);
                        bat_iv.setLayoutY(bat_iv.getLayoutY() - 10);
                        time = 2;
                    } else if (time == 2) {
                        bat_iv.setImage(Images.Bat1);
                        bat_iv.setLayoutY(bat_iv.getLayoutY() + 10);
                        time = 1;
                    }
                    time2 = System.currentTimeMillis();
                }
            }
        };
        Timer.start();
        this.getChildren().addAll(Background6_iv, Character_iv, line, bat_iv, PlayAgain_iv, Main_iv, score);

    }

    public Scene Create() {
        this.setLayoutX(Main.SelectedOffset.getX());
        this.setLayoutY(Main.SelectedOffset.getY());
        this.setScaleX(Main.Factor / 3);
        this.setScaleY(Main.Factor / 3);
        start();
        return new Scene(this, Main.SelectedResolution.getX(), Main.SelectedResolution.getY());
    }
}
