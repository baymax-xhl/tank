package com.baymax.tank.cor;

import com.baymax.tank.GameObject;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/21 9:56 下午
 */
public interface Collide {

    boolean collide(GameObject o1, GameObject o2);
}
