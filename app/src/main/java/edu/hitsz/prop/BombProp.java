package edu.hitsz.prop;

import java.util.List;

import edu.hitsz.activity.MainActivity;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.Boss;
import edu.hitsz.aircraft.EliteEnemy;
import edu.hitsz.music.MusicService;
import edu.hitsz.music.SoundPoo;
//import edu.hitsz.music.MusicBox;
//import window.ACWAR;

public class BombProp extends Props{
    protected int propsID;
    private List<BombUpdate> watchList ;
    private int exScore;
    public BombProp(int locationX, int locationY, int speedX, int speedY, int propsID,String mode) {
        super(locationX, locationY, speedX, speedY, propsID,mode);
    }

    @Override
    public void supply(AbstractAircraft aircraft) {
        exScore = 0;
        System.out.print("BombSupply Active!\n");
        Boom(1);
    }
    public void addWatcher(List<BombUpdate> watchList1){ watchList = watchList1;}

    //删除观察者
    //通知所有观察者
    public void notifyAll(int number){
        for (BombUpdate eye: watchList) {
            eye.update(number);
            if (eye.getClass() == EliteEnemy.class) {
                exScore += 10;
            } else if (eye.getClass() == Boss.class) {
                exScore += 20;
            } else {
                exScore += 5;
            }
        }
    }
    public int getExScore(){
        return exScore;
    }
    //人民币汇率改变
    public void Boom(int number) {
        notifyAll(number);
    }

}
