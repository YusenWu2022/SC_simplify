package SC2_simplify.mainUnit.Building;

import SC2_simplify.mainUnit.Unit.UnitType.*;
import SC2_simplify.mainUnit.Unit.ZergUnit.*;

public abstract class Building {
    private int placex, placey;
    private int defense;
    private int hp;
    private int side;

    public int getPlacex() {
        return placex;
    }

    public int getSide() {
        return side;
    }

    public int getPlacey() {
        return placey;
    }

    public int getHp() {
        return hp;
    }
/*
    public Unit produce(int ID) {
        Hydralisk a = new Hydralisk(getPlacex(), getPlacey(), getSide(),1,50);
        return a;
    }*/
}