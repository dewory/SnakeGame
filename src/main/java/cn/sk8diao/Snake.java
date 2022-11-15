package cn.sk8diao;

import java.util.LinkedList;

/**
 * 作者: 刁旺睿
 * 时间: 2022/11/15 10:27
 */
public class Snake {

    //创建蛇身
    private LinkedList<Node> body;

    //蛇的运动方向 默认向右
    private Direction direction = Direction.RIGHT;

    //蛇是否活着
    private boolean isLiving = true;

    public Snake() {
        //初始化蛇身体
        initSnake();
    }

    private void initSnake() {
        //创建集合
        body = new LinkedList<>();
        //创建6个节点
        body.add(new Node(12, 10));
        body.add(new Node(11, 10));
        body.add(new Node(10, 10));
        body.add(new Node(9, 10));
        body.add(new Node(8, 10));
        body.add(new Node(7, 10));
    }

    //蛇会沿着蛇头的方向移动
    //在蛇头的运动方向添加一个节点 然后把蛇尾节点删除
    public void move() {
        if (isLiving) {
            //获取蛇头
            Node head = body.getFirst();

            switch (direction) {
                case UP -> body.addFirst(new Node(head.getX(), head.getY() - 1));  // 在蛇头的上边添加一个节点
                case RIGHT -> body.addFirst(new Node(head.getX() + 1, head.getY()));
                case DOWN -> body.addFirst(new Node(head.getX(), head.getY() + 1));
                case LEFT -> body.addFirst(new Node(head.getX() - 1, head.getY()));
            }

            //删除最后节点
            body.removeLast();

            //判断蛇是否撞墙
            head = body.getFirst();
            if (head.getX() < 0 || head.getY() < 0 || head.getX() >= 20 || head.getY() >= 20) {
                this.isLiving = false;
            }

            //判断蛇是否碰到自己的身体
            for (int i = 1; i < body.size(); i++) {
                Node node = body.get(i);
                if (head.getX() == node.getX() && head.getY() == node.getY()) {
                    this.isLiving = false;
                    break;
                }
            }
        }
    }

    public LinkedList<Node> getBody() {
        return body;
    }

    public void setBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    //吃食物 沿着舌头的移动方向添加节点
    public void eat(Node food) {
        //获取蛇头
        Node head = body.getFirst();
        switch (direction) {
            case UP -> body.addFirst(new Node(head.getX(), head.getY() - 1));  // 在蛇头的上边添加一个节点
            case RIGHT -> body.addFirst(new Node(head.getX() + 1, head.getY()));
            case DOWN -> body.addFirst(new Node(head.getX(), head.getY() + 1));
            case LEFT -> body.addFirst(new Node(head.getX() - 1, head.getY()));
        }
    }
}
