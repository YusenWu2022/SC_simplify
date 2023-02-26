package SC2_simplify.mainUnit.Unit.ProtossUnit;

import SC2_simplify.mainUnit.material.*;
import SC2_simplify.mainUnit.Unit.UnitType.*;
import SC2_simplify.world.WorldRunner;

public final class Zealot extends ProtossUnit implements CreatureType {
    public Zealot(int hp, int placex, int placey, int ATK, int attackRange, int defence, int side, double speedAmount,
            double collideVolume, WorldRunner wr) {
        super(hp, placex, placey, ATK, attackRange, defence, side, speedAmount, collideVolume, wr);
    }

    private int charge_speed;

    public void charge(Unit enemy) {
        // placeChange(charge_speed, charge_speed);
    }
}