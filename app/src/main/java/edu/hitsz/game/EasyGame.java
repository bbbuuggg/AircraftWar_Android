//package edu.hitsz.application;
//import static edu.hitsz.ImageManager.BACKGROUND;
//import static edu.hitsz.ImageManager.BACKGROUND_IMAGE_SIMPLE;
//
//
//
//public class Game_Simple extends Game{
//    public Game_Simple(){super();}
//
//    public void paint(Graphics g) {
//
//        // 绘制背景,图片滚动
//        BACKGROUND = BACKGROUND_IMAGE_SIMPLE;
//        super.paint(g);
//    }
//}

package edu.hitsz.game;

import android.content.Context;
import android.os.Handler;

import edu.hitsz.ImageManager;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.prop.PropStore;
import edu.hitsz.shoot.ShotGun;

public class EasyGame extends BaseGame{

    public EasyGame(Context context, Handler handler) {
        super(context,handler);
        this.backGround = ImageManager.BACKGROUND_IMAGE_SIMPLE;
        AbstractFlyingObject.setMode("simple");
        ShotGun.setMode("simple");
        PropStore.setMode("simple");
    }


}
