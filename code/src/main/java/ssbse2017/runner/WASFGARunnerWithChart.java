package ssbse2017.runner;

import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.uma.jmetal.algorithm.multiobjective.wasfga.WASFGAMeasures;
import org.uma.jmetal.measure.MeasureListener;
import org.uma.jmetal.measure.MeasureManager;
import org.uma.jmetal.measure.impl.BasicMeasure;
import org.uma.jmetal.measure.impl.CountingMeasure;
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
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import org.uma.jmetal.util.fileoutput.SolutionListOutput;
import org.uma.jmetal.util.fileoutput.impl.DefaultFileOutputContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ssbse2017.NRPProblem;
import ssbse2017.util.ChartContainer;

public class WASFGARunnerWithChart {
  /**
   * @param args Command line arguments.
   * @throws JMetalException
 * @throws IOException 
   */
  public static void main(String[] args) throws JMetalException, IOException {
    WASFGAMeasures<BinarySolution> algorithm;
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

    double mutationProbability = 1.0 / problem.getTotalNumberOfBits() ;
    mutation = new BitFlipMutation(mutationProbability) ;

    selection = new BinaryTournamentSelection<>(
            new RankingAndCrowdingDistanceComparator<>());

    List<Double> referencePoint = new ArrayList<>();
    referencePoint.add(600.0);
    referencePoint.add(0.0);

    algorithm = new WASFGAMeasures<BinarySolution>(
            problem,
            100,
            250,
            crossover,
            mutation,
            selection,
            new SequentialSolutionListEvaluator<BinarySolution>(),
            referencePoint) ;

    
    /* Measure management */
    MeasureManager measureManager = ((WASFGAMeasures<BinarySolution>) algorithm).getMeasureManager();

    BasicMeasure<List<BinarySolution>> solutionListMeasure = (BasicMeasure<List<BinarySolution>>) measureManager
            .<List<BinarySolution>>getPushMeasure("currentPopulation");
    CountingMeasure iterationMeasure = (CountingMeasure) measureManager.<Long>getPushMeasure("currentEvaluation");

    ChartContainer chart = new ChartContainer(algorithm.getName(), 200);
    chart.setFrontChart(0, 1, null);
    chart.setReferencePoint(referencePoint);
    chart.initChart();

    solutionListMeasure.register(new ChartListener(chart));
    iterationMeasure.register(new IterationListener(chart));
    /* End of measure management */
    
    
    AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
            .execute() ;

    chart.saveChart("WASFGA", BitmapFormat.PNG);
    List<BinarySolution> population = algorithm.getResult() ;
    long computingTime = algorithmRunner.getComputingTime() ;

    JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

    new SolutionListOutput(population)
            .setSeparator("\t")
            .setVarFileOutputContext(new DefaultFileOutputContext("VAR.tsv"))
            .setFunFileOutputContext(new DefaultFileOutputContext("FUN.tsv"))
            .print();
  }
  
  private static class ChartListener implements MeasureListener<List<BinarySolution>> {
      private ChartContainer chart;
      private int iteration = 0;

      public ChartListener(ChartContainer chart) {
          this.chart = chart;
          this.chart.getFrontChart().setTitle("Iteration: " + this.iteration);
      }

      private void refreshChart(List<BinarySolution> solutionList) {
          if (this.chart != null) {
              iteration++;
              this.chart.getFrontChart().setTitle("Iteration: " + this.iteration);
              this.chart.updateFrontCharts(solutionList);
              this.chart.refreshCharts();
          }
      }

      @Override
      synchronized public void measureGenerated(List<BinarySolution> solutions) {
          refreshChart(solutions);
      }
  }

  private static class IterationListener implements MeasureListener<Long> {
      ChartContainer chart;

      public IterationListener(ChartContainer chart) {
          this.chart = chart;
          this.chart.getFrontChart().setTitle("Iteration: " + 0);
      }

      @Override
      synchronized public void measureGenerated(Long iteration) {
          if (this.chart != null) {
              this.chart.getFrontChart().setTitle("Iteration: " + iteration);
          }
      }
  }
}
