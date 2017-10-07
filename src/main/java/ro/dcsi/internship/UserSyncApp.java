package ro.dcsi.internship;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserSyncApp {
  private static final Logger logger = LoggerFactory.getLogger(UserSyncApp.class);
  public void exportAndLogAnyException(UserDao srcDao, UserDao dstDao) {
    String message = "Exporting users \n\tfrom " + srcDao + "\n\tto   " + dstDao;
    logger.info(message + " ...");
    try {
      dstDao.save(srcDao.load().toArray(new User[0]));
      logger.info(message + " done.");
    } catch (Throwable e) {
      logger.warn(message + " failed with an exception "+e.getMessage());
      throw e;
    }
  }
}
