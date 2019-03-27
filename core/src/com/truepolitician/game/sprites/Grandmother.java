package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Grandmother {

    public static final int GRANDMOTHER_WIDTH = 31; // ширина бабушки

    private Texture grandmother; // текстура бабушки
    private Vector2 posGrandmother; // вектор бабушки
    private Rectangle boundsGrandmother; // границы вокруг бабушки

    public Grandmother(float x) {
        grandmother = new Texture("characters/grandmother.png");
        posGrandmother = new Vector2(x, 100);
        boundsGrandmother = new Rectangle(posGrandmother.x, posGrandmother.y, grandmother.getWidth(), grandmother.getHeight());
    }

    // изменяем местоположение
    public void reposition(float x) {
        posGrandmother.set(x, 100);
        boundsGrandmother.setPosition(posGrandmother.x, posGrandmother.y);
    }

    // скрываем бабушку
    public void hide() {
        posGrandmother.set(0, 0);
        boundsGrandmother.setPosition(posGrandmother.x, posGrandmother.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsGrandmother);
    }

    public Texture getGrandmother() {
        return grandmother;
    }

    public Vector2 getPosGrandmother() {
        return posGrandmother;
    }

    // освобождаем ресурсы
    public void dispose() {
        grandmother.dispose();
    }

}