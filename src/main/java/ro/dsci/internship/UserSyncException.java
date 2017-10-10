package ro.dsci.internship;

public class UserSyncException extends RuntimeException {
  private static final long serialVersionUID = 24495112465398085L;

  public UserSyncException() {
    super();
  }

  public UserSyncException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public UserSyncException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserSyncException(String message) {
    super(message);
  }

  public UserSyncException(Throwable cause) {
    super(cause);
  }
}
