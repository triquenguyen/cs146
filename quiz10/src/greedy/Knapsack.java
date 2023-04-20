// package greedy;

// import java.sql.Array;
// import java.util.ArrayList;
// import java.util.Collection;
// import java.util.Collections;

// public class Knapsack {

//     static class Item {
//         String name;
//         int value;
//         int weight;
//         double costPerPound; // keeping track of this for sorting purposes;

//         Item(String name, int value, int weight) {
//             this.name = name;
//             this.value = value;
//             this.weight = weight;
//             costPerPound = (double) value / weight;
//         }

//         public String toString() {
//             return name + ", value: " + value + ", weight: " + weight;
//         }

//     }

//     static ArrayList<Double> fractionalKnapsack(ArrayList<Item> items, double maxWeight) {
//         ArrayList<Double> itemList = new ArrayList<Double>();
//         double weightLeft = maxWeight;

//         for (Item item : items) {
//             if (weightLeft >= item.weight) {
//                 itemList.add(1.0);
//                 weightLeft -= item.weight;
//             } else {
//                 double itemFraction = weightLeft / item.weight;
//                 itemList.add(itemFraction);
//                 weightLeft = 0;
//             }

//             if (weightLeft == 0) {
//                 break;
//             }
//         }

//         while (itemList.size() < items.size()) {
//             itemList.add(0.0);
//         }

//         return itemList;
//     }

//     public static void main(String[] args) {
//         Item item1 = new Item("Trique", 10000, 150); // 66.66
//         Item item2 = new Item("Phone", 200, 1); // 200
//         Item item3 = new Item("Laptop", 2000, 25); // 80
//         Item item4 = new Item("Monitor", 200, 47); // 4.25
//         Item item5 = new Item("Kindle", 400, 10); // 40
//         Item item6 = new Item("Headphone", 100, 1); // 100
//         Item item7 = new Item("Cup", 120, 2); // 60

//         ArrayList<Item> items = new ArrayList<>();
//         items.add(item2);
//         items.add(item6);
//         items.add(item3);
//         items.add(item1);
//         items.add(item7);
//         items.add(item5);
//         items.add(item4);

//         ArrayList<Double> result = fractionalKnapsack(items, 100);

//         result.forEach(res -> System.out.println(res));
//     }
// }

package greedy;

import java.util.ArrayList;

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
        ArrayList<Double> result = new ArrayList<Double>();

        int i = 0;
        while (i < items.size()) {
            result.add(0.0); // initialize result list with 0.0 for each item
            i++;
        }

        double weightSoFar = 0.0; // initialize weightSoFar to 0
        i = 0;
        while (i < items.size()) {
            Item currentItem = items.get(i);

            // if we can take the entire item, take it and update weightSoFar
            if (weightSoFar + currentItem.weight <= maxWeight) {
                result.set(i, 1.0);
                weightSoFar += currentItem.weight;
            } else {
                // if we can only take a fraction of the item, take the fraction and update
                // weightSoFar
                double remainingWeight = maxWeight - weightSoFar;
                double fraction = remainingWeight / currentItem.weight;
                result.set(i, fraction);
                weightSoFar += remainingWeight;
                break;
            }

            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        Item item1 = new Item("Trique", 10000, 150); // 66.66
        Item item2 = new Item("Phone", 200, 1); // 200
        Item item3 = new Item("Laptop", 2000, 25); // 80
        Item item4 = new Item("Monitor", 200, 47); // 4.25
        Item item5 = new Item("Kindle", 400, 10); // 40
        Item item6 = new Item("Headphone", 100, 1); // 100
        Item item7 = new Item("Cup", 120, 2); // 60

        ArrayList<Item> items = new ArrayList<>();
        items.add(item2);
        items.add(item6);
        items.add(item3);
        items.add(item1);
        items.add(item7);
        items.add(item5);
        items.add(item4);

        ArrayList<Double> result = fractionalKnapsack(items, 100);

        result.forEach(res -> System.out.println(res));
    }

}
