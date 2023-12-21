package edu.hitsz.prop;

public class BombFactory implements PropFactory {
    public Props CreateProp(int locationX, int locationY, int speedX, int speedY,int propsID,String mode)
    {
        return new BombProp( locationX, locationY, speedX, speedY,propsID,mode);
    }
}

