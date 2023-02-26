package SC2_simplify.mainUnit.Unit.ZergUnit;

import SC2_simplify.mainUnit.Unit.UnitType.*;
import SC2_simplify.world.WorldRunner;

public abstract class ZergUnit extends CreatureUnit {
    public ZergUnit(int hp, int placex, int placey, int ATK, int attackRange, int defence, int side, double speedAmount,
            double collideVolume,WorldRunner wr) {
        super(hp, placex, placey, ATK, attackRange, defence, side, speedAmount, collideVolume,wr);
    }
}