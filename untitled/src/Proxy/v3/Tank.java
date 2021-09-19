package Proxy.v3;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;
//reflect是通过二进制字节码来分析类的属性和方法，而不用类的源码
//v2是静态的代理，有一个缺点就是说比如log的proxy想代理所有object，而不只是moveable接口的实现类，就用下面的动态的代理
public class Tank implements moveable{

    @Override
    public void move() {
        System.out.println("mxc is writing proxy code into the github");
        try{
            Thread.sleep(new Random().nextInt(10000));
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
    public  static void main(String args[]) {
    Tank tank = new Tank();
    System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");//生成类的代码的文件。通过反编译

    moveable m = (moveable) Proxy.newProxyInstance(Tank.class.getClassLoader(),
            new Class[]{moveable.class},
            new loghanler(tank)
            );

    m.move();
    }


}

class loghanler implements InvocationHandler{
    Tank tank;

    public loghanler(Tank tank) {
        this.tank = tank;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method" + method.getName() + "start");
        Object o = method.invoke(tank, args);
        System.out.println("method" + method.getName() + "end");
        return o;
    }
}


interface moveable {
    void move();
}