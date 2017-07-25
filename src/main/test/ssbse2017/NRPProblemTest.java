package ssbse2017;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NRPProblemTest {
  @Test
  public void shouldConstructorCreateAValidProblemInstance() {
    NRPProblem problem = new NRPProblem(20, 30, 10, 200, 100, 1234) ;

    assertEquals(20, problem.getNumberOfClients()) ;
    assertEquals(30, problem.getNumberOfRequirements());
    assertEquals(10, problem.getHighestClientSatisfactionValue());
    assertEquals(200, problem.getHighestRequirementCost());
    assertEquals(100, problem.getHighestImportanceValue());
    assertEquals(1234, problem.getRandomSeed());
    assertTrue(Arrays
            .stream(problem.getRequirements())
            .anyMatch(item -> item >= 1 && item <= 200));
    assertTrue(Arrays
            .stream(problem.getClientSatisfaction())
            .anyMatch(item -> item >= 1 && item <= 100));
    assertTrue(Arrays
            .stream(problem.getImportanceMatrix())
            .flatMapToInt(x -> Arrays.stream(x))
            .anyMatch(item -> item >= 0 && item <= 100));
  }
}
