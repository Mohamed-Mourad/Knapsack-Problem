import java.util.*;

public class GA {
    Random rd = new Random();
    knapsack sack;
    chromosome [] population;
    chromosome [] parents;
    chromosome [] offspring;
    chromosome [] newGeneration;
    int popNum = 4;
    double pc = 0.7;
    double pm = 0.1;
    chromosome optimalSolution = new chromosome();
    int selNum = 0;

    void generatePopulation(){
        List<chromosome> populationList = new ArrayList<>();
        while (populationList.size() < popNum){
            Boolean [] cGene = new Boolean[sack.size]; // el haythat el chromosome
            List<Boolean> g = new ArrayList<>(); // temp 3shan a3raf a use .add
            for(int j = 0; j < chromosome.len; j++)
            {
                g.add(rd.nextBoolean());
            }
            cGene = g.toArray(cGene);
            chromosome toAdd = new chromosome(cGene);
            for (int j=0; j<chromosome.len; j++) {
                if(cGene[j]){
                    toAdd.fitness += sack.items.get(j).value;
                    toAdd.weight += sack.items.get(j).weight;
                }
            }
            if(!(toAdd.weight > sack.size)){
                populationList.add(toAdd);
            }
        }
        population = populationList.toArray(new chromosome[populationList.size()]);
    }

    void selection() {
        List<chromosome> parentList = new ArrayList<>();
        parentList.sort((a, b) -> (int) (b.fitness - a.fitness));
        int totalRank = 0;
        int count = 1;
        double rouletteBall = Math.random();
            for (chromosome individual: population){
                totalRank += count;
                if (parentList.size() < (popNum/2)) {
                    double value = individual.fitness / totalRank;
                    if (rouletteBall <= value) {
                        parentList.add(individual);
                    }
                    rouletteBall -= value;
                }
                count++;
            }
        parents = new chromosome[parentList.size()];
        parentList.toArray(parents);
    }

    void crossover(){
        List<chromosome> offspringList = new ArrayList<>();
        if(parents.length % 2 == 0){
            for(int i=0; i<(parents.length); i+=2){
                chromosome offspringOne = new chromosome();
                chromosome offspringTwo = new chromosome();
                if(Math.random() <= pc){
                    int crossoverPoint =  (int) ((Math.random() * ((chromosome.len-1) - 1)) + 1);
                    for(int j=0; j<crossoverPoint; j++){
                        offspringOne.genes[j] = parents[i].genes[j];
                        offspringTwo.genes[j] = parents[i+1].genes[j];
                    }
                    for(int j=crossoverPoint; j<chromosome.len; j++){
                        offspringOne.genes[j] = parents[i+1].genes[j];
                        offspringTwo.genes[j] = parents[i].genes[j];
                    }
                } else{
                    offspringOne.genes = parents[i].genes;
                    offspringTwo.genes = parents[i+1].genes;
                }
                offspringList.add(offspringOne);
                offspringList.add(offspringTwo);
            }
        }else{
            for(int i=0; i<(parents.length-1); i+=2){
                chromosome offspringOne = new chromosome();
                chromosome offspringTwo = new chromosome();
                if(Math.random() <= pc){
                    int crossoverPoint =  (int) ((Math.random() * ((chromosome.len-1) - 1)) + 1);
                    for(int j=0; j<crossoverPoint; j++){
                        offspringOne.genes[j] = parents[i].genes[j];
                        offspringTwo.genes[j] = parents[i+1].genes[j];
                    }
                    for(int j=crossoverPoint; j<chromosome.len; j++){
                        offspringOne.genes[j] = parents[i+1].genes[j];
                        offspringTwo.genes[j] = parents[i].genes[j];
                    }
                } else{
                    offspringOne.genes = parents[i].genes;
                    offspringTwo.genes = parents[i+1].genes;
                }
                offspringList.add(offspringOne);
                offspringList.add(offspringTwo);
            }
        }

        offspring = new chromosome[offspringList.size()];
        offspringList.toArray(offspring);

        for (chromosome chromosome : offspring) {
            for (int k = 0; k < chromosome.len; k++) {
                if (chromosome.genes[k]) {
                    chromosome.fitness += sack.items.get(k).value;
                    chromosome.weight += sack.items.get(k).weight;
                }
            }
        }
    }

    void mutation(){
        for(chromosome toMutate : offspring){
            if(Math.random() <= pm){
                int mutateGene = (int) (Math.random() * (chromosome.len-1 - 1 + 1) + 1);
                toMutate.genes[mutateGene] = !(toMutate.genes[mutateGene]);
            }
        }
    }

    void removeInvalid(){
        List<chromosome> offspringList = new ArrayList<>(Arrays.asList(offspring));
        for (chromosome child: offspring) {
            if(child.weight > sack.size){
                offspringList.remove(child);
            }
        }
        offspring = offspringList.toArray(new chromosome[offspringList.size()]);
    }

    void replacement(){
        List<chromosome> allIndividuals = new ArrayList<>();
        for (chromosome parent: parents) {
            allIndividuals.add(parent);
        }
        for (chromosome offspring: offspring) {
            allIndividuals.add(offspring);
        }
        allIndividuals.sort((a, b) -> (int) (b.fitness - a.fitness));

        List<chromosome> newGenerationList = new ArrayList<>();

        for (int i=0; i<parents.length; i++) {
            newGenerationList.add(allIndividuals.get(i));
        }
        optimalSolution = allIndividuals.get(0);
        newGeneration = new chromosome[newGenerationList.size()];
        newGenerationList.toArray(newGeneration);
        population = newGeneration;
    }

    void run(knapsack sack){
        this.sack = sack;
        chromosome.len = sack.numItems;
        generatePopulation();
        for(int i=0; i<5; i++){
            selection();
            crossover();
            mutation();
            removeInvalid();
            replacement();
        }
        System.out.println("best solution reached: ");
        optimalSolution.printChromosome();
    }

}
