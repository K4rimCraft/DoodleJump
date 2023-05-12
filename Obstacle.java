
package DoodleJump;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Obstacle extends ImageView {
     private int movementDir = 1;
     private Boolean landOn = false;
     private Boolean canMove = false;
     private Boolean activated = true;
     static private Point2D PictureDimensions = new Point2D(115, 30);
     static private double Width = 70;
     static private double Height = 18;
     private Boolean hasPowerUP = false;

     private int index = 0;
     static private int num = 0;
     static private Image obstacleTiles = new Image("DoodleJump/pics/ObstacleTiles.png");
     // private
     private PowerUp powerUp;

     Obstacle(double X, double Y, int index, Pane gamePane) {
          super(obstacleTiles);
          gamePane.getChildren().add(this);
          this.setViewport(new Rectangle2D(0, 0, PictureDimensions.getX(), PictureDimensions.getY()));
          this.setX(X);
          this.setY(Y);
          this.setFitWidth(Width);
          this.setFitHeight(Height);
          this.index = index;
          if (Math.random() > 0.95) {
               this.setMove(true);
               this.setViewport(new Rectangle2D(0, 30, PictureDimensions.getX(), PictureDimensions.getY()));
          }
          if (Math.random() > 0.9) {
               powerUp = new PowerUp(this, gamePane);
               hasPowerUP = true;
          }
     }

     public void swing() {
          if (canMove == true && activated == true) {
               double pos = this.getX();
               if (pos > GamePane.RightBorder - Obstacle.Width || pos < GamePane.LeftBorder) {
                    this.toggleDir();
               }
               this.setX(pos + (2 * movementDir));
          }
     }

     public void teleportUP(Player Doodle) {
          if (this.getY() + Obstacle.Height > GamePane.GameScreenHeight && activated == true) {
               this.setY(this.getY() - GamePane.GameScreenHeight);
               this.setX(Obstacle.xRandom());
               double probablity = Math.random();
               if(this.getHasPowerUP())
                    this.powerUp.switchType();

               if (probablity > 0.5) {
                    this.toggleDir();
               }
               if (probablity > 0.95) {
                    this.setMove(true);
                    this.setViewport(new Rectangle2D(0, 30, PictureDimensions.getX(), PictureDimensions.getY()));
               } else {
                    this.setMove(false);
                    this.setViewport(new Rectangle2D(0, 0, PictureDimensions.getX(), PictureDimensions.getY()));
               }
               if (Doodle.getScore() > 3000 && index % 2 == 1 && probablity > 0.7) {
                    this.Deactivate();
                    // num++;
                    // System.out.println(num);
                    this.setViewport(new Rectangle2D(0, 60, PictureDimensions.getX(), PictureDimensions.getY()));
               }
          }
     }

     static public double xRandom() {
          return ((int) (Math.random() * 100) * (GamePane.GameScreenWidth - Obstacle.Width) / 100)
                    + GamePane.LeftBorder;
     }

     public int getIndex() {
          return index;
     }

     public void toggleDir() {
          if (this.movementDir == 1)
               this.movementDir = -1;
          else
               this.movementDir = 1;
     }

     public Boolean getActivated() {
          return activated;
     }

     public void setActivated(Boolean activated) {
          this.activated = activated;
     }

     public void Deactivate() {
          this.activated = false;
     }

     public int getDir() {
          return this.movementDir;
     }

     public void landOn() {
          this.landOn = true;
     }

     public void setMove(Boolean i) {
          this.canMove = i;
     }

     public Boolean getMove() {
          return this.canMove;
     }

     public PowerUp getPowerUp() {
          return powerUp;
     }

     public Boolean getHasPowerUP() {
          return hasPowerUP;
     }
}
