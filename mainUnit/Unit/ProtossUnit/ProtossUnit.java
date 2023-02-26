package SC2_simplify.mainUnit.Unit.ProtossUnit;

import SC2_simplify.mainUnit.Unit.UnitType.*;
import SC2_simplify.world.WorldRunner;

public abstract class ProtossUnit extends Unit {
    public ProtossUnit(int hp, int placex, int placey, int ATK, int attackRange, int defence, int side,
            double speedAmount,double collideVolume,WorldRunner wr) {
        super(hp, placex, placey, ATK, attackRange, defence, side, speedAmount,collideVolume,wr);
    }

    private int shield;

    public int getShield() {
        return shield;
    }
}