package com.baymax.tank.cor;

import com.baymax.tank.Bullet;
import com.baymax.tank.GameObject;
import com.baymax.tank.Tank;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/21 10:37 下午
 */
public class BulletTankCollider implements Collide{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            if (b.collideWith(t)) {
                return false;
            }
        }else if(o2 instanceof Bullet && o1 instanceof Tank){
            return collide(o2,o1);
        }

        return true;
    }
}
