package selbetti.oop.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MoveHeroTest {

	final List<Hero> lstHeroes = new ArrayList<>();

	final Board board = new Board( lstHeroes, 8, 8, 1 );

	@Before
	public void init() {
		lstHeroes.clear();
	}

	@Test
	public void ensureCanCreateBoardHeight() {
		final int height = board.height();
		assertEquals( 8, height );
	}

	@Test
	public void ensureCanCreateBoardWidth() {
		final int width = board.width();
		assertEquals( 8, width );
	}

	@Test
	public void ensureCanMoveHeroDown() {
		final Hero hero = new Hero( 0, 0, Directions.Down );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 1, hero.positionY );
	}

	@Test
	public void ensureCantMoveHeroDown() {
		final Hero hero = new Hero( 0, 7, Directions.Down );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 7, hero.positionY );
	}

	@Test
	public void ensureCantMoveHeroUp() {
		final Hero hero = new Hero( 0, 0, Directions.Up );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 0, hero.positionY );
	}

	@Test
	public void ensureCanMoveHeroUp() {
		final Hero hero = new Hero( 0, 7, Directions.Up );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 6, hero.positionY );
	}

	@Test
	public void ensureCanMoveHeroRight() {
		final Hero hero = new Hero( 0, 0, Directions.Right );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 1, hero.positionX );
	}

	@Test
	public void ensureCantMoveHeroRight() {
		final Hero hero = new Hero( 7, 0, Directions.Right );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 7, hero.positionX );
	}

	@Test
	public void ensureCanMoveHeroLeft() {
		final Hero hero = new Hero( 7, 0, Directions.Left );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 6, hero.positionX );
	}

	@Test
	public void ensureCantMoveHeroLeft() {
		final Hero hero = new Hero( 0, 0, Directions.Left );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 0, hero.positionX );
	}

	@Test
	public void ensureCantMoveRightHero0SameLocationHero1() {
		final Hero hero0 = new Hero( 0, 0, Directions.Right );
		final Hero hero1 = new Hero( 1, 0, Directions.Left );
		lstHeroes.add( hero0 );
		lstHeroes.add( hero1 );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 0, hero0.positionX );
		assertEquals( 0, hero0.positionY );
		assertEquals( 1, hero1.positionX );
		assertEquals( 0, hero1.positionY );
	}

	@Test
	public void ensureCantMoveLeftHero0SameLocationHero1() {
		final Hero hero0 = new Hero( 1, 0, Directions.Left );
		final Hero hero1 = new Hero( 0, 0, Directions.Left );
		lstHeroes.add( hero0 );
		lstHeroes.add( hero1 );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 1, hero0.positionX );
		assertEquals( 0, hero0.positionY );
		assertEquals( 0, hero1.positionX );
		assertEquals( 0, hero1.positionY );
	}

	@Test
	public void ensureCantMoveDownHero0SameLocationHero1() {
		final Hero hero0 = new Hero( 0, 0, Directions.Down );
		final Hero hero1 = new Hero( 0, 1, Directions.Down );
		lstHeroes.add( hero0 );
		lstHeroes.add( hero1 );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 0, hero0.positionX );
		assertEquals( 0, hero0.positionY );
		assertEquals( 0, hero1.positionX );
		assertEquals( 1, hero1.positionY );
	}

	@Test
	public void ensureCantMoveUpHero0SameLocationHero1() {
		final Hero hero0 = new Hero( 0, 1, Directions.Up );
		final Hero hero1 = new Hero( 0, 0, Directions.Up );
		lstHeroes.add( hero0 );
		lstHeroes.add( hero1 );
		board.actionHero( HeroActions.Move, 0 );

		assertEquals( 0, hero0.positionX );
		assertEquals( 1, hero0.positionY );
		assertEquals( 0, hero1.positionX );
		assertEquals( 0, hero1.positionY );
	}
}
