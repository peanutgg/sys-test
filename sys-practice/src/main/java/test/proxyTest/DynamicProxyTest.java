package test.proxyTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk 实现动态代理
 */
public class DynamicProxyTest {
    interface IHello{
        void sayHello();
    }

    static class Hello implements  IHello{

        public void sayHello() {
            System.out.println("hello world");
        }
    }

    static class DynamicProxy implements InvocationHandler{

        Object originalObj;

        Object bind(Object originalObj){
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
        }
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println("welcome");

            return method.invoke(originalObj, args);
        }

        public static void main(String[] args) {

            IHello iHello = (IHello) new DynamicProxy().bind(new Hello());
            iHello.sayHello();

        }

    }
}
