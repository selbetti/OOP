package selbetti.oop.game;

import static org.junit.Assert.*;
import org.junit.*;

/**
 *
 */
public class BoardTest {

	Board board;

	@Before
	public void init()
	{
		board = Board.create(4);
	}

	@Test
	public void ensureThatCreatesSquareBoardWithSize4() {
		Assert.assertEquals(board.width, 4);
		Assert.assertEquals(board.height, 4);
	}

	@Test
	public void ensureThatCanAddFirstHeroIntoTheBoard() {
		board.addHero();
		Assert.assertEquals(board.heroes.get(0).positionX, 0);
		Assert.assertEquals(board.heroes.get(0).positionY, 0);
	}

	@Test
	public void ensureThatCanAddSecondHeroIntoTheBoard() {
		board.addHero();
		board.addHero();
		Assert.assertEquals(board.heroes.get(1).positionX, 3);
		Assert.assertEquals(board.heroes.get(1).positionY, 3);
	}

	@Test
	public void ensureThatHeroIsAlive() {
		board.addHero();
		assertTrue(board.isHeroAlive(0));
	}

	@Test
	public void ensureThatHeroIsDead() {
		Hero hero = board.addHero();
		hero.isAlive = false;
		assertFalse(board.isHeroAlive(0));
	}
}
