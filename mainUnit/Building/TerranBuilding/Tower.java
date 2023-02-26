package SC2_simplify.mainUnit.Building.TerranBuilding;

import SC2_simplify.mainUnit.material.*;
import SC2_simplify.mainUnit.material.Attackable;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.util.ArrayList;

import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.Unit.UnitType.Unit;
import SC2_simplify.mainUnit.specialFeatures.*;
import SC2_simplify.world.WorldRunner;

public abstract class Tower implements Measureable, Attackable {
    private int placex, placey;
    private int hp;
    private int ATK;
    private int attackRange;
    public WorldRunner wr;
    private int fullHp;

    public boolean isAlive() {
        return hp > 0;
    }

    public int getATK() {
        return ATK;
    }

    public int getX() {
        return placex;
    }

    public int getY() {
        return placey;
    }

    public void hurt(int harm) {
        hp -= harm;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public Tower(int hp, int placex, int placey, int ATK, int attackRange, WorldRunner wr) {
        this.hp = hp;
        this.fullHp = hp;
        this.placex = placex;
        this.placey = placey;
        this.ATK = ATK;
        this.attackRange = attackRange;
        this.wr = wr;
    }

    public void draw(Graphics g, int sizex, int sizey, ImageObserver observer) {
    }

    public void hurt(double harm) {
        hp -= harm;
    }

    public void attack(Attackable target, int timeCount) {
        target.hurt(this.getATK());
    }

    public void recover(double x) {
        System.out.println(1);
        if (hp + x >= fullHp) {
            hp = fullHp;
            System.out.println(2);

        } else {
            System.out.println(3);
            hp += x;
        }
    }

    public double distance(Measureable x) {
        return Math.sqrt(Math.pow(x.getX() - getX(), 2) + Math.pow(x.getY() - getY(), 2));
    }

    public void findAttack(ArrayList<Unit> OppositeArr, ArrayList<Tower> MyTower, int timeCount) {
        for (int j = 0; j < OppositeArr.size(); j++) {
            if (distance(OppositeArr.get(j)) <= getAttackRange())
                if (timeCount % 50 == 0) {
                    attack(OppositeArr.get(j), timeCount);
                    break;
                }

        }
    }

    public int getHp() {
        return hp;
    }

}