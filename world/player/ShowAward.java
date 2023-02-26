package SC2_simplify.world.player;

import SC2_simplify.mainUnit.Building.ZergBuilding.*;
import SC2_simplify.mainUnit.Building.*;
import SC2_simplify.mainUnit.Unit.UnitType.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.util.*;
import SC2_simplify.mainUnit.Unit.ZergUnit.*;
import SC2_simplify.mainUnit.material.Lastable;
import SC2_simplify.world.WorldRunner;
import SC2_simplify.mainUnit.Unit.*;
import sun.awt.Graphics2Delegate;
import SC2_simplify.GUIsupport.Order;
import javax.swing.*;
import java.awt.*;

public class ShowAward implements Lastable {
    public String getMinerals, getVespene;
    public int placex, placey;
    public int lastTime;

    public ShowAward(int x, int y, int minerals, int vespene, int lastTime) {
        getMinerals = new Integer(minerals).toString();
        getVespene = new Integer(vespene).toString();
        placex = x;
        placey = y;
        this.lastTime = lastTime;
    }

    public boolean timeOver(int currentTime) {
        return currentTime - lastTime >= 100;
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.setFont(new Font("Arial", 1, 10));
        g.setColor(Color.BLUE);
        g.drawString("+" + getMinerals, placex, placey);
        g.setColor(Color.GREEN);
        g.drawString("+" + getVespene, placex, placey + 10);
    }
}