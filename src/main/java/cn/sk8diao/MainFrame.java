package cn.sk8diao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者: 刁旺睿
 * 时间: 2022/11/14 11:48
 */
public class MainFrame extends JFrame {

    private Snake snake;  // 蛇
    private JPanel jPanel;  // 游戏棋盘
    private Timer timer;  // 定时器 在规定的的时间内调用蛇移动的方法
    private Node food;  // 食物

    public MainFrame() throws HeadlessException {
        //初始化窗体
        initFrame();
        //初始化游戏棋盘
        initGamePanel();
        //初始化蛇
        initSnake();
        //初始化食物
        initFood();
        //初始化定时器
        initTimer();
        //设置键盘监听
        setKeyListener();
    }

    private void initFood() {
        food = new Node();
        food.random();
    }

    private void setKeyListener() {
        addKeyListener(new KeyAdapter() {
            //当键盘按下时会自动调用此方法
            @Override
            public void keyPressed(KeyEvent e) {
                //键盘中每一个键都有一个编号
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (snake.getDirection() != Direction.DOWN) {
                            snake.setDirection(Direction.UP);
                            System.out.println("上");
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != Direction.LEFT) {
                            snake.setDirection(Direction.RIGHT);
                            System.out.println("右");
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != Direction.UP) {
                            snake.setDirection(Direction.DOWN);
                            System.out.println("下");
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != Direction.RIGHT) {
                            snake.setDirection(Direction.LEFT);
                            System.out.println("左");
                        }
                        break;
                }
            }
        });
    }

    private void initTimer() {
        //创建定时器对象
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                snake.move();
                //判断蛇头是否与食物重合
                Node head = snake.getBody().getFirst();
                if (head.getX() == food.getX() && head.getY() == food.getY()) {
                    snake.eat(food);
                    food.random();
                }
                //重新绘制游戏棋盘
                jPanel.repaint();
            }
        };
        //每100ms 执行一次定时任务
        timer.scheduleAtFixedRate(timerTask, 0, 200);
    }

    private void initSnake() {
        snake = new Snake();
    }

    public void initFrame() {
        //设置窗体宽高
        setSize(612, 635);
        //设置窗体居中
        setLocationRelativeTo(null);
        //设置关闭按钮作用
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置窗体大小不可变
        setResizable(false);
        //设置标题
        setTitle("贪吃蛇 by刁旺睿");
    }

    public void initGamePanel() {
        jPanel = new JPanel() {
            //绘制游戏棋盘中的内容
            @Override
            public void paint(Graphics g) {
                //清空棋盘
                g.clearRect(0, 0, 600, 600);
                //绘制40条横竖线
                for (int i = 0; i <= 20; i++) {
                    g.drawLine(0, i * 30, 600, i * 30);
                    g.drawLine(i * 30, 0, i * 30, 600);
                }
                //绘制蛇
                LinkedList<Node> body = snake.getBody();
                for (Node node : body) {
                    g.setColor(Color.PINK);
                    g.fillRect(node.getX() * 30, node.getY() * 30, 30, 30);
                }
                //绘制食物
                g.setColor(Color.GREEN);
                g.fillRect(food.getX() * 30, food.getY() * 30, 30, 30);
            }
        };
        add(jPanel);
    }
}