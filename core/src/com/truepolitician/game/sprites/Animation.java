package com.truepolitician.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class Animation {

    private ArrayList<TextureRegion> frames; // массив текстур
    private float maxFrameTime; // длительность одного кадра
    private float currentFrameTime; // время отображения текущего кадра
    private int frameCount; // количество кадров
    private int frame; // отдельный кадр

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new ArrayList<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount; // определяем ширину кадра

        // перебираем кадры
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }

    // анимируем с помощью кадров
    public void update(float dt) {
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime) {
            frame++;
            currentFrameTime = 0;
        }
        if (frame >= frameCount)
            frame = 0;
    }

    // получаем текущий кадр
    public TextureRegion getFrame() {
        return frames.get(frame);
    }

}