package Proxy.v2;

import java.util.Random;

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
        new  tankProxyLog(new tankProxyTime(new Tank())).move();//该成了moveable 接口，因为time的代理也继承了moveable接口，这样子两层之间就可以嵌套了
    }
}


class tankProxyTime implements moveable{
    moveable tank;//将Tank换成moveable接口。这样子就可以不只是Tank类，可以是继承moveable接口的所有是实现类

    public tankProxyTime(Tank tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        long start = System.currentTimeMillis();
        tank.move();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}


class tankProxyLog implements moveable{
    moveable tank;

    public tankProxyLog(tankProxyTime tank) {
        this.tank = tank;
    }

    @Override
    public void move() {
        System.out.println("start log ");
        tank.move();
        System.out.println("end log");
    }
}


interface moveable {
    void move();
}