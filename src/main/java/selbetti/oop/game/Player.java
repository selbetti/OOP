package selbetti.oop.game;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;

public class Player {

	final ArrayDeque<HeroActions> actions;

	public HeroActions getNextAction() {
		return actions.poll();
	}

	public Player(final Collection<HeroActions> actionSequence){
		actions = new ArrayDeque<>( actionSequence );
	}
}
