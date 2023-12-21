package edu.hitsz.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.hitsz.ImageManager;
import edu.hitsz.Player.Player;
import edu.hitsz.Player.PlayerDao;
import edu.hitsz.Player.PlayerDaoImpl;
import edu.hitsz.activity.MainActivity;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.Boss;
import edu.hitsz.aircraft.BossFactory;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.aircraft.EliteFactory;
import edu.hitsz.aircraft.Enemy;
import edu.hitsz.aircraft.EnemyFactory;
import edu.hitsz.aircraft.HeroAircraft;
import edu.hitsz.aircraft.MobEnemy;
import edu.hitsz.aircraft.MobFactory;
import edu.hitsz.basic.AbstractFlyingObject;
import edu.hitsz.bullet.AbstractBullet;
//import edu.hitsz.music.MusicBox;
import edu.hitsz.music.MusicService;
import edu.hitsz.music.SoundPoo;
import edu.hitsz.prop.BombProp;
import edu.hitsz.prop.BombUpdate;
import edu.hitsz.prop.PropStore;
import edu.hitsz.prop.Props;

/**
 * 游戏逻辑抽象基类，遵循模板模式，action() 为模板方法
 * 包括：游戏主面板绘制逻辑，游戏执行逻辑。
 * 子类需实现抽象方法，实现相应逻辑
 * @author hitsz
 */
