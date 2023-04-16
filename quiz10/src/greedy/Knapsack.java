package greedy;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Knapsack {

    static class Item {
        String name;
        int value;
        int weight;
        double costPerPound; // keeping track of this for sorting purposes;

        Item(String name, int value, int weight) {
            this.name = name;
            this.value = value;
            this.weight = weight;
            costPerPound = (double) value / weight;
        }

        public String toString() {
            return name + ", value: " + value + ", weight: " + weight;
        }

    }

    static ArrayList<Double> fractionalKnapsack(ArrayList<Item> items, double maxWeight) {
        Collections.sort(items, (item, nextItem) -> Double.compare(item.costPerPound, nextItem.costPerPound));
        ArrayList<Double> itemList = new ArrayList<Double>();
        double profit = 0;
        double weightLeft = maxWeight;

        for (Item item : items) {
            if (weightLeft >= item.weight) {
                itemList.add(1.0);
                profit += item.value;
                weightLeft -= item.weight;
            } else {
                double itemFraction = weightLeft / item.weight;
                itemList.add(itemFraction);
                profit += itemFraction * item.value;
                weightLeft = 0;
            }

            if (weightLeft == 0) {
                break;
            }
        }

        itemList.add(profit);
        return itemList;
    }

    public static void main(String[] args) {
        Item item1 = new Item("Trique", 10000, 150);
        Item item2 = new Item("Phone", 200, 1);
        Item item3 = new Item("Laptop", 2000, 25);
        Item item4 = new Item("Monitor", 200, 47);
        Item item5 = new Item("Kindle", 400, 10);
        Item item6 = new Item("Headphone", 100, 1);
        Item item7 = new Item("Cup", 120, 2);

        ArrayList<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);
        items.add(item7);

        ArrayList<Double> result = fractionalKnapsack(items, 100);

        result.forEach(res -> System.out.println(res));
    }
}
