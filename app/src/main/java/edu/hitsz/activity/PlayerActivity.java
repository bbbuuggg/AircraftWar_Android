package edu.hitsz.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.hitsz.ClientThread;
import edu.hitsz.Player.Player;
import edu.hitsz.Player.PlayerDao;
import edu.hitsz.Player.PlayerDaoImpl;
import edu.hitsz.activity.GameActivity;
import edu.hitsz.R;
import edu.hitsz.game.BaseGame;
import edu.hitsz.game.EasyGame;
import edu.hitsz.game.HardGame;
import edu.hitsz.game.MediumGame;


public class PlayerActivity extends AppCompatActivity {

    String playerId;
    int gameType;
    String mode;
    private String Password;

    private PrintWriter out = null;
    private ClientThread clientThread;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        clientThread = new ClientThread(handler);
        setContentView(R.layout.activity_player);
        Intent intent = getIntent();
        Intent intent2 = getIntent() ;
        //输入输出
        PlayerDao playerDao = new PlayerDaoImpl();

        FileInputStream fis;
        FileOutputStream fos;
        Integer gameType = intent.getIntExtra("Type", 0);
        if (gameType == 1) {
            mode = "普通模式";

        } else if (gameType == 3) {
            mode = "困难模式";
        } else {
            mode = "简单模式";
        }
        TextView lblTitle = (TextView) findViewById(R.id.GameMode);
        lblTitle.setText(mode);
        Message PwSg = new Message();
        PwSg.obj = null;
        Password = intent2.getStringExtra("password");
        String PlayerList = ((PlayerDaoImpl) playerDao).getAdr(gameType);//获取文件名字
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss");
        clientThread.setPlayer(1,BaseGame.score,sdf.format(System.currentTimeMillis()));
//        clientThread.toserverHandler.sendMessage(PwSg);
        try {
            fis = openFileInput(PlayerList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //获取ID

        if (ClientThread.getConnect()) {

            String playerId = intent.getStringExtra("ID");

            List<Player> players = playerDao.getAllPlayer(fis);// 获取表格
            if (playerId != null) {
                Player newPlayer = new Player(playerId, BaseGame.score, sdf.format(System.currentTimeMillis()),Password);
                playerDao.doAdd(newPlayer);
            }
            playerDao.scoreSort();//排序
            ((PlayerDaoImpl) playerDao).getData();
            try {
                fos = openFileOutput(PlayerList, MODE_PRIVATE);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            playerDao.outPutFile(fos);//输出
            //获得Layout里面的ListView
            ListView list = (ListView) findViewById(R.id.List);

            //生成适配器的Item和动态数组对应的元素
            SimpleAdapter listItemAdapter = new SimpleAdapter(
                    this,
                    ((PlayerDaoImpl) playerDao).getData(),
                    R.layout.listitem,
                    new String[]{"XuHao", "ID", "score", "time"},
                    new int[]{R.id.XuHao, R.id.ID, R.id.score, R.id.time});

            //添加并且显示
            list.setAdapter(listItemAdapter);

            //获取Intent中暂存的数据


            //添加单击监听
            list.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    Map<String, Object> clkMap = (Map<String, Object>) arg0.getItemAtPosition(arg2);
                    AlertDialog.Builder builder = new AlertDialog.Builder(PlayerActivity.this);
                    builder.setTitle("提示")
                            .setMessage("确认删除？");
                    builder.setPositiveButton("Yep", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            FileOutputStream fos;
                            playerDao.doDelete(clkMap.get("ID").toString());
                            playerDao.scoreSort();//排序
                            try {
                                fos = openFileOutput(PlayerList, MODE_PRIVATE);
                            } catch (FileNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            playerDao.outPutFile(fos);//输出
                            SimpleAdapter listItemAdapter = new SimpleAdapter(
                                    PlayerActivity.this,
                                    ((PlayerDaoImpl) playerDao).getData(),
                                    R.layout.listitem,
                                    new String[]{"XuHao", "ID", "score", "time"},
                                    new int[]{R.id.XuHao, R.id.ID, R.id.score, R.id.time});

                            //添加并且显示
                            list.setAdapter(listItemAdapter);
                        }
                    });
                    builder.setNegativeButton("Nop", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    builder.create().show();
                }
            });
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(PlayerActivity.this);
            builder.setTitle("连接已断开，请检查连接");
        }
    }


}

