package ubordeaux.deptinfo.compilation.project.exceptions;

public class UnknownTypeException extends RuntimeException {

  public UnknownTypeException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnknownTypeException(String message) {
    super(message);
  }
}
