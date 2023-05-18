package DoodleJump;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PowerUp extends ImageView {

    static final public int SPRING = 1;
    static final public int HAT = 2;
    static final public int TRAMPOLINE = 3;
    static final public int JETPACK = 4;

    // private Obstacle myObstacle;
    private double probablityActivated = 0.6;
    private int Type = 0;
    private Boolean activated = false;
    private int obstacleIndex = 0;

    public int getObstacleIndex() {
        return obstacleIndex;
    }

    static private Image Spring = new Image("DoodleJump/pics/spring.png");
    static private Image Hat = new Image("DoodleJump/pics/hat.png");
    static private Image Trampoline = new Image("DoodleJump/pics/trampoline.png");
    static private Image JetPack = new Image("DoodleJump/pics/jetpack.png");

    PowerUp(Pane gamePane) {
        super();
        gamePane.getChildren().add(this);
        // this.myObstacle = obstacle;
        // this.switchType();
    }

    public static void initialize(PowerUp newPowerUps[], Obstacle newObstacles[], GamePane gamePane) {
        for (int i = 0; i < newPowerUps.length; i++) {
            newPowerUps[i] = new PowerUp(gamePane);

        }
        for (int i = 0; i < newPowerUps.length; i++) {
            newPowerUps[i].Activate(newPowerUps, newObstacles);
        }
    }

    public void Activate(PowerUp newPowerUps[], Obstacle newObstacles[]) {
        int nowObstacleIndex;
        do {
            nowObstacleIndex = (int) (newObstacles.length * Math.random());
        } while (CheckDuplicates(newPowerUps, nowObstacleIndex));

        // System.out.println(nowObstacleIndex);
        this.radnomActivation();
        this.obstacleIndex = nowObstacleIndex;
        newObstacles[nowObstacleIndex].setOccupied(true);
        this.boundTo(newObstacles[nowObstacleIndex]);
    }

    public void radnomActivation() {
        double probablity = Math.random();

        if (probablity > 1 - probablityActivated) {
            this.setVisible(true);
            this.activated = true;

        } else {
            this.setVisible(false);
            this.activated = false;
        }

    }

    private Boolean CheckDuplicates(PowerUp newPowerUps[], int nowObstacleIndex) {
        for (int i = 0; i < newPowerUps.length; i++) {
            if (nowObstacleIndex == newPowerUps[i].getObstacleIndex()) {
                return true;
            }
        }
        return false;
    }

    public void boundTo(Obstacle myObstacle) {

        double probablity = Math.random();
        // System.out.println(probablity);
        if (probablity > 0.20) {
            this.setType(SPRING, myObstacle);
        } else if (probablity > 0.10) {
            this.setType(TRAMPOLINE, myObstacle);
        } else if (probablity > 0.02) {
            this.setType(HAT, myObstacle);
        } else if (probablity > 0) {
            this.setType(JETPACK, myObstacle);
        }

    }

    public void setType(int type, Obstacle myObstacle) {
        switch (type) {
            case SPRING:
                this.Type = type;
                this.setImage(Spring);
                this.setViewport(new Rectangle2D(0, 0, 34, 23));
                this.setFitWidth(20);
                this.setFitHeight(13);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() - 1));
                this.xProperty().bind(myObstacle.xProperty().add((int) (Math.random() * 40) + 6));
                break;
            case HAT:
                this.Type = type;
                this.setImage(Hat);
                this.setViewport(new Rectangle2D(0, 0, 60, 38));
                this.setFitWidth(40);
                this.setFitHeight(25);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() - 1));
                this.xProperty().bind(myObstacle.xProperty().add(14));
                break;
            case TRAMPOLINE:
                this.Type = type;
                this.setImage(Trampoline);
                this.setViewport(new Rectangle2D(0, 0, 71, 34));
                this.setFitWidth(50);
                this.setFitHeight(23);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() - 3));
                this.xProperty().bind(myObstacle.xProperty().add(11));
                break;
            case JETPACK:
                this.Type = type;
                this.setImage(JetPack);
                this.setViewport(new Rectangle2D(0, 0, 48, 74));
                this.setFitWidth(33);
                this.setFitHeight(50);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() - 2));
                this.xProperty().bind(myObstacle.xProperty().add(18));
                break;
        }

    }

    public void Execute(Player Doodle, Rectangle Hitbox, int distance, double lastYPostion, double jumpHeight) {
        if (this.activated == false) {
            return;
        }
        switch (this.getType()) {

            case PowerUp.SPRING:
                if (Hitbox.getY() + Hitbox.getHeight() == this.getY() && distance > 0) {
                    Doodle.setyVelocity(jumpHeight - 10);
                    Hitbox.setY(lastYPostion);
                }
                break;

            case PowerUp.HAT:
                Timeline hatFly = new Timeline(new KeyFrame(Duration.millis(20), e -> {
                    Doodle.setyVelocity(jumpHeight);
                    Doodle.setHasSomething(true);

                }));
                hatFly.setOnFinished(e -> {
                    Doodle.setHasSomething(false);
                });
                hatFly.setCycleCount(200);
                hatFly.play();
                this.setVisible(false);
                break;

            case PowerUp.TRAMPOLINE:
                if (Hitbox.getY() + Hitbox.getHeight() == this.getY() && distance > 0) {
                    Doodle.setyVelocity(jumpHeight -20);
                    Hitbox.setY(lastYPostion - 40);
                    //Hitbox.setY(lastYPostion);
                    Timeline flip = new Timeline(new KeyFrame(Duration.millis(4), e -> {
                        Doodle.setCanFlip(false);
                        Doodle.setRotate(Doodle.getRotate()+ 2);
    
                    }));
                    flip.setOnFinished(e -> {
                         Doodle.setRotate(0);
                         Doodle.setCanFlip(true);
                    });
                    flip.setCycleCount(180);
                    flip.play();
                    
                }
                break;
                

            case PowerUp.JETPACK:
                Timeline jetFly = new Timeline(new KeyFrame(Duration.millis(20), e -> {
                    Doodle.setyVelocity(jumpHeight - 5);
                    Doodle.setHasSomething(true);
                }));
                jetFly.setOnFinished(e -> {
                    Doodle.setHasSomething(false);
                });
                jetFly.setCycleCount(300);
                jetFly.play();
                this.setVisible(false);
                break;

        }

    }

    public void teleportUP(Player Doodle, Obstacle newObstacles[]) {
        if (this.getY() + this.getFitHeight() < 0) {
            this.radnomActivation();
            this.boundTo(newObstacles[this.obstacleIndex]);
        }
    }

    public void setPostion(double X, double Y) {
        this.setX(X);
        this.setY(Y);
    }

    public int getType() {
        return Type;
    }

    public Boolean getStatus() {
        return activated;
    }

}
