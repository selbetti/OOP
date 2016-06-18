package selbetti.oop.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	final Board b = new Board( 8, 8, 1, Directions.Right );

	@Test
	public void ensureCanCreateBoardHeight() {
		final int height = b.height();
		assertEquals( 8, height );
	}

	@Test
	public void ensureCanCreateBoardWidth() {
		final int width = b.width();
		assertEquals( 8, width );
	}

	@Test
	public void ensureCanMoveHeroDown() {
		final Hero hero = new Hero( 0, 0, Directions.Down );
		b.actionHero( HeroActions.Move, hero );

		assertEquals( 1, hero.positionY );
	}

	@Test
	public void ensureCantMoveHeroDown() {
		final Hero hero = new Hero( 0, 7, Directions.Down );
		b.actionHero( HeroActions.Move, hero );

		assertEquals( 7, hero.positionY );
	}

	@Test
	public void ensureCantMoveHeroUp() {
		final Hero hero = new Hero( 0, 0, Directions.Up );
		b.actionHero( HeroActions.Move, hero );

		assertEquals( 0, hero.positionY );
	}

	@Test
	public void ensureCanMoveHeroUp() {
		final Hero hero = new Hero( 0, 7, Directions.Up );
		b.actionHero( HeroActions.Move, hero );

		assertEquals( 6, hero.positionY );
	}

	@Test
	public void ensureCanMoveHeroRight() {
		final Hero hero = new Hero( 0, 0, Directions.Right );
		b.actionHero( HeroActions.Move, hero );

		assertEquals( 1, hero.positionX );
	}

	@Test
	public void ensureCantMoveHeroRight() {
		final Hero hero = new Hero( 7, 0, Directions.Right );
		b.actionHero( HeroActions.Move, hero );

		assertEquals( 7, hero.positionX );
	}

	@Test
	public void ensureCanMoveHeroLeft() {
		final Hero hero = new Hero( 7, 0, Directions.Left );
		b.actionHero( HeroActions.Move, hero );

		assertEquals( 6, hero.positionX );
	}

	@Test
	public void ensureCantMoveHeroLeft() {
		final Hero hero = new Hero( 0, 0, Directions.Left );
		b.actionHero( HeroActions.Move, hero );

		assertEquals( 0, hero.positionX );
	}

	@Test
	public void ensureCanRotateLeft() {
		final Hero hero = new Hero( 0, 0, Directions.Down );
		b.actionHero( HeroActions.RotateLeft, hero );
	}
}
