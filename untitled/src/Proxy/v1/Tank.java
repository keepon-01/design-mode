package Proxy.v1;



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
        new tankProxyTime(new Tank()).move();
    }
}

//前一个版本是为了解决这个计时的问题是通过tank2 extends tank解决的，但是继承这个方式耦合性太高。
class tankProxyTime implements moveable{
    Tank tank;

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

interface moveable {
    void move();
}