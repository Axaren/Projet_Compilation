package ubordeaux.deptinfo.compilation.project.environment;

import java.util.Iterator;
import java.util.Stack;
import java.util.HashMap;
import ubordeaux.deptinfo.compilation.project.node.Node;
import ubordeaux.deptinfo.compilation.project.type.Type;

public class StackEnvironment extends Environment {

  private Stack<HashMap<String, Type>> stack;

  public StackEnvironment(String label) {
    super(label);
    stack = new Stack<HashMap<String, Type>>();
  }

  public void addToCurrentScope(String id, Type t) {
    HashMap<String, Type> currentScope = stack.peek();
    currentScope.put(id, t);
  }

  public void newScope() {
    stack.push(new HashMap<String, Type>());
  }

  public void removeScope() {
    System.out.println("stack size "+stack.size());
    if (stack.size() > 0)
      stack.pop();
  }

  /**
   * Retourne le type de la variable "id"
   * Retourne null si la variable n'existe pas
   */
  public Type getType(String id) {
      Type res = null;
      Iterator it = stack.iterator(); // note : l'itérateur va du bas de la pile vers le haut
      while (it.hasNext()) {
        HashMap<String, Type> scope = (HashMap<String, Type>)it.next();
        if (scope.containsKey(id))
          res = scope.get(id);
      }
      return res;
  }

  /**
   * Retourne vrai s'il existe une variable de nom "id" qui existe dans la portée actuelle
   */
  public boolean existsInScope(String id) {
    HashMap<String, Type> currentScope = stack.peek();
    return currentScope.containsKey(id);
  }

}
