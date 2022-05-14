package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

public class EscolheModo extends StateAdapter{
    public EscolheModo(DadosJogo dados) {
        super(dados);
    }

    @Override
    public IStates selecionaJogo(int nJogadores) {
        dados.setModo(nJogadores);

        dados.iniciaJogadores();

        switch (nJogadores) {
            case 2:
                return new RegistaNomes(dados);
            case 1:
                dados.criaJogador(1, "CPU", false,'R');

                return new RegistaNomes(dados);
            case 0:
                dados.criaJogador(0, "CPU1", false,'Y');
                dados.criaJogador(1, "CPU2", false,'R');

                return new EsperaJogadaAleatoria(dados);

            default:
                return this;
        }
    }

    @Override
    public Situation getSituacaoAtual() {
        return Situation.ESCOLHE_MODO;
    }
}
