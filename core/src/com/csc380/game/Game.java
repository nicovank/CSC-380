package com.csc380.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.csc380.game.scenes.Menu;
import com.csc380.game.scenes.Scene;

/**
 * "Main" class, used by DesktopLauncher and AndroidLauncher.
 * Starts the game with the initial scene.
 */
public class Game extends ApplicationAdapter {

	// We will have this TAG in every class to easily use LibGDX's logging system.
	private static final String TAG = Game.class.getName();

	private SpriteBatch batch;
	private Scene currentScene;

	/**
	 * Entry point of the application.
	 */
	@Override
	public void create () {

		// Set application to log everything.
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		batch = new SpriteBatch();

		// Start the game with the menu
		currentScene = new Menu();

		// To log information or errors use:

		// Gdx.app.log(TAG, "This is an info log.");
		// Gdx.app.debug(TAG, "This is a debug log.");
		// Gdx.app.error(TAG, "This is an error log.");
	}

	/**
	 * "Main" rendering, renders the current scene.
	 */
	@Override
	public void render () {
		// Update current scene
		currentScene.update();

		// Clear frame
		Gdx.gl.glClearColor(1, .5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Draw next frame
		batch.begin();
		// Draw sprites and textures (or the scene) to the batch.
		// Draws the current scene.
		currentScene.render(batch);
		batch.end();
	}

	/**
	 * Disposes of the batch and the current scene.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		currentScene.dispose();
	}
}