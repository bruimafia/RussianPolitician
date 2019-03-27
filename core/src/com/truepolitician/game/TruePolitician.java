package com.truepolitician.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.truepolitician.game.states.GameStateManager;
import com.truepolitician.game.states.MenuState;

public class TruePolitician extends ApplicationAdapter {

	public static final int WIDTH = 720; // ширина экрана
	public static final int HEIGHT = 1184; // высота экрана
	public static final String TITLE = "RussianPolitician"; // заголовок экрана

	private GameStateManager gsm;
	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(0, 0, 0, 0); // закрашиваем фон
		gsm.push(new MenuState(gsm)); // создаем новый экран и помещаем его в вершину стека
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime()); // обновляем верхний экран в стеке
		gsm.render(batch); // отрисовываем верхний экран в стеке
	}

	// освобождаем ресурсы
	@Override
	public void dispose () {
		batch.dispose();
	}

}