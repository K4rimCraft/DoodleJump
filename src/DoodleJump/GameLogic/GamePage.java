package DoodleJump.GameLogic;

import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import DoodleJump.Main;
import DoodleJump.Pages.*;

public class GamePage extends Pane {

    private Stage PrimaryStage;
    private Scene thisScene;

    public static double GameScreenHeightOffset = 215;

    public static double GameScreenWidth = 650 - 60;
    public static double GameScreenHeight = 1080 + GameScreenHeightOffset;
    public static double LeftBorder = 635 + 30;
    public static double RightBorder = 1285 - 30;
    public static double PlayerLeftBorder = 635;
    public static double PlayerRightBorder = 1285;

    private long time = System.currentTimeMillis();
    private int FPS = 0;
    private boolean Status = true;

    private AnimationTimer GameLoop;
    private Player Doodle = new Player();
    private Obstacle[] newObstacles = new Obstacle[37];
    private PowerUp[] newPowerUps = new PowerUp[6];
    private Monster[] newMonsters = new Monster[2];
    private ArrayList<Projectile> newProjectiles = new ArrayList<Projectile>();
    private ImageView BackGround = new ImageView(Images.BG);
    private ImageView BackGround2 = new ImageView(Images.BG);
    private ImageView BackBackGround = new ImageView(Images.BackBackGround);
    private ImageView Pause_iv = new ImageView(Images.Pause);

    private Label moveXLabel = new Label();
    private Label moveYLabel = new Label();
    private Label FPSLabel = new Label();
    private Label ScoreLabel = new Label();
    private Label stopYLabel = new Label();
    private Font doodleFont = Font.loadFont(Main.PathToFont + "DoodleJump.ttf", 50);

    private Text score = new Text(1425, 850, "");

    private String AudioState;
    // private Audio audioGame;
    private Audio audioFall;

    private KeyboardListener keyboardListener = new KeyboardListener(this, Doodle, newObstacles, newPowerUps,
            newMonsters, newProjectiles);

    public GamePage(Point2D Resolution, Stage stage) {
        super();
        this.setWidth(Resolution.getX());
        this.setHeight(Resolution.getY());
        this.PrimaryStage = stage;
        audioFall = new Audio(Main.PathToResources + "fall.wav");
        audioFall.audioPlayer(1);
    }

    public void start() {
        

        FileIO.Write((int) Doodle.getScore() + "", "Score.txt");
        AudioState = FileIO.Read("AudioState.txt");

        ScoreLabel.setLayoutX(75);
        ScoreLabel.setLayoutY(15);
        ScoreLabel.setFont(doodleFont);
        moveYLabel.setLayoutY(15);
        stopYLabel.setLayoutY(15);
        FPSLabel.setLayoutX(75);
        stopYLabel.setLayoutX(100);
        BackGround.setX(635);
        BackGround2.setX(635);
        BackGround2.setY(-(GameScreenHeight + 500));
        this.getChildren().addAll(BackGround, BackGround2, Doodle.Hitbox);

        Obstacle.initialize(newObstacles, this);
        PowerUp.initialize(newPowerUps, newObstacles, this);
        Monster.initialize(newMonsters, newPowerUps, newObstacles, this);

        this.getChildren().addAll(Doodle.Nozzle, Doodle, BackBackGround, moveXLabel, moveYLabel, FPSLabel,
                stopYLabel,
                ScoreLabel, Pause_iv);

        keyboardListener.Start();

        

        GameLoop = new AnimationTimer() {

            @Override
            public void handle(long arg0) {
                if (Status == true) {
                    moveXLabel.setText("X = " + Doodle.getX());
                    moveYLabel.setText("Y = " + Doodle.getY());
                    // moveXLabel.setText("ad = " + newProjectiles.size());
                    // moveYLabel.setText("re = " + removeProjectiles.size());
                    ScoreLabel.setText("Score: " + (int) Doodle.getScore());

                    keyboardListener.Loop();
                    Doodle.gravityCycle(newObstacles, newPowerUps, newMonsters);
                    Doodle.screenScroll(newObstacles, BackGround, BackGround2);

                    for (int i = 0; i < newObstacles.length; i++) {
                        newObstacles[i].swing();
                        newObstacles[i].teleportUP(Doodle, newPowerUps, newMonsters);
                    }

                    if (BackGround.getY() > GameScreenHeight) {
                        BackGround.setY(BackGround.getY() - 1 * (GameScreenHeight));
                        BackGround2.setY(BackGround2.getY() - 2 * (GameScreenHeight));
                    }

                    Projectile.Loop(newProjectiles, newMonsters);
                    // BackBackGround.toFront();
                    // for (Projectile pro : removeProjectiles){
                    // pro.setVisible(false);
                    // pro = null;
                    // }
                    FPSCounter();
                    LoseCheck();

                }
            }
        };

        GameLoop.start();

        Pause_iv.setX(550);
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
            PrimaryStage.setScene(PausePage.Pause(PrimaryStage, thisScene, Doodle, GameLoop));
            GameLoop.stop();
        });

    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
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
        if (Doodle.getY() > GameScreenHeight - GameScreenHeightOffset) {
            Lose();
        }
        for (int i = 0; i < newMonsters.length; i++) {
            if (Doodle.Hitbox.getBoundsInParent().intersects(newMonsters[i].getBoundsInParent())
                    && newMonsters[i].getStatus() == true && Doodle.getHasSomething() == false) {
                Lose();
            }
        }
    }

    private void Lose() {
        Status = false;
        score = new Text(1425, 850, FileIO.Read("Score.txt"));
        FileIO.Write((int) Doodle.getScore() + "", "Score.txt");
        if (AudioState.equals("ON")) {
            audioFall.play();
        } else if (AudioState.equals("OFF")) {
            audioFall.stop();
        }
        PrimaryStage.setScene(new GameOverPage(PrimaryStage).Create());
    }

    public Scene Create() {
        thisScene = new Scene(this, Main.SelectedResolution.getX(), Main.SelectedResolution.getY());
        this.setLayoutX(Main.SelectedOffset.getX());
        this.setLayoutY(Main.SelectedOffset.getY());
        this.setScaleX(Main.Factor / 3);
        this.setScaleY(Main.Factor / 3);
        start();
        return thisScene;
    }

}
