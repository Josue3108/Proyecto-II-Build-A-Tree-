package Trees;

public class NodeTree<T> {

    private T Data;
    private NodeTree right;
    private NodeTree left;
    private int heigh;


    public NodeTree(T Data) {
        this.Data = Data;
        this.heigh = 1;
    }

    public NodeTree(T Data,int heigh) {
        this.Data = Data;
        this.heigh = heigh;
    }

    public NodeTree(){
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

    public void setRight(NodeTree<T> right) {
        this.right = right;
    }

    public NodeTree getLeft() {
        return left;
    }

    public void setLeft(NodeTree<T> left) {
        this.left = left;
    }

    public int getHeigh() {
        return heigh ;
    }

    public void setHeigh(int heigh) {
        this.heigh  = heigh;
    }
}
