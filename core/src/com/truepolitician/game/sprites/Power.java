package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.truepolitician.game.TruePolitician;

public class Power {

    public static final int POWER_WIDTH = 39; // ширина денег

    private Texture power; // текстура денег
    private Vector2 posPower; // вектор денег
    private Rectangle boundsPower; // границы вокруг денег

    public Power(float x) {
        power = new Texture("elements/power.png");
        posPower = new Vector2(x, 100);
        boundsPower = new Rectangle(posPower.x, TruePolitician.HEIGHT, power.getWidth(), TruePolitician.HEIGHT);
    }

    // изменияем местоположение
    public void reposition(float x) {
        posPower.set(x, 100);
        boundsPower.setPosition(posPower.x, posPower.y);
    }

    // скрываем деньги
    public void hide() {
        posPower.set(0, -TruePolitician.HEIGHT);
        boundsPower.setPosition(posPower.x, posPower.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsPower);
    }

    public Texture getPower() {
        return power;
    }

    public Vector2 getPosPower() {
        return posPower;
    }

    // освобождаем ресурсы
    public void dispose() {
        power.dispose();
    }

}