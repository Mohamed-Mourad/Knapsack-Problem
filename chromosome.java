import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class chromosome {
    Boolean [] genes = new Boolean[len];
    //ArrayList<Boolean> genes = new ArrayList<>();
    static int len;
    int fitness;
    int weight;
    int numSelected;

    public chromosome(){
        this.fitness = 0;
    }

//    public chromosome(knapsack sack){
//        Random random = new Random();
//        for(int i=0; i< this.len ; i++)
//            this.genes.add(random.nextBoolean());
//        for (int j=0; j<chromosome.len; j++) {
//            if(this.genes[j]){
//                this.fitness += sack.items.get(j).value;
//                this.weight += sack.items.get(j).weight;
//            }
//        }
//    }

    public chromosome(Boolean [] genes){
        this.genes = genes;
    }

    void printChromosome(){
        numSelected = 0;
        for (int i=0; i<len; i++) {
            if (genes[i]) {
                numSelected = numSelected + 1;
            }
        }
        System.out.println("Number of selected items: " + numSelected);
        System.out.println("fitness: " + fitness);
        System.out.println("weight: " + weight);
        String genome = "";
        for (int i=0; i<len; i++) {
            if(genes[i]){
                genome = genome + "1";
            }else{
                genome = genome + "0";
            }
        }
        System.out.println("genome: " + genome);
    }
}