public abstract class BaseGame extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    public static final String TAG = "BaseGame";
    boolean mbLoop = false; //控制绘画线程的标志位
    private SurfaceHolder mSurfaceHolder;
    private Canvas canvas;  //绘图的画布
    private Paint mPaint;
    private Handler handler;

    //点击屏幕位置
    float clickX = 0, clickY=0;

    private int backGroundTop = 0;

    /**
     * 背景图片缓存，可随难度改变
     */
    protected Bitmap backGround;



    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 16;

    private final HeroAircraft heroAircraft;

    protected final List<Enemy> enemyAircrafts;

    private final List<AbstractBullet> heroBullets;

    private final List<AbstractBullet> bossBullets;
    private final List<AbstractBullet> enemyBullets;

    private List<BombUpdate> watchList;
    private final List<Props> props;

    private boolean gameOverFlag;
    protected String mode = "simple";
    protected int enemyMaxNumber = 5;
    protected   int eliteMaxNumber1 = 2;
    protected  int bossH = 300;
    public static int score = 0;
    private int time = 0;
    private SoundPoo soundPoo;
    protected  double eliteH = 0.6;


    /**
     * 周期（ms)
     * 控制英雄机射击周期，默认值设为简单模式
     */
    private int cycleDuration = 600;
    private int cycleTime = 0;
    private boolean boss = false;

    boolean On = MainActivity.getSinging();


    public BaseGame(Context context, Handler handler){
        super(context);
        soundPoo = new SoundPoo(context);
        this.handler = handler;
        mbLoop = true;
        mPaint = new Paint();  //设置画笔
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        this.setFocusable(true);
        ImageManager.initImage(context);

        // 初始化英雄机
        heroAircraft = HeroAircraft.getHeroAircraft();
        heroAircraft.setHp(1000);
        props = new LinkedList<>();
        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        bossBullets = new LinkedList<>();
        heroController();
    }
    /**
     * 游戏启动入口，执行游戏逻辑
     */
    public void action() {
        System.out.println("模式\t" + mode);
        PlayerDao playerDao = new PlayerDaoImpl();

        MusicService musicService = new MusicService();
        //new Thread(new Runnable() {
        Runnable task = () -> {

                time += timeInterval;
            if (mode == "normal" || mode == "harder"&&time >= 10000){
                if (time % 10000 == 0){
                    eliteH -= 0.01;
                    if (timeInterval>10){
                        timeInterval = timeInterval/2;
                    }
                    System.out.println("难度提升!!!!!!!!\n" +
                            "精英敌机产生概率+0，01\t"+"敌机属性加强！\t");
                }
            }

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                if (enemyAircrafts.size() < enemyMaxNumber) {
                    Log.d("BaseGame","produceEnemy");
                    EnemyFactory enemyFactory;
                    Enemy enemy;
                    if(Math.random()<0.5){
                        enemyFactory = new MobFactory();
                    }
                    else {
                        enemyFactory = new EliteFactory();
                    }
                    enemy = enemyFactory.CreateEnemy(mode);
                    enemyAircrafts.add(enemy);
                    if(score%100 == 0 && score != 0&& boss==false) {
                        enemyFactory = new BossFactory();
                        enemy = enemyFactory.CreateEnemy(mode);
                        enemyAircrafts.add(enemy);
                        boss = true;
                        if (On) {
//                            soundPoo.Bgm_BossStart();
                        }
                    }
                }
//                   enemyAircrafts.add(new MobEnemy(
//                            (int) ( Math.random() * (MainActivity.screenWidth - ImageManager.MOB_ENEMY_IMAGE.getWidth()))*1,
//                            (int) (Math.random() * MainActivity.screenHeight * 0.2),
//                            0,
//                            10,
//                            30
//                    ));
//                }
                shootAction();
            }
                //道具
                propsMoveAction();
                // 子弹移动
                bulletsMoveAction();
                // 飞机移动
                aircraftsMoveAction();

                // 撞击检测
                try {

                    crashCheckAction();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 后处理
                postProcessAction();

                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            // 游戏结束检查英雄机是否存活
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
//                executorService.shutdown();
                gameOverFlag = true;
                System.out.println("Game Over!");
                if (boss&&On){
//                    soundPoo.Bgm_BossStop();
                }
              musicService.stopMusic();
                if (On) {
                    soundPoo.Game_Over();
                }
                /**
                 * 玩家链表 + 窗口弹出
                 *
                 */

            }
        };
        new Thread(task).start();
    }

    public void heroController(){
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                clickX = motionEvent.getX();
                clickY = motionEvent.getY();
                heroAircraft.setLocation(clickX, clickY);

                if ( clickX<0 || clickX> MainActivity.screenWidth || clickY<0 || clickY>MainActivity.screenHeight){
                    // 防止超出边界
                    return false;
                }
                return true;
            }
        });
    }


    private void shootAction() {
        // TODO 敌机射击
        synchronized (enemyAircrafts) {
        for (AbstractAircraft enemyAircraft: enemyAircrafts) {
                if (enemyAircraft.getClass() == EliteEnemy.class) {
                    enemyBullets.addAll(enemyAircraft.shoot(enemyAircraft));
                } else if (enemyAircraft.getClass() == Boss.class) {
                    bossBullets.addAll(enemyAircraft.shoot(enemyAircraft));
                }
            }
        }

        // 英雄射击
        heroBullets.addAll(heroAircraft.shoot(heroAircraft));
    }

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private void bulletsMoveAction() {

        for (AbstractBullet bullet : heroBullets) {
                bullet.forward();
            }

        synchronized (enemyBullets) {
        for (AbstractBullet bullet : enemyBullets) {
                bullet.forward();
            }
        }
        synchronized (bossBullets) {
        for (AbstractBullet bullet : bossBullets) {
                bullet.forward();
            }
        }
    }

    private void aircraftsMoveAction() {
        synchronized (enemyAircrafts) {
        for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                enemyAircraft.forward();
            }
        }
    }

    private void propsMoveAction() {
        synchronized (props) {
            for (Props prop : props) {
                prop.forward();
            }
        }
    }
    /**
     * 碰撞检测：
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() throws InterruptedException {
        PropStore propstore = new PropStore();
        // TODO 敌机子弹攻击英雄
        synchronized (enemyBullets) {
            for (AbstractBullet bullet : enemyBullets) {
                if (bullet.notValid()) {
                    continue;
                }

                if (heroAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    heroAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();


                }
            }
        }
        synchronized (bossBullets) {
            for (AbstractBullet bullet : bossBullets) {
                if (bullet.notValid()) {
                    continue;
                }

                if (heroAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                    heroAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();


                }
            }
        }
        // 英雄子弹攻击敌机
        synchronized (heroBullets) {
            for (AbstractBullet bullet : heroBullets) {
                if (bullet.notValid()) {
                    continue;
                }
                synchronized (enemyAircrafts) {
                    for (AbstractAircraft enemyAircraft : enemyAircrafts) {
                        synchronized (enemyAircraft) {
                            if (enemyAircraft.notValid()) {
                                // 已被其他子弹击毁的敌机，不再检测
                                // 避免多个子弹重复击毁同一敌机的判定
                                continue;
                            }
                            if (enemyAircraft.crash(bullet)) {
                                // 敌机撞击到英雄机子弹
                                // 敌机损失一定生命值
                                if (On) {
                                    soundPoo.Bgm_Bullet_HitStart();
                                }
                                enemyAircraft.decreaseHp(bullet.getPower());
                                bullet.vanish();

                                if (enemyAircraft.notValid()) {
                                    // TODO 获得分数
                                    score += 10;
                                    if (enemyAircraft.getClass() == EliteEnemy.class) {
                                        props.addAll(propstore.dropProps(enemyAircraft, false));
                                    } else if (enemyAircraft.getClass() == Boss.class) {
                                        props.addAll(propstore.dropProps(enemyAircraft, true));
                                        boss = false;
                                        if (On) {
//                                soundPoo.Bgm_BossStop();
                                        }
                                    }
                                }
                            }

                            // 英雄机 与 敌机 相撞，均损毁
                            if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                                enemyAircraft.vanish();
                                heroAircraft.decreaseHp(Integer.MAX_VALUE);
                            }

                        }
                    }
                }
            }
        }

        // Todo: 我方获得道具，道具生效
        watchList =new ArrayList<>();
        synchronized (props) {
            for (Props props : props) {
                if (props.notValid()) {
                    continue;
                }
                if (heroAircraft.notValid()) {
                    continue;
                }
                if (heroAircraft.crash(props)) {
                    if(props.getClass() == BombProp.class){
                        watchList.addAll(enemyBullets);
                        watchList.addAll(bossBullets);
                        watchList.addAll(enemyAircrafts);
                        ((BombProp) props).addWatcher(watchList);
                    }
                    props.supply(heroAircraft);
                    if(props.getClass() == BombProp.class){
                        score = score + ((BombProp) props).getExScore();

                    }
                    postProcessAction();
                    if (On) {
                        soundPoo.Get_Supply();
                    }
                    props.vanish();


                }
            }
        }
    }
    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    private void postProcessAction() {
        synchronized (enemyBullets) {
            enemyBullets.removeIf(AbstractFlyingObject::notValid);
        }
        synchronized (heroBullets) {
            heroBullets.removeIf(AbstractFlyingObject::notValid);
        }
        synchronized (bossBullets) {
            bossBullets.removeIf(AbstractFlyingObject::notValid);
        }
        synchronized (enemyAircrafts) {
            enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        }
        synchronized (props) {
            props.removeIf(AbstractFlyingObject::notValid);
        }
        if (heroAircraft.notValid()) {
            gameOverFlag = true;
            mbLoop = false;
            Log.i(TAG, "heroAircraft is not Valid");

        }

    }


    public void draw() {
        canvas = mSurfaceHolder.lockCanvas();
        if(mSurfaceHolder == null || canvas == null){
            return;
        }

        //绘制背景，图片滚动
        canvas.drawBitmap(backGround,0,this.backGroundTop-backGround.getHeight(),mPaint);
        canvas.drawBitmap(backGround,0,this.backGroundTop,mPaint);
        backGroundTop +=1;
        if (backGroundTop == MainActivity.screenHeight)
            this.backGroundTop = 0;

        //先绘制子弹，后绘制飞机
        paintImageWithPositionRevised(enemyBullets); //敌机子弹


        paintImageWithPositionRevised(heroBullets);  //英雄机子弹
        paintImageWithPositionRevised(bossBullets);  //英雄机子弹

        paintImageWithPositionRevised(enemyAircrafts);//敌机
        paintImageWithPositionRevised(props);//敌机


        canvas.drawBitmap(ImageManager.HERO_IMAGE,
                heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY()- ImageManager.HERO_IMAGE.getHeight() / 2,
                mPaint);

        //画生命值
        paintScoreAndLife();

        mSurfaceHolder.unlockCanvasAndPost(canvas);

    }

    private void paintImageWithPositionRevised(List<? extends AbstractFlyingObject> objects) {
//        if (objects.size() == 0) {
//            return;
//        }
//
//        for (AbstractFlyingObject object : objects) {
//            Bitmap image = object.getImage();
//            assert image != null : objects.getClass().getName() + " has no image! ";
//            canvas.drawBitmap(image, object.getLocationX() - image.getWidth() / 2,
//                    object.getLocationY() - image.getHeight() / 2, mPaint);
//        }
          if(objects != null && objects.size() == 0){
              return;
          }else{
              for (int i = 0;i<objects.size();i++){
                    Bitmap image = objects.get(i).getImage();
                    assert image != null : objects.getClass().getName() + " has no image! ";
                    canvas.drawBitmap(image, objects.get(i).getLocationX() - image.getWidth() / 2,
                    objects.get(i).getLocationY() - image.getHeight() / 2, mPaint);

              }
          }
    }

    private void paintScoreAndLife() {
        int x = 10;
        int y = 40;

        mPaint.setColor(Color.RED);
        mPaint.setTextSize(50);

        canvas.drawText("SCORE:" + this.score, x, y, mPaint);
        y = y + 60;
        canvas.drawText("LIFE:" + this.heroAircraft.getHp(), x, y, mPaint);
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        new Thread(this).start();
        Log.i(TAG, "start surface view thread");
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
        MainActivity.screenWidth = i1;
        MainActivity.screenHeight = i2;
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        mbLoop = false;
    }

    @Override
    public void run() {

        while (mbLoop){   //游戏结束停止绘制
            synchronized (mSurfaceHolder){
                action();
                draw();
            }
        }
        Message message = Message.obtain();
        message.what = 1 ;
        handler.sendMessage(message);
    }
}
