package SC2_simplify.mainUnit.Building.TerranBuilding;

import SC2_simplify.mainUnit.material.Attackable;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.specialFeatures.*;
import SC2_simplify.world.WorldRunner;

public class Base extends DefenseTower {
    private double hp;
    private int placex, placey;
    static String name = "hydralisk";
    public static Image img;
    public static int fullHp = 1500;

    public int getFullHP() {
        return fullHp;
    }

    static {
        try {
            img = ImageIO.read(new File("SC2_simplify/chartlet/base.jpg"));
        } catch (IOException e) {
            System.out.println("fuck");
        }
    }

    public Base(int hp, int placex, int placey, int ATK, int attackRange, WorldRunner wr) {
        super(hp, placex, placey, ATK, attackRange, wr);
    }

    @Override
    public void draw(Graphics g, int sizex, int sizey, ImageObserver observer) {
        g.drawImage(img, (int) getX() - sizex / 2, (int) getY() - sizey / 2, sizex, sizey, observer);
        // g.drawRect((int) getX(), (int) getY() - 1, (int) (sizex * ((double) getHp() /
        // getFullHP())), 4);
        g.setColor(Color.BLUE);
        g.fillRect((int) getX() - sizex / 2, (int) getY() - sizey / 2 - 1,
                (int) (sizex * ((double) getHp() / getFullHP())), 4);
    }
}