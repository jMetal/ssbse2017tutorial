package ssbse2017;

import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

import java.util.BitSet;
import java.util.Random;
import java.util.stream.IntStream;

public class NRPProblem extends AbstractBinaryProblem {
  private int numberOfRequirements ;
  private int highestClientSatisfactionValue ;
  private int highestRequirementCost ;
  private int numberOfClients ;
  private int highestImportanceValue ;
  private long randomSeed;

  private int requirementCost[] ;
  private int clientSatisfaction[] ;
  private int importanceMatrix[][] ;

  public NRPProblem(
          int numberOfClients,
          int numberOfRequirements,
          int highestClientSatisfactionValue,
          int highestRequirementCost,
          int highestImportanceValue,
          long randomSeed) {
    this.numberOfClients = numberOfClients ;
    this.numberOfRequirements = numberOfRequirements ;
    this.highestRequirementCost = highestRequirementCost ;
    this.highestClientSatisfactionValue = highestClientSatisfactionValue ;
    this.highestImportanceValue = highestImportanceValue ;
    this.randomSeed = randomSeed ;

    this.setNumberOfVariables(1);
    this.setNumberOfObjectives(2);
    this.setNumberOfConstraints(0);

    Random random = new Random(this.randomSeed) ;

    this.requirementCost = new int[this.numberOfRequirements] ;
    IntStream
            .range(0, this.numberOfRequirements)
            .forEach(i -> this.requirementCost[i] = random.nextInt(this.highestRequirementCost) + 1) ;

    this.clientSatisfaction = new int[this.numberOfClients] ;
    IntStream
            .range(0, this.numberOfClients)
            .forEach(i -> this.clientSatisfaction[i] =
                    random.nextInt(this.highestClientSatisfactionValue) + 1);

    this.importanceMatrix = new int[this.numberOfClients][this.numberOfRequirements] ;
    for (int i = 0 ; i < this.numberOfClients; i++) {
      for (int j = 0 ; j < this.numberOfRequirements; j++) {
        this.importanceMatrix[i][j] = random.nextInt(this.highestImportanceValue+1) ;
      }
    }
  }

  @Override
  protected int getBitsPerVariable(int i) {
    return this.numberOfRequirements;
  }

  @Override
  public void evaluate(BinarySolution solution) {
    solution.setObjective(0, computeCost(solution));
    // satisfaction is a maximization objective: multiply by -1 to minimize
    solution.setObjective(1, -1.0 * computeSatisfaction(solution));
  }

  private double computeCost(BinarySolution solution) {
    double result = 0.0 ;
    BitSet bitset = solution.getVariableValue(0) ;

    for (int i = 0; i < this.numberOfRequirements; i++) {
      if (bitset.get(i)) {
        result += requirementCost[i] ;
      }
    }

    return result ;
  }

  private double computeSatisfaction(BinarySolution solution) {
    double result = 0.0 ;
    BitSet bitset = solution.getVariableValue(0) ;

    for (int client = 0; client < numberOfClients; client++) {
      double sum = 0.0 ;
      for (int i = 0; i < this.numberOfRequirements; i++) {
        if (bitset.get(i)) {
          sum += importanceMatrix[client][i] ;
        }
      }
      result += sum * clientSatisfaction[client] ;
    }

    return result ;
  }

  /* Getters */

  public int getNumberOfRequirements() {
    return numberOfRequirements;
  }

  public int getHighestClientSatisfactionValue() {
    return highestClientSatisfactionValue;
  }

  public int getHighestRequirementCost() {
    return highestRequirementCost;
  }

  public int getNumberOfClients() {
    return numberOfClients;
  }

  public int getHighestImportanceValue() {
    return highestImportanceValue;
  }

  public long getRandomSeed() {
    return randomSeed;
  }

  public int[] getRequirementCost() {
    return requirementCost;
  }

  public int[] getClientSatisfaction() {
    return clientSatisfaction;
  }

  public int[][] getImportanceMatrix() {
    return importanceMatrix;
  }
}
