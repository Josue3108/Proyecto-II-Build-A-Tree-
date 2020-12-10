package Server;

import Trees.*;

/**
 * Player manager class. It is used to have a reference to each of the players in the client, stored temporarily on the server
 */
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

    /**
     * This method adds a node to the current tree of the player
     * @param value
     * @return
     */
    public String addToTree(int value){
        return "";
    }
    //"AVL","BST","BT", "SPL"

    /**
     * This method creates the current tree of the player based on the type of tree specifues in the argument
     * @param tipo
     */
    public void setArbol_actual(String tipo){
        switch (tipo){
            case "AVL":
                this.arbol_actual = new AVLTree();
                break;
            case "BST":
                this.arbol_actual = new BSTree();
                break;
            case "BT":
                this.arbol_actual = new BTree(4);
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
