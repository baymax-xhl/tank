package com.baymax.tank;

import java.awt.*;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/20 2:53 下午
 */
public class Bullet {

    private int x,y;
    private Dir dir;
    private boolean living = true;
    private TankFrame tf = null;
    private static final int SPEED = 10;
    private Group group = Group.BAD;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    Rectangle rectangle = new Rectangle();

    public Group getGroup() {
        return group;
    }

    public Bullet(int x, int y, Dir dir, TankFrame tf,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

    }
    public void paint(Graphics g) {
        if(!this.living){
            tf.bulletList.remove(this);
        }
        switch (dir){
            case LEFT :
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT :
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case UP :
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN :
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
        }
        move();
    }
    private void move(){
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

        //update rect
        rectangle.x = this.x;
        rectangle.y = this.y;
        if(x < 0 || y < 0 || x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT ){
            living = false;
        }
    }

    public void collideWith(Tank tank) {

        if(this.group == tank.getGroup()) return;
        //用一个rect记录子弹的位置
        if(rectangle.intersects(tank.rectangle)) {
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tf.explodeList.add(new Explode(eX,eY,tf));
        }
    }

    private void die() {
        living = false;
    }
}
