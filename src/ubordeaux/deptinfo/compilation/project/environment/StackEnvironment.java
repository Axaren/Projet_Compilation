package ubordeaux.deptinfo.compilation.project.environment;

import java.util.Stack;
import ubordeaux.deptinfo.compilation.project.node.Node;

public class StackEnvironment extends Environment {

  private Stack<Node> stack;

  public StackEnvironment(String label) {
    super(label);
    stack = new Stack<Node>();
  }


}
