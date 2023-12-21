package edu.hitsz.aircraft;

import edu.hitsz.activity.MainActivity;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.Props;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.FireProp;
import edu.hitsz.prop.HpProp;
import edu.hitsz.shoot.Strategy;

import java.util.LinkedList;
import java.util.List;
public class MobEnemy extends Enemy {

    /**
     * 普通敌机
     * 不可射击
     *
     * @author hitsz
     */

    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public List<AbstractBullet> shoot(AbstractAircraft aircraft) {
        return null;
    }

    @Override
    public void setStrategy(Strategy strategy) {

    }
    public void update(int number) {
        this.vanish();
    }


//    public List<Props> dropbingo() {
//        return null;
//    }

}
//package edu.hitsz.aircraft;
//
//
//import java.util.LinkedList;
//import java.util.List;
//
//import edu.hitsz.bullet.AbstractBullet;
//
//
///**
// * 普通敌机
// * 不可射击
// *
// * @author hitsz
// */
//public class MobEnemy extends AbstractAircraft {
//
//    public MobEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
//        super(locationX, locationY, speedX, speedY, hp);
//    }
//
//
//    @Override
//    public List<AbstractBullet> shoot() {
//        return new LinkedList<>();
//    }
//
//}
