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
    private ImageView BackGround = new ImageView(new Image("DoodleJump/pics/bg.png"));
    private ImageView BackBackGround = new ImageView(new Image("DoodleJump/pics/Background.png"));
    private ImageView Lost = new ImageView(new Image("DoodleJump/pics/lose.png"));

    private Button startButton = new Button("Start");
    private Label moveXLabel = new Label();
    private Label moveYLabel = new Label();
    private Label FPSLabel = new Label();
    private Label ScoreLabel = new Label();
    private Label stopYLabel = new Label();

    private Obstacle[] newObstacles;

    private KeyboardListener keyboardListener;

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

        gamePane.getChildren().addAll(BackBackGround, BackGround, Doodle.Hitbox, moveXLabel, moveYLabel, FPSLabel,
                stopYLabel, ScoreLabel);
        startButton.setLayoutX((int) GameScreenWidth + LeftBorder / 2);

        newObstacles = new Obstacle[21];

        newObstacles[0] = new Obstacle(LeftBorder + 250, 1000, 58, 15);
        gamePane.getChildren().add(newObstacles[0]);
        for (int i = 1; i < newObstacles.length; i++) {

            double atX = ((int) (Math.random() * 50) * (GameScreenWidth - 80) / 50) + LeftBorder;
            double atY = 50 * i;

            newObstacles[i] = new Obstacle(atX, 1000 - atY, 58, 15);
            newObstacles[i].setIndex(i);
            gamePane.getChildren().add(newObstacles[i]);

            double probablity = Math.random();
            if (probablity > 0.9) {
                newObstacles[i].setMove(true);
                newObstacles[i].setImage(new Image("DoodleJump/pics/obs2.png"));
            }

        }

        gamePane.getChildren().add(Doodle);
        gamePane.getChildren().add(startButton);
        keyboardListener = new KeyboardListener(this, Doodle, newObstacles);
        keyboardListener.Start();

        AnimationTimer GameLoop = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                if (Status == true) {
                    moveXLabel.setText("X = " + Doodle.getX());
                    moveYLabel.setText("Y = " + Doodle.getY());
                    ScoreLabel.setText("Score: " + Doodle.getScore());

                    keyboardListener.Loop();

                    // moving obstacles From side to side
                    for (int i = 0; i < newObstacles.length; i++) {
                        newObstacles[i].swing(GameScreenWidth);
                        newObstacles[i].teleportUP(Doodle, GameScreenWidth, GameScreenHeight);
                    }

                    Doodle.gravityCycle(newObstacles);
                    Doodle.screenScroll(newObstacles, GameScreenHeight);

                    if (System.currentTimeMillis() - time > 1000) {
                        FPSLabel.setText("FPS: " + FPS);
                        time = System.currentTimeMillis();
                        FPS = 0;
                    }
                    FPS++;

                    if(Doodle.getY() > GameScreenHeight){
                        Status = false;
                        Lost.setFitHeight(600);
                        Lost.setFitWidth(600);
                        Lost.setX(650);
                        Lost.setY(275);
                        gamePane.getChildren().add(Lost);
                    }
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

}
