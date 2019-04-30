package ubordeaux.deptinfo.compilation.project.exceptions;

public class FunctionRedeclarationException extends RuntimeException {

  public FunctionRedeclarationException(String message) {
    super(message);
  }

  public FunctionRedeclarationException(String message, Throwable cause) {
    super(message, cause);
  }
}
