
package DoodleJump;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class Obstacle extends ImageView {
     private int movementDir = 1;
     private Boolean landOn = false;
     private Boolean canMove = false;
     private Boolean activated = true;
     private int index = 0;
     static private int num = 0;

     Obstacle(double X, double Y, int width, int height) {
          super(new Image("DoodleJump/pics/obs.png"));
          this.setX(X);
          this.setY(Y);
          this.setFitWidth(width);
          this.setFitHeight(height);
     }

     public void swing(double screenWidth) {
          if (canMove == true && activated == true) {
               double pos = this.getX();
               if (pos > screenWidth - this.getFitWidth() - 50 || pos < 50) {
                    this.toggleDir();
               }
               this.setX(pos + (3 * movementDir));
          }
     }

     public void teleportUP(Player Doodle, double screenWidth, double screenHeight) {
          if (this.getY() + this.getFitHeight() > screenHeight && activated == true) {
               this.setY(this.getY() - screenHeight);

               this.setX(((int) (Math.random() * 22) * (screenWidth - 150) / 22) + 50);
               double probablity = Math.random();
               if (probablity > 0.5) {
                    this.toggleDir();
               }
               if (probablity > 0.9) {
                    this.setMove(true);
                    this.setImage(new Image("DoodleJump/pics/obs2.png"));
               } else {
                    this.setMove(false);
                    this.setImage(new Image("DoodleJump/pics/obs.png"));
               }
               if (Doodle.getScore() > 1000 && index % 2 == 0 && probablity > 0.5) {
                    this.Deactivate();
                    num++;
                    //System.out.println(num);
                    this.setImage(new Image("DoodleJump/pics/obs3.png"));
               }
          }
     }

     public int getIndex() {
          return index;
     }

     public void setIndex(int index) {
          this.index = index;
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
