package jogo.logica.dados;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;

public class Caretaker implements Serializable {
    private Tabuleiro tabuleiro;
    private Deque<IMemento> stackHist = new ArrayDeque<>();

    public Caretaker(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void gravaMemento() {
        try{
            stackHist.push(tabuleiro.getMemento());
        } catch(IOException ex) {
            System.out.println("gravaMemento: " + ex);
            stackHist.clear();
        }
    }

    public boolean undo() {
        if (stackHist.isEmpty()) { return false; }
        try {
            tabuleiro.setMemento(stackHist.pop());
        } catch(IOException | ClassNotFoundException ex) {
            System.out.println("undo: " + ex);
            stackHist.clear();

            return false;
        }

        return true;
    }

    public int getStackSize() {
        return stackHist.size();
    }

    public String toString(){
        return "\nstackHist=" + stackHist.size();
    }

}
