package xyz.furur.prepare.pattern.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 实现动态代理
 *
 * @author Fururur
 * @create 2018-07-13-15:39
 */
public class CglibDynamicProxy implements MethodInterceptor {
    private Object target;

    public CglibDynamicProxy(Object target) {
        this.target = target;
    }

    //给目标对象创建一个代理对象
    public Object getProxyInstance() {
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务...");
        Object returnValue = method.invoke(target, objects);
        System.out.println("提交事务...");
        return returnValue;
    }
}
