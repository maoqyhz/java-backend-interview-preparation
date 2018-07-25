package xyz.furur.prepare.pattern.proxy;

/**
 * 静态代理
 *
 * @author Fururur
 * @create 2018-07-13-14:15
 */
public class UserDaoProxy implements IUserDao {
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        System.out.println("开始事务...");
        target.save();
        System.out.println("提交事务...");
    }
}
