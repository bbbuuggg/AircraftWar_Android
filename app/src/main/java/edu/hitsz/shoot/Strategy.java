package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;

import java.util.List;

public interface Strategy {
    List<AbstractBullet> shoot(AbstractAircraft aircraft);
}
