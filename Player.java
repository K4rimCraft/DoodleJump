package DoodleJump;

import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;

import java.io.File;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Player extends ImageView {

    private double lastXPostion = 0;
    private double lastYPostion = 0;
    private double yVelocity = 0;
    private double xVelocity = 5;
    private double jumpHeight = -20;
    private double Score = 0;
    private double xHitBoxOffset = 0;
    private Point2D initialPosition = new Point2D(262, 940);
    static private Image playerTiles = new Image("DoodleJump/pics/DoodleTile.png");
    private AudioClip pop = new AudioClip(
            new File("C:\\Users\\kimos\\Downloads\\CSE1 2nd Term\\Programming\\VS Code Java Projects\\jump.wav").toURI()
                    .toString());
    private double accerlationOfGravity = 10;
    static final int LEFT = -1;
    static final int RIGHT = 1;
    Rectangle Hitbox = new Rectangle(GamePane.LeftBorder + initialPosition.getX(), initialPosition.getY(), 40, 59);

    Player() {
        super(playerTiles);
        this.setFitHeight(60);
        this.setFitWidth(60);
        this.setX(GamePane.LeftBorder + initialPosition.getX());
        this.setY(initialPosition.getY());
        Hitbox.setVisible(false);
        Hitbox.setFill(Color.color(0, 0, 0, 0.5));
        this.setViewport(new Rectangle2D(0, 0, 92, 90));
    }

    public void moveY(int distance, Obstacle newObstacles[]) {

        for (int i = 0; i < Math.abs(yVelocity); i++) {
            for (int j = 0; j < newObstacles.length; j++) {
                if (Hitbox.getBoundsInParent().intersects(newObstacles[j].getBoundsInParent())) {
                    if (Hitbox.getY() + Hitbox.getHeight() == newObstacles[j].getY() && distance > 0) {
                        Hitbox.setY(lastYPostion);
                        // canJump = true;
                        yVelocity = jumpHeight;
                        pop.play(0.2);
                        // direction = -1;
                        return;
                    }
                }
                if(newObstacles[j].getHasPowerUP() == true){
                    if(Hitbox.getBoundsInParent().intersects(newObstacles[j].getPowerUp().getBoundsInParent())){
                        newObstacles[j].getPowerUp().Execute(this, Hitbox, distance, lastYPostion , jumpHeight);
                    }
                }
                
            }
            lastYPostion = Hitbox.getY();

            if (distance < 0) {
                Hitbox.setY(Hitbox.getY() - 1);
            }
            if (distance > 0) {
                Hitbox.setY(Hitbox.getY() + 1);
            }
            this.setY(Hitbox.getY());
        }
    }

    public void moveX(int direction, Obstacle newObstacles[]) {

        for (int i = 0; i < Math.abs(xVelocity); i++) {
            for (int j = 0; j < newObstacles.length; j++) {
                if (Hitbox.getBoundsInParent().intersects(newObstacles[j].getBoundsInParent())) {
                    if (Hitbox.getY() + Hitbox.getHeight() == newObstacles[j].getY() && direction > 0) {
                        Hitbox.setX(lastXPostion);
                        // canJump = true;
                        return;
                    }
                }
            }
            lastXPostion = Hitbox.getX();

            if (direction == -1) {
                Hitbox.setX(Hitbox.getX() - 1);
                this.setViewport(new Rectangle2D(0, 90, 92, 90));
                xHitBoxOffset = 20;
                if(this.getX() < GamePane.PlayerLeftBorder - Hitbox.getWidth()){
                    Hitbox.setX(GamePane.PlayerRightBorder);
                }
            }

            if (direction == 1) {
                Hitbox.setX(Hitbox.getX() + 1);
                this.setViewport(new Rectangle2D(0, 0, 92, 90));
                xHitBoxOffset = 0;
                if(Hitbox.getX() > GamePane.PlayerRightBorder){
                   Hitbox.setX(GamePane.PlayerLeftBorder - Hitbox.getWidth());
                }
            }
            
            this.setX(Hitbox.getX() - xHitBoxOffset);
        }

    }

    public void screenScroll(Obstacle newObstacles[], ImageView BG, ImageView BG2) {

        double damping = 0.05;

        if (this.getY() < (GamePane.GameScreenHeight / 1.5)) {
            for (int i = 0; i < (GamePane.GameScreenHeight / 1.5) - this.getY(); i++) {
                for (int index = 0; index < newObstacles.length; index++) {
                    newObstacles[index].setY(newObstacles[index].getY() + damping);
                    
                }
                Hitbox.setY(Hitbox.getY() + damping);
                this.setY(this.getY() + damping);
                Score += damping;
                BG.setY(BG.getY() + 0.005);
                BG2.setY(BG.getY() - GamePane.GameScreenHeight);
            }

            for (int index = 0; index < newObstacles.length; index++) {
                newObstacles[index].setY(Math.ceil(newObstacles[index].getY()));
                
            }
            Hitbox.setY(Math.ceil(Hitbox.getY()));
            this.setY(Math.ceil(this.getY()));
            Score = Math.ceil(Score);
            BG.setY(Math.ceil(BG.getY()));
            BG2.setY(BG.getY() - GamePane.GameScreenHeight);

        }
    }

    public void gravityCycle(Obstacle newObstacles[]) {
        if (yVelocity < accerlationOfGravity)
            yVelocity++;
        this.moveY((int) yVelocity, newObstacles);
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getScore() {
        return Score;
    }
}
