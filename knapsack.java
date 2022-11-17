import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class knapsack {
    int size;
    int numItems;
    List<item> items = new ArrayList<>();

    void printKnapsack(){
        System.out.println(numItems);
        System.out.println(size);
        for (item item: items) {
            System.out.println(item.value + " " + item.weight);
        }
    }
}
