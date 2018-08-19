package com.yzyw.common;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.sun.deploy.util.StringUtils;
import com.yzyw.util.ImgUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * 处理资源交互
 */
public class ResourceHandler {

    private static String txtSrcPath = "/Users/tangzhongping/Desktop/images2/label";


    public final static List<ImagePointPair> allPointPair;

    //init files
    static {
        //遍历源文件夹,获取所有的txt
        allPointPair = Lists.newArrayList();
        initTxtToImages(allPointPair);
    }

    private static void initTxtToImages(List<ImagePointPair> allHandleFiles) {
        Collection<File> allFiles
                =FileUtils.listFiles(new File(txtSrcPath),new String[]{"txt"},true);

        for (File currentFile : allFiles) {
            allHandleFiles.add(ImgUtil.readPoints(currentFile)) ;
        }


    }

    /**
     * 找到txt对应的图片
     * @param orginFile
     * @return
     */
    public static String findImageFile(File orginFile) {
        return getNeExtFileName(orginFile.getAbsolutePath()).replace("label","input")+".jpg";
    }

    /**
     * 找到文件的目标地址，并且构造其名称
     * @param orginFileName
     * @return
     */
    private static String findDist(String orginFileName) {
        return getNeExtFileName(orginFileName).replace("input","complete")+".jpg";
    }


    /**
     * 保存一个文件
     * @param finalImage
     */
    public static void saveFile(String name,BufferedImage finalImage) {

        FileOutputStream fileOutputStream = null;
        try {
            File outputFile=new File(findDist(name));
            Files.createParentDirs(outputFile);
            fileOutputStream = new FileOutputStream(outputFile);
            ImageIO.write(finalImage, "jpeg", fileOutputStream);
            System.out.println("保存处理完成文件："+findDist(name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * 得到文件本名
     * @param orginFileName
     * @return
     */
    static String getNeExtFileName(String orginFileName){
        return StringUtils.splitString(orginFileName,".")[0];
    }
}
