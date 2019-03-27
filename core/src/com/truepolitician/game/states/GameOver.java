package com.truepolitician.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.truepolitician.game.TruePolitician;

public class GameOver extends State {

    private BitmapFont font, font2; // шрифт для текста

    private Texture background; // текстура фона
    private Texture restartBtn; // текстура кнопки "заново"

    private int finalBonusResult; // количество собранных денег
    private int bonusRecord; // рекорд собранных денег

    private Vector3 touchPos; // область нажатия на экран

    private GlyphLayout text1, text2, text3; // область текста

    private String resultText, recordText; // текст

    public GameOver(GameStateManager gsm, int result) {
        super(gsm);
        camera.setToOrtho(false, TruePolitician.WIDTH / 2, TruePolitician.HEIGHT / 2); // устанавливаем камеру по центру экрана
        background = new Texture("elements/background.png");
        restartBtn = new Texture("elements/btnRestart.png");

        touchPos = new Vector3();

        text1 = new GlyphLayout();
        text2 = new GlyphLayout();
        text3 = new GlyphLayout();

        // настраиваем кириллический шрифт
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixel6.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = PlayState.FONT_CHARS;
        parameter.size = 31;
        parameter.color = Color.WHITE;
//        parameter.borderColor = Color.GRAY;
//        parameter.borderWidth = 1;
        font = generator.generateFont(parameter);
        generator.dispose();

        // настраиваем кириллический шрифт
        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixel4.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.characters = PlayState.FONT_CHARS;
        parameter2.size = 55;
        parameter2.color = Color.SALMON;
        parameter2.borderColor = Color.WHITE;
        parameter2.borderWidth = 3;
//        parameter2.shadowOffsetX = 3;
//        parameter2.shadowOffsetY = 3;
//        parameter2.shadowColor = new Color(0, 0, 0, 0.75f);
        font2 = generator2.generateFont(parameter2);
        generator2.dispose();

        finalBonusResult = result; // передали количество собранных денег

        // обновляем рекорд собранных денег
        Preferences pref = Gdx.app.getPreferences("statistics");
        bonusRecord = pref.getInteger("bonusRecord", 0);
        if (finalBonusResult > bonusRecord) {
            bonusRecord = finalBonusResult;
            pref.putInteger("bonusRecord", bonusRecord);
            pref.flush();
        }

        resultText = "Вы украли " + finalBonusResult + " млн";
        recordText = "Рекорд: " + bonusRecord + " млн";
    }

    @Override
    protected void handleInput() {
        // кнопка перезапуска игры
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if ((touchPos.x < camera.position.x + (restartBtn.getWidth() / 2) && touchPos.x > camera.position.x - (restartBtn.getWidth() / 2))
                    && (touchPos.y < camera.position.y - 230 + restartBtn.getHeight() && touchPos.y > camera.position.y - 230))
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
        sb.draw(restartBtn, camera.position.x - (restartBtn.getWidth() / 2), camera.position.y - 230);  // рисуем кнопку "заново"

        text3.setText(font2, "Посадили");
        font2.draw(sb, text3, camera.position.x - text3.width/2, camera.position.y + 170); // рисуем надпись "посадили"

        text1.setText(font, resultText);
        font.draw(sb, text1, camera.position.x - text1.width/2, camera.position.y + 40); // рисуем количество очков

        text2.setText(font, recordText);
        font.draw(sb, text2, camera.position.x - text2.width/2, camera.position.y); // рисуем количество очков
        sb.end();
    }

    // освобождаем ресурсы
    @Override
    public void dispose() {
        background.dispose();
        restartBtn.dispose();
        font.dispose();
    }

}