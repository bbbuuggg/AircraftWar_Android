//package edu.hitsz.activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import java.io.BufferedReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
//import edu.hitsz.ClientThread;
//import edu.hitsz.R;
//
//public class ClientActivity extends AppCompatActivity implements View.OnClickListener {
//
//    private TextView txtshow;
//    private EditText editsend;
//    private Button btnconn, btnsend;
//    private RelativeLayout chatv;
//    private static final String HOST = "10.250.198.6";
//    private static final int PORT = 8899;
//    private Socket socket = null;
//    private BufferedReader in = null;
//    private PrintWriter out = null;
//    private String content = "";
//    private StringBuilder sb = null;
//    private Handler handler;
//    private ClientThread clientThread;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        sb = new StringBuilder();
//        txtshow = findViewById(R.id.txtmsg);
//        editsend = findViewById(R.id.sendmsg);
//        btnconn = findViewById(R.id.butConn);
//        btnsend = findViewById(R.id.butSend);
//        chatv = findViewById(R.id.chatview);
//        clientThread = new ClientThread(handler);
//        sb.append(txtshow.getText().toString());
//
//        btnconn.setOnClickListener(this);
//        btnsend.setOnClickListener(this);
//
//        //用于发送接收到的服务端的消息，显示在界面上
//        handler = new Handler(getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                //如果消息来自于子线程
//                if (msg.what == 0x123) {
//                    sb.append("\n" + msg.obj);
//                    Log.i("wotainanl", "@@" + sb);
//                    txtshow.setText(sb.toString());//将sb里的内容添加到文本框中
//                }
//            }
//        };
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.butConn:
//                btnconn.setVisibility(View.GONE);
//                chatv.setVisibility(View.VISIBLE);
//                clientThread = new ClientThread(handler);  //
//                new Thread(clientThread).start();
//                break;
//
//            case R.id.butSend:  // 为发送按钮设置点击事件
//                try {
//                    Message msg = new Message();
//                    msg.what = 0x123;
////                    Request request = new Request()
//                    msg.obj = "test1/15";
//
//                    clientThread.toserverHandler.sendMessage(msg);
//                    editsend.setText("");  //清空输入文本框
//                } catch (Exception e) {
//                    e.printStackTrace();
//                };
//                break;
//        }
//    }
//
//}
