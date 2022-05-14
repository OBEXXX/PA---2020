package jogo.iu.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import jogo.logica.dados.JogoObservavel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuInicial extends VBox implements Propriedades {
    private JogoObservavel jogoObservavel;

    private ImageView imagemInicial;
    private HBox hBoxButoes;
    private Button buttonInicia;
    private Button buttonCarrega;
    private Button buttonHistorico;
    private Button buttonSair;

    public MenuInicial(JogoObservavel jogoObservavel) throws FileNotFoundException {
        this.jogoObservavel = jogoObservavel;

        imagemInicial = new ImageView(new Image(new FileInputStream("src/jogo/iu/gui/imagens/imagemInicial.png")));

        buttonInicia = new Button("Iniciar Jogo");
        buttonCarrega = new Button("Carregar Jogo");
        buttonHistorico = new Button("Ver Histórico");
        buttonSair = new Button("Sair do Jogo");

        hBoxButoes = new HBox(buttonInicia, buttonCarrega, buttonHistorico, buttonSair);

        hBoxButoes.setAlignment(Pos.CENTER);

        hBoxButoes.setSpacing(15);

        this.getChildren().addAll(imagemInicial, hBoxButoes);

        this.setStyle("-fx-background-color: #39B4EB;");

        this.setSpacing(50);
        this.setAlignment(Pos.CENTER);

        registaListeners();
        registaObservers();
    }

    private void registaListeners() {
        buttonInicia.setOnAction(event -> jogoObservavel.inicia());

        buttonCarrega.setOnAction(event -> jogoObservavel.carrega());

    }

    private void registaObservers() {
        jogoObservavel.addPropertyChangeListener(ESCOLHE_MODO, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                buttonInicia.setDisable(true);
                buttonCarrega.setDisable(true);
                buttonHistorico.setDisable(true);
                buttonSair.setDisable(true);

            }
        });
    }
}
