package SC2_simplify.world.player;

import SC2_simplify.mainUnit.Building.ZergBuilding.*;
import SC2_simplify.mainUnit.Building.*;
import SC2_simplify.mainUnit.Unit.UnitType.*;
import java.awt.image.*;
import java.awt.Graphics;
import java.util.*;
import SC2_simplify.mainUnit.Unit.ZergUnit.*;
import SC2_simplify.world.WorldRunner;
import SC2_simplify.mainUnit.Unit.*;
import sun.awt.Graphics2Delegate;
import SC2_simplify.GUIsupport.Order;
import javax.swing.*;
import java.awt.*;

public class Player {

    private int unitNum = 0;
    private int BuildingNum = 1;
    private int playerID;
    private int score = 0;
    private ArrayList<Unit> UnitList = new ArrayList<Unit>();// 注意一定要初始化ArrayList!!不然在初始化会有Exception in thread "main"
    private String buyItemStatus = null; // java.lang.NullPointerException的空指针问题
    private ArrayList<Building> BuildingList = new ArrayList<Building>();
    private int minerals = 1000, vespene = 200;
    public Map<String, Price> priceMap = new HashMap<String, Price>();
    public Map<String, Integer> unitIDMap = new HashMap<String, Integer>();
    private WorldRunner runner;

    public Player(WorldRunner runner) {
        minerals = 1000;
        vespene = 200;
        priceMap.put("hydralisk", new Price(200, 100));
        priceMap.put("zergling", new Price(50, 0));
        priceMap.put("khaydarin", new Price(300, 200));
        priceMap.put("photoncannon", new Price(150, 0));
        priceMap.put("shieldbattery", new Price(100, 0));
        unitIDMap.put("hydralisk", 1);
        unitIDMap.put("zergling", 2);
        unitIDMap.put("photoncannon", 3);
        unitIDMap.put("khaydarin", 4);
        unitIDMap.put("shieldbattery", 5);
        this.runner = runner;
        score = 0;
    }

    public String getBuyItemStatus() {
        return buyItemStatus;
    }

    public void addMinerals(int plus) {
        minerals += plus;
    }

    public void addVespene(int plus) {
        vespene += plus;
    }

    public void setBuyItemStatus(String chooseItem) {
        buyItemStatus = chooseItem;
    }

    public void addScore(int plus) {
        score += plus;
    }

    public int getScore() {
        return score;
    }

    public boolean canBuy(String name, int status) {
        if (status == 0)
            return minerals >= priceMap.get(name).minerals;
        else
            return vespene >= priceMap.get(name).vespene;
    }

    public void buy(String unitName, int x, int y) {
        if (minerals >= priceMap.get(unitName).minerals && vespene >= priceMap.get(unitName).vespene) {
            // System.out.println(minerals + "" + priceMap.get(unitName).minerals);
            // 超出边界
            if ((x < 100 || x > 300 || y < 100 || y > 300) && unitIDMap.get(unitName) >= 3)
                return;
            runner.receiveOrder(new Order(1, unitIDMap.get(unitName), 1, x, y));
            minerals -= priceMap.get(unitName).minerals;
            vespene -= priceMap.get(unitName).vespene;
            // return new
            // Price(priceMap.get(unitName).minerals,priceMap.get(unitName).vespene);
        } else
            return;
        // return new Price(0, 0);
    }

    /*
     * public void draw(Graphics g, ImageObserver observer) { g.drawString(new
     * Integer(minerals).toString(), 700, 100); }
     */
    public int getMinerals() {
        return minerals;
    }

    public int getVespene() {
        return vespene;
    }
}