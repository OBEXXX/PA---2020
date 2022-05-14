package jogo.logica.dados;

import jogo.iu.gui.Propriedades;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class JogoObservavel implements Propriedades {
    PropertyChangeSupport propertyChangeSupport;
    Jogo jogo;

    public JogoObservavel() {
        jogo = new Jogo();

        propertyChangeSupport = new PropertyChangeSupport(jogo);
    }

    public void inicia() {
        jogo.inicia();

        propertyChangeSupport.firePropertyChange(ESCOLHE_MODO, null, null);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(property, propertyChangeListener);
    }

    public void carrega() {
    }
}
