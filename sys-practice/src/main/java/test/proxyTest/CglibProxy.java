package test.proxyTest;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cg lib 实现动态代理
 */
public class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz) {

        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("前置代理");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("后置代理");

        return null;
    }

    static class SayHello{
        public void say(){

            System.out.println("hello world");

        }
    }


    static class DoCGLib{
        public static void main(String[] args) {

            CglibProxy cglibProxy = new CglibProxy();
            SayHello proxyImpl = (SayHello) cglibProxy.getProxy(SayHello.class);
            proxyImpl.say();
        }
    }

}
