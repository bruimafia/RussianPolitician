package com.truepolitician.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.truepolitician.game.TruePolitician;
import com.truepolitician.game.sprites.Child;
import com.truepolitician.game.sprites.Doctor;
import com.truepolitician.game.sprites.Dollar;
import com.truepolitician.game.sprites.Grandfather;
import com.truepolitician.game.sprites.Grandmother;
import com.truepolitician.game.sprites.Hero;
import com.truepolitician.game.sprites.LabelBonusResult;
import com.truepolitician.game.sprites.Life;
import com.truepolitician.game.sprites.Police;
import com.truepolitician.game.sprites.Power;
import com.truepolitician.game.sprites.Pregnant;
import com.truepolitician.game.sprites.Teacher;

import java.util.ArrayList;
import java.util.Random;

public class PlayState extends State {

    public static final String FONT_CHARS = "абвгдежзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";
    public static final int ENEMY_COUNT = 1; // количество противников
    public static final int POLICE_COUNT = 1; // количество полицейских
    public static final int DOLLAR_COUNT = 10; // количество денег
    public static final int LIFE_COUNT = 1; // количество дополнительных жизней
    public static final int POWER_COUNT = 1; // количество дополнительной энергии

    public static final int FROM_SPACING_1 = 500; // расстояние от grandmother
    public static final int FROM_SPACING_2 = 700; // расстояние от grandfather
    public static final int FROM_SPACING_3 = 900; // расстояние от police
    public static final int FROM_SPACING_4 = 1100; // расстояние от pregnant
    public static final int FROM_SPACING_5 = 1300; // расстояние от child
    public static final int FROM_SPACING_6 = 1500; // расстояние от doctor
    public static final int FROM_SPACING_7 = 1700; // расстояние от teacher
    public static final int TO_SPACING = 1501; // расстояние до


    private BitmapFont font; // шрифт для текста
    private BitmapFont font2; // шрифт для текста

    private Texture background; // текстура фона
    private Texture life; // текстура сердца
    private Texture dollar; // текстура денег
    private Texture energy_0, energy_1, energy_2, energy_3, energy_4, energy_5, energy_6, energy_7, energy_8, energy_9, energy_10, energy_11, energy_12, energy_13, energy_14, energy_15; // текстуры полосы энергии
    private Texture platform; // текстура платформы
    private Vector2 platformPos1, platformPos2; // векторы платформы

    private Hero hero; // класс "главный герой"
    private LabelBonusResult labelBonusResult; // класс надписи

    private ArrayList<Grandmother> grandmothers; // массив бабушек
    private ArrayList<Grandfather> grandfathers; // массив дедушек
    private ArrayList<Police> polices; // массив полицейских
    private ArrayList<Pregnant> pregnants; // массив беременных девушек
    private ArrayList<Child> childs; // массив детей
    private ArrayList<Doctor> doctors; // массив докторов
    private ArrayList<Teacher> teachers; // массив учителей
    private ArrayList<Dollar> dollars; // массив денег
    private ArrayList<Life> lives; // массив жизней
    private ArrayList<Power> powers; // массив сил

    private Random rand; // случайные числа

    public Music musicTheme; // фоновая музыка
    private Sound screamGrandmother; // крик бабушки
    private Sound screamGrandfather; // крик дедушки
    private Sound screamPolice; // крик полицейского
    private Sound screamPregnant; // крик беременной девушки
    private Sound screamChild; // крик ребенка
    private Sound screamDoctor; // крик доктора
    private Sound screamTeacher; // крик учителя
    private Sound soundLife; // звук дополнительной жизни
    private Sound bonus; // звук денежного бонуса
    private Sound noJump; // звук уставшего героя
    private Sound soundWin; // звук победы

    private int resultBonus = 0; // количество собранных денег
    private int remainLife = 3; // количество оставшихся жизней
    private int remainEnergy = 15; // количество оставшейся энергии

    private GlyphLayout text1, text2, text3, text4; // область текста


