package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;

import java.util.LinkedList;
import java.util.List;

public  class PropStore  {
    private int direction = 1;
    public static String mode;
    public List<Props> dropProps(AbstractAircraft enemy,boolean boss)
    {
        int j = 1;
        if(boss == true){
            j = 4;
        }else {
            j = 2;
        }
        List<Props> res=new LinkedList<>();
        for(int i=1;i<j;i++){
            PropFactory createPropFactory;//抽象工厂类
            if (Math.random()<0.4) {
                createPropFactory = new HpFactory();
                res.add(createPropFactory.CreateProp(enemy.getLocationX() + (i * 2 - 3 + 1) * 10, enemy.getLocationY() + enemy.getDirection() * 2, i * 2 - 3 + 1, 5 + enemy.getDirection() * 3, 1,mode));
            } else if (Math.random()<0.8) {
                createPropFactory = new FireFactory();
                res.add( createPropFactory.CreateProp(enemy.getLocationX()+ (i * 2 - 3 + 1) * 10, enemy.getLocationY() + enemy.getDirection() * 2, i * 2 - 3 + 1, 5+enemy.getDirection() * 3, 3,mode));
            }else {
                createPropFactory = new BombFactory();
                res.add( createPropFactory.CreateProp(enemy.getLocationX()+ (i * 2 - 3 + 1) * 10, enemy.getLocationY() + enemy.getDirection() * 2, i * 2 - 3 + 1, 5+enemy.getDirection() * 3, 2,mode));
            }
        }
        return res;
    }
    public static void setMode(String mode1){
        mode = mode1;
    }
}



