package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Grandfather {

    public static final int GRANDFATHER_WIDTH = 32; // ширина дедушки

    private Texture grandfather; // текстура дедушки
    private Vector2 posGrandfather; // вектор дедушки
    private Rectangle boundsGrandfather; // границы вокруг дедушки

    public Grandfather(float x) {
        grandfather = new Texture("characters/grandfather.png");
        posGrandfather = new Vector2(x, 100);
        boundsGrandfather = new Rectangle(posGrandfather.x, posGrandfather.y, grandfather.getWidth(), grandfather.getHeight());
    }

    // изменяем местоположение
    public void reposition(float x) {
        posGrandfather.set(x, 100);
        boundsGrandfather.setPosition(posGrandfather.x, posGrandfather.y);
    }

    // скрываем дедушку
    public void hide() {
        posGrandfather.set(0, 0);
        boundsGrandfather.setPosition(posGrandfather.x, posGrandfather.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsGrandfather);
    }

    public Texture getGrandfather() {
        return grandfather;
    }

    public Vector2 getPosGrandfather() {
        return posGrandfather;
    }

    // освобождаем ресурсы
    public void dispose() {
        grandfather.dispose();
    }

}