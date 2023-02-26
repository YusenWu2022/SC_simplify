package SC2_simplify.mainUnit.Unit.ZergUnit;

import SC2_simplify.mainUnit.material.*;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.specialFeatures.*;
import SC2_simplify.world.WorldRunner;
import SC2_simplify.mainUnit.Unit.BulletUnit.*;

public final class Ultralisk extends ZergUnit implements Buriable, LightArmor {
    public static Image img;
    static {// 经过仔细考虑，还是放在前面的static处理为好，这样就可以避免在每一次创建的时候都得去读取图片浪费大量时间
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/ultralisk.jpg"));
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }

    public Ultralisk(int placex, int placey, int side, double speedAmount, double collideVolume, WorldRunner wr) {
        super(500, placex, placey, 40, 20, 0, side, speedAmount, collideVolume, wr);
        setName("ultralisk");
    }

    @Override
    public void draw(Graphics g, int sizex, int sizey, ImageObserver observer) {
        sizex = 36;
        sizey = 36;
        g.drawImage(img, (int) getX() - sizex / 2, (int) getY() - sizey / 2, sizex, sizey, observer);
        // g.drawRect((int) getX(), (int) getY() - 1, (int) (sizex * (getHp() /
        // getFullHP())), 4);
        if (getSide() == 1)
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.RED);
        g.fillRect((int) getX() - sizex / 2, (int) getY() - sizey / 2 - 1, (int) (sizex * (getHp() / getFullHP())), 4);
    }

    public void attack(Attackable target, int timeCount) {
        target.hurt(getATK());
        wr.BulletArr.add(new HydraliskBullet(getX(), getY(), target.getX(), target.getY(), timeCount));

    }
}