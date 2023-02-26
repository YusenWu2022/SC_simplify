package SC2_simplify.world;

import SC2_simplify.GUIsupport.Order;
import SC2_simplify.mainUnit.Unit.ZergUnit.Hydralisk;
import SC2_simplify.mainUnit.Unit.ZergUnit.Zergling;
import SC2_simplify.mainUnit.material.*;
import SC2_simplify.world.player.AI;
import SC2_simplify.world.player.*;
import SC2_simplify.mainUnit.Unit.UnitType.Unit;
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
import java.math.*;
import SC2_simplify.mainUnit.Building.TerranBuilding.*;
import SC2_simplify.mainUnit.Unit.BulletUnit.*;

//在这个类里面进行交战，移动和死亡的更新
public class WorldRunner {

    public ArrayList<Unit> MyUnitArr;
    public ArrayList<Unit> OppositeUnitArr;
    public ArrayList<Tower> MyTowerArr;
    public ArrayList<Bullet> BulletArr;
    public ArrayList<AI> EnemyArr;
    public ArrayList<ShowAward> AwardArr;
    public Player I;
    private boolean endGameCheck = false;
    private int mineralsEarn = 0, vespeneEarn = 0;
    private int grade = 0;
    public ArrayList<String> nameArr;

    public void receiveOrder(Order order) {
        System.out.println("order");
        switch (order.type) {
            case 1:// add
                switch (order.kind) {
                    case 1:// hydralisk
                        if (order.side == 1)
                            MyUnitArr.add(new Hydralisk(order.x, order.y, order.side, 1, 14, this));
                        else
                            OppositeUnitArr.add(new Hydralisk(order.x, order.y, order.side, 1, 14, this));
                        break;
                    case 2:// zergling
                        if (order.side == 1)
                            MyUnitArr.add(new Zergling(order.x, order.y, order.side, 1, 14, this));
                        break;
                    case 3:// photoncannon,建筑，只有玩家会建
                        MyTowerArr.add(new PhotonCannon(order.x, order.y, order.side, 1, 28, this));
                        break;
                    case 4:// Khaydarin,建筑，只有玩家会建
                        MyTowerArr.add(new Khaydarin(order.x, order.y, order.side, 1, 28, this));
                        break;
                    case 5:// shieldbattery,建筑，只有玩家会建
                        MyTowerArr.add(new ShieldBattery(order.x, order.y, order.side, 1, 28, this));
                        break;

                }
                break;
            case 2:// remove
                switch (order.kind) {
                    case 1:// hydralisk
                        if (order.side == 1)
                            MyUnitArr.remove(MyUnitArr.size() - 1);
                        else
                            OppositeUnitArr.remove(OppositeUnitArr.size() - 1);
                        break;

                }

        }
    }

    public WorldRunner() {
        MyUnitArr = new ArrayList<Unit>();
        OppositeUnitArr = new ArrayList<Unit>();
        MyTowerArr = new ArrayList<Tower>();
        MyTowerArr.add(new Base(1500, 200, 200, 100, 80, this));
        BulletArr = new ArrayList<Bullet>();
        EnemyArr = new ArrayList<AI>();
        AwardArr = new ArrayList<ShowAward>();
        EnemyArr.add(new AI(this));
        I = new Player(this);
        nameArr = new ArrayList<String>();
        nameArr.add("zergling");
        nameArr.add("hydralisk");
        nameArr.add("khaydarin");
        nameArr.add("photoncannon");
        nameArr.add("shieldbattery");
        // hydraliskArr.add(new Point(1, 1));
        // hydralisksOppositeArr.add(new Point(300, 100));
        // zerglingArr.add(new Point(300, 100));
    }

    public boolean checkEndGame() {
        return endGameCheck;
    }

    // 使用可比较接口，求两个对象之间的距离
    double distance(Measureable x, Measureable y) {
        return Math.sqrt(Math.pow(x.getX() - y.getX(), 2) + Math.pow(x.getY() - y.getY(), 2));
    }

    boolean checkCollision(Unit thisUnit, ArrayList<Unit> MyUnitArr, ArrayList<Unit> OppositeArr, int selfIndex) {
        for (int i = 0; i < MyUnitArr.size(); i++) {
            if (i == selfIndex)
                continue;
            if (Math.sqrt(Math.pow(MyUnitArr.get(i).getX() - thisUnit.getX(), 2)
                    + Math.pow(MyUnitArr.get(i).getY() - thisUnit.getY(), 2)) <= thisUnit.getCollideVolume()
                            + MyUnitArr.get(i).getCollideVolume())
                return true;
        }
        for (int i = 0; i < OppositeArr.size(); i++) {
            if (Math.sqrt(Math.pow(OppositeArr.get(i).getX() - thisUnit.getX(), 2)
                    + Math.pow(OppositeArr.get(i).getY() - thisUnit.getY(), 2)) <= thisUnit.getCollideVolume()
                            + OppositeArr.get(i).getCollideVolume())
                return true;
        }
        return false;
    }

