
package DoodleJump;

import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Obstacle extends ImageView {
     private int movementDir = 1;
     private Boolean landOn = false;
     private Boolean canMove = false;
     private Boolean activated = true;
     //private Rectangle2D tileView = new Rectangle2D(0, 0, 115, 30);
     static private double Width = 70;
     static private double Height = 18;
     private int index = 0;
     static private int num = 0;
     static private Image obstacleTiles = new Image("DoodleJump/pics/ObstacleTiles.png");

     Obstacle(double X, double Y, int index) {
          super(obstacleTiles);
          this.setViewport(new Rectangle2D(0, 0, 115, 30));
          this.setX(X);
          this.setY(Y);
          this.setFitWidth(Width);
          this.setFitHeight(Height);
          this.index = index;
          if (Math.random() > 0.95) {
               this.setMove(true);
               this.setViewport(new Rectangle2D(0, 30, 115, 30));
           }
          
     }

     public void swing(double screenWidth) {
          if (canMove == true && activated == true) {
               double pos = this.getX();
               if (pos > GameScene.LeftBorder + screenWidth - Obstacle.Width || pos < GameScene.LeftBorder) {
                    this.toggleDir();
               }
               this.setX(pos + (2 * movementDir));
          }
     }

     public void teleportUP(Player Doodle, double GameScreenWidth, double GameScreenHeight) {
          if (this.getY() + Obstacle.Height > GameScreenHeight && activated == true) {
               this.setY(this.getY() - GameScreenHeight);
               this.setX(Obstacle.xRandom(GameScreenWidth));
               double probablity = Math.random();
               if (probablity > 0.5) {
                    this.toggleDir();
               }
               if (probablity > 0.95) {
                    this.setMove(true);
                    this.setViewport(new Rectangle2D(0, 30, 115, 30));
               } else {
                    this.setMove(false);
                    this.setViewport(new Rectangle2D(0, 0, 115, 30));
               }
               if (Doodle.getScore() > 3000 && index % 2 == 1 && probablity > 0.7) {
                    this.Deactivate();
                    //num++;
                    //System.out.println(num);
                    this.setViewport(new Rectangle2D(0, 60, 115, 30));
               }
          }
     }
     static public double xRandom(double GameScreenWidth){
          return ((int) (Math.random() * 100) * (GameScreenWidth - Obstacle.Width) / 100) + GameScene.LeftBorder;
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

}
