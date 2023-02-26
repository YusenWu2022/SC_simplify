package SC2_simplify.mainUnit.Unit.BulletUnit;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.Unit.ZergUnit.Tank;
import SC2_simplify.mainUnit.specialFeatures.*;

public class TankBullet extends Bullet {
    public static Image img;

    @Override
    public void placeChange() {
    }

    public TankBullet(int placex, int placey, int targetx, int targety, int lastTime) {
        super(placex, placey, targetx, targety, lastTime);
    }

    static {
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/hydralisk_bullet.jpg"));
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }

    public void draw(Graphics g, int _sizex, int _sizey, ImageObserver observer) {
        g.setColor(Color.RED);
        g.drawLine(getX(), getY(), targetx, targety);
    }
}
