package zompocalypse.tests.logic;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import zompocalypse.datastorage.Loader;
import zompocalypse.datastorage.Parser;
import zompocalypse.gameworld.characters.HomerStrategy;
import zompocalypse.gameworld.characters.StrategyZombie;
import zompocalypse.gameworld.world.World;

public class ZombieTests {

	World game;

	public ZombieTests() {
		try {
			/**
			 * TODO: Ideally, we should create a Test map file that can be loaded in here. That
			 * file would have an instance of each object to test within the smallest possible space.
			 */
			game = Parser.ParseMap(Loader.mapFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A zombie in the middle of the map should be able to move around and get to
	 * other nearby squares successfully.
	 */

	@Test public void zombieValidMove1() {
		StrategyZombie z = new StrategyZombie(3, 3, new HomerStrategy());
		z.moveNorth();

		z.tick(game);

		assertTrue(z.getX() == 3);
		assertTrue(z.getY() == 2);
	}

	@Test public void zombieValidMove2() {
		StrategyZombie z = new StrategyZombie(4, 4, new HomerStrategy());
		// This sequence should leave the zombie at 3, 3
		z.moveNorth();
		z.tick(game);

		z.moveWest();
		z.tick(game);

		z.moveNorth();
		z.tick(game);

		z.moveSouth();
		z.tick(game);

		assertTrue(z.getX() == 3);
		assertTrue(z.getY() == 3);
	}

	/**
	 * A zombie at the edge of the map shouldn't be able to move past the edge.
	 */

	@Test public void zombieInvalidMove1() {
		StrategyZombie z = new StrategyZombie(0, 0, new HomerStrategy());
		z.moveNorth();

		z.tick(game);

		assertTrue(z.getX() == 0);
		assertTrue(z.getY() == 0);
	}

	@Test public void zombieInvalidMove2() {
		StrategyZombie z = new StrategyZombie(0, 0, new HomerStrategy());
		// This sequence of movements should still leave the Zombie at the position 0,0
		z.moveWest();
		z.tick(game);

		z.moveNorth();
		z.tick(game);

		//TODO The following down move hits a wall in current test map
		z.moveSouth();
		z.tick(game);

		z.moveNorth();
		z.tick(game);

		z.moveNorth();
		z.tick(game);

		assertTrue(z.getX() == 0);
		assertTrue(z.getY() == 0);
	}

}