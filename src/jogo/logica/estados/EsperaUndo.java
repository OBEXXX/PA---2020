package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class EsperaUndo extends StateAdapter {
    public EsperaUndo(DadosJogo dados) {
        super(dados);
    }

    @Override
    public IStates fazUndo(int vezes) {
        if (vezes <= dados.getHistorico() && vezes <= dados.getCreditos()) {
            dados.undo(vezes);
        }

        return new EsperaJogada(dados);
    }

    @Override
    public Situation getSituacaoAtual() {
        return Situation.ESPERA_UNDO;
    }
}
