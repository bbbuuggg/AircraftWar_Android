package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;

import java.util.List;

public class Context1 {
    private Strategy strategy;
    public Context1(Strategy strategy){
        this.strategy = strategy;
    }
    public void setStrategy(Strategy strategy){
        this.strategy = strategy;
    }
    public List<AbstractBullet> executeStrategy(AbstractAircraft aircraft){
        return  strategy.shoot(aircraft);
    }
}

