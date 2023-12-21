//package edu.hitsz.application;
//import static edu.hitsz.ImageManager.BACKGROUND;
//import static edu.hitsz.ImageManager.BACKGROUND_IMAGE_HARD;
//
//public class Game_Hard extends Game{
//    public Game_Hard(){super();}
//    public void paint(Graphics g) {
//
//        // 绘制背景,图片滚动
//        BACKGROUND = BACKGROUND_IMAGE_HARD;
//        super.paint(g);
//    }
//}
package edu.hitsz.game;

import static edu.hitsz.ImageManager.*;

import android.content.Context;
import android.os.Handler;

import edu.hitsz.ImageHard;
import edu.hitsz.ImageManager;
import edu.hitsz.ImageNormal;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.prop.PropStore;
import edu.hitsz.shoot.ShotGun;

public class HardGame extends BaseGame{
    public HardGame(Context context, Handler handler) {
        super(context,handler);
        this.backGround = BACKGROUND_IMAGE_HARD;
        ImageHard.initImage(context);
        AbstractFlyingObject.setMode("hard");
        ShotGun.setMode("hard");
        PropStore.setMode("hard");
    }
    public void action() {
        eliteH = 0.2;
        mode = "hard";
        bossH = 50;
        enemyMaxNumber = 8;
        eliteMaxNumber1 = 5;
        super.action();
    }

    @Override
    public void draw() {
        super.draw();
        HERO_IMAGE = HERO_IMAGE_HARD;
    }
}
