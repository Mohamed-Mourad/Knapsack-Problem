import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class main {
    public static void main(String args[]) throws IOException {

        BufferedReader fileReader = new BufferedReader(new FileReader("D:/Mado/University/Year 4 - Sem 1/Soft Computing/Assignments/Assignment1/knapsack_input.txt"));
        //StreamTokenizer st = new StreamTokenizer(fileReader);
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numTests = Integer.parseInt(fileReader.readLine());

        ArrayList<knapsack> sacks = new ArrayList<>();

        for (int i=0; i<numTests; i++) {
            fileReader.readLine();
            fileReader.readLine();
            sacks.add(new knapsack());
            sacks.get(i).numItems = Integer.parseInt(fileReader.readLine()); //items
            sacks.get(i).size = Integer.parseInt(fileReader.readLine()); //size
            for (int j = 0; j<sacks.get(i).numItems; j++) {
                StringTokenizer st = new StringTokenizer(fileReader.readLine());
                int v = Integer.parseInt(String.valueOf(st.nextToken()));
                int w = Integer.parseInt(String.valueOf(st.nextToken()));
                sacks.get(i).items.add(new item(v,w));
            }
        }
//        for (int i= 1; i<=sacks.size(); i++) {
//            System.out.println("sack: "+i);
//            sacks.get(i-1).printKnapsack();
//        }

        for (int i=1; i<=sacks.size(); i++) {
            GA ga = new GA();
            System.out.println("Test " + i + ": ");
            ga.run(sacks.get(i-1));
        }
    }
}

//First line: Number of test cases (must be at least 1)  ✅✅
//For each test case:
//Size of the knapsack  ✅✅
//Number of items  ✅✅
//For each item:
//Weight and value separated by space

//test[i].fitness = Integer.parseInt(st.nextToken());
//test[i].weight = Integer.parseInt(st.nextToken());
