package com.yzyw.common;

import com.google.common.collect.Lists;

import java.util.List;

/**坐标抽象节省时间不写get set*/
public class ImagePointPair {
    /**图片名称*/
    public String imagename;

    public List<PointPair> pairList= Lists.newArrayList();


    public  static class PointPair{
        public PointPair(Point a, Point b){
            pointDown=b;
            pointUp=a;
        }
        /**起点*/
        public Point pointUp;
        /**对角点*/
        public Point pointDown;
    }





}
