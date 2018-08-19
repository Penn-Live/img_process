package com.yzyw.common;

/**点的抽象节省时间不写get set*/
public class Point {
    public  Double x;
    public  Double y;
    public Point(String x,String y){
        this.x=Double.valueOf(x);
        this.y=Double.valueOf(y);
    }


}
