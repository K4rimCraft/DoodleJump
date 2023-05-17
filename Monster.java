package DoodleJump;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Monster extends ImageView {

    static final public int FLY = 1;
    static final public int WORM = 2;
    static final public int PP = 3;
    static final public int OP = 4;

    static final public Point2D FLYpos = new Point2D(25, 67);
    static final public Point2D WORMpos = new Point2D(0, 0);
    static final public Point2D PPpos = new Point2D(0, 0);
    static final public Point2D OPpos = new Point2D(0, 0);

    static private Image Monster1 = new Image("DoodleJump/pics/Monster_1.png");
    private int i = 0;
    private Boolean reverse = false;

    private double probablityActivated = 0.5;
    private int Type = 0;
    private Boolean activated = false;
    public Boolean getStatus() {
        return activated;
    }

    private int obstacleIndex = 0;

    public int getObstacleIndex() {
        return obstacleIndex;
    }

    public void setObstacleIndex(int obstacleIndex) {
        this.obstacleIndex = obstacleIndex;
    }

    public Monster(Pane gamePane) {
        gamePane.getChildren().add(this);
    }

    public static void initialize(Monster newMonsters[], PowerUp newPowerUps[], Obstacle newObstacles[],
            GamePane gamePane) {
        for (int i = 0; i < newMonsters.length; i++) {
            newMonsters[i] = new Monster(gamePane);

        }
        for (int i = 0; i < newMonsters.length; i++) {
            newMonsters[i].Activate(newMonsters, newPowerUps, newObstacles);
        }
    }

    public void ani() {
        // int i = 0;
        Timeline animate = new Timeline(new KeyFrame(Duration.millis(25), e -> {
            this.setViewport(new Rectangle2D(i * 156, 0, 156, 89));
            if (reverse == false) {
                i++;
                if (i == 4)
                    reverse = true;
            } else {
                i--;
                if (i == 0)
                    reverse = false;
            }
        }));
        animate.setCycleCount(Timeline.INDEFINITE);
        animate.play();

    }
     
    public void Activate(Monster newMonsters[], PowerUp newPowerUps[], Obstacle newObstacles[]) {
        int nowObstacleIndex;
        do {
            nowObstacleIndex = (int) (newObstacles.length * Math.random());
        } while (CheckDuplicates(newMonsters, newPowerUps, nowObstacleIndex));

        // System.out.println(nowObstacleIndex);
        //this.radnomActivation();
        this.obstacleIndex = nowObstacleIndex;
        this.ani();
        this.setVisible(false);
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

    private Boolean CheckDuplicates(Monster newMonsters[], PowerUp newPowerUps[], int nowObstacleIndex) {
        for (int i = 0; i < newPowerUps.length; i++) {
            if (nowObstacleIndex == newPowerUps[i].getObstacleIndex()) {
                return true;
            }
        }
        for (int i = 0; i < newMonsters.length; i++) {
            if (nowObstacleIndex == newMonsters[i].getObstacleIndex()) {
                return true;
            }
        }
        if(nowObstacleIndex % 2 == 1){
            return true;
        }
        return false;
    }

    public void teleportUP(Player Doodle, Obstacle newObstacles[]) {
        if (this.getY() + this.getFitHeight() < 0) {
            this.radnomActivation();
            this.boundTo(newObstacles[this.obstacleIndex]);
        }
    }

    public void boundTo(Obstacle myObstacle) {

        double probablity = Math.random();
        // System.out.println(probablity);
        if (probablity > 0.0) {
            this.setType(FLY, myObstacle);
        } else if (probablity > 0.10) {
            this.setType(WORM, myObstacle);
        } else if (probablity > 0.02) {
            this.setType(PP, myObstacle);
        } else if (probablity > 0) {
            this.setType(OP, myObstacle);
        }

    }

    public void setType(int type, Obstacle myObstacle) {
        switch (type) {
            case FLY:
                this.Type = type;
                this.setImage(Monster1);
                this.setViewport(new Rectangle2D(0, 0, 156, 89));
                this.setFitWidth(120);
                this.setFitHeight(70);
                Timeline move = new Timeline(new KeyFrame(Duration.millis(50), e -> {
                    this.xProperty().bind(myObstacle.xProperty().subtract(FLYpos.getX() + (int) (Math.random() * 3)));
                    this.yProperty().bind(myObstacle.yProperty().subtract(FLYpos.getY() + (int) (Math.random() * 3)));
                    //this.setX((int) (Math.random() * 3) + 200);
                    //this.setY((int) (Math.random() * 3) + 200);
                }));

                move.setCycleCount(Timeline.INDEFINITE);
                move.play();
                break;
            // case WORM:
            // this.Type = type;
            // this.setImage(Hat);
            // this.setViewport(new Rectangle2D(0, 0, 60, 38));
            // this.setFitWidth(40);
            // this.setFitHeight(25);
            // this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() -
            // 1));
            // this.xProperty().bind(myObstacle.xProperty().add(14));
            // break;
            // case PP:
            // this.Type = type;
            // this.setImage(Trampoline);
            // this.setViewport(new Rectangle2D(0, 0, 71, 34));
            // this.setFitWidth(50);
            // this.setFitHeight(23);
            // this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() -
            // 3));
            // this.xProperty().bind(myObstacle.xProperty().add(11));
            // break;
            // case OP:
            // this.Type = type;
            // this.setImage(JetPack);
            // this.setViewport(new Rectangle2D(0, 0, 48, 74));
            // this.setFitWidth(33);
            // this.setFitHeight(50);
            // this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight() -
            // 2));
            // this.xProperty().bind(myObstacle.xProperty().add(18));
            // break;
        }

    }

}
