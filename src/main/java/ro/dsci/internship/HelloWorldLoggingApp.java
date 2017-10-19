package ro.dsci.internship;

public class HelloWorldLoggingApp {
  private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HelloWorldLoggingApp.class);
  public static void main(String[] args) {
    logger.info("Hello World",new RuntimeException());
    logger.warn("this is a warning");
  }
}
