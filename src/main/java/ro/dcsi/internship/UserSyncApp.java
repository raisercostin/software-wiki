package ro.dcsi.internship;

public class UserSyncApp {
    public void export(UserDao srcDao, UserDao dstDao) {
        if (srcDao.getClass().isInstance(BeanBasedUserDao.class) && dstDao.getClass().isInstance(BeanBasedUserDao.class)){

        }else if (srcDao.getClass().isInstance(ForgerockUserDao.class) && dstDao.getClass().isInstance(ForgerockUserDao.class)){

        }else if (srcDao.getClass().isInstance(BeanBasedUserDao.class) && dstDao.getClass().isInstance(ForgerockUserDao.class)){

        }else if (srcDao.getClass().isInstance(ForgerockUserDao.class) && dstDao.getClass().isInstance(BeanBasedUserDao.class)){

        }
    }
}
