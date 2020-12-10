package Trees;

import java.util.LinkedList;

public class BTree extends Tree{

    private  String code = "";
    private  LinkedList<BTreeNode> pendiente ;
    private  LinkedList<BTreeNode> pendienteaux;
    private  int  niveles = 0;
    public BTreeNode root; // Pointer to root node
    public int t; // Minimum degree
    private int limit = 3;

    public int getLimit() {
        return limit;
    }

    public void setLimit(){
        this.limit = 3;
    }
    public void setLimit(int limit) {
        this.limit = limit;
    }

    // Constructor (Initializes tree as empty)
    public BTree(int t) {
        this.root = null;
        this.t = t;
    }
    public BTree(){
        this.root = null;
        this.t = 3;
    }



    // function to traverse the tree
    public void traverse() {
        if (this.root != null)
            this.root.traverse();
        System.out.println();
    }

    // function to search a key in this tree
    public BTreeNode search(int k) {
        if (this.root == null)
            return null;
        else
            return this.root.search(k);
    }

    public void insert(int k)
    {
        // If tree is empty
        if (root == null)
        {
            // Allocate memory for root
            root = new BTreeNode(t, true);
            root.keys[0] = k;  // Insert key
            root.n = 1;  // Update number of keys in root
        }
        else // If tree is not empty
        {
            // If root is full, then tree grows in height
            if (root.n == 2*t-1)
            {
                // Allocate memory for new root
                BTreeNode s = new BTreeNode(t, false);

                // Make old root as child of new root
                s.C[0] = root;

                // Split the old root and move 1 key to the new root
                s.splitChild(0, root);

                // New root has two children now.  Decide which of the
                // two children is going to have new key
                int i = 0;
                if (s.keys[0] < k)
                    i++;
                s.C[i].insertNonFull(k);

                // Change root
                root = s;
            }
            else  // If root is not full, call insertNonFull for root
                root.insertNonFull(k);
        }
    }

    public String getType(){
        return "B";
    }

    public String getTreeCode(){
        String c = getTreeCodeaux();
        niveles = 0;
        return c;
    }

    public boolean isFull(){
        getTreeCodeaux();
        if(niveles>=limit){
            niveles = 0;
            return true;
        }
        else{
            niveles = 0;
            return false;
        }
    }

    private String getTreeCodeaux(){
        pendiente = new LinkedList<>();
        pendienteaux = new LinkedList<>();
        pendiente.add(this.root);
        printB();
        return code;
    }

    private  void  printB(){



        while(pendiente.size()!=0) {

            String subcode = "";

            for (int i = 0; i < pendiente.size(); i++) {
                subcode+=")";
                subcode+=printMi(pendiente.get(i));

            }
            for (int i = 0; i < pendiente.size(); i++) {
                setPendienteaux(pendiente.get(i));
            }

            code+="/";
            code+= subcode.substring(1);

            niveles++;
            pendiente = pendienteaux;
            pendienteaux = new LinkedList<>();

        }
        code = code.substring(1);


    }

    private  String printMi(BTreeNode node){
        String subsubcode = "";
        int[] elem = node.getKeys();

        for(int i = 0; i<elem.length ; i++){
            if(elem[i]!=0) {
                System.out.println(elem[i]);
                subsubcode+=","+String.valueOf(elem[i]);
            }
        }

        return subsubcode.substring(1);
    }

    private  void setPendienteaux(BTreeNode node){
        BTreeNode[] childs = node.getC();
        for(int j = 0;j<childs.length;j++){
            if(childs[j]!=null){
                pendienteaux.add(childs[j]);
            }
        }

    }

}
