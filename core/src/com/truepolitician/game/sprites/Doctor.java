package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Doctor {

    public static final int DOCTOR_WIDTH = 26; // ширина доктора

    private Texture doctor; // текстура доктора
    private Vector2 posDoctor; // вектор доктора
    private Rectangle boundsDoctor; // границы вокруг доктора

    public Doctor(float x) {
        doctor = new Texture("characters/doctor.png");
        posDoctor = new Vector2(x, 100);
        boundsDoctor = new Rectangle(posDoctor.x, posDoctor.y, doctor.getWidth(), doctor.getHeight());
    }

    // изменяем местоположение
    public void reposition(float x) {
        posDoctor.set(x, 100);
        boundsDoctor.setPosition(posDoctor.x, posDoctor.y);
    }

    // скрываем доктора
    public void hide() {
        posDoctor.set(0, 0);
        boundsDoctor.setPosition(posDoctor.x, posDoctor.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsDoctor);
    }

    public Texture getDoctor() {
        return doctor;
    }

    public Vector2 getPosDoctor() {
        return posDoctor;
    }

    // освобождаем ресурсы
    public void dispose() {
        doctor.dispose();
    }

}