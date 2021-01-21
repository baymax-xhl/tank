package com.baymax.tank;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/20 4:05 下午
 */
public class Main {
    static  int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
    public static void main(String[] args) throws InterruptedException {
        TankFrame frame = new TankFrame();

        //初始化坦克
        for (int i = 0; i < initTankCount ; i++) {
            frame.tankList.add(new Tank(50 + i * 80,200,Dir.DOWN,frame,Group.BAD));
        }
        while (true) {
            Thread.sleep(50);
            frame.repaint();
        }

    }
}
