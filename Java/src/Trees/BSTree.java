package Trees;

public class BSTree<T> {


    private NodeTree<T> root = null;


    public NodeTree<T> getRoot() {
        return root;
    }

    public T getRootData(){
        return root.getData();
    }

    public void setRoot(NodeTree<T> root) {
        this.root = root;
    }

    public void setRoot(T data){
        this.root  = new NodeTree<>(data);
    }

    public void setNode(NodeTree<T> node){

        if (this.root == null){
            this.root = node;

        }else{

            NodeTree<T> subroot = this.root;
            NodeTree<T> actual = this.root;

            while(actual != null) {
                if (String.valueOf(node.getData()).compareTo(String.valueOf(node.getData())) < 0) {


                } else if (String.valueOf(node.getData()).compareTo(String.valueOf(node.getData())) > 0) {

                } else {
                    System.out.print("El dato ya se encuantra en el árbol");
                    return;
                }
            }

        }

    }


}
