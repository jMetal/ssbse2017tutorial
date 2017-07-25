package ssbse2017;

import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

import java.util.Random;
import java.util.stream.IntStream;

public class NRPProblem extends AbstractBinaryProblem {
  private long randomSeed;
  private int numberOfRequirements ;
  private int highestClientSatisfactionValue ;
  private int highestRequirementCost ;
  private int numberOfClients ;

  private int requirements [] ;
  private int clientSatisfaction[] ;

  public NRPProblem(
          int numberOfClients,
          int numberOfRequirements,
          int highestRequirementCost,
          int highestClientSatisfactionValue,
          long randomSeed) {
    this.numberOfClients = numberOfClients ;
    this.numberOfRequirements = numberOfRequirements ;
    this.highestRequirementCost = highestRequirementCost ;
    this.highestClientSatisfactionValue = highestClientSatisfactionValue ;
    this.randomSeed = randomSeed ;

    this.setNumberOfVariables(1);
    this.setNumberOfObjectives(2);
    this.setNumberOfConstraints(0);

    Random random = new Random(this.randomSeed) ;

    this.requirements = new int[this.numberOfRequirements] ;
    IntStream
            .range(0, this.numberOfRequirements)
            .forEach(i -> this.requirements[i] = random.nextInt(this.highestRequirementCost) + 1) ;
  }

  @Override
  protected int getBitsPerVariable(int i) {
    return this.numberOfRequirements;
  }

  @Override
  public void evaluate(BinarySolution binarySolution) {

  }
}
