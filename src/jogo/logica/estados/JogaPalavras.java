package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class JogaPalavras extends StateAdapter {
    public JogaPalavras(DadosJogo dados) {
        super(dados);
    }

    @Override
    public IStates ganhaMiniJogo() {
        return new EsperaJogada(dados);
    }

    @Override
    public IStates perdeMiniJogo() {
        dados.passaTurno();

        if (dados.verificaHumano())
            return new EsperaJogada(dados);
        else
            return new EsperaJogadaAleatoria(dados);
    }

    @Override
    public Situation getSituacaoAtual() {
        return Situation.JOGA_PALAVRAS;
    }
}
