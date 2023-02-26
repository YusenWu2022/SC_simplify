package SC2_simplify.mainUnit.Building.TerranBuilding;

import SC2_simplify.mainUnit.material.Attackable;
import java.awt.image.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.specialFeatures.*;
import SC2_simplify.world.WorldRunner;

public class DefenseTower extends Tower {

    public DefenseTower(int hp, int placex, int placey, int ATK, int attackRange,WorldRunner wr) {
        super(hp, placex, placey, ATK, attackRange,wr);
    }

    public void attack(Attackable target) {
        target.hurt(getATK());
    }

    public void draw(Graphics g, int sizex, int sizey, ImageObserver observer) {
    }
}