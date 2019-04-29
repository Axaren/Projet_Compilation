package ubordeaux.deptinfo.compilation.project.exceptions;

public class FunctionRedeclerationException extends RuntimeException {

  public FunctionRedeclerationException(String message) {
    super(message);
  }

  public FunctionRedeclerationException(String message, Throwable cause) {
    super(message, cause);
  }
}
