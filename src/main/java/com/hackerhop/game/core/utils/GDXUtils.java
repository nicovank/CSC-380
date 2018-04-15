package com.hackerhop.game.core.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class GDXUtils {
	/**
	 * Returns the x-position of the mouse on screen.
	 * @return the x-position of the mouse on screen.
	 */
	public static int mouseX() {
		return Gdx.input.getX();
	}

	/**
	 * Returns the x-position of the mouse on screen.
	 * @return the x-position of the mouse on screen.
	 */
	public static int mouseY() {
		return Gdx.graphics.getHeight() - Gdx.input.getY();
	}

	/**
	 * Returns the position of the mouse on the screen.
	 * @return a Vector2 representing the position of the mouse on screen.
	 */
	public static Vector2 mousePosition() {
		return new Vector2(mouseX(), mouseY());
	}
}
