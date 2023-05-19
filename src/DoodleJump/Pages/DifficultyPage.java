package DoodleJump.Pages;



////////////////////////////////////////////////////////////////////////////////
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import DoodleJump.GameLogic.GamePage;
////////////////////////////////////////////////////////////////////////////////

public class DifficultyPage extends Pane {

    private Stage stage;
    private Scene scene;
    private Pane buttons = new Pane();

    private ImageView Background2_iv = new ImageView(Images.Background2);
    private ImageView Easy_iv = new ImageView(Images.Easy);
    private ImageView Medium_iv = new ImageView(Images.Medium);
    private ImageView Hard_iv = new ImageView(Images.Hard);
    private ImageView X_iv = new ImageView(Images.X);

    public DifficultyPage(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        double x = 453.75;
        double y = 172;
////////////////////////////////////////////////////////////////////////////////

        Easy_iv.setX(720);
        Easy_iv.setY(360);
        Easy_iv.setFitWidth(x);
        Easy_iv.setFitHeight(y);
        Easy_iv.setOnMouseEntered(e -> {
            Easy_iv.setImage(Images.Easy2);
            Easy_iv.setCursor(Cursor.HAND);
            Easy_iv.setFitWidth(x + 20);
            Easy_iv.setFitHeight(y + 7.56);
        });
        Easy_iv.setOnMouseExited(e -> {
            Easy_iv.setImage(Images.Easy);
            Easy_iv.setFitWidth(x);
            Easy_iv.setFitHeight(y);
        });

        Easy_iv.setOnMouseClicked(e -> {
            GamePage game = new GamePage(GamePage.ResolutionCustom, stage);
            game.start();
            stage.setScene(game.play());

        });
////////////////////////////////////////////////////////////////////////////////

        Medium_iv.setX(720);
        Medium_iv.setY(560);
        Medium_iv.setFitWidth(x);
        Medium_iv.setFitHeight(y);
        Medium_iv.setOnMouseEntered(e -> {
            Medium_iv.setImage(Images.Medium2);
            Medium_iv.setCursor(Cursor.HAND);
            Medium_iv.setFitWidth(x + 20);
            Medium_iv.setFitHeight(y + 7.56);
        });
        Medium_iv.setOnMouseExited(e -> {
            Medium_iv.setImage(Images.Medium);
            Medium_iv.setFitWidth(x);
            Medium_iv.setFitHeight(y);
        });

        Medium_iv.setOnMouseClicked(e -> {

        });
////////////////////////////////////////////////////////////////////////////////

        Hard_iv.setX(720);
        Hard_iv.setY(760);
        Hard_iv.setFitWidth(x);
        Hard_iv.setFitHeight(y);
        Hard_iv.setOnMouseEntered(e -> {
            Hard_iv.setImage(Images.Hard2);
            Hard_iv.setCursor(Cursor.HAND);
            Hard_iv.setFitWidth(x + 20);
            Hard_iv.setFitHeight(y + 7.56);
        });
        Hard_iv.setOnMouseExited(e -> {
            Hard_iv.setImage(Images.Hard);
            Hard_iv.setFitWidth(x);
            Hard_iv.setFitHeight(y);
        });

        Hard_iv.setOnMouseClicked(e -> {

        });
////////////////////////////////////////////////////////////////////////////////

        X_iv.setX(1380);
        X_iv.setY(80);
        X_iv.setFitWidth(40);
        X_iv.setFitHeight(40);
        X_iv.setOnMouseEntered(e -> {
            X_iv.setImage(Images.X2);
            X_iv.setCursor(Cursor.HAND);
        });
        X_iv.setOnMouseExited(e -> {
            X_iv.setImage(Images.X);
        });
        X_iv.setOnMouseClicked(e -> {
            MainPage main = new MainPage(stage);
            main.start();
            stage.setScene(main.play());
        });
////////////////////////////////////////////////////////////////////////////////

        buttons.getChildren().addAll(Easy_iv, Medium_iv, Hard_iv);
        this.getChildren().addAll(Background2_iv, X_iv, buttons);
    }

    public Scene play() {
        scene = new Scene(this);
        return scene;
    }
}
