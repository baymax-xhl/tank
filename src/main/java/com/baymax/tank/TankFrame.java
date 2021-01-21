package com.baymax.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/19 10:11 下午
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200,400,Dir.DOWN,this,Group.GOOD);
    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tankList = new ArrayList<>();
    List<Explode> explodeList = new ArrayList<>();
//    Explode e = new Explode(100, 100, this);
     static final int GAME_WIDTH = 800;
     static final int GAME_HEIGHT = 600;

    Image offScreenImage = null;

    public TankFrame() throws HeadlessException {
        this.setVisible(true);
        this.setTitle("tank war");
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        this.addKeyListener(new MyKeyListener());
    }

    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }



    @Override
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
                bulletList.get(i).collideWith(tankList.get(j));
            }
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bU = false;
        boolean bL = false;
        boolean bR = false;
        boolean bD = false;
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
        }
            setMainTankDir();
    }
        private void setMainTankDir(){
            if(!bD && !bU && !bL && !bR) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (bD) myTank.setDir(Dir.DOWN);
                if (bL) myTank.setDir(Dir.LEFT);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bU) myTank.setDir(Dir.UP);
            }
        }

    }
}
