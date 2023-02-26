package SC2_simplify.mainUnit.Unit.BulletUnit;

import java.awt.*;
import javax.imageio.*;
import java.io.*;
import SC2_simplify.mainUnit.Armors.*;
import SC2_simplify.mainUnit.material.Lastable;
import SC2_simplify.mainUnit.specialFeatures.*;
import java.awt.image.*;

public class Bullet implements Lastable {

    private int placex, placey;
    private int sizex = 4, sizey = 4;
    private double speedx, speedy;
    private double speedAmount = 5;
    public int targetx, targety;
    private int lastTime;

    public boolean timeOver(int currentTime) {
        return currentTime - lastTime >= 100;
    }

    public void setDrawSize(int sizex, int sizey) {
        this.sizex = sizex;
        this.sizey = sizey;
    }

    public Bullet(int placex, int placey, int targetx, int targety, int lastTime) {
        this.placex = placex;
        this.placey = placey;
        this.targetx = targetx;
        this.targety = targety;
        this.lastTime = lastTime;
        setSpeed(targetx - placex, targety - placey);
    }

    public boolean isAlive() {
        return Math.abs(placex - targetx) >= 4 || Math.abs(placey - targety) >= 4;
    }

    public void setSpeed(double dictx, double dicty) {
        if (dictx == 0 && dicty == 0) {
            speedx = 0;
            speedy = 0;
            return;
        }
        speedx = speedAmount * (dictx / Math.sqrt(Math.pow(dictx, 2.0) + Math.pow(dicty, 2.0)));
        speedy = speedAmount * (dicty / Math.sqrt(Math.pow(dictx, 2.0) + Math.pow(dicty, 2.0)));
    }

    public void placeChange() {
        placex += speedx;
        placey += speedy;
    }

    public int getX() {
        return placex;
    }

    public int getY() {
        return placey;
    }

    public int getSizex() {
        return sizex;
    }

    public int getSizey() {
        return sizey;
    }

    public void draw(Graphics g, int _sizex, int _sizey, ImageObserver observer) {
    }

}