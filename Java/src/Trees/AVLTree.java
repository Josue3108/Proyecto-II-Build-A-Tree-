package Trees;

import javax.swing.tree.TreeNode;
import java.util.LinkedList;

public class AVLTree<T> extends Tree {
    private int limit = 0;

    private int elements = 0;





    private NodeTree<T> root = null;


    public NodeTree<T> getRoot() {
        return root;
    }

    public T getRootData(){
        return root.getData();
    }

    public void setRoot(NodeTree<T> root) {
        this.root = root;
        this.elements++;
    }

    public void setRoot(T data){
        this.root  = new NodeTree<>(data);
        this.elements++;
    }

    public AVLTree(){}

    public AVLTree( T data) {
        this.root = new NodeTree<>(data);
        elements++;
    }

    public AVLTree(NodeTree<T> root) {
        this.root = root;
        elements++;
    }

    public AVLTree( T data,int limit) {
        this.root = new NodeTree<>(data);
        this.limit = limit;
        this.elements++;
    }

    public AVLTree(NodeTree<T> root,int elements) {
        this.root = root;
        this.limit = limit;
        this.elements++;
    }

    public  AVLTree(int limit){
        this.limit = limit;
    }

    public String getType(){
        return "AVL";
    }

    public void setLimit(){
        this.limit = (int) Math.pow(2,this.getRandomNumber())-1;
    }
    // A utility function to get the height of the tree
    private int height(NodeTree<T> N) {
        if (N == null)
            return 0;

        return N.getHeigh();
    }

    // A utility function to get maximum of two integers
    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private NodeTree<T> rightRotate(NodeTree<T> y) {
        NodeTree<T> x = y.getLeft();
        NodeTree<T> T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // Update heights
        y.setHeigh(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeigh( max(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private NodeTree<T> leftRotate(NodeTree<T> x) {
        NodeTree<T> y = x.getRight();
        NodeTree<T> T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        //  Update heights
        x.setHeigh(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeigh(max(height(y.getLeft()), height(y.getRight())) + 1);

        // Return new root
        return y;
    }


    // Get Balance factor of NodeTree<T> N
    private int getBalance(NodeTree<T> N) {
        if (N == null)
            return 0;

        return height(N.getLeft()) - height(N.getRight());
    }

    public void insert(T data) {


                this.root = insert(this.root, data);
                this.elements++;
                System.out.println("FINE");

    }

    private NodeTree<T> insert(NodeTree<T> Node, T data) {

        /* 1.  Perform the normal BST insertion */
        if (Node == null) {

            return (new NodeTree<T>(data));
        }
        if ( Integer.valueOf(String.valueOf(data))<Integer.valueOf(String.valueOf(Node.getData()))  ) {
            Node.setLeft(insert(Node.getLeft(), data));
        }
        else if ( Integer.valueOf(String.valueOf(data))>Integer.valueOf(String.valueOf(Node.getData())) ) {
            Node.setRight(insert(Node.getRight(), data));
        }
        else {// Duplicate datas not allowed
            return Node;
        }
        /* 2. Update height of this ancestor NodeTree<T> */
        Node.setHeigh( 1 + max(height(Node.getLeft()),height(Node.getRight())));

        /* 3. Get the balance factor of this ancestor
              NodeTree<T> to check whether this NodeTree<T> became
              unbalanced */
        int balance = getBalance(Node);

        // If this NodeTree<T> becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && Integer.valueOf(String.valueOf(data))<Integer.valueOf(String.valueOf(Node.getLeft().getData()))  ) {
            return rightRotate(Node);
        }
        // Right Right Case
        if (balance < -1 && Integer.valueOf(String.valueOf(data))>Integer.valueOf(String.valueOf(Node.getRight().getData()))) {
            return leftRotate(Node);
        }
        // Left Right Case
        if (balance > 1 && Integer.valueOf(String.valueOf(data))>Integer.valueOf(String.valueOf(Node.getLeft().getData()))) {
            Node.setLeft(leftRotate(Node.getLeft()));
            return rightRotate(Node);
        }

        // Right Left Case
        if (balance < -1 && Integer.valueOf(String.valueOf(data))<Integer.valueOf(String.valueOf(Node.getRight().getData()))) {
            Node.setRight(rightRotate(Node.getRight()));
            return leftRotate(Node);
        }

        /* return the (unchanged) NodeTree<T> pointer */
        return Node;

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

    private int getRandomNumber() {
        return (int)(Math.random() * ((2 - 1) + 1)) + 2;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setElements(int elements) {
        this.elements = elements;
    }

    public boolean isFull(){
        if(this.elements >= this.limit){
            return true;

        }else{
            return false;
        }
    }

    public int getLimit() {
        return this.limit;
    }
    public int getElements(){
        return  this.elements;
    }
}
