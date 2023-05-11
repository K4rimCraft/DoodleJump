package DoodleJump;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameScene extends Scene {

    private static Pane gamePane = new Pane();
    public static Point2D ResolutionFullHD = new Point2D(1920, 1080);
    public static Point2D ResolutionHD = new Point2D(1280, 720);
    public static Point2D ResolutionCustom = new Point2D(600, 1080);
    private double GameScreenWidth = 600 - 60;
    private double GameScreenHeight = 1080;
    public static double LeftBorder = 660 + 30;
    public static double RightBorder = 1260;

    private long time = System.currentTimeMillis();
    private int FPS = 0;
    private boolean Status = true;

    private Player Doodle = new Player();
    private Obstacle[] newObstacles = new Obstacle[30];
    private ImageView BackGround = new ImageView(new Image("DoodleJump/pics/bg.png"));
    private ImageView BackGround2 = new ImageView(new Image("DoodleJump/pics/bg.png"));
    private ImageView BackBackGround = new ImageView(new Image("DoodleJump/pics/Background.png"));

    private ImageView Lost = new ImageView(new Image("DoodleJump/pics/lose.png"));

    private Button startButton = new Button("Start");
    private Label moveXLabel = new Label();
    private Label moveYLabel = new Label();
    private Label FPSLabel = new Label();
    private Label ScoreLabel = new Label();
    private Label stopYLabel = new Label();

    private KeyboardListener keyboardListener = new KeyboardListener(this, Doodle, newObstacles);

    GameScene(Point2D Resolution) {
        super(gamePane, Resolution.getX(), Resolution.getY());
    }

    public void start() {

        ScoreLabel.setLayoutX(75);
        ScoreLabel.setLayoutY(15);
        moveYLabel.setLayoutY(15);
        stopYLabel.setLayoutY(15);
        FPSLabel.setLayoutX(75);
        stopYLabel.setLayoutX(100);
        BackGround.setX(660);
        BackGround2.setX(660);
        BackGround2.setY(-1080);
        gamePane.getChildren().addAll(BackBackGround, BackGround, BackGround2, Doodle.Hitbox, moveXLabel, moveYLabel,
                FPSLabel, stopYLabel, ScoreLabel);
        startButton.setLayoutX((int) GameScreenWidth + LeftBorder / 2);

        newObstacles[0] = new Obstacle(LeftBorder + 250, 1000, 0);
        gamePane.getChildren().add(newObstacles[0]);
        for (int i = 1; i < newObstacles.length; i++) {
            newObstacles[i] = new Obstacle(Obstacle.xRandom(GameScreenWidth), 1000 - (35 * i), i);
            gamePane.getChildren().add(newObstacles[i]);
        }

        gamePane.getChildren().add(Doodle);
        gamePane.getChildren().add(startButton);

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
                    Doodle.screenScroll(newObstacles, GameScreenHeight, BackGround, BackGround2);

                    for (int i = 0; i < newObstacles.length; i++) {
                        newObstacles[i].swing(GameScreenWidth);
                        newObstacles[i].teleportUP(Doodle, GameScreenWidth, GameScreenHeight);
                    }
                    if (BackGround.getY() > GameScreenHeight){
                        BackGround.setY(BackGround.getY() - 1 * GameScreenHeight);
                        BackGround2.setY(BackGround2.getY() - 2 * GameScreenHeight);

                    }
                        

                    FPSCounter();
                    LoseCheck();
                }
            }
        };

        startButton.setOnAction(e -> {
            GameLoop.start();
            gamePane.getChildren().remove(startButton);
        });
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
            Lost.setFitHeight(600);
            Lost.setFitWidth(600);
            Lost.setX(650);
            Lost.setY(275);
            gamePane.getChildren().add(Lost);
        }
    }

}
