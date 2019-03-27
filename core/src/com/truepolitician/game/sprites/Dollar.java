package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Dollar {

    public static final int DOLLAR_WIDTH = 39; // ширина денег

    private Texture dollar; // текстура денег
    private Vector2 posDollar; // вектор денег
    private Rectangle boundsDollar; // границы вокруг денег

    public Dollar(float x) {
        dollar = new Texture("elements/dollar.png");
        posDollar = new Vector2(x, 100);
        boundsDollar = new Rectangle(posDollar.x, posDollar.y, dollar.getWidth(), dollar.getHeight());
    }

    // изменияем местоположение
    public void reposition(float x) {
        posDollar.set(x, 100);
        boundsDollar.setPosition(posDollar.x, posDollar.y);
    }

    // скрываем деньги
    public void hide() {
        posDollar.set(0, 0);
        boundsDollar.setPosition(posDollar.x, posDollar.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsDollar);
    }

    public Texture getDollar() {
        return dollar;
    }

    public Vector2 getPosDollar() {
        return posDollar;
    }

    // освобождаем ресурсы
    public void dispose() {
        dollar.dispose();
    }

}