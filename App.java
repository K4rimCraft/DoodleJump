package DoodleJump;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    Boolean wMove = false;
    Boolean sMove = false;
    Boolean aMove = false;
    Boolean dMove = false;
    Boolean canJump = true;

    long time = System.currentTimeMillis();
    int FPS = 0;
    
    Player Doodle = new Player();
    ImageView BackGround = new ImageView(new Image("DoodleJump/pics/bg.png"));

    Button startButton = new Button("Start");
    Label moveXLabel = new Label();
    Label moveYLabel = new Label();
    Label FPSLabel = new Label();
    Label ScoreLabel = new Label();
    Label stopYLabel = new Label();

    Obstacle[] newObstacles;

    @Override
    public void start(Stage primaryStage) {

        ScoreLabel.setLayoutX(75);
        ScoreLabel.setLayoutY(15);
        moveYLabel.setLayoutY(15);
        stopYLabel.setLayoutY(15);
        FPSLabel.setLayoutX(75);
        stopYLabel.setLayoutX(100);

        Pane P = new Pane();
        P.getChildren().addAll(BackGround, Doodle.Hitbox, moveXLabel, moveYLabel, FPSLabel, stopYLabel, ScoreLabel);
        Scene SC = new Scene(P, 600, 1080);
        startButton.setLayoutX((int) SC.getWidth() / 2);
        newObstacles = new Obstacle[21];
        newObstacles[0] = new Obstacle(250, 1000, 58, 15);

        P.getChildren().add(newObstacles[0]);
        for (int i = 1; i < newObstacles.length; i++) {

            double atX = ((int) (Math.random() * 50) * (SC.getWidth() - 150) / 50) + 50;
            double atY = 50 * i;

            newObstacles[i] = new Obstacle(atX, 1000 - atY, 58, 15);
            newObstacles[i].setIndex(i);
            P.getChildren().add(newObstacles[i]);

            double probablity = Math.random();
            if (probablity > 0.9) {
                newObstacles[i].setMove(true);
                newObstacles[i].setImage(new Image("DoodleJump/pics/obs2.png"));
            }

        }
        P.getChildren().add(Doodle);
        P.getChildren().add(startButton);

        SC.setOnKeyPressed(PressedKey -> {
            switch (PressedKey.getCode()) {
                case W:
                    // Move = true;
                    if (canJump == true) {
                        Doodle.setyVelocity(-18);
                        // canJump = false;
                    }
                    break;
                case S:
                    sMove = true;
                    break;
                case A:
                    aMove = true;
                    break;
                case D:
                    dMove = true;

                    break;
                case SPACE:

                    Doodle.setxVelocity(15);
                default:
            }
        });

        SC.setOnKeyReleased(PressedKey -> {

            switch (PressedKey.getCode()) {
                case W:
                    wMove = false;
                    break;
                case S:
                    sMove = false;
                    break;
                case A:
                    aMove = false;
                    break;
                case D:
                    dMove = false;
                    break;
                case SPACE:
                    Doodle.setxVelocity(5);
                    break;

                default:
            }
        });

        AnimationTimer GameLoop = new AnimationTimer() {

            @Override
            public void handle(long arg0) {

                moveXLabel.setText("X = " + Doodle.getX());
                moveYLabel.setText("Y = " + Doodle.getY());
                ScoreLabel.setText("Score: " + Doodle.getScore());

                if (wMove == true)
                    Doodle.moveY(5, newObstacles);
                if (sMove == true)
                    Doodle.moveY(5, newObstacles);
                if (aMove == true)
                    Doodle.moveX(Player.LEFT, newObstacles);
                if (dMove == true)
                    Doodle.moveX(Player.RIGHT, newObstacles);

                // moving obstacles From side to side
                for (int i = 0; i < newObstacles.length; i++) {
                    newObstacles[i].swing(SC.getWidth());
                    newObstacles[i].teleportUP(Doodle, SC.getWidth(), SC.getHeight());
                    
                }

                Doodle.gravityCycle(newObstacles);
                Doodle.screenScroll(newObstacles, SC.getHeight());

                if (System.currentTimeMillis() - time > 1000) {
                    FPSLabel.setText("FPS: " + FPS);
                    time = System.currentTimeMillis();
                    FPS = 0;
                }
                FPS++;
            }
        };

        startButton.setOnAction(e -> {
            GameLoop.start();
            P.getChildren().remove(startButton);
        });

        primaryStage.setScene(SC);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
