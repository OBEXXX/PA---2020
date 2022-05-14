package jogo.logica.dados;

import jogo.logica.estados.IStates;
import jogo.logica.estados.SelecionaOpcao;
import jogo.logica.estados.Situation;

import java.io.IOException;

public class Jogo {
    private DadosJogo dadosJogo;
    private IStates state;

    public Jogo() {
        dadosJogo = new DadosJogo();

        state = new SelecionaOpcao(dadosJogo);
    }


    public int getTurno() {
        return dadosJogo.getTurno();
    }

    public boolean isGameOver() {
        return dadosJogo.isGameOver();
    }

    public void setGameOver(boolean gameOver) {
        dadosJogo.setGameOver(gameOver);
    }

    public void criaJogador(int i, String nome, boolean isHumano, char id) {
        dadosJogo.criaJogador(i, nome, isHumano, id);
    }

    public int getModo() {
        return dadosJogo.getModo();
    }

    public void setModo(int modo) {

        dadosJogo.setModo(modo);
    }

    public String diagonalPar(int linha, int coluna) {

        return dadosJogo.diagonalPar(linha, coluna);
    }

    public String diagonalImpar(int linha, int coluna) {

        return dadosJogo.diagonalImpar(linha, coluna);
    }

    public String horizontal(int linha) {
        return dadosJogo.horizontal(linha);
    }

    public String vertical(int coluna) {

        return dadosJogo.vertical(coluna);
    }

    public boolean checkGameOver(int linha, int coluna) {
        return dadosJogo.checkGameOver(linha, coluna);
    }

    public int verificaJogada(int coluna) {

        return dadosJogo.verificaJogada(coluna);
    }

    public void guardaJogada() throws IOException {

        dadosJogo.guardaJogada();

    }

    public void guardaJogo() throws IOException {

        dadosJogo.guardaJogo();
    }

    public void fazJogada(int coluna) throws IOException {
        setState(getState().fazJogada(coluna));
    }

    public void fazJogadaAleatoria() throws IOException {
        int auxJogada = dadosJogo.fazJogadaAleatoria();
        setState(getState().fazJogada(auxJogada));
    }

    public void selecionaMini() {
        setState(getState().selecionaMini());
    }

    public void interrompeJogo() throws IOException {
        setState(getState().interrompeJogo());
    }

    public void selecionaJogo(int jogadores) {
        setState(getState().selecionaJogo(jogadores));
    }

    private IStates getState() {
        return state;
    }

    private void setState(IStates state) {
        this.state = state;
    }

    public Situation getSituacaoAtual() {
        return state.getSituacaoAtual();
    }

    public boolean verificaHumano() {
        return dadosJogo.verificaHumano();
    }

    public boolean verificaMiniJogo(boolean isVitoria) {
        if (isVitoria) {
            dadosJogo.getPlayer().setHasPecaEspecial(true);

            setState(getState().ganhaMiniJogo());
        } else {
            setState(getState().perdeMiniJogo());
        }

        return isVitoria;
    }

    @Override
    public String toString() {
        return dadosJogo.toString();
    }

    public boolean isSinglePlayer() {
        return dadosJogo.isSinglePlayer();
    }

    public boolean nomesIguais() {
        return dadosJogo.nomesIguais();
    }

    public void start() {
        setState(getState().inicia());
    }

    public void inicia() {
        dadosJogo.inicializaDados();
        setState(getState().jogar());
    }

    public void fazUndo() {
        setState(getState().fazUndo());
    }

    public void fazUndo(int vezes) {
        setState(getState().fazUndo(vezes));
    }

    public String getPlayer() {
        return dadosJogo.getPlayerName();
    }

    public String getPlayer(int i) {
        return dadosJogo.getPlayerName(i);
    }

    public boolean podeMiniJogo() {
        return dadosJogo.podeMiniJogo();
    }

    public boolean podePecaEspecial() {
        return dadosJogo.podePecaEspecial();
    }

    public void fazJogadaEspecial(int coluna) throws IOException {
        setState(getState().fazJogadaEspecial(coluna));
    }

    public int getHistorico() {
        return dadosJogo.getHistorico();
    }

    public boolean podeUndo() {
        return dadosJogo.podeUndo();
    }

    public int getCreditos() {
        return dadosJogo.getCreditos();
    }

    public void carregaJogo() throws IOException, ClassNotFoundException {
        setState(getState().carregaJogo());
    }

    public void termina() {
        setState(getState().termina());
        System.exit(0);
    }

    public void verificaHistorico() throws IOException {
        dadosJogo.verificaHistorico();
    }
}