package jogo.logica.dados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JogoPalavras implements Serializable {
    private long maxTempo;
    private List<String> selectedWords;
    private long inicio;
    private long fim;

    public JogoPalavras() throws IOException {
        String line;
        List<String> words = new ArrayList<>();

        File file = new File("words.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while((line = bufferedReader.readLine()) != null) {
            words.add(line);
        }

        bufferedReader.close();
        fileReader.close();

        maxTempo = 0;
        selectedWords = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            selectedWords.add(words.remove(new Random().nextInt(words.size())));

            maxTempo += selectedWords.get(i).length();
        }

        maxTempo = maxTempo * 1000 / 2;
    }

    public boolean verificaPalavra(String palavra) {
        if (palavra.equals(selectedWords.get(0))) {
            selectedWords.remove(0);
            return true;
        }

        return false;
    }

    public void inicia() {
        this.inicio = System.currentTimeMillis();
    }

    public void termina() {
        this.fim = System.currentTimeMillis();
    }

    public boolean isOver() {
        return selectedWords.isEmpty();
    }

    public String getPalavra() {
        return selectedWords.get(0);
    }

    public boolean isVitoria() {
        return (fim - inicio) < maxTempo;
    }
}
