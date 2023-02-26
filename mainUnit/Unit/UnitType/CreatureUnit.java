package SC2_simplify.mainUnit.Unit.UnitType;

import SC2_simplify.mainUnit.material.*;
import SC2_simplify.world.WorldRunner;

public abstract class CreatureUnit extends Unit implements CreatureType {
    public CreatureUnit(int hp, int placex, int placey, int ATK, int attackRange, int defence, int side,
            double speedAmount, double collisionVolume, WorldRunner wr) {
        super(hp, placex, placey, ATK, attackRange, defence, side, speedAmount, collisionVolume, wr);
    }
}