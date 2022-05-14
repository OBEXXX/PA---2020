package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class JogaContas extends StateAdapter {
    public JogaContas(DadosJogo dados) {
        super(dados);
    }

    @Override
    public IStates ganhaMiniJogo() {
        dados.guardaVitoria("JogoContas");
        return new EsperaJogada(dados);
    }

    @Override
    public IStates perdeMiniJogo() {
        dados.guardaDerrota("JogoContas");
        dados.passaTurno();



        if (dados.verificaHumano())
            return new EsperaJogada(dados);
        else
            return new EsperaJogadaAleatoria(dados);
    }

    @Override
    public Situation getSituacaoAtual() {
        return Situation.JOGA_CONTAS;
    }
}