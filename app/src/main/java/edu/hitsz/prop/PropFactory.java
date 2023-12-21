package edu.hitsz.prop;

public interface PropFactory {
    public abstract Props CreateProp(int locationX, int locationY, int speedX, int speedY,int propsID,String mode);
}

