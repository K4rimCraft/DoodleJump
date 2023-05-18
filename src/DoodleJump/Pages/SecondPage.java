package DoodleJump.Pages;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SecondPage extends Pane {

    private Stage stage;
    private Scene scene;

    private ImageView Background_iv = new ImageView(Images.Background8);
    private ImageView NewGame_iv = new ImageView(Images.NewGame);
    private ImageView Continue_iv = new ImageView(Images.Continue);
    private ImageView X_iv = new ImageView(Images.X);

    public SecondPage(Stage stage) {
        this.stage = stage;
    }

    public void start() {
        double x = 475.32;
        double y = 179.78;

        NewGame_iv.setX(720);
        NewGame_iv.setY(600);
        NewGame_iv.setFitWidth(x);
        NewGame_iv.setFitHeight(y);
        NewGame_iv.setOnMouseEntered(e -> {
            NewGame_iv.setCursor(Cursor.HAND);
            NewGame_iv.setImage(Images.NewGame2);
            NewGame_iv.setFitWidth(x + 20);
            NewGame_iv.setFitHeight(y + 7.56);
        });
        NewGame_iv.setOnMouseExited(e -> {
            NewGame_iv.setImage(Images.NewGame);
            NewGame_iv.setFitWidth(x);
            NewGame_iv.setFitHeight(y);
        });
        NewGame_iv.setOnMouseClicked(e -> {
            Login login = new Login(stage);
            login.start();
            stage.setScene(login.play());
        });
////////////////////////////////////////////////////////////////////////////////

        Continue_iv.setX(720);
        Continue_iv.setY(300);
        Continue_iv.setFitWidth(475.32);
        Continue_iv.setFitHeight(179.78);
        Continue_iv.setOnMouseEntered(e -> {
            Continue_iv.setCursor(Cursor.HAND);
            Continue_iv.setImage(Images.Continue2);
            Continue_iv.setFitWidth(x + 20);
            Continue_iv.setFitHeight(y + 7.56);
        });
        Continue_iv.setOnMouseExited(e -> {
            Continue_iv.setImage(Images.Continue);
            Continue_iv.setFitWidth(x);
            Continue_iv.setFitHeight(y);
        });
        Continue_iv.setOnMouseClicked(e -> {
            LevelPage play = new LevelPage(stage);
            play.start();
            stage.setScene(play.play());
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
            MainPage mainPage = new MainPage(stage);
            mainPage.start();
            stage.setScene(mainPage.play());
        });
////////////////////////////////////////////////////////////////////////////////

        this.getChildren().addAll(Background_iv, NewGame_iv, X_iv);
        if (!(ReadAndWrite.Read("PlayerName.txt").equals(""))) {
            this.getChildren().add(Continue_iv);
        } else {
            NewGame_iv.setX(722.34);
            NewGame_iv.setY(450.11);
        }
    }

    public Scene play() {
        scene = new Scene(this);
        return scene;
    }
}
