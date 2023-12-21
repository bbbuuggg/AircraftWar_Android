package edu.hitsz.aircraft;

import edu.hitsz.ImageManager;
import edu.hitsz.activity.MainActivity;


public class MobFactory  implements EnemyFactory{
    static int speedX = 1;
    public MobEnemy CreateEnemy(String mode)
    {
        speedX = -speedX;
        switch (mode){
            case "simple":
                return new MobEnemy(
                        (int) (Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                        (int) (Math.random() * MainActivity.screenHeight * 0.05),
                        speedX,
                        5,
                        30);
            case "normal":
                return new MobEnemy(
                        (int) (Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                        (int) (Math.random() * MainActivity.screenHeight * 0.05),
                        speedX,
                        4,
                        30);
            default:
                return new MobEnemy(
                        (int) (Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                        (int) (Math.random() * MainActivity.screenHeight * 0.05),
                        speedX,
                        3,
                        30);
        }
    }
}
