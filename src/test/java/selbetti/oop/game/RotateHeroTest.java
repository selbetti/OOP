package selbetti.oop.game;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class RotateHeroTest {

	final List<Hero> lstHeroes = new ArrayList<>();

	final Board board = new Board( lstHeroes, 8, 8, 1 );

	@Before
	public void init() {
		lstHeroes.clear();
	}

	@Test
	public void ensureCanRotateLeftWhenDown() {
		final Hero hero = new Hero( 0, 0, Directions.Down );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.RotateLeft, 0 );

		assertEquals( Directions.Right, hero.headAngle );
	}

	@Test
	public void ensureCanRotateLeftWhenRight() {
		final Hero hero = new Hero( 0, 0, Directions.Right );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.RotateLeft, 0 );

		assertEquals( Directions.Up, hero.headAngle );
	}

	@Test
	public void ensureCanRotateLeftWhenUp() {
		final Hero hero = new Hero( 0, 0, Directions.Up );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.RotateLeft, 0 );

		assertEquals( Directions.Left, hero.headAngle );
	}

	@Test
	public void ensureCanRotateLeftWhenLeft() {
		final Hero hero = new Hero( 0, 0, Directions.Left );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.RotateLeft, 0 );

		assertEquals( Directions.Down, hero.headAngle );
	}

	@Test
	public void ensureCanRotateRightWhenLeft() {
		final Hero hero = new Hero( 0, 0, Directions.Left );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.RotateRight, 0 );

		assertEquals( Directions.Up, hero.headAngle );
	}

	@Test
	public void ensureCanRotateRightWhenUp() {
		final Hero hero = new Hero( 0, 0, Directions.Up );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.RotateRight, 0 );

		assertEquals( Directions.Right, hero.headAngle );
	}

	@Test
	public void ensureCanRotateRightWhenRight() {
		final Hero hero = new Hero( 0, 0, Directions.Right );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.RotateRight, 0 );

		assertEquals( Directions.Down, hero.headAngle );
	}

	@Test
	public void ensureCanRotateRightWhenDown() {
		final Hero hero = new Hero( 0, 0, Directions.Down );
		lstHeroes.add( hero );
		board.actionHero( HeroActions.RotateRight, 0 );

		assertEquals( Directions.Left, hero.headAngle );
	}
}
