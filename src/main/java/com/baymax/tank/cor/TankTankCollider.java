package com.baymax.tank.cor;

import com.baymax.tank.GameObject;
import com.baymax.tank.Tank;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/21 10:37 下午
 */
public class TankTankCollider implements Collide{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if(t1.rectangle.intersects(((Tank) o2).rectangle)){
             t1.stop();
            }
        }
        return true;
    }
}
