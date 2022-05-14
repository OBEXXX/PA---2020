package jogo.iu.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import jogo.logica.dados.JogoObservavel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Random;

public class PainelTabuleiro extends GridPane {
    private JogoObservavel jogoObservavel;

    public PainelTabuleiro(JogoObservavel jogoObservavel) throws FileNotFoundException {
        this.jogoObservavel = jogoObservavel;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                Celula celula = new Celula(jogoObservavel);

                this.add(celula, j, i);
            }
        }

        this.setAlignment(Pos.CENTER);

        this.setPadding(new Insets(20, 20, 20, 20));

        this.setGridLinesVisible(true);

        this.setMaxHeight(Double.MAX_VALUE);
        this.setMaxWidth(Double.MAX_VALUE);

        this.setStyle("-fx-border-color: green");
    }

    private class Celula extends BorderPane {
        private JogoObservavel jogoObservavel;
        private Circle circle;

        public Celula(JogoObservavel jogoObservavel) {
            this.jogoObservavel = jogoObservavel;

            if (new Random().nextInt(2) == 1) {
                circle = new Circle(35, Color.web("#F5C842"));
            } else {
                circle = new Circle(35, Color.web("#E84B46"));
            }

            this.setStyle("-fx-background-color: #39B4EB;");;

            setCenter(circle);
        }
    }
}
