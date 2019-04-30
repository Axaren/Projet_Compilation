package ubordeaux.deptinfo.compilation.project.exceptions;

public class TypeRedeclationException extends RuntimeException {

  public TypeRedeclationException(String message) {
    super(message);
  }

  public TypeRedeclationException(String message, Throwable cause) {
    super(message, cause);
  }
}
