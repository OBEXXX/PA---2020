package jogo.logica.dados;

import java.io.IOException;

public interface IMemento {
    void setSnapshot(Object m) throws IOException;

    Object getSnapshot() throws IOException, ClassNotFoundException;
}
