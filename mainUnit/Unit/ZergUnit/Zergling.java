package SC2_simplify.mainUnit.Unit.ZergUnit;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.specialFeatures.*;
import SC2_simplify.world.WorldRunner;

import java.awt.image.*;

public final class Zergling extends ZergUnit implements Buriable, LightArmor {
    public static Image img;

    static {
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/zergling.jpg"));
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }

    public Zergling(int placex, int placey, int side, double speedAmount, double collideVolume, WorldRunner wr) {
        super(35, placex, placey, 5, 20, 0, side, speedAmount, collideVolume, wr);
        setName("zergling");
    }

    @Override
    public void draw(Graphics g, int sizex, int sizey, ImageObserver observer) {
        g.drawImage(img, (int) getX() - sizex / 2, (int) getY() - sizey / 2, sizex, sizey, observer);
        // g.drawRect((int) getX(), (int) getY() - 1, (int) (sizex * (getHp() /
        // getFullHP())), 4);
        if (getSide() == 1)
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.RED);
        g.fillRect((int) getX() - sizex / 2, (int) getY() - sizey / 2 - 1, (int) (sizex * (getHp() / getFullHP())), 4);
    }
}