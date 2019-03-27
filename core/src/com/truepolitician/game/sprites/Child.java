package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Child {

    public static final int CHILD_WIDTH = 23; // ширина ребенка

    private Texture child; // текстура ребенка
    private Vector2 posChild; // вектор ребенка
    private Rectangle boundsChild; // границы вокруг ребенка

    public Child(float x) {
        child = new Texture("characters/child.png");
        posChild = new Vector2(x, 100);
        boundsChild = new Rectangle(posChild.x, posChild.y, child.getWidth(), child.getHeight());
    }

    // изменяем местоположение
    public void reposition(float x) {
        posChild.set(x, 100);
        boundsChild.setPosition(posChild.x, posChild.y);
    }

    // скрываем ребенка
    public void hide() {
        posChild.set(0, 0);
        boundsChild.setPosition(posChild.x, posChild.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsChild);
    }

    public Texture getChild() {
        return child;
    }

    public Vector2 getPosChild() {
        return posChild;
    }

    // освобождаем ресурсы
    public void dispose() {
        child.dispose();
    }

}