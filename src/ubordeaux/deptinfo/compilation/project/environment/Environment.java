package ubordeaux.deptinfo.compilation.project.environment;

import java.util.HashMap;
import ubordeaux.deptinfo.compilation.project.main.ClonableSymbol;

public class Environment implements EnvironmentInt {

  private String label;
  private HashMap<String, ClonableSymbol> map;

  public Environment(String label) {
    this.label = label;
    map = new HashMap<String, ClonableSymbol>();
  }

  public void put(String key, ClonableSymbol symbol) {
    map.put(key, symbol);
  }

  public ClonableSymbol get(String key) {
    return map.get(key);
  }

  @Override
  public String getLabel() {
    return label;
  }
}
