package SC2_simplify.GUIsupport;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class ScoreBoard extends JFrame {
    String scores;
    String str;

    public void readScores() {
        try {
            FileReader fileReader = new FileReader("SC2_simplify/chartlet/score.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            str = "";
            String delta;
            while ((delta = bufferedReader.readLine()) != null) {
                str += delta+"\n";
            }
        } catch (Exception e) {
        }

    }

    public ScoreBoard() {
        setLayout(null);
        setFont(new Font("Helvetica", Font.PLAIN, 14));
        ImageIcon bg = new ImageIcon("SC2_simplify/chartlet/entrance.jpg");
        JLabel label = new JLabel(bg);
        JPanel pan = (JPanel) this.getContentPane();
        pan.setOpaque(false);
        readScores();
        JButton goBack = new JButton("go back");
        JLabel scoreBoardLabel = new JLabel("<html><pre>Score Board\n" + str + "</pre></html>");
        scoreBoardLabel.setForeground(Color.WHITE);
        getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        label.setBounds(0, 0, 600, 600);
        getContentPane().add(scoreBoardLabel, new Integer(0));
        getContentPane().add(goBack);
        scoreBoardLabel.setBounds(50, 20, 500, 200);
        scoreBoardLabel.setVisible(true);
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
        private ScoreBoard scoreBoard;

        MyMonitor(ScoreBoard scoreBoard) {
            this.scoreBoard = scoreBoard;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // 输入 e. 查看源码.
            if (e.getActionCommand() == "go back") {
                // Window win = SwingUtilities.getWindowAncestor(gameFrame);
                // win.dispose();
                System.out.println(1);
                Entrance entrance = new Entrance();
                scoreBoard.dispose();
            }
        }

    }
}