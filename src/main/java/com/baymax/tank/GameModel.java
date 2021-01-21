package com.baymax.tank;

import com.baymax.tank.cor.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/21 8:19 下午
 */
public class GameModel {

    Tank myTank = new Tank(200,400,Dir.DOWN,this,Group.GOOD);
    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tankList = new ArrayList<>();
    List<Explode> explodeList = new ArrayList<>();
    ColliderChain chain = new ColliderChain();
    public GameModel() {
          int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        for (int i = 0; i < initTankCount ; i++) {
            tankList.add(new Tank(50 + i * 80,200,Dir.DOWN, this,Group.BAD));
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量：" + this.bulletList.size(),10,60);
        g.drawString("敌人的数量：" + this.tankList.size(),10,80);
        g.drawString("爆炸的数量：" + this.explodeList.size(),10,100);
        g.setColor(c);
        myTank.paint(g);
        for (int i = 0; i <bulletList.size() ; i++) {
            bulletList.get(i).paint(g);
        }

        for (int j = 0; j <tankList.size() ; j++) {
            tankList.get(j).paint(g);
        }

        for (int p = 0; p <explodeList.size() ; p++) {
            explodeList.get(p).paint(g);
        }

        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j <tankList.size() ; j++) {
                GameObject o1 = bulletList.get(i);
                GameObject o2 = tankList.get(j);
                chain.collide(o1,o2);
            }
        }
    }


    public Tank getMainTank() {
        return myTank;
    }
}
