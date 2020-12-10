package Server;

import Trees.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;

import java.beans.EventHandler;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.stream.IntStream;

public class Main{

    private static String code = "";
    private static LinkedList<BTreeNode> pendiente ;
    private static LinkedList<BTreeNode> pendienteaux;
    private static int  niveles = 0;

    public static void main(String[] args) {
        pendiente = new LinkedList<>();
        pendienteaux = new LinkedList<>();


        // write your code her


        BTree tree = new BTree(3);


        tree.insert(10);

        tree.insert(20);

        tree.insert(30);

        tree.insert(40);

        tree.insert(50);

        tree.insert(25);

        tree.insert(9);

        System.out.println(tree.getTreeCode());
        System.out.println(tree.isFull());



     //   pendiente.add(tree.root);
      //  printB();

       // System.out.print(code);
        //System.out.print(niveles);



      //  System.out.println(tree.getLevels());
       // System.out.println(tree.getTreeCode());


    }

    private static void  printB(){



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

    private static String printMi(BTreeNode node){
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

    private static void setPendienteaux(BTreeNode node){
        BTreeNode[] childs = node.getC();
        for(int j = 0;j<childs.length;j++){
            if(childs[j]!=null){
                pendienteaux.add(childs[j]);
            }
        }

    }


}
