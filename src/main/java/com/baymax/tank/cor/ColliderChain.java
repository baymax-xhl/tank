package com.baymax.tank.cor;

import com.baymax.tank.GameObject;

import java.util.LinkedList;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/21 10:46 下午
 */
public class ColliderChain implements Collide{
    private LinkedList<Collide> collides = new LinkedList<>() ;

    public ColliderChain() {
        collides.add(new TankTankCollider());
        collides.add(new BulletTankCollider());
    }

    public ColliderChain add(Collide collide){
        collides.add(collide);
        return this;
    }
    @Override
    public boolean collide(GameObject o1, GameObject o2) {

        for (int i = 0; i < collides.size() ; i++) {
            if(!collides.get(i).collide(o1,o2))return false;
        }
        return true;
    }
}
