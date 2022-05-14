import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jogo.iu.gui.GameView;
import jogo.iu.gui.PainelTabuleiro;
import jogo.logica.dados.JogoObservavel;

import java.io.FileNotFoundException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        Scene scene = new Scene(new GameView(new JogoObservavel()), 500, 500);

        primaryStage.setTitle("4 em Linha");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(500);
        primaryStage.setMinWidth(550);
        primaryStage.show();
    }
}
