
package selbetti.oop.game;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import lombok.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith( MockitoJUnitRunner.class )
public class GameTest {

	static final List<HeroActions> playerASequence = Arrays.asList( HeroActions.Move, HeroActions.Move, HeroActions.RotateRight );
	static final List<HeroActions> playerBSequence = Arrays.asList( HeroActions.Move, HeroActions.RotateLeft, HeroActions.Shoot );

	Board board;

	Player playerA;

	Player playerB;

	@Before
	public void init(){
		playerA = spy(new Player(playerASequence));
		playerB = spy(new Player(playerBSequence));
		board = spy(Board.create(4));
	}

	@SneakyThrows
	@Test(timeout = 10000, expected = ExecutionException.class)
	public void ensureStartOnePlayer() {
		val runner = new Game(board);
		runner.addPlayer(playerA);
		runner.start().get();
	}

	@SneakyThrows
	@Test(timeout = 10000)
	public void ensureThatCanStartAGameWithTwoPlayers(){
		val runner = new Game( board );
		runner.addPlayer(playerA);
		runner.addPlayer(playerB);
		runner.start().get();

		verify( playerA, times( 3 ) ).getNextAction();
		verify( playerB, times( 3 ) ).getNextAction();
		verify( board, times( 6 ) ).actionHero( any(), anyInt() );
	}

	@SneakyThrows
	@Test
	public void ensureAddPlayer() {
		val game = Game.create(board);
		game.addPlayer(playerA);

		Assert.assertTrue(game.players.contains(playerA));
		verify( board ).addHero();
	}

	@Test
	public void ensureAddSecondPlayer() {
		val game = Game.create(board);
		game.addPlayer(playerA);
		game.addPlayer(playerB);
		assertEquals(game.players.size(),2);
	}

	@Test(timeout = 10000)
	public void ensureThatCanEndGame(){
		val game = Game.create(board);
		game.addPlayer(playerA);
		game.addPlayer(playerB);
		game.run();

		verify(board, times(5)).hasEnoughPlayersToContinueTheGame();
	}

	@Test(timeout = 10000)
	public void ensureThatPlayerBIsTheWinner(){
		val game = Game.create(board);
		game.addPlayer(playerA);
		game.addPlayer(playerB);
		game.run();

		assertTrue(board.isHeroAlive(game.players.indexOf(playerB)));
		assertFalse(board.isHeroAlive(game.players.indexOf(playerA)));
	}
}
