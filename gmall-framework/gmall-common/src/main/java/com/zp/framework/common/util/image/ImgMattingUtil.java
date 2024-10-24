package com.zp.framework.common.util.image;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 10:14
 * Version : v1.0.0
 * Description: 签名图片抠图工具类
 * TODO: 先转灰度值，再阈值判断会更好
 */
@Slf4j
public class ImgMattingUtil {
    /**
     * 签名内容识别的阈值，R、G、B三个值均低于此阈值对应的R、G、B的会被判定为签名内容，否则被判定为背景内容
     */
    public static final Color COLOR_THRESHOLD = new Color(100, 100, 100);

    public static void matting(String inputFilePath, String outputFilePath) throws IOException {
        matting(new File(inputFilePath), new File(outputFilePath), false);
    }

    public static void matting(File inputFilePath, File outputFilePath) throws IOException {
        matting(inputFilePath, outputFilePath, false);
    }

    public static void matting(File inputFile, File outFIle, boolean isResize) throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("matting");
        BufferedImage inputImage = ImageIO.read(inputFile);
        // 创建新的BufferedImage对象作为输出结果
        BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //签名区域右下角横纵坐标值
        int maxX = 0, maxY = 0;
        //签名区域左上角横纵坐标值
        int minX = inputImage.getWidth(), minY = inputImage.getHeight();
        //外层遍历Y轴像素
        for (int y = inputImage.getMinY(); y < inputImage.getHeight(); y++) {
            // 内层遍历X轴像素
            for (int x = inputImage.getMinX(); x < inputImage.getWidth(); x++) {
                int rgb = inputImage.getRGB(x, y);
                // 根据设定的阈值，判断该像素点是否是签名内容
                if (isSignContent(rgb)) {
                    //如果是签名内容，则将该像素点染成黑色
                    outputImage.setRGB(x, y, Color.BLACK.getRGB());
                    //如果需要裁剪，则记录签名区域左上角、右下角坐标
                    if (isResize) {
                        minX = Math.min(minX, x);
                        minY = Math.min(minY, y);
                        maxX = Math.max(maxX, x);
                        maxY = Math.max(maxY, y);
                    }
                } else {
                    //如果不是签名内容，即为背景像素，则将该像素点设置成透明
                    outputImage.setRGB(x, y, 0);
                }
            }

        }
        //如果需要裁剪，则截取上面获取的签名区域
        if (isResize) {
            // 输出图片坐标
            log.info("resize img: ({}, {}), ({}, {})", maxX, maxY, maxX, maxY);
            outputImage = outputImage.getSubimage(minX, minY, maxX - minX, maxY - minY);
        }
        // 生成图片为PNG
        ImageIO.write(outputImage, "png", outFIle);
        stopWatch.stop();
        log.info("total time: {} ms", stopWatch.getTotalTimeMillis());
    }

    /**
     * 判断指定颜色是否是签名内容
     *
     * @param colorValue 颜色值
     * @return true：是签名内容
     * false：不是签名内容
     */
    public static boolean isSignContent(int colorValue) {
        Color color = new Color(colorValue, true);
        //先判断透明度，如果是完全透明，则可以认为该像素点是背景
        int alpha = color.getAlpha();
        if (alpha <= 1) {
            return false;
        }
        //如果不是透明的情况下，再判断其G、G、B三值是否都小于阈值，如果小于阈值，则认为该像素点是签名内容，否则就是背景
        return color.getRed() <= COLOR_THRESHOLD.getRed() && color.getGreen() <= COLOR_THRESHOLD.getGreen() && color.getBlue() <= COLOR_THRESHOLD.getBlue();
    }
}
