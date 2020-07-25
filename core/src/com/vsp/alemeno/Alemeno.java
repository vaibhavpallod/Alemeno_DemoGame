package com.vsp.alemeno;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

public class Alemeno extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background, apple;
    Texture[] dinas;
    float gravity = 0.2f, velocity;
    int dinaY = 0, i = 0, feed = 0, stagenumberint = 0;
    long time, copm;
    private Stage stage;
    TextButton button2;
    private Label outputLabel;
    BitmapFont stageno, applesfeed, text1, text2;
    public AssetManager manager;
    float textWidth, applewidth, text1width, text2width;
    Music music;
    ArrayList<Integer> appleX = new ArrayList<>();
    ArrayList<Integer> appleY = new ArrayList<>();
    Sound sound;
    String string1, string2, buttontext;

    @Override
    public void create() {
        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        int Help_Guides = 12;
        int row_height = Gdx.graphics.getWidth() / 12;
        int col_width = Gdx.graphics.getWidth() / 12;
        int midwidth = Gdx.graphics.getWidth() / 2;
        int midheight = Gdx.graphics.getHeight() / 2;
        sound = Gdx.audio.newSound(Gdx.files.internal("chewsound.mp3"));
        buttontext = "Feed Apples ";
        background = new Texture("cartoonback.jpg");
        apple = new Texture("redapple.png");
        dinas = new Texture[5];
        dinas[0] = new Texture("dina0.png");
        dinas[1] = new Texture("dina1.png");
        dinas[2] = new Texture("dina2.png");
        dinas[3] = new Texture("dina3.png");
        dinas[4] = new Texture("dina4.png");
        time = System.currentTimeMillis();
        copm = System.currentTimeMillis();
        dinaY = Gdx.graphics.getHeight() / 2 - dinas[0].getHeight();
//        font = new BitmapFont(Gdx.files.internal("data/rayanfont.fnt"), false);
        stageno = new BitmapFont();
        applesfeed = new BitmapFont();
        text1 = new BitmapFont();
        text2 = new BitmapFont();
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

       /* textFieldStyle = mySkin.get(TextField.TextFieldStyle.class);
        textFieldStyle.font.getData().setScale(2.5f);
        */
        GlyphLayout layout = new GlyphLayout(stageno, "Stage 1");
        textWidth = layout.width;
        GlyphLayout applelayout = new GlyphLayout(applesfeed, "Apple feeded 12");
        applewidth = applelayout.width;
        GlyphLayout text1lay = new GlyphLayout(text1, "Meet Dyno, your new pet! ");
        text1width = text1lay.width;
        GlyphLayout text2lay = new GlyphLayout(text2, "You need to feed him 5 apples to help \nhim get to the next stage. ");
        text2width = text2lay.width;
        string1 = "Meet Dyno, your new pet!";
        string2 = "You need to feed him 5 apples to help \n        \thim get to the next stage. ";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("FtyStrategycideNcv-elGl.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        FreeTypeFontGenerator.FreeTypeFontParameter parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2.size = 38;
        FreeTypeFontGenerator.FreeTypeFontParameter parameter3 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter3.size = 50;
        parameter.size = 60;
        stageno = generator.generateFont(parameter); // font size 12 pixels
        applesfeed = generator.generateFont(parameter); // font size 12 pixels
        text1 = generator.generateFont(parameter3);
        text2 = generator.generateFont(parameter2);
        generator.dispose();

        button2 = new TextButton(buttontext, mySkin, "default");

        button2.setSize(col_width * 5, row_height);
        button2.setPosition(midwidth - button2.getWidth() / 2, row_height * 3);
        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                feed++;
                sound.play();
                makeapple();
                string1 = "Meet Dyno, your new pet!";
                string2 = "You need to feed him 5 apples to help \n        \thim get to the next stage. ";
                if (feed != 1)
                    if ((feed) % 5 == 0) {
                        if (feed == 20) {
                            i = 4;
                            buttontext="Play again";
                            button2.setText(buttontext);
                            string1 = "             You won!";
                            string2 = "Congratulations! Dyno has grown\n     \t big and strong with your help.";
                        } else {


                            i++;
                        }


                    }
                if (feed == 21) {
                    i = 0;
                    feed = 0;
                    buttontext = "Feed Apples ";

                    button2.setText(buttontext);

                }

            }
        });

        stage.addActor(button2);
        outputLabel = new Label("Press a Button", mySkin, "black");
        outputLabel.setSize(Gdx.graphics.getWidth(), row_height);
        outputLabel.setPosition(0, row_height);
        outputLabel.setAlignment(Align.center);
        stage.addActor(outputLabel);
    }

    public void makeapple() {
        float hight = Gdx.graphics.getHeight() / 2 + dinas[i].getHeight() / 2;
        float width = 0;
// - dinas[i].getWidth() / 2
        appleY.add((int) hight);
        appleX.add((int) width);

    }

    @Override
    public void render() {
//		Gdx.gl.glClearColor(1, 0, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        textFieldStyle.font.draw(batch,"Stage 1",Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/10);

/*
        button2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                i=0;
            }
        });*/

        for (int i = 0; i < appleX.size(); i++) {
            batch.draw(apple, appleX.get(i), appleY.get(i));
            if (appleX.get(i) + 10 < Gdx.graphics.getWidth() / 2 - dinas[i].getWidth() / 2)
                appleX.set(i, appleX.get(i) + 10);
            else
                appleX.remove(i);
        }

        text1.draw(batch, string1, Gdx.graphics.getWidth() / 2 - text1width * 1.5f, Gdx.graphics.getHeight() / 4);
        text2.draw(batch, string2, Gdx.graphics.getWidth() / 2 - text2width, Gdx.graphics.getHeight() / 4 - text1.getLineHeight() - 20);

        applesfeed.draw(batch,"Apples feed " +feed, Gdx.graphics.getWidth() / 2 - applewidth, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 6);
        stageno.draw(batch, "Stage " + i, Gdx.graphics.getWidth() / 2 - textWidth, Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 10);


        batch.draw(dinas[i], Gdx.graphics.getWidth() / 2 - dinas[i].getWidth() / 2, Gdx.graphics.getHeight() / 2 - dinas[i].getHeight() / 2);


      /*  if (time > copm + 2000 && i < 4) {
            i++;
            copm = System.currentTimeMillis();
        } else {
            time = System.currentTimeMillis();
            batch.draw(dinas[i], Gdx.graphics.getWidth() / 2 - dinas[i].getWidth() / 2, Gdx.graphics.getHeight() / 2 - dinas[i].getHeight() / 2);
        }*/
        /*velocity += gravity;

        dinaY -= velocity;
*/
        stage.act();
        stage.draw();
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stageno.dispose();
        applesfeed.dispose();
		/*background.dispose();
		img.dispose();*/
    }


}
