package ssbse2017;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NRPProblemTest {
  @Test
  public void shouldConstructorCreateAValidProblemInstance() {
    int numberOfClients = 20 ;
    int numberOfRequirements = 30 ;
    int highestClientSatisfactionValue = 10 ;
    int highestRequirementCost = 200 ;
    int highestImportanceValue = 100 ;
    int randomSeed = 1 ;
    NRPProblem problem = new NRPProblem(
            numberOfClients,
            numberOfRequirements,
            highestClientSatisfactionValue,
            highestRequirementCost,
            highestImportanceValue,
            randomSeed) ;

    assertEquals(numberOfClients, problem.getNumberOfClients()) ;
    assertEquals(numberOfRequirements, problem.getNumberOfRequirements());
    assertEquals(highestClientSatisfactionValue, problem.getHighestClientSatisfactionValue());
    assertEquals(highestRequirementCost, problem.getHighestRequirementCost());
    assertEquals(highestImportanceValue, problem.getHighestImportanceValue());
    assertEquals(randomSeed, problem.getRandomSeed());
    assertTrue(Arrays
            .stream(problem.getRequirementCost())
            .allMatch(item -> item >= 1 && item <= highestRequirementCost));

    assertTrue(Arrays
            .stream(problem.getClientSatisfaction())
            .allMatch(item -> item >= 1 && item <= highestClientSatisfactionValue));
    assertTrue(Arrays
            .stream(problem.getImportanceMatrix())
            .flatMapToInt(x -> Arrays.stream(x))
            .allMatch(item -> item >= 0 && item <= highestImportanceValue));
  }
}
