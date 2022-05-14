package jogo.logica.dados;

import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable {
    private String nome;
    private final boolean isHumano;
    private boolean hasPecaEspecial;
    private char id;
    private int nJogadas;
    private int creditos;
    private int minijogo;

    public Player(String nome, boolean isHumano, char id) {
        this.nome = nome;
        this.isHumano = isHumano;
        this.id = id;
        this.creditos = 5;

        this.minijogo = new Random().nextInt(2);
    }

    public boolean isHumano() {
        return isHumano;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public int getnJogadas() {
        return nJogadas;
    }

    public void incrementaJogadas() {
        this.nJogadas++;
    }

    public void setnJogadas(int nJogadas) {
        this.nJogadas = nJogadas;
    }

    public boolean hasPecaEspecial() {
        return hasPecaEspecial;
    }

    public void setHasPecaEspecial(boolean hasPecaEspecial) {
        this.hasPecaEspecial = hasPecaEspecial;
    }

    public int getCreditos() {
        return creditos;
    }

    public void decrementaCreditos(int vezes) {
        creditos = creditos - vezes;
    }

    public boolean podeMiniJogo() {
        return nJogadas >= 4;
    }

    public boolean podeUndo() {
        return creditos > 0;
    }

    public void passaMiniJogo() {
        if (minijogo == 0) {
            minijogo = 1;
        } else {
            minijogo = 0;
        }
    }

    public int getMiniJogo() {
        return minijogo;
    }
}
