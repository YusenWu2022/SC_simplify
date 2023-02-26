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
public class GUIFrame extends JFrame {
    static MapCanvas warField;
    static JTextField textMinerals;
    static JTextField textVespene;
    static int minerals, vespene;
    static GameFrame gameFrame;
    static JTextField textScore;
    static JLabel scoreLabel;
    static JLabel mineralLabel;
    static JLabel vesepeneLabel;
    static ArrayList<JTextField> priceBoard;
    static ArrayList<JTextField> vespeneBoard;

    public GUIFrame(GameFrame gamepanel) {
        this.gameFrame = gamepanel;
        setLayout(null); // 设置为网格布局，未指定行数
        JLabel background = new JLabel(new ImageIcon("SC2_simplify/chartlet/entrance.jpg"));
        setFont(new Font("Helvetica", Font.PLAIN, 14));
        JButton north = new JButton("north");
        JButton south = new JButton("south");
        JButton zerg = new JButton("zerg");
        JButton north1 = new JButton("north1");
        JButton produceZergling = new JButton("zergling");
        JButton produceHydralisk = new JButton("hydralisk");
        JButton buildPhotonCannon = new JButton("photoncannon");
        JButton buildShieldBattery = new JButton("shieldbattery");
        JButton buildKhaydarin = new JButton("khaydarin");
        JButton endGame = new JButton("go back");
        ImageIcon bg1 = new ImageIcon("SC2_simplify/chartlet/score.jpg");
        ImageIcon bg2 = new ImageIcon("SC2_simplify/chartlet/mineral.jpg");
        ImageIcon bg3 = new ImageIcon("SC2_simplify/chartlet/vespene.jpg");
        bg1.setImage(bg1.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        bg2.setImage(bg2.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        bg3.setImage(bg3.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        scoreLabel = new JLabel(bg1);
        mineralLabel = new JLabel(bg2);
        vesepeneLabel = new JLabel(bg3);
        textMinerals = new JTextField("minerals");
        textMinerals.setText("1000");
        textVespene = new JTextField("vespene");
        textVespene.setText("200");
        priceBoard = new ArrayList<JTextField>();
        vespeneBoard = new ArrayList<JTextField>();
        vespeneBoard.add(new JTextField("hydralisk"));
        vespeneBoard.add(new JTextField("zergling"));
        vespeneBoard.add(new JTextField("khaydarin"));
        vespeneBoard.add(new JTextField("photoncannon"));
        vespeneBoard.add(new JTextField("shieldbattery"));
        priceBoard.add(new JTextField("hydralisk"));
        priceBoard.add(new JTextField("zergling"));
        priceBoard.add(new JTextField("khaydarin"));
        priceBoard.add(new JTextField("photoncannon"));
        priceBoard.add(new JTextField("shieldbattery"));
        priceBoard.get(0).setText("50");
        priceBoard.get(1).setText("200");
        priceBoard.get(2).setText("300");
        priceBoard.get(3).setText("150");
        priceBoard.get(4).setText("100");
        vespeneBoard.get(0).setText("0");
        vespeneBoard.get(1).setText("100");
        vespeneBoard.get(2).setText("200");
        vespeneBoard.get(3).setText("0");
        vespeneBoard.get(4).setText("0");
        textScore = new JTextField("score");
        textMinerals.setText("0");
        minerals = 1000;
        vespene = 200;
        Font f = new Font("宋体", Font.BOLD, 8);
        warField = new MapCanvas(this);
        getContentPane().add(mineralLabel);
        getContentPane().add(vesepeneLabel);
        getContentPane().add(scoreLabel);
        getContentPane().add(endGame);
        getContentPane().add(warField);
        // getContentPane().add(north);
        // getContentPane().add(south);
        // getContentPane().add(zerg);
        // getContentPane().add(north1);
        getContentPane().add(produceZergling);
        getContentPane().add(produceHydralisk);
        getContentPane().add(textMinerals);
        getContentPane().add(textVespene);
        for (int i = 0; i < priceBoard.size(); i++) {
            getContentPane().add(priceBoard.get(i));
        }
        for (int i = 0; i < vespeneBoard.size(); i++) {
            getContentPane().add(vespeneBoard.get(i));
        }
        getContentPane().add(textScore);
        getContentPane().add(buildKhaydarin);
        getContentPane().add(buildPhotonCannon);
        getContentPane().add(buildShieldBattery);
        getContentPane().add(background);
        for (int i = 0; i < priceBoard.size(); i++) {
            priceBoard.get(i).setBounds(560, 180 + 40 * i, 30, 30);
        }
        for (int i = 0; i < vespeneBoard.size(); i++) {
            vespeneBoard.get(i).setBounds(600, 180 + 40 * i, 30, 30);
        }
        background.setBounds(-100, -100, 900, 900);
        scoreLabel.setBounds(640, 40, 40, 40);
        mineralLabel.setBounds(640, 120, 40, 40);
        vesepeneLabel.setBounds(640, 200, 40, 40);
        textScore.setBounds(700, 40, 60, 60);
        textMinerals.setBounds(700, 120, 60, 60);
        textVespene.setBounds(700, 200, 60, 60);
        north.setBounds(500, 20, 100, 30);
        south.setBounds(500, 60, 100, 30);
        zerg.setBounds(500, 100, 100, 30);
        north1.setBounds(500, 140, 100, 30);
        produceZergling.setBounds(440, 180, 100, 30);
        produceHydralisk.setBounds(440, 220, 100, 30);
        buildKhaydarin.setBounds(440, 260, 100, 30);
        buildPhotonCannon.setBounds(440, 300, 100, 30);
        buildShieldBattery.setBounds(440, 340, 100, 30);
        endGame.setBounds(600, 500, 100, 60);
        warField.setBounds(20, 40, 400, 400);
        north.addActionListener(new MyMonitor(this));
        south.addActionListener(new MyMonitor(this));
        zerg.addActionListener(new MyMonitor(this));
        produceZergling.addActionListener(new MyMonitor(this));
        produceHydralisk.addActionListener(new MyMonitor(this));
        buildKhaydarin.addActionListener(new MyMonitor(this));
        buildPhotonCannon.addActionListener(new MyMonitor(this));
        buildShieldBattery.addActionListener(new MyMonitor(this));
        endGame.addActionListener(new MyMonitor(this));
    }

    public void endGame() {
        Integer best = 0;
        try {
            FileReader fileReader = new FileReader("SC2_simplify/chartlet/score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = "";
            String delta;
            while ((delta = bufferedReader.readLine()) != null) {
                str += delta;
            }
            best = Integer.parseInt(str);
        } catch (Exception e) {
        }
        File file = new File("SC2_simplify/chartlet/score.txt");
        Writer w;
        if (warField.runner.I.getScore() > best) {
            try {
                w = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(w);
                bw.write(new Integer(warField.runner.I.getScore()).toString() + "\n");
                bw.write(new Integer(best).toString());
                bw.flush();
                bw.close();
            } catch (IOException x) {
            }
        }
        gameFrame.game.cancel();
        gameFrame.game2.cancel();
        gameFrame.musicPlayerTask.music.stopMusic();
        gameFrame.musicPlayerTask.cancel();
        gameFrame.t.purge();
        gameFrame.t.cancel();
        gameFrame.removeAll();
        gameFrame.setVisible(false);
        gameFrame.dispose();
        Entrance entrance = new Entrance();
        dispose();
    }

    // Icon ic=new ImageIcon("load.jpg"); JLabel lb=new JLabel(ic); JPanel p=new
    // JPanel(); p.add(lb);
    public class MyMonitor implements ActionListener {
        // build the ActionLister for the north button and the south button ,named
        // myActionListener
        GUIFrame thisFrame;

        public MyMonitor(GUIFrame x) {
            thisFrame = x;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 输入 e. 查看源码.
            if (e.getActionCommand() == "north") {

                // warField.runner.receiveOrder(new Order(1, 1, 1, 100, 100));
                // System.out.println("north Button been clicked ，and MyMonitor class run
                // successfully.");
            } else if (e.getActionCommand() == "south") {
                // warField.runner.receiveOrder(new Order(2, 1, 1, 0, 0));
                // warField.arr.remove(warField.arr.size() - 1);
                // warField.arr.remove(warField.new Point(100, 100));
                // System.out.println("south Button been clicked ，and MyMonitor class run
                // successfully.");
            } else if (e.getActionCommand() == "zerg") {
                System.exit(0);
            } else if (e.getActionCommand() == "zergling" || e.getActionCommand() == "hydralisk") {
                warField.runner.I.setBuyItemStatus(e.getActionCommand());// 设置购买的status，表示当前选择要生产的单位种类,增强泛化
                // warField.runner.receiveOrder(new Order(1, 2, 1, 0, 100));
                // warField.zerglingArr.add(warField.new Point(100, 100));
            } // else if (e.getActionCommand() == "hydralisk") {// 生产刺蛇
            else if (e.getActionCommand() == "khaydarin" || e.getActionCommand() == "photoncannon"
                    || e.getActionCommand() == "shieldbattery")
                warField.runner.I.setBuyItemStatus(e.getActionCommand());
            // warField.runner.receiveOrder(new Order(1, 1, 1, 0, 100));
            // }
            else if (e.getActionCommand() == "go back") {
                // Window win = SwingUtilities.getWindowAncestor(gameFrame);
                // win.dispose();
                // 在内部类中调用外部类的方法
                GUIFrame.this.endGame();
            }
        }

    }

}