package ubordeaux.deptinfo.compilation.project.environment;

import ubordeaux.deptinfo.compilation.project.main.ClonableSymbol;

public interface EnvironmentInt {

  void put(String key, ClonableSymbol symbol);

  ClonableSymbol get(String key);

  String getLabel();
}
