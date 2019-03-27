package com.truepolitician.game.sprites;

import com.badlogic.gdx.math.Vector3;

public class LabelBonusResult {

    public int MOVEMENT = 120;

    Hero hero;

    private Vector3 position; // позиция надписи
    private Vector3 velosity; // скорость надписи

    public LabelBonusResult(int x, int y) {
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
    }

    public void update(float dt) {
        if (position.x > 5000) MOVEMENT = 130; // уровень 2
        if (position.x > 10000) MOVEMENT = 140; // уровень 3
        if (position.x > 15000) MOVEMENT = 150; // уровень 4
        if (position.x > 20000) MOVEMENT = 160; // уровень 5
        if (position.x > 25000) MOVEMENT = 170; // уровень 6
        if (position.x > 30000) MOVEMENT = 180; // уровень 7
        if (position.x > 35000) MOVEMENT = 190; // уровень 8
        if (position.x > 40000) MOVEMENT = 200; // уровень 9
        if (position.x > 45000) MOVEMENT = 210; // уровень 10
        if (position.x > 50000) MOVEMENT = 220; // уровень 11
        if (position.x > 55000) MOVEMENT = 230; // уровень 12
        if (position.x > 60000) MOVEMENT = 240; // уровень 13
        if (position.x > 65000) MOVEMENT = 250; // уровень 14
        if (position.x > 70000) MOVEMENT = 260; // уровень 15
        if (position.x > 75000) MOVEMENT = 270; // уровень 16
        if (position.x > 80000) MOVEMENT = 280; // уровень 17
        if (position.x > 85000) MOVEMENT = 290; // уровень 18

        position.add(MOVEMENT * dt, velosity.y, 0);
    }

    public Vector3 getPosition() {
        return position;
    }

    // освобождаем ресурсы
    public void dispose() { }

}