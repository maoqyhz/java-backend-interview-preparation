package xyz.furur.prepare.pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 * @author Fururur
 * @create 2018-07-13-14:28
 */
public class DynamicProxy {
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("开始事务...");
                    Object returnValue = method.invoke(target, args);
                    System.out.println("提交事务...");
                    return returnValue;
                });
    }
}
