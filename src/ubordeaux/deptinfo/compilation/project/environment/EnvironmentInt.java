package ubordeaux.deptinfo.compilation.project.environment;

import ubordeaux.deptinfo.compilation.project.type.Type;

public interface EnvironmentInt {

  void put(String key, Type symbol);

  Type get(String key);

  String getLabel();
}
