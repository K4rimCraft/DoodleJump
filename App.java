package DoodleJump;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        
        GamePane game = new GamePane(GamePane.ResolutionFullHD);
        game.start();
        Scene sc = new Scene(game,1600, 900);
        primaryStage.setResizable(false);
        primaryStage.setScene(sc);
        primaryStage.show();
    
    }

    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }
}
