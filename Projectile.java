package DoodleJump;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Projectile extends ImageView {

    private static ArrayList<Projectile> removeProjectiles = new ArrayList<Projectile>();
    private static Image ProjectileImage = new Image("DoodleJump/pics/Projectile.png");
    private double Xspeed = 0;
    private double Yspeed = 0;
    private double Angle = 0;

    Projectile() {
        super(ProjectileImage);
        this.setVisible(false);
    }

    public static void Loop(ArrayList<Projectile> newProjectiles, Monster newMonsters[]){
        for (Projectile pro : newProjectiles) {
            for (int i = 0; i < 2; i++) {
                pro.setX(pro.getX() + pro.getXspeed());
                pro.setY(pro.getY() - pro.getYspeed());
                for (Monster mon : newMonsters) {
                    if (pro.getBoundsInParent().intersects(mon.getBoundsInParent())
                            && mon.getStatus() == true) {
                        removeProjectiles.add(pro);
                        pro.setVisible(false);
                        mon.Deactivate();
                    }
                }
                if (pro.getX() > GamePane.PlayerRightBorder || pro.getX() < GamePane.PlayerLeftBorder - 50 || pro.getY() > 1080
                        || pro.getY() < -20) {
                    removeProjectiles.add(pro);
                    pro.setVisible(false);
                }
            }
        }
        newProjectiles.removeAll(removeProjectiles);
        removeProjectiles.removeAll(removeProjectiles);
    }

    public static void create(Player Doodle, ArrayList<Projectile> newProjectiles, Pane gamePane, double X, double Y) {
        if(Y > Doodle.Hitbox.getY()* 2.5/3 - 70){
            return;
        }
        Projectile nowProjectile = new Projectile();
        gamePane.getChildren().add(nowProjectile);
        newProjectiles.add(nowProjectile);

        double tempX = X - Doodle.Hitbox.getX()* 2.5/3;
        double tempY = Doodle.Hitbox.getY()* 2.5/3 - Y;
        nowProjectile.Angle = Math.atan2(tempX, tempY);
        nowProjectile.Xspeed = Math.sin(nowProjectile.Angle) * 10;
        nowProjectile.Yspeed = Math.cos(nowProjectile.Angle) * 10;
        nowProjectile.setX(Doodle.Hitbox.getX() );
        nowProjectile.setY(Doodle.Hitbox.getY());
        nowProjectile.setVisible(true);

        // System.out.println("screen X: " + X);
        // System.out.println("screen Y: " + Y);
        // System.out.println("doodle X: " + Doodle.Hitbox.getX()* 2.5/3);
        // System.out.println("doodle Y: " + Doodle.Hitbox.getY()* 2.5/3);
        // System.out.println("traingle X: " + tempX);
        // System.out.println("traingle Y: " + tempY);
        // System.out.println("Angle: " + nowProjectile.Angle);
        // System.out.println("Speed X: " + nowProjectile.Xspeed);
        // System.out.println("Speed Y: " + nowProjectile.Yspeed);
        // System.out.println(newProjectiles.size());
        // System.out.println();

    }

    public double getXspeed() {
        return Xspeed;
    }

    public double getYspeed() {
        return Yspeed;
    }

}
