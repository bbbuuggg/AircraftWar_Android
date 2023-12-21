package edu.hitsz.aircraft;

import edu.hitsz.ImageManager;
import edu.hitsz.activity.MainActivity;


public class EliteFactory implements EnemyFactory{
    static int speedX = -1;
    public EliteEnemy CreateEnemy(String mode)
    {
        speedX = -1*speedX;
        switch (mode){
            case "simple":
                return new EliteEnemy(
                        (int) (Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                        (int) (Math.random() * MainActivity.screenHeight * 0.05),
                        speedX,
                        5,
                        30);
            case "normal":
                return new EliteEnemy(
                        (int) (Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                        (int) (Math.random() * MainActivity.screenHeight * 0.05),
                        speedX,
                        3,
                        30);
            default:
                return new EliteEnemy(
                        (int) (Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                        (int) (Math.random() * MainActivity.screenHeight * 0.05),
                        speedX,
                        2,
                        30);
        }
    }
}
