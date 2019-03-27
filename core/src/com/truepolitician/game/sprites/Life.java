package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Life {

    public static final int LIFE_WIDTH = 39; // ширина сердца

    private Texture life; // текстура сердца
    private Vector2 posLife; // вектор сердца
    private Rectangle boundsLife; // границы вокруг сердца

    public Life(float x) {
        life = new Texture("elements/life.png");
        posLife = new Vector2(x, 100);
        boundsLife = new Rectangle(posLife.x, posLife.y, life.getWidth(), life.getHeight());
    }

    // изменияем местоположение
    public void reposition(float x) {
        posLife.set(x, 100);
        boundsLife.setPosition(posLife.x, posLife.y);
    }

    // скрываем деньги
    public void hide() {
        posLife.set(0, 0);
        boundsLife.setPosition(posLife.x, posLife.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsLife);
    }

    public Texture getLife() {
        return life;
    }

    public Vector2 getPosLife() {
        return posLife;
    }

    // освобождаем ресурсы
    public void dispose() {
        life.dispose();
    }

}