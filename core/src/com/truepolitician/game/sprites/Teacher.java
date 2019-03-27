package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Teacher {

    public static final int TEACHER_WIDTH = 30; // ширина учителя

    private Texture teacher; // текстура учителя
    private Vector2 posTeacher; // вектор учителя
    private Rectangle boundsTeacher; // границы вокруг учителя

    public Teacher(float x) {
        teacher = new Texture("characters/teacher.png");
        posTeacher = new Vector2(x, 100);
        boundsTeacher = new Rectangle(posTeacher.x, posTeacher.y, teacher.getWidth(), teacher.getHeight());
    }

    // изменяем местоположение
    public void reposition(float x) {
        posTeacher.set(x, 100);
        boundsTeacher.setPosition(posTeacher.x, posTeacher.y);
    }

    // скрываем учителя
    public void hide() {
        posTeacher.set(0, 0);
        boundsTeacher.setPosition(posTeacher.x, posTeacher.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTeacher);
    }

    public Texture getTeacher() {
        return teacher;
    }

    public Vector2 getPosTeacher() {
        return posTeacher;
    }

    // освобождаем ресурсы
    public void dispose() {
        teacher.dispose();
    }

}