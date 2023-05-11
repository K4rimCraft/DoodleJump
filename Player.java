package DoodleJump;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Player extends ImageView {

    private double lastXPostion = 0;
    private double lastYPostion = 0;
    private double yVelocity = 0;
    private double xVelocity = 5;
    private double jumpHeight = -20;
    private double Score = 0;
    private double xHitBoxOffset = 5;
    
    private double accerlationOfGravity = 10;
    static final int LEFT = -1;
    static final int RIGHT = 1;
    Rectangle Hitbox = new Rectangle(275, 950, 33, 49);

    Player() {
        super(new Image("DoodleJump/pics/doodleR.png"));
        this.setFitHeight(50);
        this.setFitWidth(50);
        this.setX(275);
        this.setY(950);
        Hitbox.setVisible(false);
    }

    public void moveY(int distance, ImageView newObstacles[]) {

        for (int i = 0; i < Math.abs(yVelocity); i++) {
            for (int j = 0; j < newObstacles.length; j++) {
                if (Hitbox.getBoundsInParent().intersects(newObstacles[j].getBoundsInParent())) {
                    if (Hitbox.getY() + Hitbox.getHeight() == newObstacles[j].getY() && distance > 0) {
                        Hitbox.setY(lastYPostion);
                        // canJump = true;
                        yVelocity = jumpHeight;
                        // direction = -1;
                        return;
                    }
                }
            }
            lastYPostion = Hitbox.getY();
            if (distance < 0)
                Hitbox.setY(Hitbox.getY() - 1);
            if (distance > 0)
                Hitbox.setY(Hitbox.getY() + 1);
        }
        this.setY(Hitbox.getY());
    }

    public void moveX(int direction, ImageView newObstacles[]) {

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
                this.setImage(new Image("DoodleJump/pics/doodleL.png"));
                xHitBoxOffset = 17;
            }

            if (direction == 1) {
                Hitbox.setX(Hitbox.getX() + 1);
                this.setImage(new Image("DoodleJump/pics/doodleR.png"));
                xHitBoxOffset = 0;
            }

        }
        this.setX(Hitbox.getX() - xHitBoxOffset);
    }

    public void screenScroll(ImageView newObstacles[], double screenHeight) {
        if (this.getY() < (screenHeight / 1.5)) {
            for (int i = 0; i < (screenHeight / 1.5) - this.getY(); i++) {
                for (int index = 0; index < newObstacles.length; index++) {
                    newObstacles[index].setY(newObstacles[index].getY() + 0.025);
                }
                Hitbox.setY(Hitbox.getY() + 0.025);
                Score += 0.025;
            }

            for (int index = 0; index < newObstacles.length; index++) {
                newObstacles[index].setY(Math.ceil(newObstacles[index].getY()));
            }
            Hitbox.setY(Math.ceil(Hitbox.getY()));
            Score = Math.ceil(Score);
        }
    }

    public void gravityCycle(ImageView newObstacles[]) {
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
