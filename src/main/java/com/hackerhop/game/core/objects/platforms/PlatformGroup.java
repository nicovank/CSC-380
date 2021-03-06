package com.hackerhop.game.core.objects.platforms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.utils.Constants;
import org.jbox2d.dynamics.World;

import static com.hackerhop.game.core.utils.Random.randomInt;

public class PlatformGroup implements GraphicsElement, Constants {

    private static final String TAG = PlatformGroup.class.getName();


    private Platform[] platforms;   // Set of platforms
    private float y;
    private int xCount, wiggleRoom;
    private float offset;


    /**
     * Creates a new set of platforms, generating initial ones randomly.
     *
     * @param world The Box2D world of the platforms.
     */
    public PlatformGroup(World world, float y, int wiggleRoom) {
        this.wiggleRoom = wiggleRoom;
        this.y = y;
        offset = wiggleRoom - randomInt(2 * wiggleRoom);
        platforms = generatePlatforms(world);
    }

    /**
     * <p>Generates Platform objects for this PlatformGroup object.</p>
     * <p>If this PlatformObject is at the base position, i.e. if <code>this.y == 0</code>,
     * 9 Platform objects are generated to be used as the base platform.
     * </p>
     * <p>For all other cases, a maximum of 3 Platform objects are created at random
     * within a circle of radius <code>wiggleRoom</code>. The center of each circle is separated from
     * another by <code>GRID_SEPARATION</code> units.
     * </p>
     *
     * @param w the physics world where Platform objects are to be generated
     * @return array of generated Platform objects
     */
    private Platform[] generatePlatforms(World w) {
        Platform[] h;
        if (y != 0) {
            h = new Platform[PLATFORMS_PER_ROW];
            for (int i = 0; i < h.length; ++i) {

                // Generate Platform at index if randomInt > 7
                if (randomInt(10) < 7) {

                    float x = (((GRID_SEPARATION * i) + offset) < 6) ? 6 : (GRID_SEPARATION * i) + offset;
                    x = (((GRID_SEPARATION * i) + offset) > 42) ? 42 : x;

                    h[i] = new Platform(x,
                            (y * GRID_SEPARATION) + (wiggleRoom - randomInt(2 * wiggleRoom)), w);
                    ++xCount;
                }

                // If no Platforms generated, generate one Platform at random index
                if (i == 2 && xCount == 0) {
                    int index = randomInt(h.length);

                    float x = (((GRID_SEPARATION * index) + offset) < 6) ? 6 : (GRID_SEPARATION * index) + offset;
                    x = (((GRID_SEPARATION * index) + offset) > 42) ? 42 : x;

                    h[index] = new Platform(x,
                            (y * GRID_SEPARATION) + (wiggleRoom - randomInt(2 * wiggleRoom)), w);
                    ++xCount;
                    break;
                }
            }
        } else {
            h = new Platform[9];
            for (int i = 0; i < 54; i += 6) {
                h[i / 6] = new Platform(i, 0, w);
                ++xCount;
            }
        }
        return h;
    }

    /**
     * Returns the y-value of the central grid
     *
     * @return y-value of the central grid
     */
    public float getY() {
        return y * GRID_SEPARATION;
    }

    public Platform[] getPlatforms() {
        return platforms;
    }

    public int getCount() {
        return xCount;
    }

    public boolean isEmpty() {
        return getCount() == 0;
    }

    @Override
    public void loadResources() {
        for (Platform p : platforms) {
            if (p != null) {
                p.loadResources();
            }
        }
    }

    @Override
    public void dispose() {
        for (Platform p : platforms) {
            if (p != null) p.dispose();
        }
    }

    public void destroy() {
        for (Platform p : platforms) {
            if (p != null) {
                p.destroy();
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (Platform p : platforms) {
            if (p != null) p.render(batch);
        }
    }
}
