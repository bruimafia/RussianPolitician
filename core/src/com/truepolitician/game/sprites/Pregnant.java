package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pregnant {

    public static final int PREGNANT_WIDTH = 31; // ширина беременной девушки

    private Texture pregnant; // текстура беременной девушки
    private Vector2 posPregnant; // вектор беременной девушки
    private Rectangle boundsPregnant; // границы вокруг беременной девушки

    public Pregnant(float x) {
        pregnant = new Texture("characters/pregnant.png");
        posPregnant = new Vector2(x, 100);
        boundsPregnant = new Rectangle(posPregnant.x, posPregnant.y, pregnant.getWidth(), pregnant.getHeight());
    }

    // изменяем местоположение
    public void reposition(float x) {
        posPregnant.set(x, 100);
        boundsPregnant.setPosition(posPregnant.x, posPregnant.y);
    }

    // скрываем беременную женщину
    public void hide() {
        posPregnant.set(0, 0);
        boundsPregnant.setPosition(posPregnant.x, posPregnant.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsPregnant);
    }

    public Texture getPregnant() {
        return pregnant;
    }

    public Vector2 getPosPregnant() {
        return posPregnant;
    }

    // освобождаем ресурсы
    public void dispose() {
        pregnant.dispose();
    }

}