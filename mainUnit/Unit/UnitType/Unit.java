package SC2_simplify.mainUnit.Unit.UnitType;

import java.awt.image.*;
import java.awt.*;
import java.math.*;
import SC2_simplify.mainUnit.material.*;
import SC2_simplify.world.WorldRunner;

public abstract class Unit implements Attackable, Measureable {
    private double hp = 0;
    private double placex = 0, placey = 0;
    private double ATK = 0;
    private double defence = 0;
    private String name = "";
    private double side = 1;// 势力（玩家）归属
    private boolean alive = true;
    private static double testMode = 1;// 调试模式，用于控制输出调试语句，这样不用一句句注释了 1:详细信息 0：不需要输出
    private double ID;
    private static double totalID = 0;
    private String chartlet;
    private double fullHp;
    public Image img;
    private int attackRange;
    private Myspeed speed;
    private double speedAmount;
    private double collideVolume;
    public WorldRunner wr;

    class Myspeed {
        double speedx, speedy;

        Myspeed(double speedx, double speedy) {
            this.speedx = speedx;
            this.speedy = speedy;
        }
    }

    public void recover(double x) {
        if (hp + x >= fullHp)
            hp = fullHp;
        else
            hp += x;
    }

    public boolean alive() {
        return hp > 0;
        // 销毁

    }

    public void setchartlet(String s) {
        chartlet = s;
    }

    public void setImage(Image img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public double getSide() {
        return side;
    }

    public double getTestMode() {
        return testMode;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public double getHp() {
        return hp;
    }

    public double getFullHP() {
        return fullHp;
    }

    public double getID() {
        return ID;
    }

    public void setName(String s) {
        name = s;
    }

    public void setSide(double to) {
        side = to;
    }

    public double getSpeedX() {
        return speed.speedx;
    }

    public double getSpeedY() {
        return speed.speedy;
    }

    public double getATK() {
        return ATK;
    }

    // dictx，dicty决定方向，根据路径方向分配横纵速度
    public void setSpeed(double dictx, double dicty) {
        if (dictx == 0 && dicty == 0) {
            speed.speedx = 0;
            speed.speedy = 0;
            return;
        }
        speed.speedx = speedAmount * (dictx / Math.sqrt(Math.pow(dictx, 2.0) + Math.pow(dicty, 2.0)));
        speed.speedy = speedAmount * (dicty / Math.sqrt(Math.pow(dictx, 2.0) + Math.pow(dicty, 2.0)));
    }

    public double getCollideVolume() {
        return collideVolume;
    }

    public void placeChange() {
        if (placex + speed.speedx >= 400 || placex + speed.speedx <= 0)
            placex -= speed.speedx;
        if (placey + speed.speedy >= 600 || placey + speed.speedy <= 0)
            placey -= speed.speedy;
        placey += speed.speedy;
        placex += speed.speedx;
    }

    public void setPlace(double x, double y) {
        placex = x;
        placey = y;
    }

    public int getX() {
        return (int) placex;
    }

    public int getY() {
        return (int) placey;
    }

    public Unit(double hp, double placex, double placey, double ATK, int attackRange, double defence, double side,
            double speedAmount, double collideVolume, WorldRunner wr) {
        this.hp = hp;
        this.fullHp = hp;
        this.placex = placex;
        this.placey = placey;
        this.ATK = ATK;
        this.defence = defence;
        this.side = side;
        totalID++;
        this.ID = totalID;
        this.attackRange = attackRange;
        this.speed = new Myspeed(0, 0);
        this.speedAmount = speedAmount;
        this.collideVolume = collideVolume;
        this.wr = wr;
    }

    public void ATK_improve() {
        this.ATK *= 1.1;
    }

    public void defence_improve() {
        this.defence++;
    }

    public void hurt(double bonus) {
        hp -= bonus;
    }

    public void attack(Attackable enemy, int timeCount) {
        enemy.hurt(ATK);
    }

    public void draw(Graphics g, int sizex, int sizey, ImageObserver observer) {
        g.drawImage(img, (int) placex, (int) placey, sizex, sizey, observer);
    }

}