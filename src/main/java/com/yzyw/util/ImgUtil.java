package com.yzyw.util;

import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import com.sun.deploy.util.StringUtils;
import com.yzyw.common.Point;
import com.yzyw.common.ImagePointPair;
import com.yzyw.common.ResourceHandler;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

/**图片处理类*/
public class ImgUtil {

    /**
     * 处理图片
     * @param orginFile
     */
    public static void processImg(ImagePointPair orginFile){

        System.out.println("正在处理图片："+orginFile.imagename);
        //画矩形框
        ThumbnailatorUtils.drawRectAndSave(orginFile);

    }


    /**
     * 读取一个文件中的所有的描点
     * @param pointFile
     * @return
     */
    public  static ImagePointPair readPoints(File pointFile) {

        try {
            return Files.readLines(pointFile, Charset.forName("utf8"), new LineProcessor<ImagePointPair>() {
                private ImagePointPair imagePointPair=new ImagePointPair();
                @Override
                public boolean processLine(String currentLine) throws IOException {
                    String[] unProcessPoints = StringUtils.splitString(currentLine," ");
                    ImagePointPair.PointPair pointPair = new ImagePointPair.PointPair(new Point(unProcessPoints[1], unProcessPoints[2]),
                            new Point(unProcessPoints[3], unProcessPoints[4]));
                    imagePointPair.pairList.add(pointPair);
                    return true;
                }

                @Override
                public ImagePointPair getResult() {
                    imagePointPair.imagename=ResourceHandler.findImageFile(pointFile);
                    return imagePointPair;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
