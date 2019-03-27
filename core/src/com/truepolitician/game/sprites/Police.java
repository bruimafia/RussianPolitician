package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Police {

    public static final int POLICE_WIDTH = 35; // ширина беременной девушки

    private Texture police; // текстура беременной девушки
    private Vector2 posPolice; // вектор беременной девушки
    private Rectangle boundsPolice; // границы вокруг беременной девушки

    public Police(float x) {
        police = new Texture("characters/police.png");
        posPolice = new Vector2(x, 100);
        boundsPolice = new Rectangle(posPolice.x, posPolice.y, police.getWidth(), police.getHeight());
    }

    // изменяем местоположение
    public void reposition(float x) {
        posPolice.set(x, 100);
        boundsPolice.setPosition(posPolice.x, posPolice.y);
    }

    // скрываем полицейского
    public void hide() {
        posPolice.set(0, 0);
        boundsPolice.setPosition(posPolice.x, posPolice.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsPolice);
    }

    public Texture getPolice() {
        return police;
    }

    public Vector2 getPosPolice() {
        return posPolice;
    }

    // освобождаем ресурсы
    public void dispose() {
        police.dispose();
    }

}