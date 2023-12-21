package edu.hitsz.music;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;

import edu.hitsz.R;
import edu.hitsz.shoot.Context1;

public class SoundPoo extends AppCompatActivity {
    private int voice;
    SoundPool mysp;
    HashMap<Integer, Integer> soundPoolMap;
//    private Button button1,button2,button3,button4;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        button1 = (Button) this.findViewById(R.id.btn_sp1);
//        button2 = (Button) this.findViewById(R.id.btn_sp2);
//        button3 = (Button) this.findViewById(R.id.btn_sp3);
//        button4 = (Button) this.findViewById(R.id.btn_sp4);
//        button1.setOnClickListener(new BtnOnClk());
//        button2.setOnClickListener(new BtnOnClk());
//        button3.setOnClickListener(new BtnOnClk());
//        button4.setOnClickListener(new BtnOnClk());
//    }
        public SoundPoo(Context context) {
            createSoundPool();    //创建SoundPool对象

            soundPoolMap = new HashMap<Integer,Integer>();
            soundPoolMap.put(1,mysp.load(context,R.raw.bgm_boss,1));
            soundPoolMap.put(2,mysp.load(context,R.raw.bomb_explosion,1));
            soundPoolMap.put(3,mysp.load(context,R.raw.bullet,1));
            soundPoolMap.put(4,mysp.load(context,R.raw.bullet_hit,1));
            soundPoolMap.put(5,mysp.load(context,R.raw.game_over,1));
            soundPoolMap.put(6,mysp.load(context,R.raw.get_supply,1));
        }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createSoundPool() {
        if (mysp == null) {
            // Android 5.0 及 之后版本
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                AudioAttributes audioAttributes = null;
                audioAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build();
                mysp = new SoundPool.Builder()
                        .setMaxStreams(10)
                        .setAudioAttributes(audioAttributes)
                        .build();
            } else { //Android 5.0 以前版本
                mysp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);  // 创建SoundPool
            }
        }
    }

//    public  void Bgm_BossStart(){
//            voice = 1;
//            mysp.play(soundPoolMap.get(1),voice,voice,0,-1,1.0f);
//    }
//    public  void Bgm_BossStop(){
//        voice = 0;
//        mysp.play(soundPoolMap.get(1),voice,voice,0,0,1.0f);
//    }
    public  void BombStart(){
        mysp.play(soundPoolMap.get(2),1,1,0,0,1.0f);
    }
    public  void Bgm_BulletStart(){
        mysp.play(soundPoolMap.get(3),1,1,0,0,1.0f);

    }
    public  void Bgm_Bullet_HitStart(){
        mysp.play(soundPoolMap.get(4),1,1,0,0,1.0f);

    }
    public void Game_Over(){
        mysp.play(soundPoolMap.get(5),1,1,0,0,1.0f);

    }
    public  void Get_Supply(){
        mysp.play(soundPoolMap.get(6),1,1,0,0,1.0f);

    }

}

