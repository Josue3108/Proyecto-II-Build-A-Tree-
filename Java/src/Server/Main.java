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

    public static void main(String[] args) {

	// write your code her


        BTree tree = new BTree(3);


        tree.insert(10);

        tree.insert(20);

        tree.insert(30);

        tree.insert(40);

        tree.insert(50);

        tree.insert(25);

        tree.insert(9);

        System.out.println(tree.getType());



        printB(tree.root);

      //  System.out.println(tree.getLevels());
       // System.out.println(tree.getTreeCode());


    }

    private static void printB(BTreeNode node){

        BTreeNode[] childs = node.getC();
        int[] elem = node.getKeys();
        if (elem == null ){
            System.out.println("elem null");
        }
        if (childs == null){
            System.out.println("childs null");
        }

        for(int i = 0; i<elem.length ; i++){
            if(elem[i]!=0) {
                System.out.println(elem[i]);
            }
        }

    }


}
