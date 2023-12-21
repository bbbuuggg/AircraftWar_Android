//package edu.hitsz.aircraft;
//
//import edu.hitsz.ImageManager;
//import edu.hitsz.activity.MainActivity;
//import edu.hitsz.bullet.BaseBullet;
//import edu.hitsz.shoot.*;
//
//import java.util.List;
//
///**
// * 英雄飞机，游戏玩家操控
// * @author hitsz
// */
//public class HeroAircraft extends AbstractAircraft {
//
//    /**攻击方式 */
//
//    /**
//     * 子弹一次发射数量
//     */
//    int shootNum = 1;
//
//    /**
//     * 子弹伤害
//     */
//    int power = 30;
//
//    /**
//     * 子弹射击方向 (向上发射：-1，向下发射：1)
//     */
//    int direction = -1;
//    private Context context = new Context(new ShootStraight());
//
//    /**
//     * @param locationX 英雄机位置x坐标
//     * @param locationY 英雄机位置y坐标
//     * @param speedX 英雄机射出的子弹的基准速度（英雄机无特定速度）
//     * @param speedY 英雄机射出的子弹的基准速度（英雄机无特定速度）
//     * @param hp    初始生命值
//     */
////    public HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
////        super(locationX, locationY, speedX, speedY, hp);
////    }
//    private volatile static HeroAircraft heroAircraft;
//
//    private HeroAircraft(int locationX, int locationY, int speedX, int speedY, int hp) {
//        super(locationX, locationY, speedX, speedY, hp);
//    }
//
//    ;
//
//    public static HeroAircraft getHeroAircraft() {
//
//        if (heroAircraft == null) {
//            synchronized (HeroAircraft.class) {
//                if (heroAircraft == null) {
//                    heroAircraft = new HeroAircraft(MainActivity.screenWidth / 2,
//                            MainActivity.screenHeight - ImageManager.HERO_IMAGE.getHeight(),
//                            0, 0, 1000);
//                }
//
//            }
//        }
//        return heroAircraft;
//    }
//
//    @Override
//    public void forward() {
//        // 英雄机由鼠标控制，不通过forward函数移动
//    }
//
//    public void increaseHp(int increase) {
//        if (hp + increase < 1000) {
//            hp += increase;
////            vanish();
//        } else if (hp + increase >= 1000) {
//            hp = 1000;
//
//        }
//    }
//
//    public int getShootNum() {
//        return shootNum;
//    }
//
//    public int getDirection() {
//        return direction;
//    }
//
//    public int getPower() {
//        return power;
//    }
//
//    public void setStrategy(Strategy strategy){
//        if(strategy.getClass() == ShootStraight.class){
//            context.setStrategy(new ShootStraight());
//        } else if (strategy.getClass() == ShotGun.class) {
//            context.setStrategy(new ShotGun());
//        }
//
//    }
//    @Override
//    public List<BaseBullet> shoot(AbstractAircraft aircraft) {
//        return context.executeStrategy(aircraft);
//    }
//}

package edu.hitsz.aircraft;

import java.util.LinkedList;
import java.util.List;

import edu.hitsz.ImageManager;
import edu.hitsz.activity.MainActivity;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.shoot.*;

/**
 * 英雄飞机，游戏玩家操控，遵循单例模式（singleton)
 * 【单例模式】
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {

/*
        volatile 修饰，
        singleton = new Singleton() 可以拆解为3步：
        1、分配对象内存(给singleton分配内存)
        2、调用构造器方法，执行初始化（调用 Singleton 的构造函数来初始化成员变量）。
        3、将对象引用赋值给变量(执行完这步 singleton 就为非 null 了)。
        若发生重排序，假设 A 线程执行了 1 和 3 ，还没有执行 2，B 线程来到判断 NULL，B 线程就会直接返回还没初始化的 instance 了。
        volatile 可以避免重排序。
    */
    /** 英雄机对象单例 */
    private volatile static HeroAircraft heroAircraft;

    /**
     * 单例模式：私有化构造方法
     */
    private HeroAircraft() {
        super(MainActivity.screenWidth / 2, MainActivity.screenHeight - ImageManager.HERO_IMAGE.getHeight(),
                0, -5, 1000);
        this.shootNum = 1;
        this.power = 30;
        this.direction = -1;
        this.rate = 1.5;
    }
    private Context1 context = new Context1(new ShootStraight());

    /**
     * 通过单例模式获得初始化英雄机
     * 【单例模式：双重校验锁方法】
     * @return 英雄机单例
     */
    public static HeroAircraft getHeroAircraft(){
        if (heroAircraft == null) {
            synchronized (HeroAircraft.class) {
                if (heroAircraft == null) {
                    heroAircraft = new HeroAircraft();
                }
            }
        }
        return heroAircraft;
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }
    public void increaseHp(int increase) {
        if (hp + increase < 1000) {
            hp += increase;
//            vanish();
        } else if (hp + increase >= 1000) {
            hp = 1000;

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

    public void setStrategy(Strategy strategy){
        if(strategy.getClass() == ShootStraight.class){
            context.setStrategy(new ShootStraight());
        } else if (strategy.getClass() == ShotGun.class) {
            context.setStrategy(new ShotGun());
        }

    }
    @Override
    public List<AbstractBullet> shoot(AbstractAircraft aircraft) {
        return context.executeStrategy(aircraft);
    }
}
