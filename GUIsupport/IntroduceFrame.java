package SC2_simplify.GUIsupport;

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
import javafx.scene.layout.Background;

@SuppressWarnings("serial")
public class IntroduceFrame extends JFrame {

    public IntroduceFrame() {
        setLayout(null);
        setFont(new Font("Helvetica", Font.PLAIN, 14));
        ImageIcon bg = new ImageIcon("SC2_simplify/chartlet/entrance.jpg");
        JLabel label = new JLabel(bg);
        JPanel pan = (JPanel) this.getContentPane();
        pan.setOpaque(false);
        JButton goBack = new JButton("go back");
        JLabel introduceLabel = new JLabel(
                "<html><pre>这是一个防御基地的小游戏。\n(本来它应该是一个完整的RTS游戏，但由于组员退课不得不削减工时o_o)\n这个版本的基本情况是分成不同等级依次通关，\n每一关敌人刷的方向和数量、等级都会增加,但也会获得对应的分数\n基本操作简单易上手，在右边栏的单位和防御塔中选择并在左侧释放\n由于人手不足，目前暂不支持控制，它们会自动索敌并战斗\n击败敌人挣钱得分升级\n祝玩得开心。by Ice</pre></html>");
        introduceLabel.setForeground(Color.WHITE);
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        label.setBounds(0, 0, 600, 600);
        getContentPane().add(introduceLabel, new Integer(0));
        getContentPane().add(goBack);
        introduceLabel.setBounds(50, 20, 500, 200);
        introduceLabel.setVisible(true);
        goBack.setBounds(400, 500, 80, 50);
        goBack.addActionListener(new MyMonitor(this));
        setSize(600, 600);
        setVisible(true);

    }

    // Icon ic=new ImageIcon("load.jpg"); JLabel lb=new JLabel(ic); JPanel p=new
    // JPanel(); p.add(lb);
    private static class MyMonitor implements ActionListener {
        // build the ActionLister for the goBack button and the south button ,named
        // myActionListener
        IntroduceFrame introduceFrame;

        MyMonitor(IntroduceFrame introduceFrame) {
            this.introduceFrame = introduceFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 输入 e. 查看源码.
            if (e.getActionCommand() == "go back") {
                // Window win = SwingUtilities.getWindowAncestor(gameFrame);
                // win.dispose();
                System.out.println(1);
                Entrance entrance = new Entrance();
                introduceFrame.dispose();
            }
        }

    }

}