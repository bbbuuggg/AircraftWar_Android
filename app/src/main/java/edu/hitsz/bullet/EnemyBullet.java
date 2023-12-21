package edu.hitsz.bullet;

/**
 * @Author hitsz
 */
public class EnemyBullet extends AbstractBullet{

    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
        super(locationX, locationY, speedX, speedY, power);
    }
    public void update(int number) {
        this.vanish();
    }

}
//package edu.hitsz.bullet;
//
///**
// * @Author hitsz
// */
//public class EnemyBullet extends AbstractBullet {
//
//
//    public EnemyBullet(int locationX, int locationY, int speedX, int speedY, int power) {
//        super(locationX, locationY, speedX, speedY, power);
//    }
//
//}
