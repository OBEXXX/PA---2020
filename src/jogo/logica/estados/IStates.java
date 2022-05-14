package jogo.logica.estados;

import java.io.IOException;

public interface IStates {
    IStates inicia();
    IStates termina();
    IStates jogar();
    IStates fazJogada(int coluna) throws IOException;
    IStates selecionaJogo(int nJogadores);
    IStates fazUndo();
    IStates fazUndo(int vezes);
    IStates selecionaMini();
    IStates ganhaMiniJogo();
    IStates perdeMiniJogo();
    IStates interrompeJogo() throws IOException;
    IStates carregaJogo() throws IOException, ClassNotFoundException;
    IStates fazJogadaEspecial(int coluna) throws IOException;

    Situation getSituacaoAtual();
}