    public PlayState(GameStateManager gsm) {
        super(gsm);
        hero = new Hero(50, 200);
        labelBonusResult = new LabelBonusResult(40, TruePolitician.HEIGHT / 2 - 20);
        camera.setToOrtho(false, TruePolitician.WIDTH / 2, TruePolitician.HEIGHT / 2); // устанавливаем камеру по центру экрана
        background = new Texture("elements/background.png");
        life = new Texture("elements/life.png");
        dollar = new Texture("elements/dollar.png");

        energy_0 = new Texture("elements/energy-0.png");
        energy_1 = new Texture("elements/energy-1.png");
        energy_2 = new Texture("elements/energy-2.png");
        energy_3 = new Texture("elements/energy-3.png");
        energy_4 = new Texture("elements/energy-4.png");
        energy_5 = new Texture("elements/energy-5.png");
        energy_6 = new Texture("elements/energy-6.png");
        energy_7 = new Texture("elements/energy-7.png");
        energy_8 = new Texture("elements/energy-8.png");
        energy_9 = new Texture("elements/energy-9.png");
        energy_10 = new Texture("elements/energy-10.png");
        energy_11 = new Texture("elements/energy-11.png");
        energy_12 = new Texture("elements/energy-12.png");
        energy_13 = new Texture("elements/energy-13.png");
        energy_14 = new Texture("elements/energy-14.png");
        energy_15 = new Texture("elements/energy-15.png");

        text1 = new GlyphLayout();
        text2 = new GlyphLayout();
        text3 = new GlyphLayout();
        text4 = new GlyphLayout();

        // настраиваем первый шрифт
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixel.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FONT_CHARS;
        parameter.size = 26; //26
        parameter.color = Color.WHITE;
        font = generator.generateFont(parameter);
        generator.dispose();

        // настраиваем второй шрифт
        FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pixel6.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.characters = FONT_CHARS;
        parameter2.size = 18;
        parameter2.color = Color.WHITE;
        font2 = generator.generateFont(parameter2);
        generator2.dispose();

        platform = new Texture("elements/platform.png");
        platformPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, 0);
        platformPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + platform.getWidth(), 0);

        musicTheme = Gdx.audio.newMusic(Gdx.files.internal("sounds/theme.mp3")); // музыка на фон
        musicTheme.setLooping(true); // зацикливание фоновой музыки
        musicTheme.setVolume(0.05f); // громкость фоновой музыки
        musicTheme.play(); // запускаем фоновую музыку

        screamGrandmother = Gdx.audio.newSound(Gdx.files.internal("sounds/grandmother.ogg")); // звук бабушки
        screamGrandfather = Gdx.audio.newSound(Gdx.files.internal("sounds/grandfather.ogg")); // звук дедушки
        screamPolice = Gdx.audio.newSound(Gdx.files.internal("sounds/police.ogg")); // звук полицейского
        screamPregnant = Gdx.audio.newSound(Gdx.files.internal("sounds/pregnant.ogg")); // звук беременной девушки
        screamChild = Gdx.audio.newSound(Gdx.files.internal("sounds/child.ogg")); // звук ребенка
        screamDoctor = Gdx.audio.newSound(Gdx.files.internal("sounds/doctor.ogg")); // звук доктора
        screamTeacher = Gdx.audio.newSound(Gdx.files.internal("sounds/teacher.ogg")); // звук учителя
        soundLife = Gdx.audio.newSound(Gdx.files.internal("sounds/life.ogg")); // звук дополнительной жизни
        bonus = Gdx.audio.newSound(Gdx.files.internal("sounds/bonus.ogg")); // звук денежного бонуса
        noJump = Gdx.audio.newSound(Gdx.files.internal("sounds/nojump.ogg")); // звук уставшего героя
        soundWin = Gdx.audio.newSound(Gdx.files.internal("sounds/win.ogg")); // звук победы

        rand = new Random(); // случайное расстояние между объектами

        grandmothers = new ArrayList<Grandmother>();
        for (int i = 1; i <= ENEMY_COUNT; i++) {
            grandmothers.add(new Grandmother(i  * ((rand.nextInt(TO_SPACING) + FROM_SPACING_1) + Grandmother.GRANDMOTHER_WIDTH)));
        }

        grandfathers = new ArrayList<Grandfather>();
        for (int i = 1; i <= ENEMY_COUNT; i++) {
            grandfathers.add(new Grandfather(i  * ((rand.nextInt(TO_SPACING) + FROM_SPACING_2) + Grandfather.GRANDFATHER_WIDTH)));
        }

        polices = new ArrayList<Police>();
        for (int i = 1; i <= POLICE_COUNT; i++) {
            polices.add(new Police(i  * ((rand.nextInt(TO_SPACING) + FROM_SPACING_3) + Police.POLICE_WIDTH)));
        }

        pregnants = new ArrayList<Pregnant>();
        for (int i = 1; i <= ENEMY_COUNT; i++) {
            pregnants.add(new Pregnant(i  * ((rand.nextInt(TO_SPACING) + FROM_SPACING_4) + Pregnant.PREGNANT_WIDTH)));
        }

        childs = new ArrayList<Child>();
        for (int i = 1; i <= ENEMY_COUNT; i++) {
            childs.add(new Child(i  * ((rand.nextInt(TO_SPACING) + FROM_SPACING_5) + Child.CHILD_WIDTH)));
        }

        doctors = new ArrayList<Doctor>();
        for (int i = 1; i <= ENEMY_COUNT; i++) {
            doctors.add(new Doctor(i  * ((rand.nextInt(TO_SPACING) + FROM_SPACING_6) + Doctor.DOCTOR_WIDTH)));
        }

        teachers = new ArrayList<Teacher>();
        for (int i = 1; i <= ENEMY_COUNT; i++) {
            teachers.add(new Teacher(i  * ((rand.nextInt(TO_SPACING) + FROM_SPACING_7) + Teacher.TEACHER_WIDTH)));
        }

        dollars = new ArrayList<Dollar>();
        for (int i = 1; i <= DOLLAR_COUNT; i++) {
            dollars.add(new Dollar(i  * ((rand.nextInt(700) + 100) + Dollar.DOLLAR_WIDTH)));
        }

        lives = new ArrayList<Life>();
        for (int i = 1; i <= LIFE_COUNT; i++) {
            lives.add(new Life(i  * ((rand.nextInt(3000) + 1000) + Life.LIFE_WIDTH)));
        }

        powers = new ArrayList<Power>();
        for (int i = 1; i <= POWER_COUNT; i++) {
            powers.add(new Power(i  * (90 + Power.POWER_WIDTH)));
        }


    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (remainEnergy > 0) {
                hero.jump(); // при нажатии на экран главный герой прыгает
                remainEnergy--;
            } else {
                noJump.stop();
                noJump.play(0.3f);
            }
        }

        Gdx.input.setCatchBackKey(true);
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            gsm.set(new MenuState(gsm)); // при нажатии назад переходим на экран меню
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        hero.update(dt);
        labelBonusResult.update(dt);
        camera.position.x = hero.getPosition().x + 150; // привязываем главного героя к камере

        // переходим, если прошли всю игру
        if (hero.getPosition().x > 90000) {
            musicTheme.stop();
            allPause();
            soundWin.play(1f);
            gsm.set(new GameWin(gsm, resultBonus));
        }

        // бабушки
        for (Grandmother grandmother : grandmothers) {
            if (camera.position.x - (camera.viewportWidth / 2) > grandmother.getPosGrandmother().x) {
                grandmother.reposition(hero.getPosition().x + (Grandmother.GRANDMOTHER_WIDTH + (rand.nextInt(TO_SPACING) + FROM_SPACING_1)) * ENEMY_COUNT);
            }

            // действия при столкновении с бабушкой
            if (grandmother.collides(hero.getBounds())) {
                allPause();
                screamGrandmother.play(0.5f);
                if (remainLife <= 0) {
                    musicTheme.stop();
                    gsm.set(new GameOver(gsm, resultBonus));
                } else {
                    grandmother.hide();
                    remainLife--;
                }
            }
        }

        // дедушки
        for (Grandfather grandfather : grandfathers) {
            if (camera.position.x - (camera.viewportWidth / 2) > grandfather.getPosGrandfather().x) {
                grandfather.reposition(hero.getPosition().x + ((Grandfather.GRANDFATHER_WIDTH + (rand.nextInt(TO_SPACING) + FROM_SPACING_2)) * ENEMY_COUNT));
            }

            // действия при столкновении с дедушкой
            if (grandfather.collides(hero.getBounds())) {
                allPause();
                screamGrandfather.play(0.5f);
                if (remainLife <= 0) {
                    musicTheme.stop();
                    gsm.set(new GameOver(gsm, resultBonus));
                } else {
                    grandfather.hide();
                    remainLife--;
                }
            }
        }

        // полицейские
        for (Police police : polices) {
            if (camera.position.x - (camera.viewportWidth / 2) > police.getPosPolice().x) {
                police.reposition(hero.getPosition().x + ((Police.POLICE_WIDTH + (rand.nextInt(TO_SPACING) + FROM_SPACING_3)) * POLICE_COUNT));
            }

            // действия при столкновении с полицейским
            if (police.collides(hero.getBounds())) {
                allPause();
                police.hide();
                resultBonus = resultBonus / 2;
                screamPolice.play(0.5f);
            }
        }

        // беременные девушки
        for (Pregnant pregnant : pregnants) {
            if (camera.position.x - (camera.viewportWidth / 2) > pregnant.getPosPregnant().x) {
                pregnant.reposition(hero.getPosition().x + ((Pregnant.PREGNANT_WIDTH + (rand.nextInt(TO_SPACING) + FROM_SPACING_4)) * ENEMY_COUNT));
            }

            // действия при столкновении с беременной девушкой
            if (pregnant.collides(hero.getBounds())) {
                allPause();
                screamPregnant.play(0.5f);
                if (remainLife <= 0) {
                    musicTheme.stop();
                    gsm.set(new GameOver(gsm, resultBonus));
                } else {
                    pregnant.hide();
                    remainLife--;
                }
            }
        }

        // дети
        for (Child child : childs) {
            if (camera.position.x - (camera.viewportWidth / 2) > child.getPosChild().x) {
                child.reposition(hero.getPosition().x + ((Child.CHILD_WIDTH + (rand.nextInt(TO_SPACING) + FROM_SPACING_5)) * ENEMY_COUNT));
            }

            // действия при столкновении с ребенком
            if (child.collides(hero.getBounds())) {
                allPause();
                screamChild.play(0.5f);
                if (remainLife <= 0) {
                    musicTheme.stop();
                    gsm.set(new GameOver(gsm, resultBonus));
                } else {
                    child.hide();
                    remainLife--;
                }
            }
        }

        // доктора
        for (Doctor doctor : doctors) {
            if (camera.position.x - (camera.viewportWidth / 2) > doctor.getPosDoctor().x) {
                doctor.reposition(hero.getPosition().x + ((Doctor.DOCTOR_WIDTH + (rand.nextInt(TO_SPACING) + FROM_SPACING_6)) * ENEMY_COUNT));
            }

            // действия при столкновении с доктором
            if (doctor.collides(hero.getBounds())) {
                allPause();
                screamDoctor.play(0.5f);
                if (remainLife <= 0) {
                    musicTheme.stop();
                    gsm.set(new GameOver(gsm, resultBonus));
                } else {
                    doctor.hide();
                    remainLife--;
                }
            }
        }

        // учителя
        for (Teacher teacher : teachers) {
            if (camera.position.x - (camera.viewportWidth / 2) > teacher.getPosTeacher().x) {
                teacher.reposition(hero.getPosition().x + ((Teacher.TEACHER_WIDTH + (rand.nextInt(TO_SPACING) + FROM_SPACING_7)) * ENEMY_COUNT));
            }

            // действия при столкновении с учителем
            if (teacher.collides(hero.getBounds())) {
                allPause();
                screamTeacher.play(0.5f);
                if (remainLife <= 0) {
                    musicTheme.stop();
                    gsm.set(new GameOver(gsm, resultBonus));
                } else {
                    teacher.hide();
                    remainLife--;
                }
            }
        }

        // деньги
        for (Dollar dollar : dollars) {
            if (camera.position.x - (camera.viewportWidth / 2) > dollar.getPosDollar().x) {
                dollar.reposition(hero.getPosition().x + ((Dollar.DOLLAR_WIDTH + (rand.nextInt(300) + 150)) * DOLLAR_COUNT));
            }

            // действия при столкновении с деньгами
            if (dollar.collides(hero.getBounds())) {
                resultBonus++;
                dollar.hide();
                bonus.play(0.5f);
            }
        }

        // жизни
        for (Life life : lives) {
            if (camera.position.x - (camera.viewportWidth / 2) > life.getPosLife().x) {
                life.reposition(hero.getPosition().x + ((Life.LIFE_WIDTH + (rand.nextInt(3000) + 2000)) * LIFE_COUNT));
            }

            // действия при столкновении с новыми жизнями
            if (life.collides(hero.getBounds())) {
                remainLife++;
                life.hide();
                soundLife.play(0.5f);
            }
        }

        // энергия
        for (Power power : powers) {
            if (camera.position.x - (camera.viewportWidth / 2) > power.getPosPower().x) {
                power.reposition(hero.getPosition().x + ((Power.POWER_WIDTH + 90) * POWER_COUNT));
            }

            // действия при столкновении с силой
            if (power.collides(hero.getBounds())) {
                if (remainEnergy < 15)
                    remainEnergy++;
                power.hide();
            }
        }

        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined); // устанавливаем матрицу проекций для камеры
        sb.begin();
        sb.draw(background, camera.position.x - (camera.viewportWidth / 2), 0); // рисуем фон
        sb.draw(hero.getHero(), hero.getPosition().x, hero.getPosition().y); // рисуем главного героя

        switch (remainEnergy) {
            case 0: sb.draw(energy_0, camera.position.x - (energy_0.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 1: sb.draw(energy_1, camera.position.x - (energy_1.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 2: sb.draw(energy_2, camera.position.x - (energy_2.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 3: sb.draw(energy_3, camera.position.x - (energy_3.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 4: sb.draw(energy_4, camera.position.x - (energy_4.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 5: sb.draw(energy_5, camera.position.x - (energy_5.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 6: sb.draw(energy_6, camera.position.x - (energy_6.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 7: sb.draw(energy_7, camera.position.x - (energy_7.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 8: sb.draw(energy_8, camera.position.x - (energy_8.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 9: sb.draw(energy_9, camera.position.x - (energy_9.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 10: sb.draw(energy_10, camera.position.x - (energy_10.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 11: sb.draw(energy_11, camera.position.x - (energy_11.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 12: sb.draw(energy_12, camera.position.x - (energy_12.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 13: sb.draw(energy_13, camera.position.x - (energy_13.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 14: sb.draw(energy_14, camera.position.x - (energy_14.getWidth() / 2), labelBonusResult.getPosition().y); break;
            case 15: sb.draw(energy_15, camera.position.x - (energy_15.getWidth() / 2), labelBonusResult.getPosition().y); break;
        }

        sb.draw(dollar, labelBonusResult.getPosition().x + 5, labelBonusResult.getPosition().y - 35);
        font.draw(sb, "" + resultBonus, labelBonusResult.getPosition().x + 50, labelBonusResult.getPosition().y - 15); // рисуем количество заработанных денег

        sb.draw(life, labelBonusResult.getPosition().x + 255, labelBonusResult.getPosition().y - 40);
        font.draw(sb, "" + remainLife, labelBonusResult.getPosition().x + 295, labelBonusResult.getPosition().y - 15); // рисуем количество оставшихся жизней

        // поясняющие игровые надписи
        font2.draw(sb, "Украдите как можно больше денег!", 250, camera.position.y + 60);

        text1.setText(font2, "Перепрыгивайте пенсионеров, бюджетников,");
        font2.draw(sb, text1, 900 - text1.width/2, camera.position.y + 60);
        text2.setText(font2, "беременных женщин и детей!");
        font2.draw(sb, text2, 900 - text2.width/2, camera.position.y + 40);

        text3.setText(font2, "Будьте аккуратны!");
        font2.draw(sb, text3, 1400 - text3.width/2, camera.position.y + 60);
        text4.setText(font2, "Полицейские отнимают половину денег!");
        font2.draw(sb, text4, 1400 - text4.width/2, camera.position.y + 40);

        font2.draw(sb, "Вы 2-й год у власти", 5000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 3-й год у власти", 10000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 4-й год у власти", 15000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 5-й год у власти", 20000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 6-й год у власти", 25000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 7-й год у власти", 30000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 8-й год у власти", 35000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 9-й год у власти", 40000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 10-й год у власти", 45000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 11-й год у власти", 50000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 12-й год у власти", 55000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 13-й год у власти", 60000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 14-й год у власти", 65000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 15-й год у власти", 70000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 16-й год у власти", 75000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы 17-й год у власти", 80000, camera.position.y + 60); // надпись нового уровня
        font2.draw(sb, "Вы последний 18-й год у власти", 85000, camera.position.y + 60); // надпись нового уровня

        // рисуем бабушек
        for (Grandmother grandmother : grandmothers) {
            sb.draw(grandmother.getGrandmother(), grandmother.getPosGrandmother().x, grandmother.getPosGrandmother().y);
        }

        // рисуем дедушек
        for (Grandfather grandfather : grandfathers) {
            sb.draw(grandfather.getGrandfather(), grandfather.getPosGrandfather().x, grandfather.getPosGrandfather().y);
        }

        // рисуем полицейских
        for (Police police : polices) {
            sb.draw(police.getPolice(), police.getPosPolice().x, police.getPosPolice().y);
        }

        // рисуем беременных девушек
        for (Pregnant pregnant : pregnants) {
            sb.draw(pregnant.getPregnant(), pregnant.getPosPregnant().x, pregnant.getPosPregnant().y);
        }

        // рисуем детей
        for (Child child : childs) {
            sb.draw(child.getChild(), child.getPosChild().x, child.getPosChild().y);
        }

        // рисуем докторов
        for (Doctor doctor : doctors) {
            sb.draw(doctor.getDoctor(), doctor.getPosDoctor().x, doctor.getPosDoctor().y);
        }

        // рисуем учителей
        for (Teacher teacher : teachers) {
            sb.draw(teacher.getTeacher(), teacher.getPosTeacher().x, teacher.getPosTeacher().y);
        }

        // рисуем деньги
        for (Dollar dollar : dollars) {
            sb.draw(dollar.getDollar(), dollar.getPosDollar().x, dollar.getPosDollar().y);
        }

        // рисуем жизни
        for (Life life : lives) {
            sb.draw(life.getLife(), life.getPosLife().x, life.getPosLife().y);
        }

//        // рисуем силу
//        for (Power power : powers) {
//            sb.draw(power.getPower(), power.getPosPower().x, power.getPosPower().y);
//        }

        sb.draw(platform, platformPos1.x, platformPos1.y); // рисуем первую платформу
        sb.draw(platform, platformPos2.x, platformPos2.y); // рисуем вторую платформу
        sb.end();
    }

    // зацикливание текстуры платформы
    private void updateGround() {
        if (camera.position.x - (camera.viewportWidth / 2) > platformPos1.x + platform.getWidth())
            platformPos1.add(platform.getWidth() * 2, 0);
        if (camera.position.x - (camera.viewportWidth / 2) > platformPos2.x + platform.getWidth())
            platformPos2.add(platform.getWidth() * 2, 0);
    }

    // останавливаем все звуки
    private void allPause() {
        screamGrandmother.stop();
        screamGrandfather.stop();
        screamPolice.stop();
        screamPregnant.stop();
        screamChild.stop();
        screamDoctor.stop();
        screamTeacher.stop();
        soundLife.stop();
        bonus.stop();
        noJump.stop();
    }

    // освобождаем ресурсы
    @Override
    public void dispose() {
        background.dispose();
        hero.dispose();
        platform.dispose();
        life.dispose();
        dollar.dispose();
        musicTheme.dispose();
        screamGrandmother.dispose();
        screamGrandfather.dispose();
        screamPolice.dispose();
        screamPregnant.dispose();
        screamChild.dispose();
        screamDoctor.dispose();
        screamTeacher.dispose();
        soundLife.dispose();
        bonus.dispose();
        font.dispose();

        for (Grandmother grandmother : grandmothers)
            grandmother.dispose();

        for (Grandfather grandfather : grandfathers)
            grandfather.dispose();

        for (Police police : polices)
            police.dispose();

        for (Pregnant pregnant : pregnants)
            pregnant.dispose();

        for (Child child : childs)
            child.dispose();

        for (Doctor doctor : doctors)
            doctor.dispose();

        for (Teacher teacher : teachers)
            teacher.dispose();

        for (Dollar dollar : dollars)
            dollar.dispose();

        for (Life life : lives)
            life.dispose();

        for (Power power : powers)
            power.dispose();
    }

}