package ubordeaux.deptinfo.compilation.project.exceptions;

public class FunctionRedefinitionException extends RuntimeException {

  public FunctionRedefinitionException(String message) {
    super(message);
  }

  public FunctionRedefinitionException(String message, Throwable cause) {
    super(message, cause);
  }
}
