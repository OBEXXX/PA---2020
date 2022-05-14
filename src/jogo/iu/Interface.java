package jogo.iu;

import jogo.logica.dados.Jogo;
import jogo.logica.dados.JogoContas;
import jogo.logica.dados.JogoPalavras;
import jogo.logica.estados.*;

import java.io.IOException;
import java.util.Scanner;

public class Interface {
    private Jogo jogo;

    public void start() throws IOException, ClassNotFoundException {
        jogo = new Jogo();

        System.out.println("Bem-vindo ao jogo quatro em linha");

        do {
            if (jogo.getSituacaoAtual() == Situation.SELECIONA_OPCAO) {
                System.out.println(jogo);
                mostraMenuInicial();
            }

            if (jogo.getSituacaoAtual() == Situation.ESCOLHE_MODO) {
                mostraMenuModo();
            }

            if (jogo.getSituacaoAtual() == Situation.REGISTA_NOMES) {
                mostraMenuNomes();
            }

            if (jogo.getSituacaoAtual() == Situation.ESPERA_JOGADA) {
                System.out.println(jogo);
                mostraMenuJogada();
            }

            if (jogo.getSituacaoAtual() == Situation.ESPERA_JOGADA_ALEATORIA) {
                System.out.println(jogo);
                mostraJogadaAleatoria();
            }

            if (jogo.getSituacaoAtual() == Situation.ESPERA_UNDO) {
                mostraMenuUndo();
            }

            if (jogo.getSituacaoAtual() == Situation.JOGA_CONTAS) {
                mostraJogoContas();
            }

            if (jogo.getSituacaoAtual() == Situation.JOGA_PALAVRAS) {
                mostraJogoPalavras();
            }
        } while (jogo.getSituacaoAtual() != Situation.TERMINA_PROGRAMA);
    }

    private void mostraMenuUndo() {
        int vezes;

        System.out.println("Existem " + jogo.getHistorico() + " estados anteriores");
        System.out.print("Quantos quer voltar (max:" + jogo.getCreditos() + ") :");

        vezes = new Scanner(System.in).nextInt();

        if (vezes > jogo.getCreditos())
            System.out.println("Excedeu os créditos");

        jogo.fazUndo(vezes);
    }

    private void mostraJogoPalavras() throws IOException {
        String palavra;
        JogoPalavras miniJogo = new JogoPalavras();

        miniJogo.inicia();

        while (!miniJogo.isOver()) {
            System.out.print(miniJogo.getPalavra() + ": ");
            palavra = new Scanner(System.in).nextLine();

            System.out.print("A resposta está ");
            System.out.println((miniJogo.verificaPalavra(palavra) ? "certa :)" : "errada :("));
        }

        miniJogo.termina();

        jogo.verificaMiniJogo(miniJogo.isVitoria());
    }

    private void mostraJogoContas() {
        int resultado;
        JogoContas miniJogo = new JogoContas();

        miniJogo.inicia();

        for (int i = 0; i < 15; i++) {
            miniJogo.geraConta();

            System.out.print(miniJogo);
            resultado = new Scanner(System.in).nextInt();

            System.out.print("A resposta está ");

            if (miniJogo.verifica(resultado)) {
                System.out.println("certa!");
            } else {
                System.out.println("errada!");
                break;
            }
        }

        miniJogo.termina();

        if (jogo.verificaMiniJogo(miniJogo.isVitoria())) {
            System.out.println("Ganhou a peça especial!");
        } else {
            System.out.println("Não ganhou a peça especial!");
        }
    }

    private void mostraJogadaAleatoria() throws IOException {
        jogo.fazJogadaAleatoria();

        System.out.println("Jogador aleatório jogou");
    }

    private void mostraMenuModo() {
        System.out.print("Quantos jogadores(0 a 2): ");
        jogo.selecionaJogo(new Scanner(System.in).nextInt());
    }

    private void mostraMenuNomes() {
        do {
            System.out.print("Nome do jogador 1: ");
            jogo.criaJogador(0, new Scanner(System.in).nextLine(), true, 'Y');
        } while (jogo.getPlayer(0).length() == 0);

        if (!jogo.isSinglePlayer()) {
            do {
                System.out.print("Nome do jogador 2: ");
                jogo.criaJogador(1, new Scanner(System.in).nextLine(), true, 'R');
            } while (jogo.nomesIguais() || jogo.getPlayer(1).length() == 0);
        }

        jogo.start();
    }

    public void mostraMenuInicial() throws IOException, ClassNotFoundException {
        int op;

        System.out.println("1 - Iniciar Jogo");
        System.out.println("2 - Carregar Jogo");
        System.out.println("3 - Verifica Historico");
        System.out.println("4 - Terminar");

        System.out.print("Opção: ");
        op = new Scanner(System.in).nextInt();

        switch (op) {
            case 1:
                jogo.inicia();
                break;
            case 2:
                jogo.carregaJogo();
                break;

            case 3:
                jogo.verificaHistorico();
                break;

            case 4:
                jogo.termina();
                break;
        }
    }

    public void mostraMenuJogada() throws IOException {
        int op;

        System.out.println("[ E a Vez do/a " + jogo.getPlayer() + "]");
        System.out.println();
        System.out.println("Peça especial: " + jogo.podePecaEspecial());
        System.out.println("Pode jogar minijogo: " + jogo.podeMiniJogo());
        System.out.println();
        System.out.println("1 - Colocar peça");
        System.out.println("2 - Jogar minijogo");
        System.out.println("3 - Jogar peça especial");
        System.out.println("4 - Voltar atrás");
        System.out.println("5 - Interromper jogo");

        System.out.print("Opção: ");
        op = new Scanner(System.in).nextInt();

        switch (op) {
            case 1:
                System.out.print("Coluna para jogar: ");
                jogo.fazJogada(new Scanner(System.in).nextInt());

                break;

            case 2:
                if (jogo.podeMiniJogo()) {
                    jogo.selecionaMini();
                } else {
                    System.out.println("Não tem minijogo");
                }

                break;

            case 3:
                if (jogo.podePecaEspecial()) {
                    System.out.print("Coluna para jogar: ");
                    jogo.fazJogadaEspecial(new Scanner(System.in).nextInt());
                } else {
                    System.out.println("Não tem peça especial");
                }
                    break;
            case 4:
                if (jogo.getHistorico() > 0 && jogo.podeUndo()) {
                    jogo.fazUndo();
                } else {
                    if (jogo.getHistorico() <= 0)
                        System.out.println("Não existe histórico");
                    if (!jogo.podeUndo())
                        System.out.println("Já usou todos os créditos");
                }
                break;
            case 5 :
                   jogo.interrompeJogo();

        }
    }
}

