package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.shoot.ShootStraight;
import edu.hitsz.shoot.ShotGun;

public class FireProp extends Props{
    public FireProp(int locationX, int locationY, int speedX, int speedY, int propsID,String mode) {
        super(locationX, locationY, speedX, speedY, propsID,mode);
    }


    public void supply(AbstractAircraft heroAircraft){
        ShotGun shotGun = new ShotGun();
        System.out.print("FireSupply Active!\n");
        ShootStraight shootStraight = new ShootStraight();
        Runnable start = () ->{
            try {
                heroAircraft.setStrategy(shotGun);
                heroAircraft.shoot(heroAircraft);
                System.out.println("check1");
                Thread.sleep(6000);
                System.out.println("check1 sleep");
                heroAircraft.setStrategy(shootStraight);
                heroAircraft.shoot(heroAircraft);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        };
        // 英雄射击
        new Thread(start).start();
//        new Thread(shootStraight).start();
    }

}