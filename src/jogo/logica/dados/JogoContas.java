package jogo.logica.dados;

import java.io.Serializable;
import java.util.Random;

public class JogoContas implements Serializable {
    private int number1;
    private int number2;
    private int acertos;

    private Operadores operador;

    private long inicio;
    private long fim;

    public JogoContas() {
        acertos = 0;
    }

    public void geraConta() {
        number1 = new Random().nextInt(10);
        number2 = new Random().nextInt(10);

        operador = Operadores.values()[new Random().nextInt(Operadores.values().length)];
    }

    public boolean verifica(int resultado) {
        switch (operador) {
            case SOMA:
                if ((number1 + number2) == resultado) {
                    acertos++;
                    return true;
                }

                break;
            case SUBTRAI:
                if ((number1 - number2) == resultado) {
                    acertos++;
                    return true;
                }

                break;
            case DIVIDE:
                if ((number1 / number2) == resultado) {
                    acertos++;
                    return true;
                }

                break;
            case MULTIPLICA:
                if ((number1 * number2) == resultado) {
                    acertos++;
                    return true;
                }

                break;
        }

        return false;
    }

    public void inicia() {
        this.inicio = System.currentTimeMillis();
    }

    public void termina() {
        this.fim = System.currentTimeMillis();
    }

    public boolean isVitoria() {
        return (fim - inicio) < 30000 && acertos == 5;
    }

    @Override
    public String toString() {
        String s = number1 + " ";

        switch (operador) {
            case SOMA:
                s += "+ ";
                break;

            case SUBTRAI:
                s += "- ";
                break;

            case DIVIDE:
                s += "/ ";
                break;

            case MULTIPLICA:
                s += "* ";
                break;
        }

        s += number2 + " = ";

        return s;
    }
}
