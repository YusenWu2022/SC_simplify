package SC2_simplify.GUIsupport;

/*
 * 画布监视器类+环境对象运行代理类
 * 这个类用于监视鼠标点击位置（图上具体位置）并在repaint->draw定义绘图函数,优化后只负责draw画图
*/
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import SC2_simplify.mainUnit.Unit.ZergUnit.Hydralisk;
import SC2_simplify.world.WorldRunner;
import SC2_simplify.world.player.Player;

import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

@SuppressWarnings("serial")
public class MapCanvas extends JPanel {

    int x = 20, y = 20; // 设置初试坐标
    int mode = 1; // 表示默认绘制模式,1:拖动，2：移动
    static Image img, zerglingImg;
    GUIFrame gui;
    WorldRunner runner = new WorldRunner();
    static {
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/map.jpg"));
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }
    // mycanvas Mycanvas;

    /*
     * class mycanvas extends Canvas {// 内部类
     * 
     * public void paint(Graphics g, int x, int y) { Image image = new
     * ImageIcon("sailboat.jpg").getImage();// 获取图片资源 g.drawImage(image, x, y,
     * this);// 绘制图像
     * 
     * } }
     */
    public void updatePrice() {
        gui.textMinerals.setText(new Integer(runner.I.getMinerals()).toString());
        gui.textVespene.setText(new Integer(runner.I.getVespene()).toString());
        for (int i = 0; i < gui.priceBoard.size(); i++)
            if (runner.I.canBuy(runner.nameArr.get(i), 0))
                gui.priceBoard.get(i).setForeground(Color.BLACK);
            else
                gui.priceBoard.get(i).setForeground(Color.RED);
        for (int i = 0; i < gui.vespeneBoard.size(); i++)
            if (runner.I.canBuy(runner.nameArr.get(i), 1))
                gui.vespeneBoard.get(i).setForeground(Color.BLACK);
            else
                gui.vespeneBoard.get(i).setForeground(Color.RED);
    }

    MapCanvas(GUIFrame gui) {
        super();
        this.gui = gui;
        this.setOpaque(true);
        this.setBackground(Color.red);
        setSize(300, 200);

        // Image image = new ImageIcon("src/ycy.jpg").getImage();
        addMouseMotionListener(new MouseMotionListener() {// 创建匿名内部类
            public void mouseDragged(MouseEvent e) { // 鼠标拖动
                mode = 1;
                x = e.getX(); // x轴的坐标
                y = e.getY(); // y轴的坐标
                // repaint();
            }

            public void mouseMoved(MouseEvent e) { // 鼠标移动
                mode = 2; // 设置为移动模式
                x = e.getX();
                y = e.getY();

                // repaint();
            }
        });
        // Mycanvas = new mycanvas();
        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                mode = 3;
                x = e.getX();
                y = e.getY();
                runner.I.buy(runner.I.getBuyItemStatus(), x, y);
                updatePrice();
                System.out.println("click");
                // repaint();
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }
        });

    }

    // repaint 调用这个，间接调用draw
    // 原来如此！！好耶，不是repaint的问题，而是自己重写的paintComponent里面mode美更新的问题！之前写的鼠标检测移动会导致没有draw
    // 事实证明依次print几次还是很好用的。
    // 下一步可以开始添加鼠标符号和游戏算法了!还有拆包重组！
    public void paintComponent(Graphics g) {
        if (mode == 1)
            g.setFont(new Font("宋体", Font.BOLD, g.getFont().getSize() + 10));
        if (mode == 3)
            g.setFont(new Font("宋体", Font.BOLD, g.getFont().getSize() + 30));
        if (mode == 3) {
        }
        g.clearRect(0, 0, 400, 600); // 清屏
        draw(g, x, y);
        // System.out.println(-1);
    }

    // 有函数调用repaint时候自动调用draw
    public void draw(Graphics g, int x, int y) {
        // g.drawString("Hello, Java世界", x, y);
        // System.out.println(0);
        g.drawImage(img, 0, 0, 400, 400, this);
        g.setColor(Color.WHITE);
        g.drawRect(100, 100, 200, 200);
        setBackground(Color.blue);
        gui.textMinerals.setText(new Integer(runner.I.getMinerals()).toString());
        gui.textVespene.setText(new Integer(runner.I.getVespene()).toString());
        gui.textScore.setText(new Integer(runner.I.getScore()).toString());
        for (int i = 0; i < runner.MyTowerArr.size(); i++) {
            runner.MyTowerArr.get(i).draw(g, 40, 40, this);
        }
        for (int i = 0; i < runner.MyUnitArr.size(); i++) {
            runner.MyUnitArr.get(i).draw(g, 20, 20, this);

        }
        for (int i = 0; i < runner.OppositeUnitArr.size(); i++) {
            runner.OppositeUnitArr.get(i).draw(g, 20, 20, this);
        }
        for (int i = 0; i < runner.BulletArr.size(); i++) {
            runner.BulletArr.get(i).draw(g, 4, 4, this);
        }
        for (int i = 0; i < runner.AwardArr.size(); i++) {
            runner.AwardArr.get(i).draw(g, this);
        }
        if (mode == 3)
            g.drawImage(img, x, y, 40, 40, this);
        // for(int i=1;i<=)
        // Mycanvas.paint(g, x, y);
    }

    public Dimension getPreferredSize() { // 获取最佳尺寸
        return new Dimension(400, 400);
    }

    public void update(int timeCount, int mode) {
        if (runner.checkEndGame())// 检查基地掉没掉
        {
            System.out.println(1);
            gui.endGame();
        } else {
            updatePrice();
            runner.update(timeCount, mode);
        }
    }
}