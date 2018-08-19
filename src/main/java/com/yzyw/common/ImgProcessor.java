package com.yzyw.common;

import com.yzyw.util.ImgUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImgProcessor {

    //线程池
    private static ExecutorService executor=Executors.newFixedThreadPool(50);

    public static void main(String[] args) {

        for (ImagePointPair imagePointPair : ResourceHandler.allPointPair) {
            executor.execute(()-> ImgUtil.processImg(imagePointPair));
        }

        //关闭线程池
        executor.shutdown();

    }



}
