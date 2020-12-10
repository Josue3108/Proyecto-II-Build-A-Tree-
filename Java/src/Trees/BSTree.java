package Trees;

import java.util.LinkedList;

public class BSTree<T> extends Tree{

    private int limit = 0;
    private int elements = 0;
    private NodeTree<T> root = null;
    private int levels = 0;


    public NodeTree<T> getRoot() {
        return root;
    }

    public T getRootData(){
        return root.getData();
    }

    public void setRoot(NodeTree<T> root) {
        this.root = root;
        elements++;
        levels++;
    }

    public void setRoot(T data){
        this.root  = new NodeTree<>(data);
        elements++;
        levels++;
    }

    public BSTree(int limit, NodeTree<T> root) {
        this.limit = limit;
        this.root = root;
        levels++;
    }

    public BSTree() {
    }

    public BSTree(int limit) {
        this.limit = limit;
    }

    private int getElements() {
        return elements;
    }

    public int getLevels() {
        return levels;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getType(){
        return "BST";
    }


    private void insertaux(NodeTree<T> node){
        if (this.root == null){
            this.root = node;
            this.levels++;
        }else{
            NodeTree<T> subroot = this.root;
            NodeTree<T> actual = this.root;
            int H = 1;
            while(actual != null) {
                if (   Integer.valueOf(String.valueOf(node.getData()))<Integer.valueOf(String.valueOf(actual.getData()))   ) {
                    subroot = actual;
                    actual = actual.getLeft();
                } else if (Integer.valueOf(String.valueOf(node.getData()))>Integer.valueOf(String.valueOf(actual.getData())) ) {
                    subroot = actual;
                    actual = actual.getRight();
                } else {
                    System.out.print("El dato ya se encuantra en el árbol");
                    return;
                }
                H++;
            }
            if (   Integer.valueOf(String.valueOf(node.getData()))<Integer.valueOf(String.valueOf(subroot.getData()))   ) {
                subroot.setLeft(node);
            } else if (Integer.valueOf(String.valueOf(node.getData()))>Integer.valueOf(String.valueOf(subroot.getData())) ) {
                subroot.setRight(node);
            }

            if( H>this.levels){
                this.levels = H;
            }

        }

    }

    public void insert(T data){

        insertaux(new NodeTree<T>(data));
        this.elements++;

    }

    public NodeTree<T> getNode(T data){
        return getNodeaux(this.root,data);
    }

    private NodeTree<T> getNodeaux (NodeTree<T> node, T data){
        if(node.getData().equals(data)){
            return node;
        }
        else if( Integer.valueOf(String.valueOf(data))<Integer.valueOf(String.valueOf(node.getData())) ){
            getNodeaux ( node.getLeft(), data);
        }

        else if( Integer.valueOf(String.valueOf(data))>Integer.valueOf(String.valueOf(node.getData())) ){
            getNodeaux( node.getRight(),data);
        }
        else{
            System.out.print("El nodo no se encuentra");

        }return null;
    }
    public void preOrder(){
        preOrder(this.root);
    }
    private void preOrder(NodeTree<T> node) {

        if (node != null) {
            System.out.print(node.getData() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }
    public String  getTreeCode(){
        int range = 1;
        int c = 0;
        LinkedList<NodeTree> lista = new LinkedList<>();
        lista.add(this.root);
        String codigo = String.valueOf(this.root.getData()) ;
        codigo += ",";
        codigo += "/";
        int top = 1;

        while(top<this.elements){

            for(int i = c; i< range+c ;i++ ){

                if(lista.get(i)!=null) {

                    if (lista.get(i).getLeft() != null) {
                        lista.add(lista.get(i).getLeft());
                        codigo += String.valueOf(lista.get(i).getLeft().getData());
                        codigo += ",";
                        top += 1;
                    } else {
                        lista.add(null);
                        codigo += "null";
                        codigo += ",";
                    }


                    if (lista.get(i).getRight() != null) {
                        lista.add(lista.get(i).getRight());
                        codigo += String.valueOf(lista.get(i).getRight().getData());
                        codigo += ",";
                        top += 1;
                    } else {
                        lista.add(null);
                        codigo += "null";
                        codigo += ",";
                    }
                }else{
                    lista.add(null);
                    codigo += "null";
                    codigo += ",";

                    lista.add(null);
                    codigo += "null";
                    codigo += ",";

                }
            }

            c=range+c;
            range = range*2;
            codigo += "/";

        }


        return  codigo;
    }
    public int getRandomNumber() {
        return (int) (Math.random() * ((4 - 1) + 1)) + 2;
    }

    public boolean isFull(){
        if(this.levels>=this.limit){
            return true;
        }else{
            return false;
        }
    }

    public void setLimit(){
        this.limit = (int) this.getRandomNumber();
    }
}
