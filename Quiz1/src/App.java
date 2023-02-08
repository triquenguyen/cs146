import java.util.*;
import linked.SortedLinkedList;

public class App {
    public static void main(String[] args) throws Exception {
        SortedLinkedList newList = new SortedLinkedList();
        newList.insert(3);       
        newList.insert(1);
        newList.insert(8);
        newList.insert(5);
        newList.insert(3);
        newList.insert(1);
        System.out.println(newList);
    }
}


        // newList.insert(30);
        // newList.insert(27);
        // newList.insert(2);
        // newList.insert(1);
        // newList.insert(4);
        
        
        // System.out.println(newList.getDescending());