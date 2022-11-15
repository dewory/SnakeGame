package cn.sk8diao;

import java.util.Random;

/**
 * 作者: 刁旺睿
 * 时间: 2022/11/15 10:15
 */
public class Node {

    //每一条蛇是由若干节点组成的
    //每一个节点由横纵坐标来确定位置

    private int x;
    private int y;

    public Node() {
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    //随机生成位置的方法
    public void random() {
        Random random = new Random();
        this.x = random.nextInt(20);
        this.y = random.nextInt(20);
    }

}
