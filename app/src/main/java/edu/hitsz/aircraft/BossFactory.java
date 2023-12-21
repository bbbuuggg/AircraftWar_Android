package edu.hitsz.aircraft;

import edu.hitsz.activity.MainActivity;
import edu.hitsz.ImageManager;

public class BossFactory implements EnemyFactory{


        public Boss CreateEnemy(String mode) {

            switch (mode) {
                case "simple":
                    return new Boss(
                            (int) (Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                            (int) (Math.random() * MainActivity.screenHeight * 0.05),
                            3,
                            0,
                            200);
                case "normal":
                    return new Boss(
                            (int) (Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                            (int) (Math.random() * MainActivity.screenHeight * 0.05),
                            2,
                            0,
                            400);

                default:
                    return new Boss(
                            (int) (Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth())),
                            (int) (Math.random() * MainActivity.screenHeight * 0.05),
                            1,
                            0,
                            600);
            }
        }
}