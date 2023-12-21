package edu.hitsz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;
import java.util.Map;

import edu.hitsz.aircraft.Boss;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.bullet.BossBullet;
import edu.hitsz.bullet.EnemyBullet;
import edu.hitsz.bullet.HeroBullet;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.FireProp;
import edu.hitsz.prop.HpProp;
import edu.hitsz.prop.PropStore;
/**
 * 综合管理图片的加载，访问
 * 提供图片的静态访问方法
 *
 * @author hitsz
 */
public class ImageNormal {

    /**
     * 类名-图片 映射，存储各基类的图片 <br>
     * 可使用 CLASSNAME_IMAGE_MAP.get( obj.getClass().getName() ) 获得 obj 所属基类对应的图片
     */
    private static final Map<String, Bitmap> CLASSNAME_IMAGE_MAP = new HashMap<>();

    public static Bitmap BACKGROUND_IMAGE_SIMPLE;
    public static Bitmap BACKGROUND_IMAGE_NORMAL;
    public static Bitmap BACKGROUND_IMAGE_HARD;
    public static Bitmap HERO_IMAGE1;
    public static Bitmap HERO_BULLET_IMAGE;
    public static Bitmap ENEMY_BULLET_IMAGE;
    public static Bitmap BOSS_BULLET_IMAGE;
    public static Bitmap MOB_ENEMY_IMAGE;
    public static Bitmap Elite_ENEMY_IMAGE;
    public static Bitmap BOSS_ENEMY_IMAGE;
    public static Bitmap FIRE_PROP_IMAGE;
    public static Bitmap HP_PROP_IMAGE;
    public static Bitmap BOMB_PROP_IMAGE;

    
    public static void initImage(Context context){

        ImageNormal.BACKGROUND_IMAGE_NORMAL = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg_normal);

        ImageNormal.HERO_IMAGE1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.hero_normal1);

        ImageNormal.MOB_ENEMY_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.mob_normal);
        ImageNormal.Elite_ENEMY_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.elite_normal);
        ImageNormal.BOSS_ENEMY_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.boss_normal);

        ImageNormal.HERO_BULLET_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet_hero_normal);
        ImageNormal.ENEMY_BULLET_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet_enemy_normal);
        ImageNormal.BOSS_BULLET_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet_boss_normal);


        ImageNormal.FIRE_PROP_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.bullet_normal);
        ImageNormal.HP_PROP_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.blood_normal);
        ImageNormal.BOMB_PROP_IMAGE = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb_normal);



        CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), HERO_IMAGE1);
        CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), MOB_ENEMY_IMAGE);
        CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), Elite_ENEMY_IMAGE);
        CLASSNAME_IMAGE_MAP.put(Boss.class.getName(), BOSS_ENEMY_IMAGE);

        CLASSNAME_IMAGE_MAP.put(BossBullet.class.getName(), BOSS_BULLET_IMAGE);
        CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), HERO_BULLET_IMAGE);
        CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ENEMY_BULLET_IMAGE);

        CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), BOMB_PROP_IMAGE);
        CLASSNAME_IMAGE_MAP.put(FireProp.class.getName(), FIRE_PROP_IMAGE);
        CLASSNAME_IMAGE_MAP.put(HpProp.class.getName(), HP_PROP_IMAGE);

    }


    public static Bitmap get(String className){
        return CLASSNAME_IMAGE_MAP.get(className);
    }

    public static Bitmap get(Object obj){
        if (obj == null){
            return null;
        }
        return get(obj.getClass().getName());
    }


}
