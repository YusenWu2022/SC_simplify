package SC2_simplify.mainUnit.Building.TerranBuilding;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.Unit.BulletUnit.KhaydarinBullet;
import SC2_simplify.mainUnit.material.Attackable;
import SC2_simplify.mainUnit.specialFeatures.*;
import SC2_simplify.world.WorldRunner;

import java.awt.image.*;

public class Khaydarin extends DefenseTower {
    public Khaydarin(int placex, int placey, int side, double tmp, double collideVolume, WorldRunner wr) {
        super(500, placex, placey, 50, 100, wr);
    }

    public static Image img;

    public int getFullHP() {
        return 500;
    }

    static {
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/khaydarin.jpg"));
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }

    @Override
    public void draw(Graphics g, int _sizex, int _sizey, ImageObserver observer) {
        int sizex = 35, sizey = 35;
        g.drawImage(img, (int) getX() - sizex / 2, (int) getY() - sizey / 2, sizex, sizey, observer);
        // g.drawRect((int) getX(), (int) getY() - 1, (int) (sizex * ((double) getHp() /
        // getFullHP())), 4);
        g.setColor(Color.BLUE);
        g.fillRect((int) getX() - sizex / 2, (int) getY() - sizey / 2 - 1,
                (int) (sizex * ((double) getHp() / getFullHP())), 4);
    }

    public void attack(Attackable target, int timeCount) {
        target.hurt(getATK());
        wr.BulletArr.add(new KhaydarinBullet(getX(), getY(), target.getX(), target.getY(), timeCount));

    }
}