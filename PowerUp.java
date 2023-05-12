package DoodleJump;

import java.util.function.DoubleUnaryOperator;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class PowerUp extends ImageView {

    static final public int SPRING = 1;
    static final public int HAT = 2;
    static final public int TRAMPOLINE = 3;
    static final public int JETPACK = 4;
    private Obstacle myObstacle;

    private int Type = 0;

    
    static private Image Spring = new Image("DoodleJump/pics/Spring.png");
    static private Image Hat = new Image("DoodleJump/pics/hat.png");
    static private Image Trampoline = new Image("DoodleJump/pics/trampoline.png");
    static private Image JetPack = new Image("DoodleJump/pics/jetpack.png");

    PowerUp(Obstacle obstacle,Pane gamePane) {
        super();
        gamePane.getChildren().add(this);
        this.myObstacle = obstacle;
        double probablity = Math.random();
        System.out.println(probablity);
        if (probablity > 0.0) {
            this.setType(JETPACK);
        } else if (probablity > 0.3) {
            this.setType(HAT);
        } else if (probablity > 0.1) {
            this.setType(TRAMPOLINE);
        } else if (probablity > 0) {
            this.setType(JETPACK);
        }

    }

    public void setType(int type) {
        switch (type) {
            case SPRING:
                this.Type = type;
                this.setImage(Spring);
                this.setViewport(new Rectangle2D(0,0,34,23));
                this.setFitWidth(20);
                this.setFitHeight(13);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight()-1));
                this.xProperty().bind(myObstacle.xProperty().add(6));
                break;
            case HAT:
                this.Type = type;
                this.setImage(Hat);
                this.setViewport(new Rectangle2D(0,0,60,38));
                this.setFitWidth(40);
                this.setFitHeight(25);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight()-1));
                this.xProperty().bind(myObstacle.xProperty().add(14));
                break;
            case TRAMPOLINE:
                this.Type = type;
                this.setImage(Trampoline);
                this.setViewport(new Rectangle2D(0,0,71,34));
                this.setFitWidth(50);
                this.setFitHeight(23);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight()-3));
                this.xProperty().bind(myObstacle.xProperty().add(11));
                break;
            case JETPACK:
                this.Type = type;
                this.setImage(JetPack);
                this.setViewport(new Rectangle2D(0,0,48,74));
                this.setFitWidth(33);
                this.setFitHeight(50);
                this.yProperty().bind(myObstacle.yProperty().subtract(this.getFitHeight()-2));
                this.xProperty().bind(myObstacle.xProperty().add(18));
                break;
        }

    }
    public void setPostion(double X,double Y) {
        this.setX(X);
        this.setY(Y);
    }

    public int getType() {
        return Type;
    }
    public void switchType(){
        
            double probablity = Math.random();
        if (probablity > 0.5) {
            this.setType(SPRING);
        } else if (probablity > 0.3) {
            this.setType(HAT);
        } else if (probablity > 0.1) {
            this.setType(TRAMPOLINE);
        } else if (probablity > 0) {
            this.setType(JETPACK);
        }
        
        
    }

    

}
