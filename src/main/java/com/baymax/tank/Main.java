package com.baymax.tank;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/20 4:05 下午
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();

        //初始化坦克

        while (true) {
            Thread.sleep(50);
            tf.repaint();
        }

    }
}
