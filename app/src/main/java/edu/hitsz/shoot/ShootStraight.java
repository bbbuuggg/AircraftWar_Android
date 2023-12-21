package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class ShootStraight implements Strategy{
    public List<AbstractBullet> shoot(AbstractAircraft aircraft){
        List<AbstractBullet> res = new LinkedList<>();
        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.getDirection()*2;
        int speedX = 0;
        int speedY = aircraft.getSpeedY() + aircraft.getDirection()*5;
        AbstractBullet bullet;
        for(int i=0; i<aircraft.getShootNum(); i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new HeroBullet(x + (i*2 - aircraft.getShootNum() + 1)*10, y, speedX, speedY, aircraft.getPower());
            res.add(bullet);
        }
        return res;
    }

}
