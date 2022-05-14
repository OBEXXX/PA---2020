package jogo.logica.estados;
import jogo.logica.dados.DadosJogo;

public class TerminaPrograma extends  StateAdapter{
    public TerminaPrograma(DadosJogo dados){
        super(dados);
    }

    @Override
    public Situation getSituacaoAtual() {
        return Situation.TERMINA_PROGRAMA;
    }
}