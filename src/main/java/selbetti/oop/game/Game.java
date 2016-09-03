package selbetti.oop.game;

import java.util.*;
import java.util.concurrent.*;
import lombok.*;

@RequiredArgsConstructor
public class Game implements Runnable {

	final Board board;
	final List<Player> players = new ArrayList<>();

	final ExecutorService threads = Executors.newSingleThreadExecutor();

	public Future<?> start() {
		return threads.submit(this);
	}

	@Override
	@SneakyThrows
	public void run() {
		if (!board.hasEnoughPlayersToContinueTheGame())
			throw new IllegalStateException("Not enough players to start the game, motha focka!");

		while (board.hasEnoughPlayersToContinueTheGame()) {
			play();
			Thread.sleep(100);
		}
	}

	private void play() {
		int i = 0;
		for (final Player player : players) {
			board.actionHero(player.getNextAction(), i);
			i++;
		}
	}

	public void addPlayer(Player player) {
		players.add(player);
		board.addHero();
	}


	public static Game create(Board board) {
		return new Game(board);
	}
}
