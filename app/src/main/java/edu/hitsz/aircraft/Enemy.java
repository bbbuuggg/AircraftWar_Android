package edu.hitsz.aircraft;

import edu.hitsz.activity.MainActivity;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.BombUpdate;
import edu.hitsz.prop.Props;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.FireProp;
import edu.hitsz.prop.HpProp;
import edu.hitsz.shoot.ShootEnemyStraight;

import java.util.LinkedList;
import java.util.List;

/**
 * 精英敌机，游戏玩家操控
 * @author hitsz
 */
public abstract class Enemy extends AbstractAircraft implements BombUpdate {


//    /**
//     * @param locationX 英雄机位置x坐标
//     * @param locationY 英雄机位置y坐标
//     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
//     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
//     * @param hp    初始生命值
//     */

    public Enemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= MainActivity.screenHeight) {
            vanish();
        }
    }
    public abstract List<AbstractBullet> shoot(AbstractAircraft aircraft);
    public abstract void update(int number);
}