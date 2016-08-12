
package selbetti.oop.game;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import lombok.SneakyThrows;
import lombok.val;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith( MockitoJUnitRunner.class )
public class CommandRunnerTest {

	@Mock
	Board board;

	@Mock
	Player playerA;

	@Mock
	Player playerB;

	@SneakyThrows
	@Test
	public void ensureStartOnePlayer()
	{
		val runner = new CommandRunner( board, asList( playerA ) );
		runner.start();
		Thread.sleep( 190 );

		verify( playerA, times( 2 ) ).getNextAction();
		verify( board, times( 2 ) ).actionHero( any(), anyInt() );
	}

	@SneakyThrows
	@Test
	public void ensureThatCanStartTwoPlayers(){
		val runner = new CommandRunner( board, asList( playerA, playerB ) );
		runner.start();
		Thread.sleep( 190 );

		verify( playerA, times( 2 ) ).getNextAction();
		verify( playerB, times( 2 ) ).getNextAction();
		verify( board, times( 4 ) ).actionHero( any(), anyInt() );
	}
}
