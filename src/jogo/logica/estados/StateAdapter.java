package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

import java.io.IOException;

public class StateAdapter implements IStates {
    DadosJogo dados;

    public StateAdapter(DadosJogo dados) {
        this.dados = dados;
    }

    @Override
    public IStates inicia() {
        return this;
    }

    @Override
    public IStates termina() {
        return this;
    }

    @Override
    public IStates jogar() {
        return this;
    }

    @Override
    public IStates fazJogada(int coluna) throws IOException {
        return this;
    }

    @Override
    public IStates fazJogadaEspecial(int coluna) throws IOException {
        return this;
    }

    @Override
    public Situation getSituacaoAtual() {
        return null;
    }

    @Override
    public IStates selecionaJogo(int nJogadores) {
        return this;
    }

    @Override
    public IStates fazUndo() {
        return this;
    }

    @Override
    public IStates fazUndo(int vezes) {
        return this;
    }

    @Override
    public IStates selecionaMini() {
        return this;
    }

    @Override
    public IStates ganhaMiniJogo() {
        return this;
    }

    @Override
    public IStates perdeMiniJogo() {
        return this;
    }

    @Override
    public IStates interrompeJogo() throws IOException {
        return this;
    }
    @Override
    public IStates carregaJogo() throws IOException, ClassNotFoundException {
        return this;
    }
}

