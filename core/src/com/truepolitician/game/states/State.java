package com.truepolitician.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {

    protected OrthographicCamera camera; // окно в игровой мир
    protected Vector3 mouse; // координаты
    protected GameStateManager gsm; // управление окнами или состояниями игры

    // конструктор
    public State(GameStateManager gsm) {
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
    }

    protected abstract void handleInput(); // метод, который определяет нажатия на экран

    public abstract void update(float dt); // метод, который обновляет картинку

    public abstract void render(SpriteBatch sb); // метод, который рисует экран

    public abstract void dispose(); // метод, который освобождает ресурсы

}