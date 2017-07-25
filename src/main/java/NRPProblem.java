import org.uma.jmetal.problem.BinaryProblem;
import org.uma.jmetal.problem.impl.AbstractBinaryProblem;
import org.uma.jmetal.solution.BinarySolution;

public class NRPProblem extends AbstractBinaryProblem {

  public NRPProblem(
          int numberOfClients,
          int numberOfRequirements) {

  }

  @Override
  protected int getBitsPerVariable(int i) {
    return 0;
  }

  @Override
  public void evaluate(BinarySolution binarySolution) {

  }
}
