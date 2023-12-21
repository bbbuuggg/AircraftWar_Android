package edu.hitsz.prop;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.HeroAircraft;

public class HpProp extends Props{
    public HpProp(int locationX, int locationY, int speedX, int speedY, int propsID,String mode) {
        super(locationX, locationY, speedX, speedY, propsID,mode);
    }

    @Override
    public void supply(AbstractAircraft aircraft) {
        HeroAircraft.getHeroAircraft().increaseHp(30);
        System.out.print("HpSupply Active!\n");
    }

}
