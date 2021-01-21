package com.baymax.tank;

import java.awt.*;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/20 2:53 下午
 */
public class Bullet extends GameObject{

    private int x,y;
     Dir dir;
    private boolean living = true;
     GameModel gm = null;
    private static final int SPEED = 10;
     Group group = Group.BAD;
    public static int WIDTH = ResourceMgr.bulletD.getWidth();
    public static int HEIGHT = ResourceMgr.bulletD.getHeight();
    Rectangle rectangle = new Rectangle();

    public Group getGroup() {
        return group;
    }

    public Bullet(int x, int y, Dir dir, GameModel gm,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
        gm.bulletList.add(this);

    }
    public void paint(Graphics g) {
        if(!this.living){
            gm.bulletList.remove(this);
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
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
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT ){
            living = false;
        }
    }

    public boolean collideWith(Tank tank) {

        if(this.group == tank.getGroup()) return false;
        //用一个rect记录子弹的位置
        if(rectangle.intersects(tank.rectangle)) {
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            gm.explodeList.add(new Explode(eX,eY,gm));
            return true;
        }
        return false;
    }

    private void die() {
        living = false;
    }
}
