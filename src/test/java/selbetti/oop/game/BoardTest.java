package selbetti.oop.game;

import org.junit.*;

/**
 *
 */
public class BoardTest {

	@Test
	public void ensureThatCreatesSquareBoardWithSize2() {
		Board board = Board.create(2);
		Assert.assertEquals(board.width, 2);
		Assert.assertEquals(board.height, 2);
	}

	@Test
	public void ensureThatCanAddFirstHeroIntoTheBoard() {
		Board board = Board.create(4);
		board.addHero();
		Assert.assertEquals(board.heroes.get(0).positionX, 0);
		Assert.assertEquals(board.heroes.get(0).positionY, 0);
	}

	@Test
	public void ensureThatCanAddSecondHeroIntoTheBoard() {
		Board board = Board.create(4);
		board.addHero();
		board.addHero();
		Assert.assertEquals(board.heroes.get(1).positionX, 3);
		Assert.assertEquals(board.heroes.get(1).positionY, 3);
	}
}
