package com.baymax;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @description: some desc
 * @author: baymax
 * @email: xxx@xx.com
 * @date: 2021/1/20 4:36 下午
 */
public class ImageTest {

    @Test
    void test(){
        try {
            BufferedImage bi = ImageIO.read(
                    ImageTest.class.getClassLoader()
                            .getResourceAsStream("images/bulletD.gif"));
            assertNotNull(bi);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
