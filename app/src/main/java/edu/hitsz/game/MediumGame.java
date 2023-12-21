//package edu.hitsz.application;
//import static edu.hitsz.ImageManager.BACKGROUND;
//import static edu.hitsz.ImageManager.BACKGROUND_IMAGE_NORMAL;
//
//public class Game_Normal extends Game{
//
//    public Game_Normal(){super();}
//
//    public void paint(Graphics g) {
//
//        // 绘制背景,图片滚动
//        BACKGROUND = BACKGROUND_IMAGE_NORMAL;
//        super.paint(g);
//    }
//}

package edu.hitsz.game;

import static edu.hitsz.ImageManager.*;

import android.content.Context;
import android.os.Handler;

import edu.hitsz.ImageManager;
import edu.hitsz.ImageNormal;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.prop.PropStore;
import edu.hitsz.shoot.ShotGun;

public class MediumGame extends BaseGame{
    public MediumGame(Context context, Handler handler) {
        super(context,handler);
        this.backGround = ImageManager.BACKGROUND_IMAGE_NORMAL;
        ImageNormal.initImage(context);
        AbstractFlyingObject.setMode("normal");
        ShotGun.setMode("normal");
        PropStore.setMode("normal");
    }
    public void action() {
        eliteH = 0.4;
        mode = "normal";
        bossH = 100;
        enemyMaxNumber = 8;
        eliteMaxNumber1 = 5;
        super.action();
    }

    @Override
    public void draw() {
        super.draw();
        HERO_IMAGE = HERO_IMAGE_NORMAL;
    }
}
