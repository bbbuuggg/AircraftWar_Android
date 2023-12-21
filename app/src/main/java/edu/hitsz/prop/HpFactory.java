package edu.hitsz.prop;

public class HpFactory  implements PropFactory {
    public Props CreateProp(int locationX, int locationY, int speedX, int speedY,int propsID,String mode)
    {
        return new HpProp( locationX, locationY, speedX, speedY,propsID,mode);
    }
}

