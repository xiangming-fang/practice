package com.xm.jy.test.jdk.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @Author: xiangming.fang
 * @Date: 2023/4/14 10:05
 * // todo 狗屁不通
 */
public class AvifToPng {

    public static void main(String[] args) throws Exception{
        fileToImage("E:\\avif\\1.avif","E:\\avif\\1.png");
    }

    private static final int NAN = -1;

    private static int readByte(DataInputStream dis) throws IOException {
        int b;
        try {
            b = dis.readByte();
        } catch (EOFException e) {
            b = NAN;
        }
        return b;
    }

    public static void fileToImage(String sourceFile, String imageFile) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(sourceFile));
        int size = ((int) Math.sqrt(dis.available())) + 2;
        BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < size; y++) {
            boolean finished = false;
            for (int x = 0; x < size; x++) {
                int alpha = 3;
                int red = readByte(dis);
                int green = readByte(dis);
                int blue = readByte(dis);
                if (red == NAN) {
                    alpha--;
                    red = 0;
                }
                if (green == NAN) {
                    alpha--;
                    green = 0;
                }
                if (blue == NAN) {
                    alpha--;
                    blue = 0;
                }
                int rgb = ((alpha & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
                image.setRGB(x, y, rgb);
                if (alpha < 3) {
                    finished = true;
                    break;
                }
            }
            if (finished) break;
        }
        dis.close();
        ImageIO.write(image, "png", new File(imageFile));
    }

    public static void imageToFile(String imageFile, String outputFile) throws IOException {
        BufferedImage image = ImageIO.read(new File(imageFile));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputFile));
        for (int y = 0; y < image.getHeight(); y++) {
            boolean finished = false;
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                int alpha = (rgb >> 24) & 0xFF;
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                if (alpha == 0) {
                    finished = true;
                    break;
                }
                if (alpha >= 1) dos.write(red);
                if (alpha >= 2) dos.write(green);
                if (alpha == 3) dos.write(blue);
            }
            if (finished) break;
        }
        dos.close();
    }

}
