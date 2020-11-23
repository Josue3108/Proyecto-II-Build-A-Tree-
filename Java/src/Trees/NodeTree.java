package Trees;

public class NodeTree<T> {

    private T Data;
    private NodeTree right;
    private NodeTree left;


    public NodeTree(T Data) {
        this.Data = Data;

    }

    public T getData() {
        return Data;
    }

    public void setData(T Data) {
        this.Data = Data;
    }

    public NodeTree getRight() {
        return right;
    }

    public void setRight(NodeTree right) {
        this.right = right;
    }

    public NodeTree getLeft() {
        return left;
    }

    public void setLeft(NodeTree left) {
        this.left = left;
    }
}
