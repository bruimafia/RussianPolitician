package com.truepolitician.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.truepolitician.game.TruePolitician;

public class MenuState extends State {

    private BitmapFont font; // шрифт для текста

    private GlyphLayout text1, text2; // область текста

    private Vector3 touchPos; // область нажатия на экран

    private Texture background; // текстура фона
    private Texture playBtn; // текстура кнопки "Play"

    public Music musicTheme; // фоновая музыка

    public MenuState(GameStateManager gsm) {
        super(gsm);
        camera.setToOrtho(false, TruePolitician.WIDTH / 2, TruePolitician.HEIGHT / 2); // устанавливаем камеру по центру экрана
        background = new Texture("elements/background.png");
        playBtn = new Texture("elements/btnPlay.png");
        musicTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/theme.mp3")); // музыка на фон
        musicTheme.setLooping(true); // зацикливание фоновой музыки
        musicTheme.setVolume(0.05f); // громкость фоновой музыки
        musicTheme.play(); // запускаем фоновую музыку

        touchPos = new Vector3();

        text1 = new GlyphLayout();
        text2 = new GlyphLayout();

        // настраиваем кириллический шрифт
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixel4.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = PlayState.FONT_CHARS;
        parameter.size = 45;
        parameter.color = Color.SALMON;
        parameter.borderColor = Color.WHITE;
        parameter.borderWidth = 3;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0, 0, 0.45f);
        font = generator.generateFont(parameter);
        generator.dispose();
    }

    @Override
    protected void handleInput() {
        // кнопка старта игры
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if ((touchPos.x < camera.position.x + (playBtn.getWidth() / 2) && touchPos.x > camera.position.x - (playBtn.getWidth() / 2))
                    && (touchPos.y < camera.position.y - 180 + playBtn.getHeight() && touchPos.y > camera.position.y - 180))
                gsm.set(new PlayState(gsm)); // при нажатии на экран переходим из экрана меню в игровой экран
        }

        Gdx.input.setCatchBackKey(false); // выход из игры при нажатии назад
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined); // устанавливаем матрицу проекций для камеры
        sb.begin();
        sb.draw(background, 0, 0); // рисуем фон
        sb.draw(playBtn, camera.position.x - (playBtn.getWidth() / 2), camera.position.y - 180);  // рисуем кнопку "Play"

        text1.setText(font, "Russian");
        font.draw(sb, text1, camera.position.x - text1.width/2, camera.position.y + 200); // рисуем название игры

        text2.setText(font, "Politician");
        font.draw(sb, text2, camera.position.x - text2.width/2, camera.position.y + 150); // рисуем название игры
        sb.end();
    }

    // освобождаем ресурсы
    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
        musicTheme.dispose();
    }

}