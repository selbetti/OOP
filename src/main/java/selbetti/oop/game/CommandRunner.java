package selbetti.oop.game;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class CommandRunner implements Runnable {

	final Board board;
	final List<Player> players;

	final ExecutorService threads = Executors.newSingleThreadExecutor();

	public void start() {
		threads.execute( this );
	}

	@Override
	@SneakyThrows
	public void run() {
		// TODO Auto-generated method stub
		while ( true )
		{
			play();
			Thread.sleep( 100 );
		}
	}

	private void play() {
		int i = 0;
		for ( final Player player : players )
		{
			board.actionHero( player.getNextAction(), i );
			i++;
		}
	}


}
