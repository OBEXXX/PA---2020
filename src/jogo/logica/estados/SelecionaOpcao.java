package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

import java.io.IOException;
import java.io.InvalidClassException;

public class SelecionaOpcao extends StateAdapter {

    public SelecionaOpcao(DadosJogo dados) {
        super(dados);
    }

    @Override
    public IStates jogar() {
        return new EscolheModo(dados);
    }

    @Override
    public IStates carregaJogo() throws IOException, ClassNotFoundException, InvalidClassException {

        dados = dados.carrega();
        return new EsperaJogada(dados);
    }

    @Override
    public IStates termina() {
        return new TerminaPrograma(dados);
    }

    @Override
    public Situation getSituacaoAtual() {
        return Situation.SELECIONA_OPCAO;
    }
}



