package xyz.furur.prepare.pattern.proxy;

/**
 * 实现
 *
 * @author Fururur
 * @create 2018-07-13-14:14
 */
public class UserDao implements IUserDao {
    @Override
    public void save() {
        System.out.println("----- save -----");
    }
}
