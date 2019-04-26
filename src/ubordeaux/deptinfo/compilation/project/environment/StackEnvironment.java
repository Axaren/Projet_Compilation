package ubordeaux.deptinfo.compilation.project.environment;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import ubordeaux.deptinfo.compilation.project.node.Node;
import ubordeaux.deptinfo.compilation.project.type.Type;

public class StackEnvironment extends Environment {

  private ArrayList<HashMap<String, Type>> stack;

  public StackEnvironment(String label) {
    super(label);
    stack = new ArrayList<HashMap<String, Type>>();
  }

  public HashMap<String, Type> peek() {
    if (stack.size() == 0) return null;
    return stack.get(stack.size() - 1);
  }

  public void addToCurrentScope(String id, Type t) {
    HashMap<String, Type> currentScope = peek();
    if (currentScope != null) currentScope.put(id, t);
  }

  public void newScope() {
    stack.add(new HashMap<String, Type>());
    System.out.println("added scope, size="+stack.size());
  }

  public void removeScope() {
    if (stack.size() > 0)
      stack.remove(stack.size() - 1);
    System.out.println("removed scope, size="+stack.size());
  }

  /**
   * Retourne le type de la variable "id"
   * Retourne null si la variable n'existe pas
   */
  public Type getType(String id) {
      if (stack.size() == 0) return null;
      HashMap<String, Type> currentScope;
      for (int i = stack.size() - 1; i >= 0; i--) {
        currentScope = stack.get(i);
        if (currentScope.containsKey(id)) {
          return currentScope.get(id);
        }
      }
      return null;
  }

  /**
   * Retourne vrai s'il existe une variable de nom "id" qui existe dans la portée actuelle
   */
  public boolean existsInScope(String id) {
    HashMap<String, Type> currentScope = peek();
    if (currentScope == null) return false;
    return currentScope.containsKey(id);
  }

}
