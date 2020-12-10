package Server;

import Trees.*;

public class PlayerManager {
    private int puntos;
    private Tree arbol_actual;

    public PlayerManager(){
        this.puntos = 0;
        this.arbol_actual = null;
    }

    public int getPuntos(){
        return this.puntos;
    }

    public void addPuntos(int addedPoints){
        this.puntos += addedPoints;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String addToTree(int value){
        return "";
    }
}
