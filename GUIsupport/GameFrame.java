package SC2_simplify.GUIsupport;

/*
 * 多线程类
 * 这个类拿来完成多线程操作，调用TotalFrame的repaint画图，同时承载监视线程
*/
import SC2_simplify.GUIsupport.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

public class GameFrame extends JFrame {
    myTimerTask game;
    myTimerTask game2;
    myTimerTask musicPlayerTask;
    java.util.Timer t;

    public void gameStart() { // 该方法用来启动计时器
        t = new java.util.Timer(); // 建立计时器对象
        // java.util.Timer t1 = new java.util.Timer();
        game = new myTimerTask(this); // 建立计时任务对象
        game2 = new myTimerTask(1, game);
        musicPlayerTask = new myTimerTask(1);
        t.scheduleAtFixedRate(game, 0, 1000 / 24); // 设定帧速率并启动计时任务
        t.scheduleAtFixedRate(game2, 0, 1000 / 24);
        t.scheduleAtFixedRate(musicPlayerTask, 0, 1000 / 24);
        // t.scheduleAtFixedRate(game3, 0, 1000);
    }

    class myTimerTask extends TimerTask {// 建立内部类，继承TimerTask类
        GUIFrame world;
        int x = 1;
        int mode;
        myTimerTask g;
        int timeCount = 0;// 从头开始计算时间，用于攻速处理和制造单位
        MusicPlayer music;

        myTimerTask(GameFrame gamePanel) {// 唯一的主线程GUIFrame
            super();
            world = new GUIFrame(gamePanel);
            world.setName("Test2两个按钮实现同一个监听");
            world.setVisible(true);
            world.setLocation(100, 100);
            world.setSize(800, 800);
            world.getContentPane().setBackground(new Color(50, 125, 240));
            mode = 1;
        }

        myTimerTask(int x) {// 音乐播放线程
            music = new MusicPlayer();
            music.loopMusic();
            mode = 3;
        }

        myTimerTask(int x, myTimerTask g) {
            this.x = x;
            this.g = g;
            mode = 2;
        }

        // timerTask里面不能更新UI项目，必须在UI线程里面！
        // repaint调用paintComponent
        public void run() { // 重载run()方法，将游戏循环放置其中
            if (mode == 1) {
                // world.warField.update(timeCount, 1);// 所有相关环境对象斗争update里面更新
                world.warField.repaint();
                timeCount++;
                // System.out.println(1);
            } else if (mode == 2)
            // System.out.println(g.world.warField.arr.size());
            {
                g.world.warField.update(timeCount, 2);
                // world.warField.repaint();
                timeCount++;
                // System.out.println(2);
            } else {

            }
            // System.out.println(x);

        }

    }
}