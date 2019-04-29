package ubordeaux.deptinfo.compilation.project.exceptions;

public class UnknownFunctionException extends RuntimeException {

  public UnknownFunctionException(String message) {
    super(message);
  }

  public UnknownFunctionException(String message, Throwable cause) {
    super(message, cause);
  }
}
