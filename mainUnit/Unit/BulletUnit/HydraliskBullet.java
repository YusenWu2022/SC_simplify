package SC2_simplify.mainUnit.Unit.BulletUnit;

import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.specialFeatures.*;

public class HydraliskBullet extends Bullet {
    public static Image img;
    public HydraliskBullet(int placex, int placey, int targetx,int targety,int lastTime) {
        super(placex, placey, targetx,targety,lastTime);
    }

    static {
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/hydralisk_bullet.jpg"));
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }
    public void draw(Graphics g, int _sizex, int _sizey, ImageObserver observer) {

        g.drawImage(img, (int) getX(), (int) getY(), getSizex(), getSizey(), observer);
    }
}
