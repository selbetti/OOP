package selbetti.oop.game;

import java.util.*;
import java.util.concurrent.*;
import lombok.*;

@RequiredArgsConstructor
public class Game implements Runnable {

	final Board board;
	final List<Player> players;

	final ExecutorService threads = Executors.newSingleThreadExecutor();

	public void start() {
		threads.execute(this);
	}

	@Override
	@SneakyThrows
	public void run() {
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
		return new Game(board, new ArrayList<>());
	}
}
