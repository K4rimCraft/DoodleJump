
package DoodleJump;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Obstacle extends ImageView {
     private int movementDir = 1;
     private Boolean landOn = false;
     private Boolean canMove = false;
     private Boolean activated = true;
     static private double Width = 58;
     static private double Height = 15;
     private int index = 0;
     static private int num = 0;

     Obstacle(double X, double Y, int index) {
          super(new Image("DoodleJump/pics/obs.png"));
          this.setX(X);
          this.setY(Y);
          this.setFitWidth(Width);
          this.setFitHeight(Height);
          this.index = index;
          if (Math.random() > 0.95) {
               this.setMove(true);
               this.setImage(new Image("DoodleJump/pics/obs2.png"));
           }
     }

     public void swing(double screenWidth) {
          if (canMove == true && activated == true) {
               double pos = this.getX();
               if (pos > GameScene.LeftBorder + screenWidth - Obstacle.Width || pos < GameScene.LeftBorder) {
                    this.toggleDir();
               }
               this.setX(pos + (3 * movementDir));
          }
     }

     public void teleportUP(Player Doodle, double GameScreenWidth, double GameScreenHeight) {
          if (this.getY() + Obstacle.Width > GameScreenHeight && activated == true) {
               this.setY(this.getY() - GameScreenHeight);
               this.setX(Obstacle.xRandom(GameScreenWidth));
               double probablity = Math.random();
               if (probablity > 0.5) {
                    this.toggleDir();
               }
               if (probablity > 0.95) {
                    this.setMove(true);
                    this.setImage(new Image("DoodleJump/pics/obs2.png"));
               } else {
                    this.setMove(false);
                    this.setImage(new Image("DoodleJump/pics/obs.png"));
               }
               if (Doodle.getScore() > 1000 && index % 2 == 1 && probablity > 0.7) {
                    this.Deactivate();
                    //num++;
                    //System.out.println(num);
                    this.setImage(new Image("DoodleJump/pics/obs3.png"));
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
