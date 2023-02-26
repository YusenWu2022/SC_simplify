package SC2_simplify;

import SC2_simplify.world.player.*;
//import SC2_simplify.mainUnit.Unit.*;
//大致理解：就是一个白板主类不断往上加功能接口，多接口继承
//感觉接口唯一的用途就是弥补不能多继承的问题了
//感觉依然是单线的过程，是从白板出发一直加接口的逐渐差异化的效果
import SC2_simplify.mainUnit.Unit.ZergUnit.*;
import SC2_simplify.GUIsupport.*;

interface movable {
    public void move();
}

// 感动人心！！！终于修好了！！！只要把对应的Java插件退版本就好了,一定要记得遇到这种问题找不到的必须倒版本
public class MainWorld {
    public static void main(String[] args) {
        Entrance entrance = new Entrance();
        // 可以传入标记表示不同的设定好的框架
        // world.warField.repaint();
        // System.exit(0);

    }
}