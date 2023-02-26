package SC2_simplify.mainUnit.Unit.ZergUnit;

import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.Unit.UnitType.*;
import SC2_simplify.mainUnit.material.Attackable;
import SC2_simplify.mainUnit.specialFeatures.*;
import SC2_simplify.world.WorldRunner;

public final class Infestor extends ZergUnit implements Buriable, PsionicsUnit, HeavyArmor {
    private int psionics;

    @Override
    public void attack(Attackable enemy,int timeCount) {
        return;
    }

    public Infestor(int hp, int placex, int placey, int ATK, int attackRange, int defence, int psionics, int side,
            double speedAmount,double collideVolume,WorldRunner wr) {
        super(hp, placex, placey, ATK, attackRange, defence, side, speedAmount,collideVolume,wr);
        setName("infestor");
        if (getTestMode() == 1)
            System.out.println(getName() + " No." + getID() + " was born at (" + placex + "," + placey + ")");
        this.psionics = psionics;
    }

    /*
     * @Override public void update() { super.update(); psionics++; }
     */
    public void meijunzisheng(Unit[] enemies) {
        if (psionics >= 50) {
            psionics -= 50;
            for (Unit enemy : enemies) { // 增强for语句
                enemy.hurt(30);
            }
            if (getTestMode() == 1)
                System.out.println("this infestor used meijunzisheng");
        }
    }
    /*
     * public void shenjingjisheng(Unit enemy) { if (enemy.controllable()) {
     * enemy.setSide(this.getSide()); } }
     */
}