    // 根据游戏内容更新这一帧内容的变化
    public void update(int timeCount, int mode) {
        // enviUpdate();//环境托管内容更新
        mineralsEarn = 0;
        vespeneEarn = 0;
        if (MyTowerArr.size() > 0)
            endGameCheck = !MyTowerArr.get(0).isAlive();
        else
            endGameCheck = true;
        MyTowerArr.removeIf(i -> !i.isAlive());
        for (int i = 0; i < MyTowerArr.size(); i++) {
            MyTowerArr.get(i).findAttack(OppositeUnitArr, MyTowerArr, timeCount);
        }
        MyUnitArr.removeIf(i -> !i.alive());// labmda表达式批量去除整个数组中符合条件的项
        for (int i = 0; i < MyUnitArr.size(); i++) {
            boolean needMove = true;
            // MyUnitArr.get(i).placeChange(0.5, 0);
            for (int j = 0; j < OppositeUnitArr.size(); j++)
                if (Math.sqrt(Math.pow(MyUnitArr.get(i).getX() - OppositeUnitArr.get(j).getX(), 2)
                        + Math.pow(MyUnitArr.get(i).getY() - OppositeUnitArr.get(j).getY(), 2)) < MyUnitArr.get(i)
                                .getAttackRange()) {
                    // MyUnitArr.get(i).placeChange(-0.5, 0);
                    MyUnitArr.get(i).setSpeed(0, 0);
                    if (timeCount % 50 == 0)// 冷却到了就开打；可以改成类里面定义攻速和上一次攻击时间，判断是否有cd
                        MyUnitArr.get(i).attack(OppositeUnitArr.get(j), timeCount);
                    needMove = false;
                    break;
                }
            if (!needMove)
                continue;
            if (OppositeUnitArr.size() == 0) {
                continue;
            }
            MyUnitArr.get(i).setSpeed(OppositeUnitArr.get(0).getX() - MyUnitArr.get(i).getX(),
                    OppositeUnitArr.get(0).getY() - MyUnitArr.get(i).getY());// 朝着目标走过去
            if (checkCollision(MyUnitArr.get(i), MyUnitArr, OppositeUnitArr, i)) {
                MyUnitArr.get(i).setSpeed(-MyUnitArr.get(i).getSpeedY(), MyUnitArr.get(i).getSpeedX());
                // if (checkCollision(MyUnitArr.get(i), MyUnitArr, OppositeUnitArr)) {
                // MyUnitArr.get(i).setSpeed(-MyUnitArr.get(i).getSpeedX(),
                // -MyUnitArr.get(i).getSpeedY());
                // if (checkCollision(MyUnitArr.get(i), MyUnitArr, OppositeUnitArr))
                // MyUnitArr.get(i).setSpeed(0, 0);
                // }
            }
            MyUnitArr.get(i).placeChange();

        }
        // OppositeUnitArr.removeIf(i -> !i.alive());
        for (int i = 0; i < OppositeUnitArr.size(); i++) {
            if (OppositeUnitArr.get(i).getHp() <= 0) {
                if (OppositeUnitArr.get(i).getName() == "hydralisk") {// 查询对应奖励;
                    I.addMinerals(50);
                    I.addVespene(25);
                    I.addScore(100);
                    AwardArr.add(new ShowAward(OppositeUnitArr.get(i).getX(), OppositeUnitArr.get(i).getY(), 50, 25,
                            timeCount));
                    // 这里面可以加入+50的奖励蓝色字
                } else if (OppositeUnitArr.get(i).getName() == "zergling") {
                    I.addMinerals(25);
                    I.addScore(40);
                    AwardArr.add(new ShowAward(OppositeUnitArr.get(i).getX(), OppositeUnitArr.get(i).getY(), 25, 0,
                            timeCount));
                } else if (OppositeUnitArr.get(i).getName() == "Ultralisk") {
                    I.addMinerals(100);
                    I.addVespene(100);
                    AwardArr.add(new ShowAward(OppositeUnitArr.get(i).getX(), OppositeUnitArr.get(i).getY(), 100, 100,
                            timeCount));
                } else if (OppositeUnitArr.get(i).getName() == "tank") {
                    I.addMinerals(100);
                    I.addVespene(50);
                    AwardArr.add(new ShowAward(OppositeUnitArr.get(i).getX(), OppositeUnitArr.get(i).getY(), 100, 50,
                            timeCount));
                }
                OppositeUnitArr.remove(i);
                i--;// 防止空指针
            }
        }
        for (int i = 0; i < OppositeUnitArr.size(); i++) {
            boolean needMove = true;
            // MyUnitArr.get(i).placeChange(0.5, 0);
            for (int j = 0; j < MyUnitArr.size(); j++)// 优先打单位
                if (Math.sqrt(Math.pow(MyUnitArr.get(j).getX() - OppositeUnitArr.get(i).getX(), 2)
                        + Math.pow(MyUnitArr.get(j).getY() - OppositeUnitArr.get(i).getY(), 2)) < OppositeUnitArr.get(i)
                                .getAttackRange()) {
                    // MyUnitArr.get(i).placeChange(-0.5, 0);
                    OppositeUnitArr.get(i).setSpeed(0, 0);
                    if (timeCount % 50 == 0)// 冷却到了就开打；可以改成类里面定义攻速和上一次攻击时间，判断是否有cd
                        OppositeUnitArr.get(i).attack(MyUnitArr.get(j), timeCount);
                    needMove = false;
                    break;
                }
            if (needMove)// 还没在打
            {
                if (MyUnitArr.size() == 0) {
                    boolean needMove1 = true;
                    for (int j = MyTowerArr.size() - 1; j >= 0; j--) {// 倒着走，必须清理掉所有防御塔才能攻击基地
                        if (Math.sqrt(Math.pow(MyTowerArr.get(j).getX() - OppositeUnitArr.get(i).getX(), 2) + Math
                                .pow(MyTowerArr.get(j).getY() - OppositeUnitArr.get(i).getY(), 2)) < OppositeUnitArr
                                        .get(i).getAttackRange()) {
                            needMove1 = false;
                            OppositeUnitArr.get(i).setSpeed(0, 0);
                            if (timeCount % 50 == 0)
                                OppositeUnitArr.get(i).attack(MyTowerArr.get(j), timeCount);
                            break;
                        }
                    }
                    if (needMove1) {// 还没找到目标
                        int j = MyTowerArr.size() - 1;
                        if (j >= 0)
                            OppositeUnitArr.get(i).setSpeed(MyTowerArr.get(j).getX() - OppositeUnitArr.get(i).getX(),
                                    MyTowerArr.get(j).getY() - OppositeUnitArr.get(i).getY());
                    }
                    // continue;
                } else
                    OppositeUnitArr.get(i).setSpeed(MyUnitArr.get(0).getX() - OppositeUnitArr.get(i).getX(),
                            MyUnitArr.get(0).getY() - OppositeUnitArr.get(i).getY());
                if (checkCollision(OppositeUnitArr.get(i), OppositeUnitArr, MyUnitArr, i)) {
                    OppositeUnitArr.get(i).setSpeed(-OppositeUnitArr.get(i).getSpeedY(),
                            OppositeUnitArr.get(i).getSpeedX());
                    // if (checkCollision(OppositeUnitArr.get(i), MyUnitArr, OppositeUnitArr)) {
                    // OppositeUnitArr.get(i).setSpeed(-OppositeUnitArr.get(i).getSpeedX(),
                    // -OppositeUnitArr.get(i).getSpeedY());
                    // if (checkCollision(OppositeUnitArr.get(i), MyUnitArr, OppositeUnitArr))
                    // MyUnitArr.get(i).setSpeed(0, 0);
                    // }
                }
            }
            OppositeUnitArr.get(i).placeChange();// 统一移动
        }
        // MyTowerArr.removeIf(i -> i.getHp() < 0);
        // 由于需要挣钱，所以还是勤劳点自己实现
        // System.out.println(timeCount);
        // System.out.println(arr.size());
        // if (mode == 2 && timeCount % 200 == 0)
        // OppositeUnitArr.add(new Hydralisk(400, 100, 2, 1, 10, this));
        // AIUpdate();//AI内容更新
        // 玩家内容更新
        // 把只从右侧出兵改为AI从所有方向暴兵！
        BulletArr.removeIf(i -> !i.isAlive() || i.timeOver(timeCount));
        for (int i = 0; i < BulletArr.size(); i++) {
            BulletArr.get(i).placeChange();
        }
        AwardArr.removeIf(i -> i.timeOver(timeCount));
        for (int i = 0; i < EnemyArr.size(); i++) {
            Unit tmp = EnemyArr.get(i).produceUnit(timeCount);
            if (tmp != null)
                OppositeUnitArr.add(tmp);
        }
        if (I.getScore() >= grade * 1000 + 1000) {
            EnemyArr.add(new AI(this));
            grade++;
        }

    }

}