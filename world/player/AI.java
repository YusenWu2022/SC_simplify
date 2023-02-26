package SC2_simplify.world.player;

import java.util.*;

import SC2_simplify.mainUnit.Unit.UnitType.Unit;
import SC2_simplify.mainUnit.Unit.ZergUnit.Hydralisk;
import SC2_simplify.mainUnit.Unit.ZergUnit.Tank;
import SC2_simplify.mainUnit.Unit.ZergUnit.Ultralisk;
import SC2_simplify.mainUnit.Unit.ZergUnit.Zergling;
import SC2_simplify.world.WorldRunner;

public class AI {
    private WorldRunner wr;

    public AI(WorldRunner wr) {
        this.wr = wr;
    }

    // 可以用文件/数据库寄存/读取配置好的出兵顺序
    public Unit produceUnit(int timeCount) {
        Random rd1 = new Random();
        int side = rd1.nextInt(4);// 0：左边，1：上边，2：右边，3：下边
        int delta = rd1.nextInt(400);
        int placex = 0, placey = 0;
        switch (side) {
            case 0:
                placex = 0;
                placey = delta;
                break;
            case 1:
                placex = delta;
                placey = 0;
                break;
            case 2:
                placex = 400;
                placey = delta;
                break;
            case 3:
                placex = delta;
                placey = 400;
                break;
        }
        // 生成随机出兵位置，限制在四条边上，从0到400.
        if (timeCount >= 2000 && timeCount % 1000 == 0)
            return new Ultralisk(placex, placey, 2, 0.5, 50, wr);
        if (timeCount % 500 == 0)
            return new Tank(placex, placey, 2, 0.5, 30, wr);
        if (timeCount % 100 == 0)
            return new Hydralisk(placex, placey, 2, 1.2, 20, wr);
        else if (timeCount % 50 == 0)
            return new Zergling(placex, placey, 2, 1.2, 20, wr);
        else
            return null;
    }
}