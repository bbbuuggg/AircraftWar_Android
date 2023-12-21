package edu.hitsz.prop;

import edu.hitsz.activity.MainActivity;
import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.basic.AbstractFlyingObject;

/**
 * 子弹类。
 * 也可以考虑不同类型的子弹
 *
 * @author hitsz
 */
public  abstract class Props extends AbstractFlyingObject {

    String name;
    protected int propsID;
    public Props (int locationX, int locationY, int speedX, int speedY, int propsID,String mode) {
        super(locationX, locationY, speedX, speedY);
        this.propsID =propsID;
    }


    @Override
    public void forward() {
        super.forward();

        // 判定 x 轴出界
        if (locationX <= 0 || locationX >= MainActivity.screenWidth) {
            vanish();
        }

        // 判定 y 轴出界
        if (speedY > 0 && locationY >= MainActivity.screenHeight ) {
            // 向下飞行出界
            vanish();
        }else if (locationY <= 0){
            // 向上飞行出界
            vanish();
        }
    }

    public int getPropsID(){
        return propsID;}

    public abstract void supply(AbstractAircraft aircraft);


}