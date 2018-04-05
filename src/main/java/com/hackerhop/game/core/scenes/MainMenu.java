package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.utils.Character;
import com.hackerhop.game.core.MainController;


import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


public class MainMenu extends Scene {

    private static final String TAG = MainMenu.class.getName();

    // Main Screen Textures
    private Texture logo;
    private Texture background;
    private Sprite sprite1;
    private Sprite sprite2;
    private Sprite sprite3;
    private Sprite sprite4;
    private Sprite highScoreButton;
    private Sprite getGitHubButtonActivated;
    private Sprite gitHubButton;
    private Music music;

    public MainMenu(MainController controller) {
        super(controller);
    }

    @Override
    public void update() {

    }

    @Override
    public void loadResources() {
        logo = new Texture("mainScreen/Logo.png");
        background = new Texture("background/ShinemanPixel.png");

        sprite1 = new Sprite(new Texture("player/rob.png"));
        sprite1.setPosition(100, 75);

        sprite2 = new Sprite(new Texture("player/Nick.png"));
        sprite2.setPosition(200, 75);

        sprite3 = new Sprite(new Texture("player/Katie.png"));
        sprite3.setPosition(300, 75);

        sprite4 = new Sprite(new Texture("player/Ye.png"));
        sprite4.setPosition(400, 75);

        highScoreButton = new Sprite(new Texture("mainScreen/HighScoreButton.png"));
        highScoreButton.setPosition(75, 325);

        gitHubButton = new Sprite(new Texture("mainScreen/GitHubButton.png"));
        gitHubButton.setPosition(285, 325);
        getGitHubButtonActivated = new Sprite(new Texture("mainScreen/GitHubButtonActivated.png"));
        getGitHubButtonActivated.setPosition(285, 325);

        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/waves.mp3"));
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(SpriteBatch batch) {

        Gdx.gl.glClearColor(1, .5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw next frame (current scene)
        batch.begin();

        batch.draw(background, 0, 0);
        batch.draw(logo, 0, 50);
        if (sprite1.getBoundingRectangle().contains(Gdx.input.getX(), (Gdx.input.getY() / 7))) {
            batch.draw(sprite1, 75, 50, 90, 125);
        } else {
            sprite1.draw(batch);
        }
        if (sprite2.getBoundingRectangle().contains(Gdx.input.getX(), (Gdx.input.getY() / 7))) {
            batch.draw(sprite2, 175, 50, 90, 125);
        } else {
            sprite2.draw(batch);
        }
        if (sprite3.getBoundingRectangle().contains(Gdx.input.getX(), (Gdx.input.getY() / 7))) {
            batch.draw(sprite3, 275, 50, 90, 125);
        } else {
            sprite3.draw(batch);
        }
        if (sprite4.getBoundingRectangle().contains(Gdx.input.getX(), (Gdx.input.getY() / 7))) {
            batch.draw(sprite4, 375, 50, 90, 125);
        } else {
            sprite4.draw(batch);
        }if(gitHubButton.getBoundingRectangle().contains(Gdx.input.getX(),(Gdx.input.getY()))){
            batch.draw(getGitHubButtonActivated,285,325);
        }else{
            gitHubButton.draw(batch);
        }
        highScoreButton.draw(batch);

        batch.end();
    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int y = Gdx.graphics.getHeight() - screenY;
        MainController controller = super.getController();

        if (sprite1.getBoundingRectangle().contains(screenX, y)) {
            controller.setScene(new GameScene(controller, Character.ROB));
        } else if (sprite2.getBoundingRectangle().contains(screenX, y)) {
            controller.setScene(new GameScene(controller, Character.NICK));
        } else if (sprite3.getBoundingRectangle().contains(screenX, y)) {
            controller.setScene(new GameScene(controller, Character.KATIE));
        } else if (sprite4.getBoundingRectangle().contains(screenX, y)) {
            controller.setScene(new GameScene(controller, Character.YE));
        } else if (gitHubButton.getBoundingRectangle().contains(screenX, y)) {
            try {
                openWebpage(new URL("https://github.com/nicovank/HackerHop"));
            } catch (MalformedURLException ignored) {

            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    @Override
    public void dispose() {
        logo.dispose();
        background.dispose();

        sprite1.getTexture().dispose();
        sprite2.getTexture().dispose();
        sprite3.getTexture().dispose();
        sprite4.getTexture().dispose();
        highScoreButton.getTexture().dispose();
        gitHubButton.getTexture().dispose();
        getGitHubButtonActivated.getTexture().dispose();

        music.stop();
        music.dispose();
    }

    private static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }
}