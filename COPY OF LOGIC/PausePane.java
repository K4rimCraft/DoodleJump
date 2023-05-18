package DoodleJump.GameLogic;

import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import DoodleJump.Pages.*;

public class PausePane {

    private static ImageView X_iv = new ImageView(Images.X);
    private static ImageView PlayAgain_iv = new ImageView(Images.PlayAgain);
    private static ImageView Main_iv = new ImageView(Images.Main);
    private static ImageView Resume_iv = new ImageView(Images.Resume);
    private static ImageView BackGround4 = new ImageView(Images.Background9);

    public static Scene Pause(Stage stage, Scene scene, Player Doodle, AnimationTimer GameLoop) {
        Pane pane = new Pane();
        Scene scene2;
        Text score = new Text(1150, 375, (int) Doodle.Score + "");
        score.setFill(Color.BLACK);
        score.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 50));


        PlayAgain_iv.setX(570);
        PlayAgain_iv.setY(730);
        PlayAgain_iv.setFitWidth(365.63);
        PlayAgain_iv.setFitHeight(138.29);
        PlayAgain_iv.setOnMouseEntered(e -> {
            PlayAgain_iv.setImage(Images.PlayAgain2);
            PlayAgain_iv.setFitWidth(365.63 + 20);
            PlayAgain_iv.setFitHeight(138.29 + 7.56);
            PlayAgain_iv.setCursor(Cursor.HAND);
        });
        PlayAgain_iv.setOnMouseExited(e -> {
            PlayAgain_iv.setImage(Images.PlayAgain);
            PlayAgain_iv.setFitWidth(365.63);
            PlayAgain_iv.setFitHeight(138.29);
        });
        PlayAgain_iv.setOnMouseClicked(e -> {
            LevelPage play = new LevelPage(stage);
            play.start();
            stage.setScene(play.play());
        });


        Main_iv.setX(970);
        Main_iv.setY(730);
        Main_iv.setFitWidth(365.63);
        Main_iv.setFitHeight(138.29);
        Main_iv.setOnMouseEntered(e -> {
            Main_iv.setCursor(Cursor.HAND);
            Main_iv.setImage(Images.Main2);
            Main_iv.setFitWidth(365.63 + 20);
            Main_iv.setFitHeight(138.29 + 7.56);
        });
        Main_iv.setOnMouseExited(e -> {
            Main_iv.setImage(Images.Main);
            Main_iv.setFitWidth(365.63);
            Main_iv.setFitHeight(138.29);
        });
        Main_iv.setOnMouseClicked(e -> {
            MainPage mainPage = new MainPage(stage);
            mainPage.start();
            stage.setScene(mainPage.play());
        });


        Resume_iv.setX(760);
        Resume_iv.setY(520);
        Resume_iv.setFitWidth(402.2);
        Resume_iv.setFitHeight(152.12);
        Resume_iv.setOnMouseEntered(e -> {
            Resume_iv.setCursor(Cursor.HAND);
            Resume_iv.setImage(Images.Resume2);
            Resume_iv.setFitWidth(402.2 + 20);
            Resume_iv.setFitHeight(152.12 + 7.56);
        });
        Resume_iv.setOnMouseExited(e -> {
            Resume_iv.setImage(Images.Resume);
            Resume_iv.setFitWidth(402.2);
            Resume_iv.setFitHeight(152.12);
        });
        Resume_iv.setOnMouseClicked(e -> {
            stage.setScene(scene);
            GameLoop.start();
        });


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
            stage.setScene(scene);
        });


        pane.getChildren().addAll(BackGround4, X_iv, PlayAgain_iv, Main_iv, Resume_iv, score);
        scene2 = new Scene(pane);
        return scene2;
    }
}
