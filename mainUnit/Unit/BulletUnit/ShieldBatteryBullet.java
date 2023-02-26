package SC2_simplify.mainUnit.Unit.BulletUnit;

import javax.imageio.*;

import java.io.*;
import java.awt.image.*;
import java.awt.*;

public class ShieldBatteryBullet extends Bullet {
    public static Image img;
    private int startPlacex, startPlacey;

    public ShieldBatteryBullet(int placex, int placey, int targetx, int targety, int lastTime) {
        super(placex, placey, targetx, targety, lastTime);
        setDrawSize(6, 6);
        startPlacex = placex;
        startPlacey = placey;
    }

    static {
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/shieldbattery_bullet.jpg"));
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }

    public void draw(Graphics g, int _sizex, int _sizey, ImageObserver observer) {
        // System.out.println(1);
        g.setColor(Color.BLUE);
        g.drawOval(startPlacex - 25, startPlacey - 25, 50, 50);
        // g.drawImage(img, (int) getX(), (int) getY(), getSizex(), getSizey(),
        // observer);
    }
}