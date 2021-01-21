package com.baymax.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/21 2:30 下午
 */
public class PropertyMgr {
    static Properties property = new Properties();

     static {
         try {
             property.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public static Object get(String key) {
         if(property == null) return null;
         return property.get(key);
    }
    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
