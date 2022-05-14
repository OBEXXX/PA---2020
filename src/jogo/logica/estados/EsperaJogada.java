package jogo.logica.estados;

import jogo.logica.dados.DadosJogo;

import java.io.IOException;
import java.io.Serializable;

public class EsperaJogada extends StateAdapter implements Serializable {
    public EsperaJogada(DadosJogo dados) {
        super(dados);
    }

    @Override
    public IStates interrompeJogo() throws IOException {

            dados.gravaJogo();


        return new SelecionaOpcao(dados);
    }

    @Override
    public IStates fazJogada(int coluna) throws IOException {
        //Validar a jogada e devolver novo estado dependendo da jogada
        int linha = dados.verificaJogada(coluna);


        if (linha == -1) {
            return new EsperaJogada(dados);
        } else {
                dados.guardaJogada();
            }

            if (dados.checkGameOver(linha, coluna)) {
                try{
                    dados.guardaJogo();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return new SelecionaOpcao(dados);
            }else if(dados.verificaEmpate())
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
    public IStates fazJogadaEspecial(int coluna) throws IOException {
        //Validar a jogada e devolver novo estado dependendo da jogada
        int linha = dados.verificaJogadaEspecial(coluna);

        if (linha == -1) {
            return new EsperaJogada(dados);
        } else {
            try {
                dados.guardaJogada();
            } catch (IOException e) {
                e.printStackTrace();
            }

            dados.passaTurno();

            if (dados.verificaHumano())
                return new EsperaJogada(dados);
            else
                return new EsperaJogadaAleatoria(dados);
        }
    }

    @Override
    public IStates selecionaMini() {
        if (dados.podeMiniJogo()) {
            dados.passaMiniJogo();

            if (dados.getMinijogo() == 1) {
                return new JogaContas(dados);
            } else {
                return new JogaPalavras(dados);
            }
        } else {
            return this;
        }
    }

    @Override
    public IStates fazUndo() {
        if (dados.getHistorico() > 0 && dados.podeUndo()) {
            return new EsperaUndo(dados);
        } else {
            return this;
        }
    }

    @Override
    public Situation getSituacaoAtual() {
        return Situation.ESPERA_JOGADA;
    }
}


