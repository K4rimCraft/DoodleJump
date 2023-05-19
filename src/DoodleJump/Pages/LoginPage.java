package DoodleJump.Pages;

import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginPage extends Pane {

    private Stage stage;
    private Scene scene;
    private ImageView Background_iv = new ImageView(Images.Background4);
    private ImageView Next_iv = new ImageView(Images.Next);
    private ImageView X_iv = new ImageView(Images.X);
    TextField Name = new TextField();
    TextField Age = new TextField();
    ChoiceDialog b = new ChoiceDialog();

    public LoginPage(Stage stage) {
        this.stage = stage;
    }

    public void start() {

        Next_iv.setX(780);
        Next_iv.setY(790);
        Next_iv.setFitWidth(365.63);
        Next_iv.setFitHeight(138.29);
        Next_iv.setOnMouseEntered(e -> {
            Next_iv.setCursor(Cursor.HAND);
            Next_iv.setImage(Images.Next2);
        });
        Next_iv.setOnMouseExited(e -> {
            Next_iv.setImage(Images.Next);
        });
        Next_iv.setOnMouseClicked(e -> {
            if (!(Name.getText().equals("") && Age.getText().equals(""))) {
                FileIO.Write(Name.getText(), "PlayerName.txt");
                FileIO.Write("0", "Score.txt");
                FileIO.Write("0", "TopScore.txt");
                DifficultyPage play = new DifficultyPage(stage);
                play.start();
                stage.setScene(play.play());
            }
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
        Name.setPromptText("Ente Your Name");
        Name.setLayoutX(880);
        Name.setLayoutY(445);
        Name.setPrefSize(450, 40);
////////////////////////////////////////////////////////////////////////////////

        Age.setPromptText("Ente Your Age");
        Age.setLayoutX(880);
        Age.setLayoutY(650);
        Age.setPrefSize(250, 40);
////////////////////////////////////////////////////////////////////////////////
        this.getChildren().addAll(Background_iv, Next_iv, X_iv, Name, Age);

    }

    public Scene play() {
        scene = new Scene(this);
        return scene;
    }
}
