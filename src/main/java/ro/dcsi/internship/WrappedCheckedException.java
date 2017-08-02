package ro.dcsi.internship;

public class WrappedCheckedException extends RuntimeException {
  private static final long serialVersionUID = -1796029080899107110L;

  public WrappedCheckedException() {
    super();
  }

  public WrappedCheckedException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public WrappedCheckedException(String message, Throwable cause) {
    super(message, cause);
  }

  public WrappedCheckedException(String message) {
    super(message);
  }

  public WrappedCheckedException(Throwable cause) {
    super(cause);
  }
}
