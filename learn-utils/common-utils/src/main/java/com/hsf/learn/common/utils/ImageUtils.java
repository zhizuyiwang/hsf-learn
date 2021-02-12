package com.hsf.learn.common.utils;


import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;


import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
/**
 * 图片处理
 * @author og 19.11.25
 */
public class ImageUtils {
    /**
     * 图片
     * @param ins 文件流
     * @param boxList 标识框
     * @return
     * @throws IOException
     */
    public static InputStream rectangleImage(InputStream ins, List<String> boxList) throws IOException {
        //图片流
        BufferedImage image = null;
        ByteArrayOutputStream bs = null;
        ImageOutputStream imOut = null;
        InputStream inputStream = null;
        try {
            image = ImageIO.read(ins);
            //画布
            final Graphics2D g = image.createGraphics();
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(22.0f));

//            for(UtilsAiBox box : boxList){
//                g.drawRect(box.getX(), box.getY(), box.getXLength(), box.getYWidth());
//            }
            bs = new ByteArrayOutputStream();
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(image, "jpg", imOut);
            inputStream = new ByteArrayInputStream(bs.toByteArray());
        } finally {
            //资源即时释放
            if(imOut != null){
                imOut.close();
            }
            if(bs != null){
                bs.close();
            }
            if(image != null){
                image.flush();
            }
        }
        //读取文件 并返回
        return inputStream;
    }
    /**
     * 图片旋转
     * @param src
     * @param angel
     * @return
     */
    public static BufferedImage Rotate(Image src, int angel) {
        int src_width = src.getWidth(null);
        int src_height = src.getHeight(null);
        // 计算旋转后图片的尺寸
        Rectangle rect_des = calcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), angel);
        BufferedImage res = null;
        res = new BufferedImage(rect_des.width, rect_des.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = res.createGraphics();
        // 进行转换
        g2.translate((rect_des.width - src_width) / 2,
                (rect_des.height - src_height) / 2);
        g2.rotate(Math.toRadians(angel), src_width / 2, src_height / 2);

        g2.drawImage(src, null, null);
        return res;
    }



    /**
     * 计算旋转角度
     * @param src
     * @param angel
     * @return
     */
    private static Rectangle calcRotatedSize(Rectangle src, int angel){
        //旋转角度 > 90度做响应的转换
        if (angel >= 90) {
            if (angel / 90 % 2 == 1) {
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }
    public static InputStream metaScala(InputStream is) throws IOException {
        BufferedImage originalImage = null;
        BufferedImage thumbnail = null;
        ByteArrayOutputStream bs = null;
        ImageOutputStream imOut = null;
        final InputStream inputStream;
        try {
            originalImage = ImageIO.read(is);

            thumbnail = Thumbnails.of(originalImage)
                    .scale(0.25)
                    .asBufferedImage();
            bs = new ByteArrayOutputStream();
            imOut = ImageIO.createImageOutputStream(bs);
            ImageIO.write(thumbnail, "jpeg", imOut);
            inputStream = new ByteArrayInputStream(bs.toByteArray());
        } finally {
            if(imOut != null){
                imOut.close();
            }
            if(bs != null){
                bs.close();
            }
            if(thumbnail != null){
                thumbnail.flush();
            }
            if(originalImage != null){
                originalImage.flush();
            }
        }
        return inputStream;
    }

    /**
     * 判断文件是否存在
     * @param httpPath
     * @return
     */
    private static Boolean existHttpPath(String httpPath){
        URL httpurl = null;
        try {
            httpurl = new URL(new URI(httpPath).toASCIIString());
            URLConnection urlConnection = httpurl.openConnection();
            // urlConnection.getInputStream();
            Long TotalSize=Long.parseLong(urlConnection.getHeaderField("Content-Length"));
            if (TotalSize <= 0){
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
