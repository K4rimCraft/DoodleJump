package DoodleJump.Pages;

import DoodleJump.Main;
////////////////////////////////////////////////////////////////////////////////
import javafx.animation.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
////////////////////////////////////////////////////////////////////////////////

public class MainPage extends Pane {

    private Stage stage;
    private Scene scene;
    private Pane buttons = new Pane();

    private ImageView Background_iv = new ImageView(Images.Background1);
    private ImageView Character_iv = new ImageView(Images.Character);
    private ImageView Play_iv = new ImageView(Images.Play);
    private ImageView Settings_iv = new ImageView(Images.Settings);
    private ImageView Scores_iv = new ImageView(Images.Scores);
    private ImageView Exit_iv = new ImageView(Images.Exit);
    private ImageView bat_iv = new ImageView(Images.Bat1);
//    private Ellipse ellipse = new Ellipse(1600, 770, 120, 40);
    private Line line = new Line(535, 920, 535, 620);
    private int time = 1;
    private long time2 = System.currentTimeMillis();
    private String data;
    private String AudioState;
    private Audio audioGame;

    public MainPage(Stage stage) {
        this.stage = stage;
        audioGame = new Audio(Main.PathToResources + "jump.wav");
        audioGame.audioPlayer();
    }

    public void start() {
        double x = 292.5;
        double y = 110.63;
////////////////////////////////////////////////////////////////////////////////

        data = FileIO.Read("Character_Right.txt");
        AudioState = FileIO.Read("AudioState.txt");

        if (AudioState.equals("ON")) {
            audioGame.play();
        } else if (AudioState.equals("OFF")) {
            audioGame.stop();
        }
////////////////////////////////////////////////////////////////////////////////

//        ellipse.setFill(Color.color(0, 0, 0, 0));
//        ellipse.setOnMouseEntered(e -> {
//            Exit_iv.setX(e.getX() + 10);
//            Exit_iv.setY(e.getY() + 10);
//        });
//        ellipse.setOnMouseExited(e -> {
//            Exit_iv.setX(1470);
//            Exit_iv.setY(710);
//        });
////////////////////////////////////////////////////////////////////////////////
        Character_iv.setX(490);
        Character_iv.setY(886);
        Character_iv.setFitWidth(111.3);
        Character_iv.setFitHeight(109);
        Character_iv.setImage(new Image(data));
////////////////////////////////////////////////////////////////////////////////

        line.setStroke(Color.color(0, 0, 0, 0));
        PathTransition path = new PathTransition(Duration.millis(900), line, Character_iv);
        path.setAutoReverse(true);
        path.setCycleCount(Timeline.INDEFINITE);
        path.play();
////////////////////////////////////////////////////////////////////////////////

        Play_iv.setFitHeight(y);
        Play_iv.setFitWidth(x);
        Play_iv.setX(1350);
        Play_iv.setY(170);
        Play_iv.setOnMouseEntered(e -> {
            Play_iv.setImage(Images.Play2);
            Play_iv.setCursor(Cursor.HAND);
            Play_iv.setFitWidth(x + 20);
            Play_iv.setFitHeight(y + 7.56);
        });
        Play_iv.setOnMouseExited(e -> {
            Play_iv.setImage(Images.Play);
            Play_iv.setFitWidth(x);
            Play_iv.setFitHeight(y);
        });
        Play_iv.setOnMouseClicked(e -> {
            audioGame.stop();
            SelectPage page = new SelectPage(stage);
            page.start();
            stage.setScene(page.play());
        }
        );
////////////////////////////////////////////////////////////////////////////////

        Settings_iv.setFitHeight(y);
        Settings_iv.setFitWidth(x);
        Settings_iv.setX(1450);
        Settings_iv.setY(350);
        Settings_iv.setOnMouseEntered(e -> {
            Settings_iv.setImage(Images.Settings2);
            Settings_iv.setCursor(Cursor.HAND);
            Settings_iv.setFitWidth(x + 20);
            Settings_iv.setFitHeight(y + 7.56);
        });
        Settings_iv.setOnMouseExited(e -> {
            Settings_iv.setImage(Images.Settings);
            Settings_iv.setFitWidth(x);
            Settings_iv.setFitHeight(y);
        });
        Settings_iv.setOnMouseClicked(e -> {
            audioGame.stop();
            SettingsPage settings = new SettingsPage(stage);
            settings.start();
            stage.setScene(settings.setting());
        });
////////////////////////////////////////////////////////////////////////////////
        Scores_iv.setFitHeight(y);
        Scores_iv.setFitWidth(x);
        Scores_iv.setX(1380);
        Scores_iv.setY(530);
        Scores_iv.setOnMouseEntered(e -> {
            Scores_iv.setImage(Images.Scores2);
            Scores_iv.setCursor(Cursor.HAND);
            Scores_iv.setFitWidth(x + 20);
            Scores_iv.setFitHeight(y + 7.56);
        });
        Scores_iv.setOnMouseExited(e -> {
            Scores_iv.setImage(Images.Scores);
            Scores_iv.setFitWidth(x);
            Scores_iv.setFitHeight(y);
        });
        Scores_iv.setOnMouseClicked(e -> {
            audioGame.stop();
            ScoresPage score = new ScoresPage(stage);
            score.start();
            stage.setScene(score.play());
        });
////////////////////////////////////////////////////////////////////////////////

        Exit_iv.setFitHeight(y);
        Exit_iv.setFitWidth(x);
        Exit_iv.setX(1470);
        Exit_iv.setY(710);
        Exit_iv.setOnMouseEntered(e -> {
            Exit_iv.setImage(Images.Exit2);
            Exit_iv.setCursor(Cursor.HAND);
            Exit_iv.setFitWidth(x + 20);
            Exit_iv.setFitHeight(y + 7.56);
        });
        Exit_iv.setOnMouseExited(e -> {
            Exit_iv.setImage(Images.Exit);
            Exit_iv.setFitWidth(x);
            Exit_iv.setFitHeight(y);
        });
        Exit_iv.setOnMouseClicked(e -> {
            stage.close();
        });

////////////////////////////////////////////////////////////////////////////////
        bat_iv.setFitHeight(67);
        bat_iv.setFitWidth(120);
        bat_iv.setX(800);
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
////////////////////////////////////////////////////////////////////////////////

        buttons.getChildren().addAll(Play_iv, Settings_iv, Scores_iv, Exit_iv, Character_iv, line);
        this.getChildren().addAll(Background_iv, bat_iv, buttons);
    }

    public Scene play() {
        scene = new Scene(this);
        return scene;
    }
}
