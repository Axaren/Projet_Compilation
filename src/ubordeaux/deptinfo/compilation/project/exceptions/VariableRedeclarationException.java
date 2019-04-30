package ubordeaux.deptinfo.compilation.project.exceptions;

public class VariableRedeclarationException extends RuntimeException {

  public VariableRedeclarationException(String message, Throwable cause) {
    super(message, cause);
  }

  public VariableRedeclarationException(String message) {
    super(message);
  }
}
