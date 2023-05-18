package DoodleJump.GameLogic;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import DoodleJump.Main;
import DoodleJump.Pages.*;


public class GamePane extends Pane {

    public final static Point2D ResolutionFullHD = new Point2D(1920, 1080);
    public final static Point2D ResolutionHD = new Point2D(1280, 720);
    public final static Point2D ResolutionCustom = new Point2D(600, 1080);
    public static double GameScreenWidth = 650 - 60;
    public static double GameScreenHeight = 1080;
    public static double LeftBorder = 635 + 30;
    public static double RightBorder = 1285 - 30;
    public static double PlayerLeftBorder = 635;
    public static double PlayerRightBorder = 1285;

    private long time = System.currentTimeMillis();
    private int FPS = 0;
    private boolean Status = true;

    private Player Doodle = new Player();
    private Obstacle[] newObstacles = new Obstacle[30];
    private ImageView BackGround1 = new ImageView(Images.Background10);
    private ImageView BackGround2 = new ImageView(Images.Background10);
    private ImageView BackGround3 = new ImageView(Images.Background);

    private ImageView Pause_iv = new ImageView(Images.Pause);

    private Button startButton = new Button("Start");
    private Label moveXLabel = new Label();
    private Label moveYLabel = new Label();
    private Label FPSLabel = new Label();
    private Label ScoreLabel = new Label();
    private Label stopYLabel = new Label();

    private Text score = new Text(1425, 850, "");

    private Stage stage;
    private Scene scene;
    private String AudioState;
    //private Audio audioGame;
    private Audio audioFall;

    private KeyboardListener keyboardListener = new KeyboardListener(this, Doodle, newObstacles);

    public GamePane(Point2D Resolution, Stage stage) {
        super();
        this.setWidth(Resolution.getX());
        this.setHeight(Resolution.getY());
        this.stage = stage;
        audioFall = new Audio(Main.PathToResources + "fall.wav");
        audioFall.audioPlayer(1);
    }

    public void start() {

        ReadAndWrite.Write((int) Doodle.Score + "", "Score.txt");
        AudioState = ReadAndWrite.Read("AudioState.txt");

        ScoreLabel.setLayoutX(75);
        ScoreLabel.setLayoutY(15);
        moveYLabel.setLayoutY(15);
        stopYLabel.setLayoutY(15);
        FPSLabel.setLayoutX(75);
        stopYLabel.setLayoutX(100);
        BackGround1.setX(635);
        BackGround2.setX(635);
        BackGround2.setY(-1080);
        this.getChildren().addAll(BackGround1, BackGround2, Doodle.Hitbox);
        startButton.setLayoutX((int) GameScreenWidth + LeftBorder / 2);

        newObstacles[0] = new Obstacle(LeftBorder + 250, 1000, 0, this);
        for (int i = 1; i < newObstacles.length; i++) {
            newObstacles[i] = new Obstacle(Obstacle.xRandom(), 1000 - (35 * i), i, this);
        }
        this.getChildren().add(Doodle);
        this.getChildren().addAll(startButton, BackGround3);
        this.getChildren().addAll(moveXLabel, moveYLabel, FPSLabel, stopYLabel, ScoreLabel, Pause_iv);

        keyboardListener.Start();

        AnimationTimer GameLoop = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                if (Status == true) {
                    moveXLabel.setText("X = " + Doodle.getX());
                    moveYLabel.setText("Y = " + Doodle.getY());
                    ScoreLabel.setText("Score: " + Doodle.getScore());

                    keyboardListener.Loop();
                    Doodle.gravityCycle(newObstacles);
                    Doodle.screenScroll(newObstacles, BackGround1, BackGround2);

                    for (Obstacle newObstacle : newObstacles) {
                        newObstacle.swing();
                        newObstacle.teleportUP(Doodle);
                    }
                    if (BackGround1.getY() > GameScreenHeight) {
                        BackGround1.setY(BackGround1.getY() - 1 * GameScreenHeight);
                        BackGround2.setY(BackGround2.getY() - 2 * GameScreenHeight);
                    }
                    FPSCounter();
                    LoseCheck();
                }
            }
        };

        startButton.setOnAction(e -> {
            GameLoop.start();
            this.getChildren().remove(startButton);
        });


        Pause_iv.setX(650);
        Pause_iv.setY(10);
        Pause_iv.setFitWidth(80);
        Pause_iv.setFitHeight(80);
        Pause_iv.setOnMouseEntered(e -> {
            Pause_iv.setImage(Images.Pause2);
            Pause_iv.setFitWidth(90);
            Pause_iv.setFitHeight(90);
            Pause_iv.setCursor(Cursor.HAND);
        });
        Pause_iv.setOnMouseExited(e -> {
            Pause_iv.setImage(Images.Pause);
            Pause_iv.setFitWidth(80);
            Pause_iv.setFitHeight(80);
        });
        Pause_iv.setOnMouseClicked(e -> {
            stage.setScene(PausePane.Pause(stage, scene, Doodle, GameLoop));
            GameLoop.stop();
        });


    }


    public Scene play() {
        scene = new Scene(this);
        return scene;
    }


    public boolean getStatus() {
        return Status;
    }


    public void FPSCounter() {
        if (System.currentTimeMillis() - time > 1000) {
            FPSLabel.setText("FPS: " + FPS);
            time = System.currentTimeMillis();
            FPS = 0;
        }
        FPS++;
    }


    public void LoseCheck() {
        if (Doodle.getY() > GameScreenHeight) {
            Status = false;
            score = new Text(1425, 850, ReadAndWrite.Read("Score.txt"));
            ReadAndWrite.Write((int) Doodle.Score + "", "Score.txt");
            if (AudioState.equals("ON")) {
                audioFall.play();
            } else if (AudioState.equals("OFF")) {
                audioFall.stop();
            }
            GameOver gameover = new GameOver(stage);
            gameover.start();
            stage.setScene(gameover.play());
        }

    }


    

}
