package jogo.logica.dados;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class DadosJogo implements Serializable {
    private Caretaker caretaker;
    private Tabuleiro tabuleiro;

    private Player[] players;
    private List<String> jogoList;
    private boolean gameOver;
    private int modo;
    private int minijogo;
    private int numero1;
    private int numero2;
    private int numero_op;


    public DadosJogo() {
        tabuleiro = new Tabuleiro();
        players = new Player[2];
        minijogo = 1;

        inicializaDados();
    }

    //Inicializa Tabuleiro
    public void inicializaDados() {
        for (int row = 0; row < tabuleiro.getLinhas(); row++) {
            for (int col = 0; col < tabuleiro.getColunas(); col++) {
                tabuleiro.setPosicao(row, col, ' ');
            }
        }

        caretaker = new Caretaker(tabuleiro);

        tabuleiro.setTurno(new Random().nextInt(2));

        gameOver = false;

        jogoList = new ArrayList<>();
    }


    public int getTurno() {
        return tabuleiro.getTurno();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void criaJogador(int i, String nome, boolean isHumano, char id) {
        players[i] = new Player(nome, isHumano, id);
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public String diagonalPar(int linha, int coluna) {
        String diagonal = "";

        // verifica diagonal \
        for (int h = 0; h < tabuleiro.getLinhas(); h++) {
            int w = coluna - linha + h;

            if (0 <= w && w < tabuleiro.getColunas()) {
                diagonal += tabuleiro.getPosicao(h, w);
            }
        }

        return diagonal;
    }

    public String diagonalImpar(int linha, int coluna) {
        String diagonal = "";

        // verifica diagonal \
        for (int h = 0; h < tabuleiro.getLinhas(); h++) {
            int w = coluna + linha - h;

            if (0 <= w && w < tabuleiro.getColunas()) {
                diagonal += tabuleiro.getPosicao(h, w);
            }
        }

        return diagonal;
    }

    public String horizontal(int linha) {
        return new String(tabuleiro.getLinha(linha));
    }

    public String vertical(int coluna) {
        String col = "";
        for (int h = 0; h < tabuleiro.getLinhas(); h++) {
            col += (tabuleiro.getPosicao(h, coluna));
        }

        return col;
    }

    public boolean checkGameOver(int linha, int coluna) {
        char id = players[tabuleiro.getTurno()].getId();
        String vitoria = String.format("%c%c%c%c", id, id, id, id);

        return diagonalImpar(linha, coluna).contains(vitoria) ||
                diagonalPar(linha, coluna).contains(vitoria) ||
                vertical(coluna).contains(vitoria) ||
                horizontal(linha).contains(vitoria);

    }

    public int verificaJogada(int coluna) {
        if (coluna < 0 || coluna > tabuleiro.getColunas() - 1) {
            return -1;
        }

        if (tabuleiro.getPosicao(0, coluna) != ' ') {
            return -1;
        }

        for (int linha = tabuleiro.getLinhas() - 1; linha >= 0; linha--) {
            if (tabuleiro.getPosicao(linha, coluna) == ' ') {
                caretaker.gravaMemento();

                tabuleiro.setPosicao(linha, coluna, players[tabuleiro.getTurno()].getId());
                return linha;
            }
        }

        return 1;
    }

    public void guardaJogada() throws IOException {

        jogoList.add(toString());

    }

    public void guardaJogo() throws IOException {
        boolean resultadoMini;
        try {
            FileWriter arq = new FileWriter("save.txt", true);
            PrintWriter gravarArq = new PrintWriter(arq);
            System.out.println("*****\n");
            for (String s : jogoList) {
                gravarArq.println(s);
            }


            gravarArq.close();
        } catch (IOException e) {
            System.err.println("Cannot find the file");
        }
    }


    public int fazJogadaAleatoria() throws IOException {
        List<Integer> jogadas = new ArrayList<>();

        for (int i = 0; i < tabuleiro.getColunas(); i++) {
            if (tabuleiro.getPosicao(0, i) == ' ')
                jogadas.add(i);
        }

        int auxRandom = new Random().nextInt(jogadas.size());
            return jogadas.get(auxRandom);
    }


    public void passaTurno() {
        players[tabuleiro.getTurno()].incrementaJogadas();

        if (tabuleiro.getTurno() == 0) {
            tabuleiro.setTurno(1);
        } else {
            tabuleiro.setTurno(0);
        }
    }

    public void passaMiniJogo() {
        players[tabuleiro.getTurno()].setnJogadas(0);

        players[tabuleiro.getTurno()].passaMiniJogo();
    }

    public int getMinijogo() {
        return players[tabuleiro.getTurno()].getMiniJogo();
    }


    public boolean verificaHumano() {
        return players[tabuleiro.getTurno()].isHumano();
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                s += "|" + tabuleiro.getPosicao(i, j);
            }
            s += "|\n";
        }

        return s;
    }

    public boolean isSinglePlayer() {
        return modo == 1;
    }

    public boolean nomesIguais() {
        return players[0].getNome().equals(players[1].getNome());
    }

    public String getPlayerName() {
        return players[tabuleiro.getTurno()].getNome();
    }

    public void iniciaJogadores() {
        for (int i = 0; i < 2; i++) {
            players[i] = new Player("", false, ' ');
        }
    }

    public String getPlayerName(int i) {
        return players[i].getNome();
    }

    public boolean podeMiniJogo() {
        return players[tabuleiro.getTurno()].podeMiniJogo();
    }

    public boolean podePecaEspecial() {
        return players[tabuleiro.getTurno()].hasPecaEspecial();
    }

    public int verificaJogadaEspecial(int coluna) {
        if (coluna < 0 || coluna > tabuleiro.getColunas() - 1) {
            return -1;
        }

        caretaker.gravaMemento();

        for (int linha = 0; linha < tabuleiro.getLinhas(); linha++) {
            tabuleiro.setPosicao(linha, coluna, ' ');
        }

        players[tabuleiro.getTurno()].setHasPecaEspecial(false);

        return 1;
    }

    public int getHistorico() {
        return caretaker.getStackSize();
    }

    public boolean podeUndo() {
        return players[tabuleiro.getTurno()].podeUndo();
    }

    public int getCreditos() {
        return players[tabuleiro.getTurno()].getCreditos();
    }

    public void undo(int vezes) {
        players[tabuleiro.getTurno()].decrementaCreditos(vezes);

        for (int i = 0; i < vezes; i++) {
            caretaker.undo();
        }

        for (Player player:players) {
            player.setnJogadas(0);
        }
    }


    public void guardaDerrota(String minijogo) {
        jogoList.add("jogador " + getPlayerName() + "perdeu " + minijogo);
    }

    public void guardaVitoria(String jogoContas) {
        jogoList.add("jogador " + getPlayerName() + "ganhou " + minijogo);
    }

    public void gravaJogo() throws IOException {

        FileOutputStream f = new FileOutputStream(new File("jogo.bin"));
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);

        o.close();
        f.close();
    }

    public DadosJogo carrega() throws IOException, ClassNotFoundException, InvalidClassException {
        FileInputStream f = new FileInputStream(new File("jogo.bin"));
        ObjectInputStream o = new ObjectInputStream(f);
        DadosJogo aux;
        aux = (DadosJogo) o.readObject();
        o.close();
        f.close();
        return aux;


    }

    public Player getPlayer() {
        return players[tabuleiro.getTurno()];
    }

    public int getColunas() {
        return tabuleiro.getColunas();
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public void verificaHistorico() throws IOException {
        String aux;
        FileReader f = new FileReader(new File("save.txt"));
        Scanner scanner = new Scanner(f);
        while (scanner.hasNextLine()) {
            aux = scanner.nextLine();
            System.out.println(aux);


        }
        f.close();
    }


    public boolean verificaEmpate() throws IOException {
        if(fazJogadaAleatoria() >= 0)
        {
            return false;
        }
        return true;
    }
}
