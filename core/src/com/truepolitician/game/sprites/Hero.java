package com.truepolitician.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Hero {

    public int MOVEMENT = 120; // ускорение

    public static final int GRAVITY = -18; // гравитация

    private Vector3 position; // позиция главного героя
    private Vector3 velosity; // скорость главного героя
    private Texture hero; // текстура главного героя
    private Animation heroAnimation; // анимация главного героя
    private Rectangle bounds; // границы вокруг главного героя

    private Sound jump;

    public Hero(int x, int y) {
        position = new Vector3(x, y, 0);
        velosity = new Vector3(0, 0, 0);
        hero = new Texture("characters/hero.png");
        heroAnimation = new Animation(new TextureRegion(hero), 8, 0.5f);
        bounds = new Rectangle(x + 6, y, (hero.getWidth() / 8 - 6), hero.getHeight());
        jump = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.ogg")); // звук прыжка главного героя
    }


    public void update(float dt) {

        // усложнение игры с помощью постепенного ускорения
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

        heroAnimation.update(dt);
        if (position.y > 0) // максимальная высота прыжка
            velosity.add(0, GRAVITY, 0);
        velosity.scl(dt);
        position.add(MOVEMENT * dt, velosity.y, 0);
        if (position.y < 100) // ставит главного героя на платформу
            position.y = 100;

        velosity.scl(1 / dt);
        bounds.setPosition(position.x, position.y);
    }

    // прыжок главного героя
    public void jump() {
        velosity.y = 320; // высота прыжка
        jump.play(0.3f); // звук прыжка
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getHero() {
        return heroAnimation.getFrame();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    // освобождаем ресурсы
    public void dispose() {
        hero.dispose();
        jump.dispose();
    }

}