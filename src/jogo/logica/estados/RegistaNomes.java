package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class RegistaNomes extends StateAdapter {
    public RegistaNomes(DadosJogo dados) {
        super(dados);
    }

    @Override
    public IStates inicia() {
        if (dados.verificaHumano())
            return new EsperaJogada(dados);
        return new EsperaJogadaAleatoria(dados);
    }

    @Override
    public Situation getSituacaoAtual() {
        return Situation.REGISTA_NOMES;
    }
}
