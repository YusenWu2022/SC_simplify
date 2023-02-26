package SC2_simplify.mainUnit.Building.TerranBuilding;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.sql.Time;
import java.util.ArrayList;

import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.Unit.BulletUnit.ShieldBatteryBullet;
import SC2_simplify.mainUnit.Unit.UnitType.Unit;
import SC2_simplify.mainUnit.specialFeatures.*;
import SC2_simplify.world.WorldRunner;
import SC2_simplify.mainUnit.material.Attackable;
import java.awt.image.*;

public class ShieldBattery extends DefenseTower {
    public ShieldBattery(int placex, int placey, int side, double tmp, double collideVolume, WorldRunner wr) {
        super(150, placex, placey, 10, 40, wr);
    }

    public static Image img;

    public int getFullHP() {
        return 150;
    }

    static {
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/shieldbattery.jpg"));// 找不到图，用光棱塔代替
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }

    @Override
    public void draw(Graphics g, int _sizex, int _sizey, ImageObserver observer) {
        int sizex = 30, sizey = 30;
        g.drawImage(img, (int) getX() - sizex / 2, (int) getY() - sizey / 2, sizex, sizey, observer);
        // g.drawRect((int) getX(), (int) getY() - 1, (int) (sizex * ((double) getHp() /
        // getFullHP())), 4);
        g.setColor(Color.BLUE);
        g.fillRect((int) getX() - sizex / 2, (int) getY() - sizey / 2 - 1,
                (int) (sizex * ((double) getHp() / getFullHP())), 4);
    }

    @Override
    public void findAttack(ArrayList<Unit> Opposite, ArrayList<Tower> MyTower, int timeCount) {
        for (int j = 0; j < MyTower.size(); j++) {
            if (distance(MyTower.get(j)) <= getAttackRange())
                if (timeCount % 50 == 0) {
                    attack(MyTower.get(j), timeCount);
                }

        }
    }

    @Override
    public void attack(Attackable target, int timeCount) {
        target.recover(getATK());
        wr.BulletArr.add(new ShieldBatteryBullet(getX(), getY(), target.getX(), target.getY(), timeCount));
    }
}