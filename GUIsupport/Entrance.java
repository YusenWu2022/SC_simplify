package SC2_simplify.GUIsupport;

/*
 * GUI框架类,总的交互界面
 * 这个类用来承载组装画布MapCanvas类实例和添加按键，并传到GamePanel进行线程操作
*/
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
import SC2_simplify.world.player.*;

@SuppressWarnings("serial")
public class Entrance extends JFrame {

    public Entrance() {
        setLayout(null);
        setFont(new Font("Helvetica", Font.PLAIN, 14));
        ImageIcon bg = new ImageIcon("SC2_simplify/chartlet/entrance.jpg");
        JLabel label = new JLabel(bg);
        label.setBounds(0, 0, 600, 600);
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        JPanel pan = (JPanel) this.getContentPane();
        pan.setOpaque(false);
        JButton begin = new JButton("start game");
        JButton bestScore = new JButton("score board");
        JButton about = new JButton("about");
        Font f = new Font("宋体", Font.BOLD, 8);
        getContentPane().add(begin);
        getContentPane().add(bestScore);
        getContentPane().add(about);
        begin.setBounds(250, 100, 100, 60);
        bestScore.setBounds(250, 200, 100, 60);
        about.setBounds(250, 300, 100, 60);
        begin.addActionListener(new MyMonitor(this));
        bestScore.addActionListener(new MyMonitor(this));
        about.addActionListener(new MyMonitor(this));
        setSize(600, 600);
        setVisible(true);

    }

    // Icon ic=new ImageIcon("load.jpg"); JLabel lb=new JLabel(ic); JPanel p=new
    // JPanel(); p.add(lb);
    private static class MyMonitor implements ActionListener {
        // build the ActionLister for the begin button and the south button ,named
        // myActionListener
        Entrance entranceFrame;

        MyMonitor(Entrance entranceFrame) {
            this.entranceFrame = entranceFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 输入 e. 查看源码.
            if (e.getActionCommand() == "start game") {
                entranceFrame.dispose();
                GameFrame gp = new GameFrame();
                gp.gameStart();
            } else if (e.getActionCommand() == "about") {
                entranceFrame.dispose();
                IntroduceFrame myIntroduceFrame = new IntroduceFrame();
            } else if (e.getActionCommand() == "score board") {
                entranceFrame.dispose();
                ScoreBoard myScoreBoard = new ScoreBoard();
            }
        }

    }

}