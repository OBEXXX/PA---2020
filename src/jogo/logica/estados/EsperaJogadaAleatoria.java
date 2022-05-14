package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

import java.io.IOException;

public class EsperaJogadaAleatoria extends StateAdapter {
    public EsperaJogadaAleatoria(DadosJogo dados) {
        super(dados);
    }

    @Override
    public IStates fazJogada(int coluna) {
        int linha = dados.verificaJogada(coluna);

        try {
            dados.guardaJogada();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(dados.checkGameOver(linha, coluna))
        {
            return new SelecionaOpcao(dados);
        }
        dados.passaTurno();

        if (dados.verificaHumano())
            return new EsperaJogada(dados);
        else
            return new EsperaJogadaAleatoria(dados);

    }

    @Override
    public Situation getSituacaoAtual() {
        return Situation.ESPERA_JOGADA_ALEATORIA;
    }
}
