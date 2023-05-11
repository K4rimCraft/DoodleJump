package DoodleJump;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        GameScene game = new GameScene(GameScene.ResolutionFullHD);
        game.start();
        primaryStage.setScene(game);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
