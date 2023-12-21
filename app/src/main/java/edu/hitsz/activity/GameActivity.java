package edu.hitsz.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;

import edu.hitsz.Player.Player;
import edu.hitsz.Player.PlayerDao;
import edu.hitsz.Player.PlayerDaoImpl;
import edu.hitsz.game.BaseGame;
import edu.hitsz.game.EasyGame;
import edu.hitsz.game.HardGame;
import edu.hitsz.game.MediumGame;
import edu.hitsz.music.MusicService;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = "GameActivity";

    private int gameType=0;
    public String playerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PlayerDao playerDao = new PlayerDaoImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        MusicService musicService = new MusicService();
        Intent intent = new Intent(GameActivity.this, PlayerActivity.class);
        Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Log.d(TAG,"handleMessage");
                if (msg.what == 1) {

                    Toast.makeText(GameActivity.this,"GameOver",Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                    builder.setTitle("你的得分是："+ BaseGame.score)
                            .setMessage("点击ENTER查看排行榜");
//                    EditText editText = new EditText(GameActivity.this);
//                    editText.setText(null);
//
//                    builder.setView(editText);
                    builder.setPositiveButton("ENTER",((dialogInterface, i) -> {
//                        playerId = editText.getText().toString();
//                        intent.putExtra("ID", playerId);
                        intent.putExtra("Type", gameType);
                        startActivity(intent);
                    }));
                    builder.show();



                }
            }
        };
        if(getIntent() != null){
            gameType = getIntent().getIntExtra("gameType",1);
        }
        BaseGame basGameView;
        if(gameType == 1){
            basGameView = new MediumGame(this,handler);

        }else if(gameType == 3){
            basGameView = new HardGame(this,handler);
        }else{
            basGameView = new EasyGame(this,handler);
        }
        setContentView(basGameView);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}