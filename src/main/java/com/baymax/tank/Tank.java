package com.baymax.tank;

import java.awt.*;
import java.util.Random;

/**
 * @description: tank类
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/20 2:23 下午
 */

public class Tank {

    private int x,y;
    private Dir dir = Dir.DOWN;
    private boolean moving = true;
    private boolean living = true;
    private TankFrame tf ;
    private Group group = Group.BAD;
    private Random random = new Random();
    private static final int SPEED = 5;
    public static int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankD.getHeight();
    Rectangle rectangle = new Rectangle();


    public Group getGroup() {
        return group;
    }

    public Tank(int x, int y, Dir dir, TankFrame tankFrame,Group group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tankFrame;
        this.group = group;
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics g) {
        if(!living) tf.tankList.remove(this);
        switch (dir){
            case LEFT :
                g.drawImage(this.group.equals(Group.GOOD)? ResourceMgr.goodTankL:ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT :
                g.drawImage(this.group.equals(Group.GOOD)? ResourceMgr.goodTankR:ResourceMgr.badTankR,x,y,null);
                break;
            case UP :
                g.drawImage(this.group.equals(Group.GOOD)? ResourceMgr.goodTankU:ResourceMgr.badTankU,x,y,null);
                break;
            case DOWN :
                g.drawImage(this.group.equals(Group.GOOD)? ResourceMgr.goodTankD:ResourceMgr.badTankD,x,y,null);
                break;
        }
        move();
    }
    private void move(){
        if(!moving) return;
            switch (dir) {
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
            }

        if(this.group.equals(Group.BAD) && random.nextInt(10) > 8) fire();
        if(this.group.equals(Group.BAD) && random.nextInt(100) > 95) randomDir();

        boundCheck();
        //update rect
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void boundCheck() {
      if(x < 2) x = 2;
      if(y < 28) y = 28;
      if (this.x > TankFrame.GAME_WIDTH- Tank.WIDTH -2) x = TankFrame.GAME_WIDTH - Tank.WIDTH -2;
      if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT -2 ) y = TankFrame.GAME_HEIGHT -Tank.HEIGHT -2;
    }

    private void randomDir() {

        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        int bX = x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
      tf.bulletList.add( new Bullet(bX,bY,dir,tf,this.group));
    }


    public void die() {

        this.living = false;
    }
}
