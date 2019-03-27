package com.truepolitician.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {

    private Stack<State> states; // массив экранов

    // конструктор
    public GameStateManager() {
        states = new Stack<State>();
    }

    public void push(State state) {
        states.push(state); // помещаем следующий экран в вершину стека
    }

    public void pop() {
        states.pop().dispose(); // удаляем из стека верхний экран
    }

    public void set(State state) {
        states.pop().dispose(); // удаляем из стека верхний экран
        states.push(state); // помещаем следующий экран в вершину стека
    }

    public void update(float dt) {
        states.peek().update(dt); // обновляем верхний экран, не удаляя его из стека
    }

    public void render(SpriteBatch sb) {
        states.peek().render(sb); // отрисовываем верхний экран, не удаляя его из стека
    }

}