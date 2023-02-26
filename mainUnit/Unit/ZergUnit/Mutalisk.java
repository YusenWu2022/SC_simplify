package SC2_simplify.mainUnit.Unit.ZergUnit;

import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.specialFeatures.*;
import SC2_simplify.world.WorldRunner;

public final class Mutalisk extends ZergUnit implements Fliable, LightArmor {
    private double flySpeed = 1;
    private double flyAcceleration = 0.5;
    private boolean alwaysRotate = false;
    private double rotateSpeed = 0.5;

    public Mutalisk(int hp, int placex, int placey, int ATK, int attackRange, int defence, int side,
            double speedAmount,double collideVolume,WorldRunner wr) {
        super(hp, placex, placey, ATK, attackRange, defence, side, speedAmount,collideVolume,wr);
        setName("mutalisk");
        if (getTestMode() == 1)
            System.out.println(getName() + " No." + getID() + " was born at (" + placex + "," + placey + ")");
    }

    public void fly(int x, int y) {
        // time.wait(acceleration)
        // placeChange(x, y);
    }
    // 我对接口的感觉还是不太熟，类继承还是很方便的，接口到现在我都没什么应用场景TaT
}