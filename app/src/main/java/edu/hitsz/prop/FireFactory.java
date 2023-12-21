package edu.hitsz.prop;
public class FireFactory implements PropFactory {
    public Props CreateProp(int locationX, int locationY, int speedX, int speedY,int propsID,String mode)
    {
        return new FireProp( locationX, locationY, speedX, speedY,propsID,mode);
    }


}