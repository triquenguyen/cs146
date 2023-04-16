package greedy;

import java.util.ArrayList;


public class Knapsack {
    
    static class Item {
        String name;
        int value;
        int weight;
        double costPerPound; //keeping track of this for sorting purposes;
        
        Item(String name,int value,int weight) {
            this.name = name;
            this.value = value;
            this.weight = weight;
            costPerPound = (double)value/weight;
            
        }
        
        public String toString() {
            return name +", value: "+value + ", weight: " + weight;
        }

    }
    
    static ArrayList<Double> fractionalKnapsack(ArrayList<Item> items,double maxWeight){
        
       
    }
    

}

