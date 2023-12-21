package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class ShotEnemyGun implements Strategy{
    public List<AbstractBullet> shoot(AbstractAircraft aircraft){
        List<AbstractBullet> res = new LinkedList<>();

        int x = aircraft.getLocationX();
        int y = aircraft.getLocationY() + aircraft.getDirection() * 2;
        int speedX = 0;
        int speedY = aircraft.getSpeedY() + aircraft.getDirection() * 5;
        AbstractBullet bullet;
        for (int i = 0; i < 3; i++) {
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            bullet = new EnemyBullet(x + (i * 2 - 3 + 1) * 10, y, speedX+ (i * 2 - 3 + 1), speedY, aircraft.getPower());
            res.add(bullet);
        }
        return res;
    }
}
