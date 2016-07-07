package selbetti.oop.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.val;

import org.junit.Before;
import org.junit.Test;

public class HeroShotRightTest {

	List<Hero> heroes;

	Board board;

	@Before
	public void init() {
		heroes = Arrays.asList(
				new Hero( 0, 0, Directions.Right ),
				new Hero( 4, 0, Directions.Right ),
				new Hero( 7, 0, Directions.Right ) );
		board = new Board( heroes, 8, 8, 1 );
	}

	@Test
	public void ensureHero0CanKillHero1inSameRowShootingRight() {
		board.actionHero( HeroActions.Shoot, 0 );

		assertFalse( "Hero nao morreu", heroes.get( 1 ).isAlive );
	}

	@Test
	public void ensureHero1OnlyKillHero2inSameRowShootingRight() {
		board.actionHero( HeroActions.Shoot, 1 );

		assertFalse( "Hero nao morreu", heroes.get( 2 ).isAlive );
		assertTrue( "Hero morreu", heroes.get( 0 ).isAlive );
	}

	@Test
	public void ensureHero0OnlyKillHero1inSameRowShootingRight() {
		board.actionHero( HeroActions.Shoot, 0 );

		assertFalse( "Hero nao morreu", heroes.get( 1).isAlive );
		assertTrue( "Hero morreu", heroes.get( 2).isAlive );
	}

	@Test
	public void ensureHero0OnlyKillHero2Because1isDeadinSameRowShootingRight() {
		heroes.get( 1).isAlive = false;
		board.actionHero( HeroActions.Shoot, 0 );

		assertFalse( "Hero nao morreu", heroes.get( 1).isAlive );
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
