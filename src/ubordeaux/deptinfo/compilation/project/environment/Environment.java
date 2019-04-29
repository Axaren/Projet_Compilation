package ubordeaux.deptinfo.compilation.project.environment;

import java.util.HashMap;
import ubordeaux.deptinfo.compilation.project.type.Type;

public class Environment implements EnvironmentInt {

  private String label;
  private HashMap<String, Type> map;

  public Environment(String label) {
    this.label = label;
    map = new HashMap<String, Type>();
  }

  public void put(String key, Type symbol) {
    map.put(key, symbol);
  }

  public Type get(String key) {
    return map.get(key);
  }

  @Override
  public String getLabel() {
    return label;
  }
}
