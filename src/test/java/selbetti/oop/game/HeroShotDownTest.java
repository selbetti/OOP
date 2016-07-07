package selbetti.oop.game;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class HeroShotDownTest {

	List<Hero> heroes;

	Board board;

	@Before
	public void init() {
		heroes = Arrays.asList(
				new Hero( 0, 0, Directions.Down ),
				new Hero( 0, 4, Directions.Down ),
				new Hero( 0, 7, Directions.Down ) );
		board = new Board( heroes, 8, 8, 1 );
	}

	@Test
	public void ensureHero0CanKillHero1inSameColumn() {
		board.actionHero( HeroActions.Shoot, 0 );

		assertFalse( "Hero nao morreu", heroes.get( 1 ).isAlive );
	}

	@Test
	public void ensureHero1OnlyKillHero2inSameColumn() {
		board.actionHero( HeroActions.Shoot, 1 );

		assertFalse( "Hero nao morreu", heroes.get( 2 ).isAlive );
		assertTrue( "Hero morreu", heroes.get( 0 ).isAlive );
	}

	@Test
	public void ensureHero0OnlyKillHero1inSameColumn() {
		board.actionHero( HeroActions.Shoot, 0 );

		assertFalse( "Hero nao morreu", heroes.get( 1 ).isAlive );
		assertTrue( "Hero morreu", heroes.get( 2 ).isAlive );
	}

	@Test
	public void ensureHero0OnlyKillHero2Because1isDeadinSameColumn() {
		heroes.get( 1 ).isAlive = false;
		board.actionHero( HeroActions.Shoot, 0 );

		assertFalse( "Hero nao morreu", heroes.get( 1 ).isAlive );
		assertFalse( "Hero nao morreu", heroes.get( 2 ).isAlive );
	}

	@Test
	public void ensureHero2KillNobody()
	{
		board.actionHero( HeroActions.Shoot, 2 );

		assertTrue( "Hero morreu", heroes.get( 0 ).isAlive );
		assertTrue( "Hero morreu", heroes.get( 1 ).isAlive );
	}
}
