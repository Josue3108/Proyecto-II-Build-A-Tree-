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

        int[] root = tree.root.getKeys();
        System.out.println(root[0]);

        for(int i = 0; i<root.length ; i++){
            System.out.println(root[i]);
        }



      //  System.out.println(tree.getLevels());
       // System.out.println(tree.getTreeCode());






    }




}
