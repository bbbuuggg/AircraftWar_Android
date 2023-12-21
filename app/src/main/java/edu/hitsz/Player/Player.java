package edu.hitsz.Player;

import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Player implements Serializable{
    private String playerId;

    private String time;
    private String Password;
    private int Score;
    public Player(String playerId,int Score,String time,String Password){
        this.playerId = playerId;

        this.Score = Score;
        this.time = time;
        this.Password = Password;
    }

    public  String getPlayerId(){
        return playerId;
    }

    public int getScore(){
        return  Score;
    }
    public String getTime(){
        return time ;
    }

    public String getPassword() {
        return Password;
    }
}
