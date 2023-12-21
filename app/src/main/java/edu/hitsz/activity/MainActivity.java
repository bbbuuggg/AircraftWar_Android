package edu.hitsz.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

import edu.hitsz.ClientThread;
import edu.hitsz.R;
import edu.hitsz.music.MusicService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainActivity";

    public static int screenWidth;
    public static int screenHeight;
    public static boolean Singing;

    private int gameType=0;
    //
    private TextView txtshow;
    private EditText id;
    private EditText password;
    private String Password;
    private Button btnconn, btnsend;
    private RelativeLayout chatv;
    private RelativeLayout chatv2;
    private static final String HOST = "10.250.198.6";
    private static final int PORT = 8899;
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String content = "";
    private StringBuilder sb = null;
    private Handler handler;
    private ClientThread clientThread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        sb = new StringBuilder();
        txtshow = findViewById(R.id.txtmsg);
        id = findViewById(R.id.sendmsg);
        password = findViewById(R.id.sendmsg2);
        btnconn = findViewById(R.id.butConn);
        btnsend = findViewById(R.id.butSend);
        chatv = findViewById(R.id.chatview);
        chatv2 = findViewById(R.id.chatview2);
        clientThread = new ClientThread(handler);
        sb.append(txtshow.getText().toString());

        btnconn.setOnClickListener(this);
        btnsend.setOnClickListener(this);

        //用于发送接收到的服务端的消息，显示在界面上
        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                //如果消息来自于子线程
                if (msg.what == 0x123) {
                    sb.append("\n" + msg.obj);
                    Log.i("wotainanl", "@@" + sb);
                    txtshow.setText(sb.toString());//将sb里的内容添加到文本框中
                }
            }
        };

        //

        Button medium_btn = findViewById(R.id.medium_btn);
        Button easy_btn = findViewById(R.id.easy_btn);
        Button hard_btn = findViewById(R.id.hard_btn);
        Switch MusicButton = findViewById(R.id.MusicBox);
        getScreenHW();

        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        Intent intent1 = new Intent(this, MusicService.class);
        Intent intent2 = new Intent(MainActivity.this, PlayerActivity.class);
        intent2.putExtra("password",password.getText().toString());
        medium_btn.setOnClickListener(view -> {
            gameType=1;
            synchronized (intent1) {
                if (Singing) {
                    intent1.putExtra("action", "play");
                    startService(intent1);
                }
                intent.putExtra("gameType", gameType);
                startActivity(intent);
            }
        });

        easy_btn.setOnClickListener(view -> {
            gameType =2;
            synchronized (intent1) {
                if (Singing) {
                    intent1.putExtra("action", "play");
                    startService(intent1);
                }
                intent.putExtra("gameType", gameType);
                startActivity(intent);
            }
        });

        hard_btn.setOnClickListener(view -> {
            gameType =3;
            synchronized (intent1) {
                if (Singing) {
                    intent1.putExtra("action", "play");
                    startService(intent1);
                }
                intent.putExtra("gameType", gameType);
                startActivity(intent);
            }
        });
        MusicButton.setOnClickListener(view -> {
            Singing = !Singing;
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.butConn:
//                btnconn.setVisibility(View.GONE);
//                chatv.setVisibility(View.VISIBLE);
                chatv2.setVisibility(View.VISIBLE);
                btnconn.setVisibility(View.GONE);
                chatv.setVisibility(View.GONE);
                new Thread(clientThread).start();
                break;

            case R.id.butSend:  // 为发送按钮设置点击事件
                try {
                    Message IdMsg = new Message();
                    Message PwSg = new Message();
                    IdMsg.what = 0x123;
                    IdMsg.obj = id.getText().toString();
                    PwSg.what = 0x123;
                    PwSg.obj = id.getText().toString();
                    clientThread = new ClientThread(handler);  //
                    clientThread.toserverHandler.sendMessage(IdMsg);
                    clientThread.toserverHandler.sendMessage(PwSg);

                    clientThread.setID(id.getText().toString());
                    clientThread.setPassword(password.getText().toString());

                    this.Password = password.getText().toString();
                    id.setText("请输入你的id");  //清空输入文本框
                    password.setText("请输入你的密码");
                } catch (Exception e) {
                    e.printStackTrace();
                };
                break;
        }
    }
    public void getScreenHW(){
        //定义DisplayMetrics 对象
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //窗口的宽度
        screenWidth= dm.widthPixels;
        //窗口高度
        screenHeight = dm.heightPixels;

        Log.i(TAG, "screenWidth : " + screenWidth + " screenHeight : " + screenHeight);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    public static boolean getSinging() {
        return Singing;
    }
}