package ubordeaux.deptinfo.compilation.project.exceptions;

public class UnknownVariableException extends RuntimeException {

  public UnknownVariableException(String message) {
    super(message);
  }

  public UnknownVariableException(String message, Throwable cause) {
    super(message, cause);
  }
}
