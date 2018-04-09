package com.hackerhop.game.core.objects.platforms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.platforms.PlatformGroup;
import org.jbox2d.dynamics.World;

public class Platforms implements GraphicsElement {

	// used in update(World world) method
	private static final int THRESHOLD = 420;   //blaze it
	private static final String TAG = PlatformGroup.class.getName();
	// change value in test every time you touch this value
	private static final int wiggleRoom = 8;
	// tracks lowest PlatformGroup
	private static int tracker;
	// only 4 PlatformGroup objects, cycled through as player advances
	private PlatformGroup[] platformGroups = new PlatformGroup[4];

	public Platforms(World world) {
		platformGroups[0] = new PlatformGroup(world, 0, wiggleRoom);
		for (int i = 1; i < 4; ++i) {
			platformGroups[i] = new PlatformGroup(world, i, wiggleRoom);
		}
		tracker = 0;
	}

	/**
	 * <p>Checks if the y-value of the PlatformGroup at <code>tracker</code> is below
	 * the y-value of the <code>Camera</code> by more than <code>THRESHOLD</code> units.
	 * </p>
	 * <p>If the PlatformGroup is lower than the specified threshold, then it is replaced
	 * with a new PlatformGroup object above the current highest PlatformGroup object,
	 * and increments the tracker by 1. All Platform objects in the old PlatformGroup are
	 * destroyed from <code>world</code> before PlatformGroup is replaced.
	 * </p>
	 *
	 * @param cameraPositionY the y-value of the camera used to check against the y-value
	 *                        of PlatformGroup objects
	 * @param world           the physics world from where Platform objects reside
	 */
	public void update(float cameraPositionY, World world) {
		if (platformGroups[tracker].getY() <= (cameraPositionY - THRESHOLD) / 10) {
			float tmpY = platformGroups[tracker].getY() / 20 + 4;
			platformGroups[tracker].destroy();
			PlatformGroup p = new PlatformGroup(world, tmpY, wiggleRoom);
			p.loadResources();
			platformGroups[tracker] = p;


			tracker = (tracker < 3) ? ++tracker : 0;
		}
	}

	/**
	 * Does what it says on the tin - destroys all physical objects from <code>world</code>.
	 */
	public void destroyAll() {
		for (PlatformGroup p : platformGroups) {
			p.destroy();
		}
	}

	/**
	 * Counts the total number of Platform objects in the world
	 *
	 * @return number of Platform objects in the physics world
	 */
	public int getCount() {
		int count = 0;
		for (PlatformGroup g : platformGroups) {
			count += g.getCount();
		}
		return count;
	}

	@Override
	public void loadResources() {
		for (PlatformGroup p : platformGroups) {
			p.loadResources();
		}
	}

	@Override
	public void dispose() {
		for (PlatformGroup g : platformGroups) {
			g.dispose();
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		for (PlatformGroup g : platformGroups) {
			g.render(batch);
		}
	}
}