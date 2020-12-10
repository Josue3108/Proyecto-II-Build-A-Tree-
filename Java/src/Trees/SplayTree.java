package Trees;

import java.util.LinkedList;

public class SplayTree<T> extends Tree {


    private int elements= 0;
    private int limit = 0;
    private NodeTree<T> root;

    public SplayTree(NodeTree node) {
        this.root = node;
        elements++;
    }

    public SplayTree() {
    }

    public SplayTree(T data) {
        this.root = new NodeTree<>(data);
        elements++;
    }


    private NodeTree<T> newNode(T key) {
        NodeTree<T> Node = new NodeTree<>(key);
        return (Node);
    }

    // A utility function to right
// rotate subtree rooted with y
// See the diagram given above.
    private NodeTree<T> rightRotate(NodeTree<T> x) {
        NodeTree<T> y = x.getLeft();
        x.setLeft(y.getRight());
        y.setRight(x);
        return y;
    }

    // A utility function to left
// rotate subtree rooted with x
// See the diagram given above.
    private NodeTree<T> leftRotate(NodeTree<T> x) {
        NodeTree<T> y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        return y;
    }



    public SplayTree(int limit) {
        this.limit = limit;
    }

    // This function brings the key at
// root if key is present in tree.
// If key is not present, then it
// brings the last accessed item at
// root. This function modifies the
// tree and returns the new root
    public NodeTree<T> splay(NodeTree<T> root, T key) {
        // Base cases: root is null or
        // key is present at root
        if (root == null || root.getData() == key)
            return root;

        // Key lies in left subtree
        if (Integer.valueOf(String.valueOf(root.getData())) > Integer.valueOf(String.valueOf(key))) {
            // Key is not in tree, we are done
            if (root.getLeft() == null) return root;

            // Zig-Zig (Left Left)
            if (Integer.valueOf(String.valueOf(root.getLeft().getData())) > Integer.valueOf(String.valueOf(key))) {
                // First recursively bring the
                // key as root of left-left
                root.getLeft().setLeft(splay(root.getLeft().getLeft(), key));

                // Do first rotation for root,
                // second rotation is done after else
                root = rightRotate(root);
            } else if (Integer.valueOf(String.valueOf(root.getLeft().getData())) < Integer.valueOf(String.valueOf(key))) // Zig-Zag (Left Right)
            {
                // First recursively bring
                // the key as root of left-right
                root.getLeft().setRight(splay(root.getLeft().getRight(), key));

                // Do first rotation for root.left
                if (root.getLeft().getRight() != null)
                    root.setLeft(leftRotate(root.getLeft()));
            }

            // Do second rotation for root
            return (root.getLeft() == null) ?
                    root : rightRotate(root);
        } else // Key lies in right subtree
        {
            // Key is not in tree, we are done
            if (root.getRight() == null) return root;

            // Zag-Zig (Right Left)
            if (Integer.valueOf(String.valueOf(root.getRight().getData())) > Integer.valueOf(String.valueOf(key))) {
                // Bring the key as root of right-left
                root.getRight().setLeft(splay(root.getRight().getLeft(), key));

                // Do first rotation for root.right
                if (root.getRight().getLeft() != null)
                    root.setLeft(rightRotate(root.getRight()));
            } else if (Integer.valueOf(String.valueOf(root.getRight().getData())) < Integer.valueOf(String.valueOf(key)))// Zag-Zag (Right Right)
            {
                // Bring the key as root of
                // right-right and do first rotation
                root.getRight().setRight(splay(root.getRight().getRight(), key));
                root = leftRotate(root);
            }

            // Do second rotation for root
            return (root.getRight() == null) ?
                    root : leftRotate(root);
        }
    }

    public boolean isFull(){
        if(this.elements >= this.limit){
            return true;

        }else{
            return false;
        }
    }

    public NodeTree<T> insert(T data) {
        this.root = insert(this.root, data);
        this.elements++;
        return this.root;

    }

    private NodeTree<T> insert(NodeTree<T> root, T k) {
        // Simple Case: If tree is empty
        if (root == null) return newNode(k);

        // Bring the closest leaf node to root
        root = splay(root, k);

        // If key is already present, then return
        if (root.getData() == k) return root;

        // Otherwise allocate memory for new node
        NodeTree<T> newnode = newNode(k);

        // If root's key is greater, make
        // root as right child of newnode
        // and copy the left child of root to newnode
        if (Integer.valueOf(String.valueOf(root.getData())) > Integer.valueOf(String.valueOf(k))) {
            newnode.setRight(root);
            newnode.setLeft(root.getLeft());
            root.setLeft(null);
        }

        // If root's key is smaller, make
        // root as left child of newnode
        // and copy the right child of root to newnode
        else {
            newnode.setLeft(root);
            newnode.setRight(root.getRight());
            root.setRight(null);
        }

        return newnode; // newnode becomes new root
    }


    public String getTreeCode() {
        int range = 1;
        int c = 0;
        LinkedList<NodeTree> lista = new LinkedList<>();
        lista.add(this.root);
        String codigo = String.valueOf(this.root.getData());
        codigo += ",";
        codigo += "/";
        int top = 1;

        while (top < this.elements) {

            for (int i = c; i < range + c; i++) {

                if (lista.get(i) != null) {

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

            c = range + c;
            range = range * 2;
            codigo += "/";

        }


        return codigo;
    }

    public void generateLimit(){
        this.limit = (int) Math.pow(2,this.getRandomNumber())-1;
    }

    public int getElements() {
        return elements;
    }

    public void setElements(int elements) {
        this.elements = elements;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public NodeTree<T> getRoot() {
        return root;
    }

    public void setRoot(NodeTree<T> root) {
        this.root = root;
    }

    public void setLimit() {
        this.limit = (int) Math.pow(2, this.getRandomNumber()) - 1;
    }

    private int getRandomNumber() {
        return (int) (Math.random() * ((2 - 1) + 1)) + 2;
    }

    // The search function for Splay tree.
// Note that this function returns the
// new root of Splay Tree. If key is
// present in tree then, it is moved to root.
    public NodeTree<T> search(T key) {
        return splay(this.root, key);
    }

    public void preOrder() {
        preOrder(this.root);
    }

    private void preOrder(NodeTree<T> node) {

        if (node != null) {
            System.out.print(node.getData() + " ");
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public String getType(){
        return "SPLAY";
    }



}