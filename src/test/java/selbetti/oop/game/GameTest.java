
package selbetti.oop.game;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;
import java.util.*;
import lombok.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith( MockitoJUnitRunner.class )
public class GameTest {

	static final List<HeroActions> playerASequence = Arrays.asList( HeroActions.Move, HeroActions.Move, HeroActions.RotateRight );
	static final List<HeroActions> playerBSequence = Arrays.asList( HeroActions.Move, HeroActions.RotateLeft, HeroActions.Shoot );

	@Mock
	Board board;

	Player playerA;

	Player playerB;

	@Before
	public void init(){
		playerA = spy(new Player(playerASequence));
		playerB = spy(new Player(playerBSequence));
		doReturn(new Hero(0, 0, Directions.Down)).when(board).createHero();
	}

	@SneakyThrows
	@Test
	public void ensureStartOnePlayer() {
		val runner = new Game( board, asList( playerA ) );
		runner.start();
		Thread.sleep( 190 );

		verify( playerA, times( 2 ) ).getNextAction();
		verify( board, times( 2 ) ).actionHero( any(), anyInt() );
	}

	@SneakyThrows
	@Test
	public void ensureThatCanStartAGameWithTwoPlayers(){
		val runner = new Game( board, asList( playerA, playerB ) );
		runner.start();
		Thread.sleep( 190 );

		verify( playerA, times( 2 ) ).getNextAction();
		verify( playerB, times( 2 ) ).getNextAction();
		verify( board, times( 4 ) ).actionHero( any(), anyInt() );
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
		Assert.assertEquals(game.players.size(),2);
	}

	@Test(timeout = 10000)
	public void ensureThatCanEndGame(){
		val newGame = Game.create(Board.create(4));
		newGame.addPlayer(playerA);
		newGame.addPlayer(playerB);

		newGame.run();


	}

}
