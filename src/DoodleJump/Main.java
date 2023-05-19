package DoodleJump;

import java.io.File;

import DoodleJump.Pages.Images;
import DoodleJump.Pages.MainPage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application {

        public static String PathToFont = "file:E:\\";
        public static String PathToResources = "C:\\Users\\kimos\\Downloads\\CSE1 2nd Term\\Programming\\VS Code Java Projects\\DoodleJump\\";

        @Override
        public void start(Stage stage) {

                // Media media = new Media(new File(PathToResources +
                // "Intro.mp4").toURI().toString());
                // MediaPlayer Intro = new MediaPlayer(media);
                // MediaView intro = new MediaView(Intro);
                // intro.setFitWidth(1920);
                // intro.setFitHeight(1080);

                Images images = new Images();
                MainPage firstPage = new MainPage(stage);
                firstPage.start();

                // Intro.play();
                stage.setScene(firstPage.play());
                // Intro.setOnEndOfMedia(new Runnable() {
                // @Override
                // public void run() {
                // Intro.pause();
                // firstPage.start();
                // stage.setScene(firstPage.play());
                // }
                // });

                stage.setTitle("Doddle_Jumb");
                stage.show();
        }

        public static void main(String[] args) {
                launch(args);
        }

}
