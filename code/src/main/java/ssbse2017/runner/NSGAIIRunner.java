package ssbse2017.runner;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.SinglePointCrossover;
import org.uma.jmetal.operator.impl.mutation.BitFlipMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.solution.BinarySolution;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.comparator.RankingAndCrowdingDistanceComparator;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;

import java.util.List;

import ssbse2017.NRPProblem;

/**
 * Class to configure and run the NSGA-II algorithm
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class NSGAIIRunner {
  /**
   * @param args Command line arguments.
   * @throws JMetalException
   * Invoking command:
    java org.uma.jmetal.runner.multiobjective.NSGAIIRunner
   */
  public static void main(String[] args) throws JMetalException {
    Algorithm<List<BinarySolution>> algorithm;
    CrossoverOperator<BinarySolution> crossover;
    MutationOperator<BinarySolution> mutation;
    SelectionOperator<List<BinarySolution>, BinarySolution> selection;

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

    double crossoverProbability = 0.9 ;
    crossover = new SinglePointCrossover(crossoverProbability) ;

    double mutationProbability = 1.0 / numberOfRequirements ;
    mutation = new BitFlipMutation(mutationProbability) ;

    selection = new BinaryTournamentSelection<>(
        new RankingAndCrowdingDistanceComparator<>());

    algorithm = new NSGAIIBuilder<>(problem, crossover, mutation)
        .setSelectionOperator(selection)
        .setMaxEvaluations(25000)
        .setPopulationSize(100)
        .build() ;

    AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
        .execute() ;

    List<BinarySolution> population = algorithm.getResult() ;
    long computingTime = algorithmRunner.getComputingTime() ;

    JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

    new SolutionListOutput(population)
            .setSeparator("\t")
            .setVarFileOutputContext(new DefaultFileOutputContext("VAR.tsv"))
            .setFunFileOutputContext(new DefaultFileOutputContext("FUN_NSGAII.tsv"))
            .print();
  }
}
