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
    //"AVL","BST","BT", "SPL"
    public void setArbol_actual(String tipo){
        switch (tipo){
            case "AVL":
                this.arbol_actual = new AVLTree();
                break;
            case "BST":
                this.arbol_actual = new BSTree();
                break;
            case "BT":
                this.arbol_actual = new BTree();
                break;
            case "SPL":
                this.arbol_actual = new SplayTree();
                break;

        }
    }

    public Tree getArbol_actual() {
        return this.arbol_actual;
    }
}
