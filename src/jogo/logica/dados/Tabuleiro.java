package jogo.logica.dados;

import java.io.IOException;
import java.io.Serializable;

public class Tabuleiro implements Serializable {
    private char[][] tabuleiro;
    private int turno;
    private int nLinhas;
    private int nColunas;

    public Tabuleiro() {
        nLinhas = 6;
        nColunas = 7;
        tabuleiro = new char[nLinhas][nColunas];
    }

    public IMemento getMemento() throws IOException {
        Memento m = new Memento();

        m.setSnapshot(this);

        return m;
    }

    public void setMemento(IMemento m) throws IOException, ClassNotFoundException {

        Memento aux = (Memento) m;

        Object obj = aux.getSnapshot();

        Tabuleiro tmp = (Tabuleiro) obj;

        this.tabuleiro = tmp.tabuleiro;

        this.turno = tmp.getTurno();
    }

    public void setPosicao(int row, int col, char c) {
        tabuleiro[row][col] = c;
    }

    public char getPosicao(int row, int col) {
        return tabuleiro[row][col];
    }

    public int getTurno() {
        return turno;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getLinhas() {
        return nLinhas;
    }

    public int getColunas() {
        return nColunas;
    }

    public char[] getLinha(int linha) {
        return tabuleiro[linha];
    }
}
