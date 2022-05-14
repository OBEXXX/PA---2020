package jogo.iu.gui;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import jogo.logica.dados.JogoObservavel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;

public class GameView extends BorderPane {
    private JogoObservavel jogoObservavel;

    private MenuInicial menuInicial;
    private PainelTabuleiro painelTabuleiro;

    public GameView(JogoObservavel jogoObservavel) throws FileNotFoundException {
        this.jogoObservavel = jogoObservavel;

        menuInicial = new MenuInicial(jogoObservavel);
        painelTabuleiro = new PainelTabuleiro(jogoObservavel);

        this.setCenter(menuInicial);

    }
}
