package com.baymax.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/21 9:57 下午
 */
public abstract class GameObject implements Serializable {

    private int x,y;

    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();


}
