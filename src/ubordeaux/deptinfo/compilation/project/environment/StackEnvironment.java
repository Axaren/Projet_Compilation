package ubordeaux.deptinfo.compilation.project.environment;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.HashMap;
import ubordeaux.deptinfo.compilation.project.node.Node;
import ubordeaux.deptinfo.compilation.project.type.Type;

public class StackEnvironment extends Environment {

  /**
   * On utilise une ArrayList comme un stack, car la classe
   * Stack ne nous permet pas d'itérer de haut en bas de la pile
   * facilement.
   */
  private ArrayList<HashMap<String, Type>> stack;

  public StackEnvironment(String label) {
    super(label);
    stack = new ArrayList<HashMap<String, Type>>();
  }

  /**
   * Retourne la portée la plus haute
   */
  public HashMap<String, Type> peek() {
    if (stack.size() == 0) return null;
    return stack.get(stack.size() - 1);
  }

  /**
   * Enregistre la variable donnée à la portée la plus haute
   */
  public void addToCurrentScope(String id, Type t) {
    HashMap<String, Type> currentScope = peek();
    if (currentScope != null) currentScope.put(id, t);
  }

  /**
   * Créé une nouvelle portée de variable 
   */
  public void newScope() {
    stack.add(new HashMap<String, Type>());
    // System.out.println("added scope, size="+stack.size());
  }

  /**
   * Supprime la portée la plus haute
   */
  public void removeScope() {
    if (stack.size() > 0)
      stack.remove(stack.size() - 1);
    // System.out.println("removed scope, size="+stack.size());
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
   * Retourne vrai si la variable "id" est globale (i.e elle est à la couche 0 du stack)
   * Returne faux si elle ne l'est pas ou si elle n'existe pas
   */
  public boolean isGlobal(String id) {
    if (stack.size() == 0) return false;
    HashMap<String, Type> currentScope;
    for (int i = stack.size() - 1; i >= 0; i--) {
      currentScope = stack.get(i);
      if (currentScope.containsKey(id)) {
        return i == 0;
      }
    }
    return false;
  }

  /**
   * Retourne vrai s'il existe une variable de nom "id" qui existe dans la portée actuelle (la plus haute)
   */
  public boolean existsInScope(String id) {
    HashMap<String, Type> currentScope = peek();
    if (currentScope == null) return false;
    return currentScope.containsKey(id);
  }

}
