package edu.hitsz.aircraft;

import edu.hitsz.activity.MainActivity;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.shoot.*;
import java.util.List;
public class Boss extends Enemy {

    /**攻击方式 */

    /**
     * 子弹一次发射数量
     */
    private int shootNum = 3;

    /**
     * 子弹伤害
     */
    private int power = 30;

    /**
     * 子弹射击方向 (向上发射：1，向下发射：-1)
     */
    private int direction = 1;
    private Context1 context = new Context1(new ShotEnemyGun());
    private Boss boss;

    public Boss(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    public void forward() {
        locationX += speedX;
        locationY += speedY;
        if (locationX <= 0 || locationX >= MainActivity.screenWidth) {
            // 横向超出边界后反向
            speedX = -speedX;
        }
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

    public List<AbstractBullet> shoot(AbstractAircraft aircraft) {
        return context.executeStrategy(aircraft);
    }

    @Override
    public void setStrategy(Strategy strategy) {}
    public void update(int number){}
}