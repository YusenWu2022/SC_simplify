package SC2_simplify.mainUnit.Unit.BulletUnit;

import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import java.awt.*;

public class KhaydarinBullet extends Bullet {
    public static Image img;

    public KhaydarinBullet(int placex, int placey, int targetx, int targety,int lastTime) {
        super(placex, placey, targetx, targety,lastTime);
        setDrawSize(10, 10);
    }

    static {
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/khaydarin_bullet.jpg"));
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }

    public void draw(Graphics g, int _sizex, int _sizey, ImageObserver observer) {

        g.drawImage(img, (int) getX(), (int) getY(), getSizex(), getSizey(), observer);
    }
}