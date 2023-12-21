package edu.hitsz.aircraft;


import edu.hitsz.activity.MainActivity;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.Props;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.FireProp;
import edu.hitsz.prop.HpProp;
import edu.hitsz.shoot.Context1;
import edu.hitsz.shoot.Context1;
import edu.hitsz.shoot.ShootEnemyStraight;
import edu.hitsz.shoot.ShootStraight;
import edu.hitsz.shoot.Strategy;

import java.util.LinkedList;
import java.util.List;
public class EliteEnemy extends Enemy {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 1;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;
    private EliteEnemy eliteEnemy;
    private Context1 context = new Context1(new ShootEnemyStraight());

    public EliteEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public int getShootNum() {
        return shootNum;
    }

    public int getDirection() {
        return direction;
    }

    public int getPower() {
        return power;
    }

//    @Override
    /**
     * 通过射击产生子弹
     * @return 射击出的子弹List
     */
    public List<AbstractBullet> shoot(AbstractAircraft aircraft) {
        return context.executeStrategy(aircraft);
    }

    @Override
    public void setStrategy(Strategy strategy) {

    }
    public void update(int number) {
        this.vanish();
    }
}